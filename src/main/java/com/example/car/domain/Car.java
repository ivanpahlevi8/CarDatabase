package com.example.car.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Car {
    private String id;
    private String brand;
    private String type;
    private String code;
    private String color;
    private String pass;
    private int year;

    public Car(){
        this.id = UUID.randomUUID().toString();
    }
    public Car(String brand, String type, String code, String pass, String color, int year){
        this.brand = brand;
        this.type = type;
        this.code = code;
        this.pass = pass;
        this.color = color;
        this.year = year;
    }

}
