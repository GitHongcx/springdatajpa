package com.hcx.test;

import com.hcx.dao.ContactsDao;
import com.hcx.dao.CustomerDao;
import com.hcx.domain.Contacts;
import com.hcx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一对多关联查询
 * Created by hongcaixia on 2020/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ContactsDao contactsDao;

    /**
     * 单向
     */
    @Test
    @Transactional
    @Rollback(false) //不自动回滚
    public void testSave1(){
        //创建客户同时创建联系人
        Customer customer = new Customer();
        customer.setName("阿里巴巴");

        Contacts contacts = new Contacts();
        contacts.setName("小红");

        //update contacts set contacts_customer_id=? where id=?
        customer.getContactsSet().add(contacts);

        customerDao.save(customer);
        contactsDao.save(contacts);
    }

    /**
     * 单向
     */
    @Test
    @Transactional
    @Rollback(false) //不自动回滚
    public void testSave2(){
        //创建客户同时创建联系人
        Customer customer = new Customer();
        customer.setName("阿里巴巴");

        Contacts contacts = new Contacts();
        contacts.setName("小红");

        //只有两条insert语句
        contacts.setCustomer(customer);

        customerDao.save(customer);
        contactsDao.save(contacts);
    }

    /**
     * 双向
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testSave3(){
        //创建客户同时创建联系人
        Customer customer = new Customer();
        customer.setName("阿里巴巴");

        Contacts contacts = new Contacts();
        contacts.setName("小红");

        customer.getContactsSet().add(contacts);
        contacts.setCustomer(customer);

        customerDao.save(customer);
        contactsDao.save(contacts);
    }

    /**
     * 级联添加：保存一个客户的同时，保存客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        Customer customer = new Customer();
        customer.setName("阿里巴巴");

        Contacts contacts = new Contacts();
        contacts.setName("小红");

        contacts.setCustomer(customer);
        customer.getContactsSet().add(contacts);

        customerDao.save(customer);
    }

    /**
     * 级联删除：删除一个客户的同时，删除客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove(){
        Customer customer = customerDao.findOne(1l);
        customerDao.delete(customer);
    }

}
