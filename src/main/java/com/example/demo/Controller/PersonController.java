package com.example.demo.Controller;
import com.example.demo.Service.PersonDao;
import com.example.demo.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonDao personDao;

    @PostMapping("/add")
    public Person createPerson(@Valid @RequestBody Person person){
        return personDao.save(person);
    }

    @GetMapping("/allperson")
    public List<Person> getAllPerson(){
        return personDao.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable(value = "id") int id) {
         return personDao.findById(id);
    }

    @PutMapping("/person/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid @RequestBody Person person){
        Person person1 = personDao.findById(id);
        person1.setName(person.getName());
        Person newPerson= personDao.save(person1);
        return "başarıyla değiştirildi";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@RequestBody @Valid @PathVariable(value = "id") int id){
        Person person=personDao.findById(id);
        personDao.delete(person);
        return "başarıyla silindi";
    }
}
