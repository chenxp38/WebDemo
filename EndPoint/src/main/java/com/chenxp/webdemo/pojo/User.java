package com.chenxp.webdemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Description:
 * @author: chenxp
 * @date: 2020/8/17 17:04
 */
public class User {
    private String uid;
    private String openid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    /**
     * @JsonIgnore在post返回user时，忽略password的属性
     */
    @JsonIgnore
    private String password;
    private Integer balance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sex;
    private String phone;
    //@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    //private Date birthday;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getSex() {
        return sex;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

}

