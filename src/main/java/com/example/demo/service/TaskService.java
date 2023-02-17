package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskDto;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    TaskRepository taskRepo;

    public TaskDto getById(Long id) {
        return mapToTaskSaveModel(taskRepo.findById(id).get().getId());
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Long saveTask(Task task) {
        return taskRepo.save(task).getId();
    }

    public void deletePersonById(Long id) {
        taskRepo.deleteById(id);
    }
    public TaskDto mapToTaskSaveModel (Long id) {
        Task task = taskRepo.getReferenceById(id);
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setIssuedDate(task.getIssuedDate());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    public List<TaskDto> getAllByUserId (Long userId) {

        List<Task> tasks = taskRepo.findAllByUserId(userId);
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task: tasks) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTitle(task.getTitle());
            taskDto.setDescription(task.getDescription());
            taskDto.setStatus(task.getStatus());
            taskDto.setIssuedDate(task.getIssuedDate());
            taskDtos.add(taskDto);
        }

    return taskDtos;
    }

    public TaskDto updateTaskById(Task task) {
        return mapToTaskSaveModel(taskRepo.save(task).getId());
    }
}
