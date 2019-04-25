package com.example.demo.module.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *  @JsonIgnore 忽略这个字段不返回
 *   @JsonFormat(pattern = "yy-MM-dd hh:mm:ss a" ,locale = "zh",timezone = "GMT+8")//格式化时间
 *   @JsonInclude(JsonInclude.Include.NON_NULL)//如果该字段为null，就不显示给前台
 */
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}