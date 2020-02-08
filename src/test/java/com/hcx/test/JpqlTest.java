package com.hcx.test;

import com.hcx.dao.CustomerDao;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用jpql查询
 * Created by hongcaixia on 2020/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindByName(){
        Customer customer = customerDao.findJpqlByName("我可能是个假开发");
        System.out.println(customer);
    }

    @Test
    public void testFindCustomerByNameAndId(){
        Customer customer = customerDao.findCustomerByNameAndId(7l,"我可能是个假开发");
        System.out.println(customer);
    }

    /**
     * jpql中执行更新操作
     * 1.需要手动添加事务支持 @Transactional
     * 2.默认执行结束后回滚事务 @Rollback
     */
    @Test
    @Transactional
    @Rollback(value = false) //设置不回滚
    public void testUpdateCustomer(){
        customerDao.updateCustomer(2l,"是我大黑");
    }

}
