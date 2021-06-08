package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.model.ContactModel.ContactRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    List<ContactDto> find(ContactRequest request);

    List<ContactDto> findByName(ContactRequest request);
    List<ContactDto> findByFirstName(ContactRequest request);
    List<ContactDto> findByLastName(ContactRequest request);
    ContactDto findByPhoneNumber(Contact contact);
    ContactDto findById(ContactRequest request);
    List<ContactDto> findByCityNameAndStreetName(ContactRequest request);
    String uploadCSV(MultipartFile file) throws IOException, Exception;

    void createNewContact(ContactRequest request);
    void editContact(ContactRequest request);
    void deleteContactPhoneNumber(ContactRequest request);

}
