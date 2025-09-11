package edu.cit.angus.shayne.campusequipmentloan.service;

import java.time.LocalDate;

public interface PenaltyStrategy {
    double calculatePenalty(LocalDate dueDate, LocalDate returnDate);
}
