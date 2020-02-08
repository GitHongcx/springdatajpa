package com.hcx.test;

import com.hcx.dao.CustomerDao;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用方法命名规则查询
 * Created by hongcaixia on 2020/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MethodNameTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindByName(){
        Customer customer = customerDao.findByName("我一定是个假开发");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameLike(){
        List<Customer> customerList = customerDao.findByNameLike("极多人%");
        customerList.stream().forEach(customer -> System.out.println(customer));
    }

    @Test
    public void testFindByNameLikeAndIndustry(){
        Customer customer = customerDao.findByNameLikeAndIndustry("极多人%", "it");
        System.out.println(customer);
    }


}
