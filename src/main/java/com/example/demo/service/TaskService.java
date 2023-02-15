package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.enums.TaskStatus;
import com.example.demo.exceptions.TaskExceptions;
import com.example.demo.model.TaskUpdateModel;
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

    public TaskUpdateModel updateStatusOfTask(Long taskId, Long userId) throws TaskExceptions {
        Task task = taskRepo.findByIdAndUserId(taskId,userId);
        TaskUpdateModel model = new TaskUpdateModel();

        if(task == null){
            throw new NullPointerException("This user does not have mentioned task");
        } else if(task.getStatus() == TaskStatus.DONE)  throw new TaskExceptions("The status of your task is DONE");

        TaskStatus status = task.getStatus() == TaskStatus.NEW
                ? TaskStatus.IN_WORK
                : TaskStatus.DONE;

        task.setStatus(status);
        taskRepo.save(task);
        model.setTitle(task.getTitle());
        model.setStatus(task.getStatus());
        model.setIssuedDate(task.getIssuedDate());
        model.setDescription(task.getDescription());
        return model;
    }
}
