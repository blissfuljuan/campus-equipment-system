package edu.cit.sagaral.earljericho.campusequipmentloan.dto;

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