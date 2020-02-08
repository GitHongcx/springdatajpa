package com.hcx.test;

import com.hcx.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * 使用sql查询
 * Created by hongcaixia on 2020/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindAllBySql(){
        List<Object[]> customerList = customerDao.findAllBySql();
        customerList.stream().forEach(objects -> System.out.println(Arrays.toString(objects)));
    }

    @Test
    public void testFindAllBySqlCondition(){
        List<Object[]> customerList = customerDao.findAllBySqlCondition("极多人%");
        customerList.stream().forEach(objects -> System.out.println(Arrays.toString(objects)));
    }





}
