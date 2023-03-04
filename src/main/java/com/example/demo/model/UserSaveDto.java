package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDto {
    private String name;
    private String email;
    private String password;
}