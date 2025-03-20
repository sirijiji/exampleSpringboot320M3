package com.example.exampleSpringboot320M3.rest;

import com.example.exampleSpringboot320M3.record.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @CrossOrigin
    @GetMapping(value = "/greeting/test", produces = "application/json")
    public ResponseEntity<Person> greetingTest() {

        Person person = new Person("nom", "prenom");
        return ResponseEntity.ok(person);
    }

}