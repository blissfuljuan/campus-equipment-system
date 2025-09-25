package edu.cit.sagaral.earljericho.campusequipmentloan.service;

import edu.cit.sagaral.earljericho.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.sagaral.earljericho.campusequipmentloan.dto.ReturnLoanRequest;
import edu.cit.sagaral.earljericho.campusequipmentloan.model.*;
import edu.cit.sagaral.earljericho.campusequipmentloan.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final EquipmentRepository equipmentRepository;

    private static final BigDecimal DAILY_PENALTY = BigDecimal.valueOf(50);

    public LoanService(LoanRepository loanRepository,
                       StudentRepository studentRepository,
                       EquipmentRepository equipmentRepository) {
        this.loanRepository = loanRepository;
        this.studentRepository = studentRepository;
        this.equipmentRepository = equipmentRepository;
    }

    // Borrow equipment
    public Loan createLoan(CreateLoanRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Equipment equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        int activeLoans = loanRepository.countByStudentIdAndStatus(student.getId(), LoanStatus.ACTIVE);
        if (activeLoans >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        LocalDate start = request.getStartDate() == null ? LocalDate.now() : request.getStartDate();
        LocalDate due = start.plusDays(7);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(start);
        loan.setDueDate(due);
        loan.setStatus(LoanStatus.ACTIVE);

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    // Return equipment
    public Loan returnLoan(Long loanId, ReturnLoanRequest request) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new RuntimeException("Loan already returned");
        }

        LocalDate returnedDate = request.getReturnDate() == null ? LocalDate.now() : request.getReturnDate();
        loan.setReturnDate(returnedDate);

        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), returnedDate);
        if (daysLate > 0) {
            BigDecimal penalty = DAILY_PENALTY.multiply(BigDecimal.valueOf(daysLate));
            loan.setPenalty(penalty);
            loan.setStatus(LoanStatus.OVERDUE);
        } else {
            loan.setPenalty(BigDecimal.ZERO);
            loan.setStatus(LoanStatus.RETURNED);
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }
}