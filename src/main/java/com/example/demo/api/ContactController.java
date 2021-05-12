package com.example.demo.api;


import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/createNewContact")
    public void createNewContact(@RequestBody ContactRequest request) {
        contactService.createNewContact(request);
    }


    @PostMapping("/findById")
    public Optional<Contact> findById(@RequestBody ContactRequest request) {
        return contactService.findById(request);
    }


    @PostMapping("/findContactByName")
    public List<Contact> findByName(@RequestBody ContactRequest request) {
        return contactService.findByName(request);
    }

    @PostMapping("/findByFirstName")
        public List<Contact> findByFirstName(@RequestBody ContactRequest request) {
        return contactService.findByFirstName(request);
    }

    @PostMapping("/findContactByLastName")
    public List<Contact> findContactByLastName(@RequestBody ContactRequest request) {
        return contactService.findByLastName(request);
    }

    @PostMapping("/findByPhoneNumber")
    public Contact findByPhoneNumber(@RequestBody ContactRequest request) {
        return contactService.findByPhoneNumber(request);
    }

    @PostMapping("/editContact")
    public void editContact(@RequestBody ContactRequest request) {
        contactService.editContact(request);
    }

    @PostMapping("/deleteContactPhoneNumber")
    public void deleteContactPhoneNumber(@RequestBody ContactRequest request) {

        contactService.deleteContactPhoneNumber(request);
    }

}
