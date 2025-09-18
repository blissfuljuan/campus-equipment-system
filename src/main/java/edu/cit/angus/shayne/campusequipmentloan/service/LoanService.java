package edu.cit.angus.shayne.campusequipmentloan.service;

import edu.cit.angus.shayne.campusequipmentloan.model.*;
import edu.cit.angus.shayne.campusequipmentloan.repository.*;
import edu.cit.angus.shayne.campusequipmentloan.dto.LoanRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final EquipmentRepository equipmentRepository;
    private final PenaltyStrategy penaltyStrategy = new LateFeePenalty();

    public LoanService(LoanRepository loanRepository,
                       StudentRepository studentRepository,
                       EquipmentRepository equipmentRepository) {
        this.loanRepository = loanRepository;
        this.studentRepository = studentRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public Loan createLoan(LoanRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Equipment equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // Rule 1: Max 2 ACTIVE loans per student
        long activeLoans = loanRepository.countByStudentAndStatus(student, "ACTIVE");
        if (activeLoans >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        // Rule 2: Equipment cannot be loaned if already ACTIVE
        long activeEquipmentLoans = loanRepository.countByEquipmentIdAndStatus(equipment.getId(), "ACTIVE");
        if (activeEquipmentLoans > 0) {
            throw new RuntimeException("Equipment is already loaned out");
        }

        // Rule 3: Loan length = 7 days
        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = startDate.plusDays(7);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(startDate);
        loan.setDueDate(dueDate);
        loan.setStatus("ACTIVE");

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus("OVERDUE");
            double penalty = penaltyStrategy.calculatePenalty(loan.getDueDate(), loan.getReturnDate());
            System.out.println("Late penalty: ₱" + penalty);
        } else {
            loan.setStatus("RETURNED");
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }
}
