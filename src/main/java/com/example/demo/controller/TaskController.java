package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskDto;
import com.example.demo.model.TaskSaveDto;
import com.example.demo.model.TaskUpdateStatusDto;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

   TaskService service;

    @Deprecated
    @GetMapping("/task/{id}")
    Task getById(@PathVariable Long id) {
       return service.getById(id);
    }

    @Deprecated
    @GetMapping("/task/all")
    List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/task/{taskId}/{userId}")
    TaskDto getTaskByIdAndUserId(@PathVariable Long taskId,
                           @PathVariable Long userId)  {
        return service.getByIdAndUserId(taskId, userId);
    }


    @PostMapping("/task/update/status")
    TaskUpdateStatusDto updateStatusTask(@RequestParam Long taskId, @RequestParam Long userId) throws Exception {
        return service.updateStatusTask(taskId, userId);
    }


    @GetMapping("/taskAll/{userId}")
    List<TaskDto> getAllByUserId(@PathVariable Long userId) {
        return service.getAllByUserId(userId);
    }

    @PostMapping("/task/save")
    Long saveTask(@RequestBody TaskSaveDto dto) {
        return service.saveTask(dto);
    }


    @DeleteMapping("/task/delete/{id}")
    void deletePersonById(@PathVariable Long id) {
        service.deletePersonById(id);
    }

    @PutMapping("/task/update")
    Task updateSurnameById(@RequestBody Task task) {
        return service.updateById(task);
    }

    @PutMapping ("/task/updateTask")
    TaskDto updateTask (@RequestBody Task task) {
        return service.updateTaskById(task);
    }
}