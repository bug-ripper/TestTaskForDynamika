package com.denisbondd111.testtaskfordynamika.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Must contain only letters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Must contain only letters")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Column(nullable = false, unique = true)
    private String isbn;
}