package edu.cit.jumawan.clydenixon.campusequipmentloan.service;
import java.time.LocalDate;
public interface PenaltyCalculator {
    double calculate(LocalDate dueDate, LocalDate returnDate);
}