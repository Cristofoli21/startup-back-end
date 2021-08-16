package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    public Contact(){}  

    public Contact(String phone, String email){
        this.phone = phone;
    	this.email = email;
    }

    public long getId(){
        return id;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public void setNumber(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return "Contact [id ="+ id +", phone="+ phone +", email="+ email + "]";
    }
}