package com.example.demo.mapper;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.model.ContactModel.ContactRequest;
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
    ContactDto contactToContactDto(ContactRequest contact);

    @Mappings({
            @Mapping(source = "address", target = "address", qualifiedByName = "addressDtoToAddress")
    })
    Contact contactDtoToContact(ContactRequest contactDto);

    @IterableMapping(qualifiedByName = "contactToContactDto")
    List<ContactDto> listContactToListContactDTO(List<Contact> list);

//    @IterableMapping(qualifiedByName = "listToFileDTO")
//    List<FileDto> listToFileDTO(List list);
}
