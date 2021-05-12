package com.example.demo.model.ContactModel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactRequest {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
