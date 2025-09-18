package edu.cit.lo.joshuanoel.campusequipmentloan.controller;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.service.loanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/loans")
public class loanController {
    @Autowired
    loanService loanServ;
    @GetMapping("/allLoans")
    public List<loanEntity> getAllLoans() {
        return loanServ.getAllLoans();
    }
    @GetMapping("/getLoanId")
    public ResponseEntity<loanEntity> getLoanById(@PathVariable int id) {
        return loanServ.getLoanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/postNewLoan")
    public loanEntity createLoan(@RequestBody loanEntity loan) {
        return loanServ.saveLoan(loan);
    }
    @PutMapping("/updateLoan")
    public ResponseEntity<loanEntity> updateLoan(@PathVariable int id, @RequestBody loanEntity loanDetails) {
        return loanServ.getLoanById(id)
                .map(loan -> {
                    loan.setEquipment(loanDetails.getEquipment());
                    loan.setStudent(loanDetails.getStudent());
                    loan.setStartDate(loanDetails.getStartDate());
                    loan.setDueDate(loanDetails.getDueDate());
                    loan.setReturnDate(loanDetails.getReturnDate());
                    loan.setStatus(loanDetails.getStatus());
                    loanEntity updatedLoan = loanServ.saveLoan(loan);
                    return ResponseEntity.ok(updatedLoan);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/deleteLoan/{LoanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable int id) {
        if (loanServ.getLoanById(id).isPresent()) {
            loanServ.deleteLoan(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


