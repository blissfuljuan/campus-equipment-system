package edu.cit.miel.kaysean.campusequipmentloan.service;

import edu.cit.miel.kaysean.campusequipmentloan.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final EquipmentRepository equipmentRepository;

    public LoanServiceImpl(LoanRepository loanRepository,
                           StudentRepository studentRepository,
                           EquipmentRepository equipmentRepository) {
        this.loanRepository = loanRepository;
        this.studentRepository = studentRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Loan createLoan(Long studentId, Long equipmentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // Rule: max 2 active loans
        List<Loan> activeLoans = loanRepository.findByStudentIdAndStatus(studentId, LoanStatus.ACTIVE);
        if (activeLoans.size() >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7));
        loan.setStatus(LoanStatus.ACTIVE);

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getStudentLoans(Long studentId) {
        return loanRepository.findByStudentIdAndStatus(studentId, LoanStatus.ACTIVE);
    }
}
