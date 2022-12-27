package br.com.gusta.services;

import br.com.gusta.controller.PersonController;
import br.com.gusta.data.vo.v1.PersonVO;
import br.com.gusta.exceptions.RequiredObjectIsNullException;
import br.com.gusta.exceptions.ResourceNotFoundException;
import br.com.gusta.mapper.DozerMapper;
import br.com.gusta.model.Person;
import br.com.gusta.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Service;

import javax.transaction.*;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;

	public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {
		logger.info("Finding all persons!");

		var personPage = repository.findAll(pageable);

		var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));
		personVosPage.map(p -> p.add(linkTo(methodOn(PersonController.class).findByID(p.getId())).withSelfRel()));

		Link link = linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		return assembler.toModel(personVosPage, link);
	}

	public PagedModel<EntityModel<PersonVO>> findPersonsByName(String firstname, Pageable pageable) {
		logger.info("Finding all persons!");

		var personPage = repository.findPersonsByName(firstname, pageable);

		var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));
		personVosPage.map(p -> p.add(linkTo(methodOn(PersonController.class).findByID(p.getId())).withSelfRel()));

		Link link = linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		return assembler.toModel(personVosPage, link);
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
		vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getId())).withSelfRel());
		return vo;
		
	}

	public PersonVO update(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one person!");
		var entity = repository.findById(person.getId()).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByID(vo.getId())).withSelfRel());
		return vo;
	}

	@Transactional
	public PersonVO disablePerson(Long id) {

		logger.info("Finding one person!");

		repository.disabledPerson(id);

		var entity = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("no records found for this id"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");
		repository.deleteById(id);
	}
	
}
