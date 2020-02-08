package com.hcx.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hongcaixia on 2020/2/8.
 */
@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private Integer age;

    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)//targetEntity：对方实体类字节码
    @JoinTable(name = "user_role",
            //joinColumns：当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "ur_user_id",referencedColumnName = "user_id")},
            //inverseJoinColumns：对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "ur_role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roleSet = new HashSet<Role>();
}
