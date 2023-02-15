package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.model.TaskSaveModel;
import com.example.demo.model.UserSaveModel;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    TaskRepository taskRepo;

    public Task getById(Long id) {
        return taskRepo.findById(id).get();
    }
    
    public List<Task> getAllPersons() {
        return taskRepo.findAll();
    }

    public Long saveTask(Task task) {
        return taskRepo.save(task).getId();
    }

    public void deletePersonById(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateById(Task task) {
        return taskRepo.save(task);
    }

    public Long saveTaskT(String title , String description, Timestamp issuedDate) {
        Task task = new Task(title,description,issuedDate);
        return taskRepo.save(task).getId();
    }
    public TaskSaveModel getByTaskIdAndUserId(Long id, Long userId){
        Task task  = taskRepo.findByIdAndUserId(id, userId);
        TaskSaveModel taskSaveModel = new TaskSaveModel();
        taskSaveModel.setTitle(task.getTitle());
        taskSaveModel.setDescription(task.getDescription());
        taskSaveModel.setIssuedDate(task.getIssuedDate());
        taskSaveModel.setStatus(task.getStatus());
        return taskSaveModel;
    }

}
