package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskDto;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final TaskRepository taskRepository;
    private final DefaultEmailService emailService;

    @Autowired
    public JobService(TaskRepository taskRepository, DefaultEmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 30 9 * * ?") // запуск в 9:30 каждый день
    public void sendReminder() {
        LocalDate today = LocalDate.now();
        List<TaskDto> tasks = convertToTaskDTO(taskRepository.findAll());

        for (TaskDto task : tasks) {
            LocalDate date = task.getIssuedDate().toLocalDateTime().toLocalDate();
            if(date.equals(today)) {
                String email = task.getUserEmail();
                String message = "Reminder: You have a task due today - " + task.getDescription();
                emailService.sendSimpleEmail(email, "Task Reminder", message);
            }
        }
    }

    public List<TaskDto> convertToTaskDTO(List<Task> list) {
        List<TaskDto> taskDto = new ArrayList<>();
        for(Task tasks: list) {
            TaskDto task = new TaskDto();
            task.setTitle(tasks.getTitle());
            task.setDescription(tasks.getDescription());
            task.setIssuedDate(tasks.getIssuedDate());
            task.setStatus(tasks.getStatus());
            task.setUserEmail(tasks.getUser().getEmail());
            taskDto.add(task);
        }

        return taskDto;
    }
}
