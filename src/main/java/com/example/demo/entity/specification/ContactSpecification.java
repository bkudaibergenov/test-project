package com.example.demo.entity.specification;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel.ContactRequest;
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
    /*
    private Specification<Contact> generateSpecification(ContactRequest contactRequest) {
        Specification<Contact> spec = Specification.where(null);

        if (contactRequest.getName() != null) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byName(contactRequest.getName()));
        }

        if (contactRequest.getFirstName() != null) {
            assert spec != null;
            spec = spec.and(ContactSpecification.byFirstName(contactRequest.getFirstName()));
        }

        return spec;
    }*/
}
