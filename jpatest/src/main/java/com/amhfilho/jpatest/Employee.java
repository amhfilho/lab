package com.amhfilho.jpatest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Department department;

    protected Employee(){}

    public Employee(Long id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
