package com.example.demo.api;


import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.model.ContactModel.ContactRequest;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    ContactMapper contactMapper;

    @PostMapping("/find")
    public List<ContactDto> find(@RequestBody ContactRequest request) {
        return contactService.find(request);
    }

    @PostMapping("/createNewContact")
    public void createNewContact(@RequestBody ContactRequest request) {
        System.out.println(request);
        contactService.createNewContact(request);
    }


    @PostMapping("/findById")
    public ContactDto findById(@RequestBody ContactRequest request) {
        return contactService.findById(request);
    }


    @PostMapping("/findContactByName")
    public List<ContactDto> findByName(@RequestBody ContactRequest request) {
        return contactService.findByName(request);
    }

    @PostMapping("/findByFirstName")
    public List<ContactDto> findByFirstName(@RequestBody ContactRequest request) {
        return contactService.findByFirstName(request);
    }

    @PostMapping("/findContactByLastName")
    public List<ContactDto> findContactByLastName(@RequestBody ContactRequest request) {
        return contactService.findByLastName(request);
    }

    @PostMapping("/findByPhoneNumber")
    public ContactDto findByPhoneNumber(@RequestBody ContactRequest request) {
        Contact contactDto = contactMapper.contactDtoToContact(request);
        return contactService.findByPhoneNumber(contactDto);
    }

    @PostMapping("/editContact")
    public void editContact(@RequestBody ContactRequest request) {
        contactService.editContact(request);
    }

    @PostMapping("/deleteContactPhoneNumber")
    public void deleteContactPhoneNumber(@RequestBody ContactRequest request) {
        contactService.deleteContactPhoneNumber(request);
    }

    @PostMapping("/findByCityNameAndStreetName")
    public List<ContactDto> findByCityNameAndStreetName(@RequestBody ContactRequest request) {
        return contactService.findByCityNameAndStreetName(request);
    }

    @PostMapping(value = "/uploadCSV")
    public List<String> uploadCSV(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return contactService.uploadCSV(file);
    }

//    @PostMapping("/uploadCSV2")
//    public String uploadFile(@ModelAttribute MultipartFile file) throws IOException {
//        FileInputStream stream = new FileInputStream(new File(String.valueOf(file)));
//        return stream.toString();
//    }
//
//    @PostMapping("/uploadCSV3")
//    public String uploadCSVFile1(@RequestParam(value = "file") MultipartFile file) {
//        if(file.isEmpty()) {
//            return "not cool";
//        } else {
//            return "Cool";
//        }
//    }
//
//    @PostMapping("/uploadCSV")
//    public String uploadCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
//        if(!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if(!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath() + File.separator);
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//
//                System.out.println("Server File Location=" + serverFile.getAbsolutePath());
//
//                return null;
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        return "OK";
//
//    }
}

