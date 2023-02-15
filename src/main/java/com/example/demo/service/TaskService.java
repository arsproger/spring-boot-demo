package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.enums.TaskStatus;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Task updateStatusOfTask(Long taskId, Long userId){
        Task task = taskRepo.findByIdAndUserId(taskId,userId);
        if(task.getStatus() == TaskStatus.DONE){
            System.out.println("Fail");
        }else if(task.getStatus() == TaskStatus.NEW){
            task.setStatus(TaskStatus.IN_WORK);
        }else if(task.getStatus() == TaskStatus.IN_WORK){
            task.setStatus(TaskStatus.DONE);
        }
        taskRepo.save(task);
        return task;
    }
}
