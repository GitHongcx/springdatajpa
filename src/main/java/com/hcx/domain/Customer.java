package com.hcx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hongcaixia on 2020/2/7.
 */
@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @Column(name = "industry")
    private String industry;

    @Column(name = "grade")
    private String grade;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    //一个客户包含多个联系人
//    @OneToMany(targetEntity = Contacts.class)
    //name:外键字段名称
    //referencedColumnName:当前外键参照的主表的主键字段名称
//    @JoinColumn(name = "contacts_customer_id",referencedColumnName = "id")
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER) //一对多的配置参照：对方配置关系的属性名称
    private Set<Contacts> contactsSet = new HashSet<>();

}
