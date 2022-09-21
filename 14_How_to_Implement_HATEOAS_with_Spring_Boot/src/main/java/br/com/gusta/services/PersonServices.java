package br.com.gusta.services;

import br.com.gusta.controller.PersonController;
import br.com.gusta.data.vo.v1.PersonVO;
import br.com.gusta.exceptions.RequiredObjectIsNullException;
import br.com.gusta.exceptions.ResourceNotFoundException;
import br.com.gusta.mapper.DozerMapper;
import br.com.gusta.model.Person;
import br.com.gusta.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all persons!");
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findByID(p.getKey())).withSelfRel()));
		return persons;
	}
	
	
	public PersonVO findByID(Long key) {
		
		logger.info("Finding one person!");
		var entity = repository.findById(key).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByID(key)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
		return vo;
		
	}
	public PersonVO update(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one person!");
		var entity = repository.findById(person.getKey()).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getKey())).withSelfRel());
		return vo;
	}
	public void delete(Long key) {
		logger.info("Deleting one person!");
		repository.deleteById(key);
	}
	
}
