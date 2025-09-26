package edu.cit.laborada.johnjoseph.campusequipmentloan.penalty;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.LoanModel;

public class FixedPenaltyStrategy implements PenaltyStrategy {

    private static final double DAILY_PENALTY = 50.0;

    @Override
    public double calculatePenalty(LoanModel loan) {
        if (loan.getReturnDate() == null || loan.getDueDate() == null) return 0.0;

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
                    loan.getDueDate(), loan.getReturnDate()
            );
            return daysLate * DAILY_PENALTY;
        }
        return 0.0;
    }
}
