package com.example.demo.entity;

import com.example.demo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    private String description;

    private Timestamp issuedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /*
    Енумка статусов
    */
    @Enumerated(EnumType.STRING)
    TaskStatus status;

    /*
    Время удаления
     */
    private Timestamp rdt;
}