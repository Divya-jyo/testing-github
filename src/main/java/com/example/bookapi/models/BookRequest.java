package com.example.bookapi.models;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Author is required") String author) {
}