package edu.cit.angus.shayne.campusequipmentloan.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LateFeePenalty implements PenaltyStrategy {
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
