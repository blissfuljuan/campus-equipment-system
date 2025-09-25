package edu.cit.lo.joshuanoel.campusequipmentloan.controller;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.service.loanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans/api")
public class loanController {

    @Autowired
    private loanService loanService;

    @PostMapping("/PostNewLoans")
    public loanEntity createLoan(@RequestBody loanEntity loan) {
        // startDate may be manually set or auto-default
        return loanService.createLoan(loan);
    }

    @GetMapping("/getAllLoan")
    public List<loanEntity> getAllLoans() {
        return loanService.getAllLoans();
    }

    // returnDate is always input manually
    @PutMapping("/{loanId}/return")
    public loanEntity returnLoan(@PathVariable int loanId, @RequestParam LocalDate returnDate) {
        return loanService.returnLoan(loanId, returnDate);
    }

    @GetMapping("/{loanId}/penalty")
    public double getPenalty(@PathVariable int loanId) {
        loanEntity loan = loanService.getAllLoans().stream()
                .filter(l -> l.getLoanId() == loanId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return loan.getPenalty();
    }
}