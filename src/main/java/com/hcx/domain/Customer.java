package com.hcx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by hongcaixia on 2020/2/7.
 */
@Entity
@Table(name = "customer")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

}
