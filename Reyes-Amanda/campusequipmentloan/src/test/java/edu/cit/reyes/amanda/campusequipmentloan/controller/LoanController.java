package edu.cit.reyes.amanda.campusequipmentloan.controller;

import edu.cit.reyes.amanda.campusequipmentloan.model.Loan;
import edu.cit.reyes.amanda.campusequipmentloan.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(@RequestParam Long studentId, @RequestParam Long equipmentId) {
        return loanService.createLoan(studentId, equipmentId);
    }

    @PostMapping("/{id}/return")
    public double returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }
}
