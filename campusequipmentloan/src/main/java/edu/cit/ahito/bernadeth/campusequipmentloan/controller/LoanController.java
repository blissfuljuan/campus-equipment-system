package edu.cit.angus.shayne.campusequipmentloan.controller;

import edu.cit.angus.shayne.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.angus.shayne.campusequipmentloan.dto.ReturnLoanRequest;
import edu.cit.angus.shayne.campusequipmentloan.model.Loan;
import edu.cit.angus.shayne.campusequipmentloan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody CreateLoanRequest request) {
        Loan loan = loanService.createLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id,
                                           @RequestBody(required = false) ReturnLoanRequest request) {
        Loan loan = loanService.returnLoan(id, request == null ? new ReturnLoanRequest() : request);
        return ResponseEntity.ok(loan);
    }
}
