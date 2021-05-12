package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByName(String name);
    List<Contact> findByFirstName(String first_name);
    List<Contact> findByLastName(String last_name);
    Optional<Contact> findByPhoneNumber(String phone_number);

}
