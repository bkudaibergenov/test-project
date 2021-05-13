package com.example.demo.mapper;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface ContactMapper {

    @Mappings({
            @Mapping(source = "address", target = "address", qualifiedByName = "addressToAddressDto")
    })
    ContactDto contactToContactDto(Contact contact);

    @IterableMapping(qualifiedByName = "contactToContactDto")
    List<ContactDto> listContactToListContactDTO(List<Contact> list);
}
