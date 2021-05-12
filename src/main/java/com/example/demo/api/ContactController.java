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

    @PostMapping("/findContactByName")
    public List<Contact> findByName(@RequestBody ContactRequest request) {
        return contactService.findContactByName(request);
    }

    @PostMapping("/findContactByFirstName")
        public List<Contact> findContactByFirst_Name(@RequestBody ContactRequest request) {
        return contactService.findContactByFirst_Name(request);
    }

    @PostMapping("/findContactByLastName")
    public List<Contact> findContactByLast_Name(@RequestBody ContactRequest request) {
        return contactService.findContactByLast_Name(request);
    }

    @PostMapping("/findByPhoneNumber")
    public Optional<Contact> findByPhone_Number(@RequestBody ContactRequest request) {
        return contactService.findByPhone_Number(request);
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
