package edu.cit.reyes.amanda.campusequipmentloan.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FixedPenaltyStrategy implements PenaltyStrategy {
    private static final double DAILY_PENALTY = 50.0;

    @Override
    public double calculatePenalty(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * DAILY_PENALTY;
        }
        return 0.0;
    }
}


