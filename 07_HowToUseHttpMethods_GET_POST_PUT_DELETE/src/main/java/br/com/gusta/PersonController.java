package br.com.gusta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gusta.model.Person;
import br.com.gusta.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	/*
	 * para a annotation @autowired ser usada, é necessário 
	 * que a classe selecionada tenha uma annotation @serivce ou outra que
	 * seja um pseudônimo de component.
	 */
	private PersonServices service;
	//private PersonServices personServices = new PersonServices();
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findByID(
		@PathVariable(value = "id") String id){
		
			return service.findByID(id);
			
		}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person){
		
		return service.create(person);
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person){
		
		return service.update(person);
		
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE)
	public void delete(
		@PathVariable(value = "id") String id){
		service.delete(id);
	}
	
}
