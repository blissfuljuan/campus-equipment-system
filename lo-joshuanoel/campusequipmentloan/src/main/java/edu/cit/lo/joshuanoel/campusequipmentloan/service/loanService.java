package edu.cit.lo.joshuanoel.campusequipmentloan.service;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.repository.loanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class loanService {

    @Autowired
    loanRepository loanRepo;

    public List<loanEntity> getAllLoans() {
        return loanRepo.findAll();
    }

    public Optional<loanEntity> getLoanById(int LoanId) {
        return loanRepo.findById(LoanId);
    }

    public loanEntity saveLoan(loanEntity loan) {
        return loanRepo.save(loan);
    }

    public void deleteLoan(int LoanId) {
        loanRepo.deleteById(LoanId);
    }
}