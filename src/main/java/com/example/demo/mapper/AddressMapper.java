package com.example.demo.mapper;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.AddressDto;
import com.example.demo.entity.dto.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public class AddressMapper {

    @Mappings({})
    AddressDto addressToAddressDto(Address address);
}
