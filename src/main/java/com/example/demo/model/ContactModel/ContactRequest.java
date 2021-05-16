package com.example.demo.model.ContactModel;

import com.example.demo.model.AddressModel.AddressModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ContactRequest {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @JsonProperty("address")
    private AddressModel address;

}
