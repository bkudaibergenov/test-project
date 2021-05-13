package com.example.demo.mapper;

import com.example.demo.entity.Address;
import com.example.demo.entity.dto.AddressDto;
import com.example.demo.model.AddressModel.AddressModel;
import com.example.demo.model.AddressModel.AddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({})
    AddressDto addressToAddressDto(Address address);

    @Mappings({})
    Address addressDtoToAddress(AddressRequest addressDto);
}
