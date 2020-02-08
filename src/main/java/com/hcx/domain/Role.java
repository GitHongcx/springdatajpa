package com.hcx.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hongcaixia on 2020/2/8.
 */
@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

//    @ManyToMany(targetEntity = User.class)//targetEntity：对方实体类字节码
//    @JoinTable(name = "user_role",
//            //joinColumns：当前对象在中间表中的外键
//            joinColumns = {@JoinColumn(name = "ur_role_id",referencedColumnName = "role_id")},
//            //inverseJoinColumns：对方对象在中间表的外键
//            inverseJoinColumns = {@JoinColumn(name = "ur_user_id",referencedColumnName = "user_id")}
//    )
    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>();
}
