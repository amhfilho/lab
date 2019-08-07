package com.amhfilho.ow.winemanager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer vintage;
    private String vineyard;

    protected Wine(){}

    public Wine(String name, Integer vintage){
        this.name = name;
        this.vintage = vintage;
    }

}
