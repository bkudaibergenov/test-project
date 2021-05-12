package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
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
    public List<Contact> findContactByName(ContactRequest request) {
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
    public List<Contact> findContactByFirst_Name(ContactRequest request) {
        List<Contact> findByFirstNameList = contactRepository.findByFirstName(request.getFirst_name());
        List<Contact> contactList = new ArrayList<>();
        findByFirstNameList.forEach(a -> {
            if (Objects.equals(a.getFirstName(), request.getFirst_name())) {
                contactList.add(a);
            }
        });
        return contactList;
    }

    @Override
    public List<Contact> findContactByLast_Name(ContactRequest request) {
        List<Contact> findByLastNameList = contactRepository.findByLastName(request.getLast_name());
        List<Contact> contactList = new ArrayList<>();
        findByLastNameList.forEach(a -> {
            if (Objects.equals(a.getLastName(), request.getLast_name())) {
                contactList.add(a);
            }
        });
        return contactList;
    }

    @Override
    public Optional<Contact> findByPhone_Number(ContactRequest request) {
        return contactRepository.findByPhoneNumber(request.getPhone_number());
    }


    @Override
    public void createNewContact(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .phoneNumber(request.getPhone_number())
                .build();
        contactRepository.save(contact);
    }

    @Override
    public void editContact(ContactRequest request) {
        Contact findedById =  contactRepository.findById(request.getId()).orElse(null);

        assert findedById != null;
        findedById.setFirstName(request.getFirst_name());
        findedById.setName(request.getName());
        findedById.setLastName(request.getName());
        findedById.setPhoneNumber(request.getPhone_number());

        contactRepository.save(findedById);
    }

    @Override
    public void deleteContactPhoneNumber(ContactRequest request) {


    }
}
