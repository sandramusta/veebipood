package ee.sandra.veebipood.controller;

import ee.sandra.veebipood.dto.LoginCredentials;
import ee.sandra.veebipood.dto.PersonDTO;
import ee.sandra.veebipood.dto.PersonPublicDTO;
import ee.sandra.veebipood.entity.Person;
import ee.sandra.veebipood.entity.Product;
import ee.sandra.veebipood.repository.PersonRepository;
import ee.sandra.veebipood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController// võimaldab front-endil teha back-endi päringuid
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://veebipood-frontend-a9oo.onrender.com"})
public class PersonController {

    //private String url;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    //localhost:8080/persons
    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @PostMapping("persons")
    public PersonDTO savePerson(@RequestBody Person person) {
        if (person.getId() != null) {
            throw new RuntimeException("Cannot add person with id"); //katkesta kood ja viska välja viga
        }
        Person dbPerson = personRepository.findByEmail(person.getEmail());
        if (dbPerson != null) {
            throw new RuntimeException("Email already in use"); //katkesta kood ja viska välja viga
        }
        Person savedPerson = personRepository.save(person);
//        PersonDTO personDTO = new PersonDTO(
//                savedPerson.getId(),
//                person.getFirstName(),
//                person.getLastName(),
//                person.getEmail()
//        );
//        return personDTO;
        //return personRepository.findAll();
        return modelMapper.map(savedPerson, PersonDTO.class);
    }

    @PostMapping("login")
    public PersonDTO login(@RequestBody LoginCredentials loginCredentials) {
        Person dbPerson = personRepository.findByEmail(loginCredentials.email());
        return modelMapper.map(dbPerson, PersonDTO.class);
    }

    @CacheEvict(value = "persons", key = "#id")
    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @GetMapping("persons/public")
    public List<PersonPublicDTO> getPublicPersons() {
        List<Person> persons = personRepository.findAll();
        return List.of(modelMapper.map(persons, PersonPublicDTO[].class));
    }

    @Cacheable(value = "persons", key = "#id")
    @GetMapping("persons/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findById(id).orElseThrow();
    }
}
