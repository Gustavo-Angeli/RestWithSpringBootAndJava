package br.com.gusta.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gusta.exceptions.ResourceNotFoundException;
import br.com.gusta.model.Person;
import br.com.gusta.repositories.PersonRepositoy;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepositoy repository;
	
	public List<Person> findAll() {
		logger.info("Finding all persons!");
		return repository.findAll();
	}
	
	
	public Person findByID(Long id) {
		
		logger.info("Finding one person!");
		return repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return repository.save(person);
	}
	public Person update(Person person) {
		logger.info("Updating one person!");
		Person entity = repository.findById(person.getId()).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}
	public void delete(Long id) {
		logger.info("Deleting one person!");
		repository.deleteById(id);
	}
	
}
