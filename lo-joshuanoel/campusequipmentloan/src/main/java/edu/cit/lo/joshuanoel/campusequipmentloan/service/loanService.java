package edu.cit.lo.joshuanoel.campusequipmentloan.service;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.repository.loanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class loanService {

    private final loanRepository loanRepository;

    public loanService(loanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    // Rule 1 & 2: Max 2 active loans + Loan length = 7 days
    public loanEntity createLoan(loanEntity loan) {
        List<loanEntity> activeLoans = loanRepository.findActiveLoansByStudent(loan.getStudent());
        if (activeLoans.size() >= 2) {
            throw new RuntimeException("Max 2 active loans per student reached!");
        }

        // Allow manual input of startDate, else default to today
        LocalDate start = (loan.getStartDate() != null) ? loan.getStartDate() : LocalDate.now();
        loan.setStartDate(start);

        // Always system-calculated dueDate = startDate + 7 days
        loan.setDueDate(start.plusDays(7));
        loan.setReturnDate(null);
        loan.setStatus("ACTIVE");
        loan.setPenalty(0.0); // no penalty at creation

        return loanRepository.save(loan);
    }

    public List<loanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    // Rule 3 & 4: Overdue check + Penalty calculation
    public loanEntity returnLoan(int loanId, LocalDate returnDate) {
        loanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // returnDate always comes from input
        loan.setReturnDate(returnDate);

        if (returnDate.isAfter(loan.getDueDate())) {
            loan.setStatus("OVERDUE");
            double penalty = calculateLatePenalty(loan);
            loan.setPenalty(penalty);
        } else {
            loan.setStatus("RETURNED");
            loan.setPenalty(0.0);
        }

        return loanRepository.save(loan);
    }

    // Rule 4: ₱50/day late penalty
    public double calculateLatePenalty(loanEntity loan) {
        if (loan.getReturnDate() != null && loan.getReturnDate().isAfter(loan.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
            return daysLate + 50.0;
        }
        return 0.0;
    }
}