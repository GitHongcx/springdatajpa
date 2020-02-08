package com.hcx.dao;

import com.hcx.domain.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by hongcaixia on 2020/2/8.
 */
public interface ContactsDao extends JpaRepository<Contacts,Long>,JpaSpecificationExecutor<Contacts> {
}
