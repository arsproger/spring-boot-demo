package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskSaveModel;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;



@RestController
@AllArgsConstructor
public class TaskController {

   TaskService service;

    @GetMapping("/task/{id}")
    Task getById(@PathVariable Long id) {
       return service.getById(id);
    }
    
    @GetMapping("/task/all")
    List<Task> getAllPersons() {
        return service.getAllPersons();
    }

    
    @DeleteMapping("/task/delete/{id}")
    void deletePersonById(@PathVariable Long id) {
        service.deletePersonById(id);
    }

    @PutMapping("/task/update")
    Task updateSurnameById(@RequestBody Task task) {
        return service.updateById(task);
    }

    @PostMapping("/task/save")
    Long saveNewTask(@RequestParam String title,
                     @RequestParam String description,
                     @RequestParam Timestamp issuedDate) {
        return service.saveTaskT(title,description,issuedDate);
    }
    @GetMapping("/task/{id}/user/{userId}")
    TaskSaveModel getByTaskIdAndUserId(@PathVariable Long id,
                                       @PathVariable Long userId) {
        return service.getByTaskIdAndUserId(id, userId);

    }


}