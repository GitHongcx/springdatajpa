package com.hcx.test;

import com.hcx.dao.CustomerDao;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Specifications动态查询
 * Created by hongcaixia on 2020/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * findOne(Specification<T> var1)
     * 条件查询
     */
    @Test
    public void testSpecification() {
//        Specification<Customer> specification = new Specification<Customer>() {
//            @Override
//            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                //获取比较的属性
//                Path<Object> customerName = root.get("name");
//                //构造查询条件 equal()精准匹配
//                /**
//                 * equal(Expression<?> var1, Object var2)
//                 * var1:需要比较的属性
//                 * var2:当前需要比较的取值
//                 */
//                Predicate predicate = criteriaBuilder.equal(customerName, "我可能是个假开发");
//                return predicate;
//            }
//        };
//        Customer customer = customerDao.findOne(specification);
//        System.out.println(customer);

        Customer customer = customerDao.findOne((root, criteriaQuery, criteriaBuilder) -> {
            //获取比较的属性
            Path<Object> customerName = root.get("name");
            //构造查询条件 equal()精准匹配
            /**
             * equal(Expression<?> var1, Object var2)
             * var1:需要比较的属性
             * var2:当前需要比较的取值
             */
            Predicate predicate = criteriaBuilder.equal(customerName, "我可能是个假开发");
            return predicate;
        });
        System.out.println(customer);
    }

    /**
     * findOne(Specification<T> var1)
     * 多条件查询
     * 根据客户名和行业查询
     */
    @Test
    public void testSpecificationByNameAndIndustry() {
        //使用findOne还是findAll根据实际的返回结果决定
        Customer customer = customerDao.findOne((Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            //获取属：客户名和所属行业
            Path<Object> customerName = root.get("name");
            Path<Object> industry = root.get("industry");

            //构造查询：构造客户名的精准查询和所属行业的精准查询，将两个查询联合
            Predicate predicate1 = criteriaBuilder.equal(customerName, "极多人小红");
            Predicate predicate2 = criteriaBuilder.equal(industry, "it");
            //组合：与|或
            Predicate predicate = criteriaBuilder.and(predicate1, predicate2);
            return predicate;
        });
        System.out.println(customer);
    }

    /**
     * findAll(Specification<T> var1)
     * 根据姓名模糊查询
     */
    @Test
    public void testSpecificationByNameLike() {
        List<Customer> customerList = customerDao.findAll((Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            //查询属性：name
            Path<Object> name = root.get("name");

            //查询方式：模糊匹配
            Predicate predicate = criteriaBuilder.like(name.as(String.class), "极多人%");
            return predicate;
        });
        customerList.stream().forEach(customer -> System.out.println(customer));
    }

    /**
     * findAll(Specification<T> var1, Sort var2)
     * 模糊查询并排序
     */
    @Test
    public void testSpecificationByNameLikeAndSort() {
        /**
         * Sort(Sort.Direction direction, String... properties)
         * direction：排序的顺序
         * properties：排序的属性名称
         */
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Customer> customerList = customerDao.findAll((Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            //查询属性：name
            Path<Object> name = root.get("name");

            //查询方式：模糊匹配
            Predicate predicate = criteriaBuilder.like(name.as(String.class), "极多人%");
            return predicate;
        }, sort);
        customerList.stream().forEach(customer -> System.out.println(customer));
    }

    /**
     * findAll(Pageable var1)
     * 无条件分页查询
     */
    @Test
    public void testSpecificationByPageWithNoCondition() {
        /**
         * PageRequest(int page, int size)
         * page：当前页（从0开始）
         * size：每页显示多少条
         */
        Page<Customer> customerPage = customerDao.findAll(new PageRequest(0, 2));

        //获取查询的结果列表
        List<Customer> content = customerPage.getContent();
        //获取结果总条数
        long totalElements = customerPage.getTotalElements();
        //获取总页数
        int totalPages = customerPage.getTotalPages();

        System.out.println("结果列表：" + content + " ，总条数：" + totalElements + " ,总页数：" + totalPages);
    }

    /**
     * findAll(Specification<T> var1, Pageable var2)
     * 带条件分页查询
     * Specification:查询条件
     * Pageable：分页参数
     */
    @Test
    public void testSpecificationByPageWithCondition() {
        /**
         * PageRequest(int page, int size)
         * page：当前页（从0开始）
         * size：每页显示多少条
         */
        Page<Customer> customerPage = customerDao.findAll((Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            //查询属性：name
            Path<Object> name = root.get("name");
            //查询方式：模糊匹配
            Predicate predicate = criteriaBuilder.like(name.as(String.class), "极多人%");
            return predicate;
        }, new PageRequest(0, 2));

        //获取查询的结果列表
        List<Customer> content = customerPage.getContent();
        //获取结果总条数
        long totalElements = customerPage.getTotalElements();
        //获取总页数
        int totalPages = customerPage.getTotalPages();

        System.out.println("结果列表：" + content + " ，总条数：" + totalElements + " ,总页数：" + totalPages);
    }

}
