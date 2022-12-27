package br.com.gusta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gusta.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
