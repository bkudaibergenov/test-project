package com.example.demo.model.AddressModel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressModel {

    private Long id;
    private String streetName;
    private String buildingNumber;
    private Integer flatNumber;
    private String stateName;
    private String cityName;
}
