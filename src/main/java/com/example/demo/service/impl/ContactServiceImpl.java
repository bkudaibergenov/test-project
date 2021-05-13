package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findByName(ContactRequest request) {
        List<Contact> contacts = contactRepository.findByName(request.getName());
        List<Contact> contactList = new ArrayList<>();
        contacts.forEach(a -> {
            if (Objects.equals(a.getName(), request.getName())) {
                contactList.add(a);
            }
        });
        return contactList;
    }

    @Override
    public List<Contact> findByFirstName(ContactRequest request) {
        List<Contact> contact = contactRepository.findByFirstName(request.getFirstName());
        return contact;
    }

    @Override
    public List<Contact> findByLastName(ContactRequest request) {
        List<Contact> findByLastNameList = contactRepository.findByLastName(request.getLastName());
        List<Contact> contactList = new ArrayList<>();
        findByLastNameList.forEach(a -> {
            if (Objects.equals(a.getLastName(), request.getLastName())) {
                contactList.add(a);
            }
        });
        return contactList;
    }

    @Override
    public ContactDto findByPhoneNumber(ContactRequest request) {
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            return ContactDto.builder()
                    .firstName(contact.getFirstName())
                    .build();
        }
        return null;
    }

    @Override
    public Optional<Contact> findById(ContactRequest request) {
        return contactRepository.findById(request.getId());
    }


    @Override
    public void createNewContact(ContactRequest request) {
        contactRepository.save(Contact.builder()
                .name(request.getName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build());
    }

    @Override
    public void editContact(ContactRequest request) {
        contactRepository.findById(request.getId()).ifPresent(contact -> {
            contact.setFirstName(request.getFirstName());
            contact.setName(request.getName());
            contact.setLastName(request.getName());
            contact.setPhoneNumber(request.getPhoneNumber());
            contactRepository.save(contact);
        });

        /*
        Optional<Contact> contactOptional = contactRepository.findById(request.getId());

        if (contactOptional.isPresent()) {
            Contact con = contactOptional.get();
            //
        }
        */
    }

    @Override
    public void deleteContactPhoneNumber(ContactRequest request) {
        Optional<Contact> contact = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        contact.ifPresent(contactRepository::delete);
    }
}
