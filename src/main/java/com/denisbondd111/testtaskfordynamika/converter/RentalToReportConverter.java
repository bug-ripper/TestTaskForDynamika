package com.denisbondd111.testtaskfordynamika.converter;

import com.denisbondd111.testtaskfordynamika.dto.RentalReportDTO;
import com.denisbondd111.testtaskfordynamika.entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalToReportConverter {

    public RentalReportDTO convert(Rental rental) {
        RentalReportDTO dto = new RentalReportDTO();
        dto.setClientFullName(rental.getClient().getFullName());
        dto.setClientBirthDate(rental.getClient().getBirthDate());
        dto.setBookTitle(rental.getBook().getTitle());
        dto.setBookAuthor(rental.getBook().getAuthor());
        dto.setBookIsbn(rental.getBook().getIsbn());
        dto.setRentalDate(rental.getRentalDate());
        return dto;
    }
}
