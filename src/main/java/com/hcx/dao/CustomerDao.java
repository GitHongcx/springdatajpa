package com.hcx.dao;

import com.hcx.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by hongcaixia on 2020/2/7.
 */
public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer>{

    /**
     * 根据客户名称查询客户
     * jpql：from customer where name=?
     * @param customerName
     * @return
     */
    @Query(value = "FROM Customer WHERE name=?")
    Customer findJpqlByName(String customerName);

    /**
     * 根据名称和id查询客户
     * @param customerName
     * @param customerId
     * @return
     * 多个参数赋值，默认情况下占位符的位置需要和方法参数的位置保持一致
     * 可以指定占位符参数的位置：?索引方法，指定占位符取值来源
     */
    @Query(value = "FROM Customer WHERE name=?2 AND id=?1")
    Customer findCustomerByNameAndId(Long customerId,String customerName);

    /**
     * 根据id更新客户名称
     * @param customerId
     * @param customerName
     * @Query 代表查询
     * @Modifying 声明此方法用来执行更新操作
     */
    @Query(value = "UPDATE Customer SET name=?2 WHERE id=?1")
    @Modifying
    void updateCustomer(long customerId,String customerName);


    /**
     * 查询全部
     * @return
     */
    @Query(value = "SELECT * FROM customer",nativeQuery = true)
    List<Object[]> findAllBySql();


    /**
     * 条件查询
     * @param name
     * @return
     */
    @Query(value = "SELECT * FROM customer WHERE name LIKE ?1",nativeQuery = true)
    List<Object[]> findAllBySqlCondition(String name);


    /**
     * findBy+属性名：根据属性名进行匹配查询
     * @param customerName
     * @return
     */
    Customer findByName(String customerName);


    /**
     * findBy+属性名+"查询方式(Like|isNull)"
     * @param customerName
     * @return
     */
    List<Customer> findByNameLike(String customerName);

    /**
     * 使用客户名称模糊查询和行业精准查询
     * 多条件查询：findBy+属性名+"查询方式(Like|isNull)"+"多条件的连接符(and|or)"+属性名+"查询方式"
     * 此时参数顺序与方法名称的顺序保持一致，因为没有占位符控制
     * @param customerName
     * @param customerIndustry
     * @return
     */
    Customer findByNameLikeAndIndustry(String customerName,String customerIndustry);


}
