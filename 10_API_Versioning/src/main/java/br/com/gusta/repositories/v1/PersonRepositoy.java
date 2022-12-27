package br.com.gusta.repositories.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gusta.model.v1.Person;

@Repository
public interface PersonRepositoy extends JpaRepository<Person, Long>{

}
