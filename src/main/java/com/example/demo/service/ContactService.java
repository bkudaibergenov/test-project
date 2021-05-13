package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.model.ContactModel.ContactRequest;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<ContactDto> find(ContactRequest request);
    List<ContactDto> findByName(ContactRequest request);
    List<ContactDto> findByFirstName(ContactRequest request);
    List<ContactDto> findByLastName(ContactRequest request);
    ContactDto findByPhoneNumber(ContactRequest request);
    ContactDto findById(ContactRequest request);

    void createNewContact(ContactRequest request);
    void editContact(ContactRequest request);
    void deleteContactPhoneNumber(ContactRequest request);

}
