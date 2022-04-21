package com.saitej.core.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saitej.core.entities.Contact;
import com.saitej.core.services.ContactService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ContactController.class })
public class ContactControllerTest {

	private static final String BASE_URL = "/contact/";

	Contact contact;

	@Before
	public void setUp() throws Exception {
		contact = new Contact();
		contact.setName("John");
		contact.setNumber(123456L);

	}

	@MockBean // provided by Spring Context
	ContactService contactService;

	@Autowired
	MockMvc mockMvc; // provided by Spring Context

	@Test
	public void testGetContact() throws Exception {

		when(contactService.getContact(anyLong())).thenReturn(contact);
		mockMvc.perform(get(BASE_URL + "1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(contact.getName())));

	}

	@Test
	public void testSaveContact() throws Exception {

		Contact returnedContact = new Contact();
		returnedContact.setName(contact.getName());
		returnedContact.setNumber(contact.getNumber());

		when(contactService.saveContact(contact)).thenReturn(returnedContact);

		// when/then
		mockMvc.perform(post(BASE_URL + "save").contentType(MediaType.APPLICATION_JSON).content(asJsonString(contact)))
				.andExpect(status().isCreated());
				//.andExpect(jsonPath("$.name", equalTo(contact.getName())));

	}

	private String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
