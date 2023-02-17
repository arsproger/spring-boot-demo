package com.example.demo.model;

import com.example.demo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String description;
    private TaskStatus taskStatus;
    private Timestamp issued_date;
}
