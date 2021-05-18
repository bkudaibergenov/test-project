package com.example.demo.service.impl;

import com.example.demo.Transactional.AddressTransactional;
import com.example.demo.Transactional.ContactTransactional;
import com.example.demo.entity.Address;
import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.entity.dto.FileDto;
import com.example.demo.entity.specification.ContactSpecification;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactTransactional contactTransactional;

    @Autowired
    private AddressTransactional addressTransactional;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactDto> find(ContactRequest request) {
        Specification<Contact> spec = generateSpecification(request);
        List<Contact> contacts = contactRepository.findAll(spec);
        return contactMapper.listContactToListContactDTO(contacts);
    }

    @Override
    public List<ContactDto> findByCityNameAndStreetName(ContactRequest request) {
        Specification<Contact> spec = byCityNameAndStreetName(request);
        List<Contact> contacts = contactRepository.findAll(spec);
        return contactMapper.listContactToListContactDTO(contacts);
    }


    @Override
    public String uploadCSV(MultipartFile file) throws IOException {
        CsvSchema fileDtoLineSchema = CsvSchema.builder().setColumnSeparator(';').build().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        for (MappingIterator<FileDto> it = csvMapper.readerFor(FileDto.class)
                .with(fileDtoLineSchema)
                .readValues(file.getInputStream()); it.hasNext(); ) {
            FileDto row = it.next();

            Contact newContact = contactTransactional.save(Contact.builder()
                    .name(row.getName())
                    .firstName(row.getFirstName())
                    .lastName(row.getLastName())
                    .phoneNumber(row.getPhoneNumber())
                    .build());

            addressRepository.save(Address.builder()
                    .id(newContact.getId())
                    .contact(newContact)
                    .cityName(row.getCityName())
                    .stateName(row.getStateName())
                    .streetName(row.getStreetName())
                    .buildingNumber(row.getBuildingNumber())
                    .flatNumber(row.getFlatNumber())
                    .build());
        }

        return "Ok";
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

    private static Specification<Contact> byCityNameAndStreetName(ContactRequest request) {
        Specification<Contact> spec = Specification.where(null);

        if(request.getAddress().getCityName() != null && !request.getAddress().getCityName().isEmpty()) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byCityName((request.getAddress().getCityName())));
        }

        if(request.getAddress().getStreetName() != null && !request.getAddress().getStreetName().isEmpty()) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byStreetName((request.getAddress().getStreetName())));
        }
        return spec;
    }


    @Override
    public void createNewContact(ContactRequest request) {

        Contact contact = contactMapper.contactDtoToContact(request);

        Contact newContact = contactRepository.save(Contact.builder()
                .name(contact.getName())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phoneNumber(contact.getPhoneNumber())
                .build());

        addressRepository.save(Address.builder()
                .id(newContact.getId())
                .contact(newContact)
                .cityName(request.getAddress().getCityName())
                .stateName(request.getAddress().getStateName())
                .streetName(request.getAddress().getStreetName())
                .buildingNumber(request.getAddress().getBuildingNumber())
                .flatNumber(request.getAddress().getFlatNumber())
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


    private Specification<Contact> generateSpecification(ContactRequest request) {
        Specification<Contact> spec = Specification.where(null);

        if (request.getName() != null) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byName(request.getName()));
        }

        if (request.getFirstName() != null) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byFirstName(request.getFirstName()));
        }

        return spec;
    }

    private static String csvToJson(List<String> csv){

        //remove empty lines
        //this will affect permanently the list.
        //be careful if you want to use this list after executing this method
        csv.removeIf(e -> e.trim().isEmpty());

        //csv is empty or have declared only columns
        if(csv.size() <= 1){
            return "[]";
        }

        //get first line = columns names
        String[] columns = csv.get(0).split(",");

        //get all rows
        StringBuilder json = new StringBuilder("[\n");
        csv.subList(1, csv.size()) //substring without first row(columns)
                .stream()
                .map(e -> e.split(","))
                .filter(e -> e.length == columns.length) //values size should match with columns size
                .forEach(row -> {

                    json.append("\t{\n");

                    for(int i = 0; i < columns.length; i++){
                        json.append("\t\t\"")
                                .append(columns[i])
                                .append("\" : \"")
                                .append(row[i])
                                .append("\",\n"); //comma-1
                    }

                    //replace comma-1 with \n
                    json.replace(json.lastIndexOf(","), json.length(), "\n");

                    json.append("\t},"); //comma-2

                });

        //remove comma-2
        json.replace(json.lastIndexOf(","), json.length(), "");

        json.append("\n]");
        System.out.println(json.toString());

        return json.toString();

    }
}
