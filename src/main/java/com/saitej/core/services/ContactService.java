package com.saitej.core.services;

import com.saitej.core.entities.Contact;

public interface ContactService {
	
	Contact getContact(Long id);
	Contact saveContact(Contact contact);

}
