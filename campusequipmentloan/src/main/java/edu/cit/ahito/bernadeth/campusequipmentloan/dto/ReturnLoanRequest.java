package edu.cit.angus.shayne.campusequipmentloan.dto;

import java.time.LocalDate;

public class ReturnLoanRequest {
    private LocalDate returnDate;

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
