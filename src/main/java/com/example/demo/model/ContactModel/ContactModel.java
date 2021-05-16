package com.example.demo.model.ContactModel;

import com.example.demo.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactModel {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;

}
