package edu.cit.yu.rainricrandy.campuseequipmentloan.service;

import edu.cit.yu.rainricrandy.campuseequipmentloan.model.Loan;
import java.math.BigDecimal;

public interface PenaltyCalculator {
    BigDecimal calculatePenalty(Loan loan);
}
