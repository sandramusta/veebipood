package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.dto.LoginCredentials;
import ee.sandra.veebipood.entity.Person;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.PersonRepository;
import ee.sandra.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController// võimaldab front-endil teha back-endi päringuid
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    //private String url;
    private final PersonRepository personRepository;

    //localhost:8080/persons
    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @PostMapping("persons")
    public Person savePerson(@RequestBody Person person) {
        if (person.getId() != null) {
            throw new RuntimeException("Cannot add person with id"); //katkesta kood ja viska välja viga
        }
        Person dbPerson = personRepository.findByEmail(person.getEmail());
        if (dbPerson != null) {
            throw new RuntimeException("Email already in use"); //katkesta kood ja viska välja viga
        }
        return personRepository.save(person);
        //return personRepository.findAll();
    }

    @PostMapping("login")
    public Person login(@RequestBody LoginCredentials loginCredentials) {
        return personRepository.findByEmail(loginCredentials.email());
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }
}
