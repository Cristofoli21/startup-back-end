package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="devices")
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private String price;


    public Devices(){}  

    public Devices(String name, String price){
        this.name = name;
    	this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(String price){
        this.price = price;
    }


    @Override
    public String toString(){
        return "Device [id ="+ id +", name="+ name +", price="+ price + "," + "]";
    }
}