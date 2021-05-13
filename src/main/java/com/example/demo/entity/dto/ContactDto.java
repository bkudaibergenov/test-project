package com.example.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
