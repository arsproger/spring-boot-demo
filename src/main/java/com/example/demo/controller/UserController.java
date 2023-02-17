package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.UserSaveModel;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

   UserService service;

    @GetMapping("/user/{id}")
    User getById(@PathVariable Long id) {
       return service.getById(id);
    }

    @GetMapping("/user/all")
    List<User> getAllPersons() {
        return service.getAllPersons();
    }

    @PostMapping("/user/save")
    Long saveNewPerson(@RequestBody UserSaveModel user) {
        return service.saveNewPerson(user);
    }

    @DeleteMapping("/user/delete/{id}")
    void deletePersonById(@PathVariable Long id) {
        service.deletePersonById(id);
    }

    @PutMapping("/user/update")
    User updateSurnameById(@RequestBody User user) {
        return service.updateById(user);
    }
}