package edu.cit.laborada.johnjoseph.campusequipmentloan.penalty;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.LoanModel;

public interface PenaltyStrategy {
    double calculatePenalty(LoanModel loan);
}
