package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    UserService userService;
    DefaultEmailService emailService;

    TaskService taskService;

//    @Scheduled(initialDelay=5000, fixedDelay = 3000)
//    @Scheduled(cron="0 15 10 1 * *")
    public void sendEmail() {
        List<User> users =  userService.getAllPersons();
        for (User user : users) {
            emailService.sendSimpleEmail(user.getEmail(), "Task Manager", "To do");
        }
    }
    @Scheduled(cron =" 0 30 9 * * *")
    public void sendEmailIfCurrentDay(){
        List<Task> tasks = taskService.getAllTasks();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        for(Task task: tasks){
            if(task.getIssuedDate().getDate() == (time.getDate())){
                emailService.sendSimpleEmail(task.getUser().getEmail(),"Issued Date","Today is the day of your task.");
            }
        }
    }
}
