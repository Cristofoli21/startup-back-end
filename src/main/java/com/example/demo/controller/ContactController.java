package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;

// @CrossOrigin(origins = "ec2-18-230-154-138.sa-east-1.compute.amazonaws.com:9090")
@RestController
@RequestMapping("/api")

public class ContactController {
    @Autowired
    ContactRepository ContactRepository;
 
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false)String phone){
        try{
            List<Contact> contacts = new ArrayList<Contact>();
            
            if(phone == null)
            ContactRepository.findAll().forEach(contacts::add);
            else
                ContactRepository.findByPhoneContaining(phone).forEach(contacts::add);
            
            if(contacts.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") long id){
        Optional<Contact> contactData = ContactRepository.findById(id);

        if(contactData.isPresent()){
            return new ResponseEntity<>(contactData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        try{
            Contact _contact = ContactRepository
                    .save(new Contact(contact.getPhone(), contact.getEmail()));
            return new ResponseEntity<>(_contact,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact contact){
        Optional<Contact> contactData = ContactRepository.findById(id);

        if (contactData.isPresent()){
            Contact _contact = contactData.get();
            _contact.setNumber(contact.getPhone());
            _contact.setEmail(contact.getEmail());
            return new ResponseEntity<>(ContactRepository.save(_contact),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") long id){
        try {
            ContactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}