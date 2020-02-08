package com.hcx.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by hongcaixia on 2020/2/8.
 */
@Data
@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "position")
    private String position;

    @Column(name = "remark")
    private String remark;

    //一个联系人从属于一个客户
    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "contacts_customer_id",referencedColumnName = "id") //customer类的id
    private Customer customer;

}
