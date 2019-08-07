package com.amhfilho.ow.winemanager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Wine wine;
    private Integer quantity;

    

    protected Stock() {}

    public Stock(Wine wine, Integer initialQuantity){
        this.wine=wine;
        this.quantity=initialQuantity;
    }




}
