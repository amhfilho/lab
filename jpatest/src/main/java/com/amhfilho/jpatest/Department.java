package com.amhfilho.jpatest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Department {
    @Id
    private Long id;
    private String name;

    protected Department(){}

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
