package com.example.demo.entity.specification;

import com.example.demo.entity.Contact;
import com.example.demo.model.AddressModel.AddressModel;
import com.example.demo.model.AddressModel.AddressRequest;
import com.example.demo.model.ContactModel.ContactModel;
import com.example.demo.model.ContactModel.ContactRequest;
import org.apache.tomcat.jni.Address;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ContactSpecification {

    public static Specification<Contact> byName(final String name) {
        return (Specification<Contact>) (root, query, cb) ->
                cb.equal(root.get("name"), name);
    }

    public static Specification<Contact> byFirstName(final String firstName) {
        return (Specification<Contact>) (root, query, cb) ->
                cb.equal(root.get("firstName"), firstName);
    }

    public static Specification<Contact> byCityName(final String cityName) {
        return (Specification<Contact>) (root, query, cb) ->

            cb.equal(root.get("address").get("cityName"), cityName);
    }


    public static Specification<Contact> byStreetName(final String streetName) {
        return (Specification<Contact>) (root, query, cb) ->
                cb.equal(root.get("address").get("streetName"), streetName);
    }

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

