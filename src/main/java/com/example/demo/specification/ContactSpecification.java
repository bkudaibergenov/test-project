package com.example.demo.specification;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {

    public static Specification<Contact> byName(final String name) {
        return (Specification<Contact>) (root, query, cb) ->
                cb.equal(root.<Contact> get("name"), name);
    }

    public static Specification<Contact> byFirstName(final String firstName) {
        return (Specification<Contact>) (root, query, cb) ->
                cb.equal(root.<Contact> get("firstName"), firstName
                );
    }
}
