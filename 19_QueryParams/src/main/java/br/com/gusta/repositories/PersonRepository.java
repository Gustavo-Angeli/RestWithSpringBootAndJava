package br.com.gusta.repositories;

import br.com.gusta.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    @Modifying
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
    void disabledPerson(@Param("id") Long id);
}
