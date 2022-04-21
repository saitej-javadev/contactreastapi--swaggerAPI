package com.saitej.core.services;

import org.springframework.stereotype.Service;

import com.saitej.core.entities.Contact;
import com.saitej.core.repositories.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService{
	
	private final ContactRepository repository;
	

	public ContactServiceImpl(ContactRepository repository) {
		this.repository = repository;
	}

	@Override
	public Contact getContact(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public Contact saveContact(Contact contact) {
		return repository.save(contact);
	}

}
