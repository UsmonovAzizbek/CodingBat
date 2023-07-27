package com.example.codingbat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"taskName","category_id"})})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String taskName;
    @Column(nullable = false)
    private String condition;
    @Column(nullable = false)
    private String example;
    @Column(nullable = false)
    private boolean star;
    @ManyToOne
    private Category category;
}
