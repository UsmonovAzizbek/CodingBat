package com.example.codingbat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LanguageDTO {
    @NotNull(message = "name ustuni bo'sh bo'lishi mumkin emas")
    private String name;
}
