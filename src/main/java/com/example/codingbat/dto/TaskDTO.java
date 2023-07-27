package com.example.codingbat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {
    @NotNull(message = "taskName ustuni bo'sh bo'lishi mumkin emas")
    private String taskName;
    @NotNull(message = "condition ustuni bo'sh bo'lishi mumkin emas")
    private String condition;
    @NotNull(message = "example ustuni bo'sh bo'lishi mumkin emas")
    private String example;
    @NotNull(message = "star ustuni bo'sh bo'lishi mumkin emas")
    private boolean star;
    @NotNull(message = "category_id ustuni bo'sh bo'lishi mumkin emas")
    private Integer category_id;

    public boolean getStar() {
        return false;
    }
}
