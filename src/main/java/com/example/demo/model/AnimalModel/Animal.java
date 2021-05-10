package com.example.demo.model.AnimalModel;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Animal {

    private String name;
    private Double weight;

}
