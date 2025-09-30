package com.drathveloper.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
    private LocalDate birthDate;
}
