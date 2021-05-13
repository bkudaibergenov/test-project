package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.model.ContactModel.ContactRequest;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findByName(ContactRequest request);
    List<Contact> findByFirstName(ContactRequest request);
    List<Contact> findByLastName(ContactRequest request);
    ContactDto findByPhoneNumber(ContactRequest request);
    Optional<Contact> findById(ContactRequest request);

    void createNewContact(ContactRequest request);
    void editContact(ContactRequest request);
    void deleteContactPhoneNumber(ContactRequest request);

}
