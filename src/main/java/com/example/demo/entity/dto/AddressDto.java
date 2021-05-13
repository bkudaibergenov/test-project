package com.example.demo.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("street_name")
    private String streetName;

    @JsonProperty("building_number")
    private String buildingNumber;

    @JsonProperty("flat_number")
    private Integer flatNumber;

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("city_name")
    private String cityName;

}
