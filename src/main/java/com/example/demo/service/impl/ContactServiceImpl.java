package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
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
        List<Contact> findByNameList = contactRepository.findByName(request.getName());
        List<Contact> contactList = new ArrayList<>();
        findByNameList.forEach(a -> {
            if (Objects.equals(a.getName(), request.getName())) {
                contactList.add(a);
            }
        });
        return contactList;
    }

    @Override
    public List<Contact> findByFirstName(ContactRequest request) {
        List<Contact> findByFirstNameList = contactRepository.findByFirstName(request.getFirstName());
        System.out.println("We are in byFirstName");
//        List<Contact> contactList = new ArrayList<>();
//        System.out.println(findByFirstNameList);
//        findByFirstNameList.forEach(a -> {
//            if (Objects.equals(a.getFirstName(), request.getFirstName())) {
//                contactList.add(a);
//            }
//        });
        return findByFirstNameList;
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
    public Contact findByPhoneNumber(ContactRequest request) {
        return contactRepository.findByPhoneNumber(request.getPhoneNumber()).orElse(null);
    }

    @Override
    public Optional<Contact> findById(ContactRequest request) {
        return contactRepository.findById(request.getId());
    }


    @Override
    public void createNewContact(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        contactRepository.save(contact);
    }

    @Override
    public void editContact(ContactRequest request) {
        Contact findById =  contactRepository.findById(request.getId()).orElse(null);

        assert findById != null;
        findById.setFirstName(request.getFirstName());
        findById.setName(request.getName());
        findById.setLastName(request.getName());
        findById.setPhoneNumber(request.getPhoneNumber());

        contactRepository.save(findById);
    }

    @Override
    public void deleteContactPhoneNumber(ContactRequest request) {

        Optional<Contact> contact = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        contact.ifPresent(contactRepository::delete);

    }
}
