package com.denisbondd111.testtaskfordynamika.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RentalReportDTO {
    private String clientFullName;
    private LocalDate clientBirthDate;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private LocalDateTime rentalDate;
}
