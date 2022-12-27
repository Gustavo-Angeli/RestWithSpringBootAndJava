package br.com.gusta.integrationtests.repositories;

import br.com.gusta.integrationtests.testcontainers.*;
import br.com.gusta.model.*;
import br.com.gusta.repositories.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    public PersonRepository personRepository;

    private static Person person;

    @BeforeAll
    public static void setup() {
        person = new Person();
    }

    @Test
    @Order(0)
    public void testFindByName() {

        Pageable pageable = PageRequest.of(0, 6, Sort.by(Sort.Direction.ASC, "firstName"));
        person = personRepository.findPersonsByName("bi", pageable).getContent().get(0);

        assertNotNull(person.getId());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());

        assertTrue(person.getEnabled());

        assertEquals(28, person.getId());

        assertEquals("Binni", person.getFirstName());
        assertEquals("Treend", person.getLastName());
        assertEquals("1 Shelley Alley", person.getAddress());
        assertEquals("Female", person.getGender());
    }

    @Test
    @Order(1)
    public void testDisablePerson() {

        personRepository.disabledPerson(person.getId());
        Pageable pageable = PageRequest.of(0, 6, Sort.by(Sort.Direction.ASC, "firstName"));
        person = personRepository.findPersonsByName("bi", pageable).getContent().get(0);

        assertNotNull(person.getId());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());

        assertFalse(person.getEnabled());

        assertEquals(28, person.getId());

        assertEquals("Binni", person.getFirstName());
        assertEquals("Treend", person.getLastName());
        assertEquals("1 Shelley Alley", person.getAddress());
        assertEquals("Female", person.getGender());
    }

}
