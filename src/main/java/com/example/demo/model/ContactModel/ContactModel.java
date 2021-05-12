package com.example.demo.model.ContactModel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactModel {

    private String name;
    private String first_name;
    private String last_name;
    private String phone_number;

}
