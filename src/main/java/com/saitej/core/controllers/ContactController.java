package com.saitej.core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.saitej.core.entities.Contact;
import com.saitej.core.services.ContactService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
	
	static final String BASE_URL = "/contact";
	private final ContactService service;
	

	public ContactController(ContactService service) {
		this.service = service;
	}

    @ApiOperation(value = "Retreive the single contact",notes = "A single contact",
    		response = Contact.class,responseContainer = "List",produces = "application/json")
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
	public Contact getContact(@PathVariable Long id) {
		return service.getContact(id);
	}
    
    @ApiOperation(value = "save the contact")
    @PostMapping("/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Contact saveContact(@RequestBody Contact contact) {
    	return service.saveContact(contact);
    	
    }

}
