package com.denisbondd111.testtaskfordynamika.controller;

import com.denisbondd111.testtaskfordynamika.dto.RentalReportDTO;
import com.denisbondd111.testtaskfordynamika.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalReportController {

    private RentalService rentalService;

    public RentalReportController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalReportDTO>> getRentalReports() {
        return ResponseEntity.ok(rentalService.getRentalReports());
    }
}
