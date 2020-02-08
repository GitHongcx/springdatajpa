package com.hcx.test;

import com.hcx.dao.CustomerDao;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 使用接口定义好的方法进行查询
 * Created by hongcaixia on 2020/2/7.
 */
//声明spring提供的单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring容器的配置信息
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    @Test
    public void testFindAll(){
        List<Customer> customerList = customerDao.findAll();
        customerList.stream().forEach(customer -> System.out.println(customer));
    }

    /**
     * save:保存或更新
     * 保存：传递的对象没有主键id
     * 更新：传递的对象存在主键属性，根据id查询数据并更新
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setName("我可能是个假开发");
        customer.setIndustry("it");
        customer.setSource("fa");
        customer.setGrade("4");
        customer.setPhone("1324657839");
        customer.setAddress("广东深圳");
        customerDao.save(customer);
    }

    /**
     * 更新：如果没设置的属性，将被设置为null，所以还是先查询再更新
     */
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setName("我一定是个假开发");
        customer.setIndustry("it行业");
        customer.setGrade("4");
        customer.setPhone("1324657839");
        customer.setAddress("广东深圳");
        customer.setId(3L);
        customerDao.save(customer);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete(){
        customerDao.delete(4L);
    }


    /**
     * 根据id查询
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(2l);
        System.out.println(customer);
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 判断id为4的客户是否存在
     * select count(*) as col_0_0_ from customer customer0_ where customer0_.id=? and 1=1
     */
    @Test
    public void testExist(){
        boolean exists = customerDao.exists(4l);
        System.out.println(exists);
    }


    
}
