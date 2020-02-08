package com.hcx.test;

import com.hcx.dao.ContactsDao;
import com.hcx.dao.CustomerDao;
import com.hcx.domain.Contacts;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 使用对象导航
 * Created by hongcaixia on 2020/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectNavigation {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ContactsDao contactsDao;

    /**
     * 从客户导航查询所属联系人
     * 查询使用的是延迟加载，调用get方法不会立即发送查询，而是使用关联对象时才会查询
     */
    @Test
    @Transactional
    public void testQuery1(){
        Customer customer = customerDao.getOne(1l);
        //使用对象导航查询该客户下的所有联系人
        Set<Contacts> contactsSet = customer.getContactsSet();
        contactsSet.stream().forEach(contact -> System.out.println(contact));
    }

    @Test
    @Transactional
    public void testQuery2(){
        Customer customer = customerDao.findOne(1l);
        //使用对象导航查询该客户下的所有联系人
        Set<Contacts> contactsSet = customer.getContactsSet();
        contactsSet.stream().forEach(contact -> System.out.println(contact));
    }

    /**
     * 从联系人导航查询所属的客户
     * 默认是立即加载
     * 使用延迟加载：fetch = FetchType.LAZY
     */
    @Test
    @Transactional
    public void testQuery3(){
        Contacts contact = contactsDao.findOne(2l);
        Customer customer = contact.getCustomer();
        System.out.println(contact+" "+customer);
    }

}
