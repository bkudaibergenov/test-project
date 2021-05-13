package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.entity.specification.ContactSpecification;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public List<ContactDto> find(ContactRequest request) {
        Specification<Contact> spec = generateSpecification(request);
        List<Contact> contacts = contactRepository.findAll(spec);
        return contactMapper.listContactToListContactDTO(contacts);
    }

    @Override
    public List<ContactDto> findByName(ContactRequest request) {
        List<Contact> contacts = contactRepository.findByName(request.getName());
        List<ContactDto> contactList = new ArrayList<>();
        contacts.forEach(contact -> {
            contactList.add(contactMapper.contactToContactDto(contact));
        });
        return contactList;
    }

    @Override
    public List<ContactDto> findByFirstName(ContactRequest request) {
        List<Contact> contacts = contactRepository.findByFirstName(request.getFirstName());
        List<ContactDto> contactList = new ArrayList<>();
        contacts.forEach(contact -> {
            contactList.add(contactMapper.contactToContactDto(contact));
        });
        return contactList;
    }

    @Override
    public List<ContactDto> findByLastName(ContactRequest request) {
        List<Contact> contacts = contactRepository.findByLastName(request.getLastName());
        List<ContactDto> contactList = new ArrayList<>();
        contacts.forEach(contact -> {
            contactList.add(ContactDto.builder()
                    .name(contact.getName())
                    .firstName(contact.getFirstName())
                    .lastName(contact.getLastName())
                    .phoneNumber(contact.getPhoneNumber())
                    .build());
        });
        return contactList;
    }

    @Override
    public ContactDto findByPhoneNumber(ContactRequest request) {
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            return ContactDto.builder()
                    .name(contact.getName())
                    .firstName(contact.getFirstName())
                    .lastName(contact.getLastName())
                    .phoneNumber(contact.getPhoneNumber())
                    .build();
        }
        return null;
    }

    @Override
    public ContactDto findById(ContactRequest request) {
        Optional<Contact> contactOptional = contactRepository.findById(request.getId());
        if(contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            return ContactDto.builder()
                    .name(contact.getName())
                    .firstName(contact.getFirstName())
                    .lastName(contact.getLastName())
                    .phoneNumber(contact.getPhoneNumber())
                    .build();
        }
        return null;
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

    private Specification<Contact> generateSpecification(ContactRequest contactRequest) {
        Specification<Contact> spec = Specification.where(null);

        if (contactRequest.getName() != null && !contactRequest.getName().isEmpty()) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byName(contactRequest.getName()));
        }

        if (contactRequest.getFirstName() != null) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byFirstName(contactRequest.getFirstName()));
        }

        return spec;
    }
}
