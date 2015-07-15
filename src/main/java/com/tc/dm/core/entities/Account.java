package com.tc.dm.core.entities;

import javax.persistence.*;

/**
 * Created by sg40304 on 7/14/15.
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
