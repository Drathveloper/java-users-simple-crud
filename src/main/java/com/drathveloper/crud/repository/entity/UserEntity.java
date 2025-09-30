package com.drathveloper.crud.repository.entity;

import java.time.LocalDate;

public record UserEntity(long id, String name, String email, LocalDate birthDate) {
}
