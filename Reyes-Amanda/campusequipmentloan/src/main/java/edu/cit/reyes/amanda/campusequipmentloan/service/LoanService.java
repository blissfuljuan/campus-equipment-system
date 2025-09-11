package edu.cit.reyes.amanda.campusequipmentloan.service;

import edu.cit.reyes.amanda.campusequipmentloan.model.*;
import edu.cit.reyes.amanda.campusequipmentloan.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class LoanService {
    private final LoanRepository loanRepo;
    private final EquipmentRepository equipmentRepo;
    private final StudentRepository studentRepo;
    private final PenaltyStrategy penaltyStrategy;

    public LoanService(LoanRepository loanRepo, EquipmentRepository equipmentRepo,
                       StudentRepository studentRepo, PenaltyStrategy penaltyStrategy) {
        this.loanRepo = loanRepo;
        this.equipmentRepo = equipmentRepo;
        this.studentRepo = studentRepo;
        this.penaltyStrategy = penaltyStrategy;
    }

    @Transactional
    public Loan createLoan(Long studentId, Long equipmentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        long activeLoans = loanRepo.countByStudentAndStatus(student, Loan.Status.ACTIVE);
        if (activeLoans >= 2) {
            throw new IllegalStateException("Max 2 active loans allowed per student");
        }
        if (!equipment.isAvailable()) {
            throw new IllegalStateException("Equipment not available");
        }

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7));
        loan.setStatus(Loan.Status.ACTIVE);

        equipment.setAvailable(false);
        equipmentRepo.save(equipment);

        return loanRepo.save(loan);
    }

    @Transactional
    public double returnLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        loan.setReturnDate(LocalDate.now());

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus(Loan.Status.OVERDUE);
        } else {
            loan.setStatus(Loan.Status.RETURNED);
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepo.save(equipment);

        loanRepo.save(loan);

        return penaltyStrategy.calculatePenalty(loan.getDueDate(), loan.getReturnDate());
    }
}


