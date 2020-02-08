package com.hcx.test;

import com.hcx.dao.RoleDao;
import com.hcx.dao.UserDao;
import com.hcx.domain.Role;
import com.hcx.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 多对多关联查询
 * Created by hongcaixia on 2020/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存用户和角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testSave1(){
        User user = new User();
        user.setUsername("小红");

        Role role = new Role();
        role.setRoleName("管理员");

        user.getRoleSet().add(role);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testSave2(){
        User user = new User();
        user.setUsername("小红");

        Role role = new Role();
        role.setRoleName("管理员");

        user.getRoleSet().add(role);
        role.getUserSet().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 级联添加（保存一个用户的同时保存用户关联的角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeSave(){
        User user = new User();
        user.setUsername("小红");

        Role role = new Role();
        role.setRoleName("管理员");

        user.getRoleSet().add(role);
        role.getUserSet().add(user);

        userDao.save(user);
    }

    /**
     * 级联删除：删除用户同时删除用户关联的角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCasCadeDelete(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }

}
