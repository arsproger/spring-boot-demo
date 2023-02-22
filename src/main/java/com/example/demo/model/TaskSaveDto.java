package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskSaveDto {
    private Long userId;
    private String title;
    private String description;
}
