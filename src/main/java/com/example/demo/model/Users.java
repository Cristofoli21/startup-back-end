package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private String age;

    @Column(name="verified")
    private boolean verified;

    public Users(){}  

    public Users(String name, String age, boolean verified){
        this.name = name;
    	this.age = age;
        this.verified = verified;
    }

    public long getVerified(){
        return verified;
    }

    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(String age){
        this.age = age;
    }

    public boolean isVerified(){
        return verified;
    }

    public void setVerified(boolean isVerified){
        this.verified = isVerified;
    }

    @Override
    public String toString(){
        return "User [id ="+ id +", name="+ name +", age="+ age + ", verified=" + verified + "]";
    }
}