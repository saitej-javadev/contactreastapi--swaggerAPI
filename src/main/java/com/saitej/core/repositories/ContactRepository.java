package com.saitej.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saitej.core.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
