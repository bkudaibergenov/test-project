package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "demo_data")
public class Address {

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name="street_name")
    private String streetName;

    @Column(name="building_number")
    private String buildingNumber;

    @Column(name="flat_number")
    private Integer flatNumber;

    @Column(name="state_name")
    private String stateName;

    @Column(name="city_name")
    private String cityName;

    @OneToOne(mappedBy = "address")
    private Contact contact;
}