package com.example.demoh2;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class DemoProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Key is mandatory")
    private String key;
    @NotBlank(message = "Value is mandatory")
    private String value;

    public DemoProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    protected DemoProperty(){}
}
