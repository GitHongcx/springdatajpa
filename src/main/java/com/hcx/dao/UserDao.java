package com.hcx.dao;

import com.hcx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by hongcaixia on 2020/2/8.
 */
public interface UserDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>{
}
