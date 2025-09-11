package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(Long studentId, Long equipmentId);
    Loan returnLoan(Long loanId);
    List<Loan> getStudentLoans(Long studentId);
}
