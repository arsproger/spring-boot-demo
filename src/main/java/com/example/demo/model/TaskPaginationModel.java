package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter

public class TaskPaginationModel {
    private String title;

    private String description;

    private Timestamp issuedDate;
    private String status;

}
