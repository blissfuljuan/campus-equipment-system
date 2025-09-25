package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.service;

import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.EquipmentEntity;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.LoanEntity;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.StudentEntity;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository.EquipmentRepository;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository.LoanRepository;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class LoanService {

    private static final int MAX_DAYS = 7;
    private static final int MAX_ACTIVE_LOANS_PER_STUDENT = 2;
    private static final int FINE_PER_DAY_PHP = 50;

    private final EquipmentRepository equipmentRepository;
    private final StudentRepository studentRepository;
    private final LoanRepository loanRepository;

    public LoanService(EquipmentRepository equipmentRepository,
                       StudentRepository studentRepository,
                       LoanRepository loanRepository) {
        this.equipmentRepository = equipmentRepository;
        this.studentRepository = studentRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public LoanEntity createLoan(Long productId, Long studentId) {
        EquipmentEntity equipment = equipmentRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + productId));
        if (equipment.getAvailablity() == null || !equipment.getAvailablity()) {
            throw new IllegalStateException("Equipment is not available for loan");
        }

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));

        long activeLoans = loanRepository.countActiveLoansByStudent(student);
        if (activeLoans >= MAX_ACTIVE_LOANS_PER_STUDENT) {
            throw new IllegalStateException("Student already has maximum number of active loans");
        }

        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = startDate.plusDays(MAX_DAYS);

        LoanEntity loan = new LoanEntity();
        // loan_id provided by DB; if not auto-increment, expect caller to set; otherwise JPA should manage
        loan.setEquipment(equipment);
        loan.setStudent(student);
        loan.setStartDate(startDate);
        loan.setDueDate(dueDate);
        loan.setStatus("ACTIVE");

        equipment.setAvailablity(false);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    @Transactional
    public ReturnResult returnLoan(Long loanId) {
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found: " + loanId));

        if (!"ACTIVE".equalsIgnoreCase(loan.getStatus())) {
            throw new IllegalStateException("Loan is not active");
        }

        LocalDate today = LocalDate.now();
        loan.setReturnDate(today);
        loan.setStatus("RETURNED");

        EquipmentEntity equipment = loan.getEquipment();
        equipment.setAvailablity(true);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);

        long overdueDays = Math.max(0, ChronoUnit.DAYS.between(loan.getDueDate(), today));
        long fine = overdueDays * FINE_PER_DAY_PHP;

        return new ReturnResult(overdueDays, fine);
    }

    public record ReturnResult(long overdueDays, long finePhp) {}
}



