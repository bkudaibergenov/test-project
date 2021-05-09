package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonRequest {

    private Integer plus;
    private String name;
    private String cityName;
}
