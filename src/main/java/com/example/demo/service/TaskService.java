package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.enums.TaskStatus;
import com.example.demo.model.TaskDto;
import com.example.demo.model.TaskSaveDto;
import com.example.demo.model.TaskUpdateStatusDto;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    TaskRepository taskRepo;
    UserService userService;

    public Task getById(Long id) {
        return taskRepo.findById(id).get();
    }

    public TaskDto getByIdAndUserId(Long taskId, Long userId) {
        User user = userService.getById(userId);
        Task task = taskRepo.findByIdAndUserId(taskId, userId);
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setIssuedDate(task.getIssuedDate());
        taskDto.setUserName(user.getName());
        return taskDto;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Long saveTask(TaskSaveDto dto) {

        User user = userService.getById(dto.getUserId());
        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setIssuedDate(new Timestamp(System.currentTimeMillis()));
        task.setStatus(TaskStatus.NEW);
        task.setUser(user);

        task = taskRepo.save(task);
        return task.getId();
    }

    public TaskUpdateStatusDto updateStatusTask(Long taskId, Long userId) throws Exception {
        Task task = taskRepo.findByIdAndUserId(taskId, userId);

        if (task == null || task.getStatus() == TaskStatus.DONE)
        throw new Exception("The status of your task is DONE");

        TaskStatus status = task.getStatus() == TaskStatus.NEW
                ? TaskStatus.IN_WORK
                : TaskStatus.DONE;

        task.setStatus(status);
        taskRepo.save(task);

        TaskUpdateStatusDto model = new TaskUpdateStatusDto();
        model.setTitle(task.getTitle());
        model.setStatus(task.getStatus());
        model.setIssuedDate(task.getIssuedDate());
        model.setDescription(task.getDescription());

        return model;
    }

    public void deletePersonById(Long id) {
        taskRepo.deleteById(id);
    }

    public TaskDto mapToTaskSaveModel(Long id) {
        Task task = taskRepo.getReferenceById(id);
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setIssuedDate(task.getIssuedDate());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }


    public Task updateById(Task task) {
        return taskRepo.save(task);
    }

    public List<TaskDto> getAllByUserId(Long userId) {

        List<Task> tasks = taskRepo.findAllByUserId(userId);
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : tasks) {
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
