package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel.ContactRequest;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findContactByName(ContactRequest request);
    List<Contact> findContactByFirst_Name(ContactRequest request);
    List<Contact> findContactByLast_Name(ContactRequest request);
    Optional<Contact> findByPhone_Number(ContactRequest request);

    void createNewContact(ContactRequest request);
    void editContact(ContactRequest request);
    void deleteContactPhoneNumber(ContactRequest request);

}
