package com.example.codingbat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    @NotNull(message = "nameCate ustuni bo'sh bo'lishi mumkin emas")
    private String nameCate;
    @NotNull(message = "starCount ustuni bo'sh bo'lishi mumkin emas")
    private Integer starCount;
    @NotNull(message = "description ustuni bo'sh bo'lishi mumkin emas")
    private String description;
    @NotNull(message = "language_id ustuni bo'sh bo'lishi mumkin emas")
    private Integer language_id;
}
