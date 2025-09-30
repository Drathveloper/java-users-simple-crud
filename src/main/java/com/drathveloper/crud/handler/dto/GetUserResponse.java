package com.drathveloper.crud.handler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record GetUserResponse(
        long id,
        String name,
        String email,
        @JsonProperty("birth_date") LocalDate birthDate) {
}
