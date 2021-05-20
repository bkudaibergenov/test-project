package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

    List<Contact> findByName(String name);
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByLastName(String lastName);
    Optional<Contact> findByPhoneNumber(String phoneNumber);
    Optional<Contact> findById(Long id);
}
