package com.example.demo.model;

import com.example.demo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskDto {
    private String title;
    private String description;
    private Timestamp issuedDate;
    private TaskStatus status;
    private String userName;
}
