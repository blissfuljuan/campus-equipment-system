package edu.cit.laborada.johnjoseph.campusequipmentloan.service;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.EquipmentModel;
import edu.cit.laborada.johnjoseph.campusequipmentloan.model.LoanModel;
import edu.cit.laborada.johnjoseph.campusequipmentloan.model.StudentModel;
import edu.cit.laborada.johnjoseph.campusequipmentloan.penalty.FixedPenaltyStrategy;
import edu.cit.laborada.johnjoseph.campusequipmentloan.penalty.PenaltyStrategy;
import edu.cit.laborada.johnjoseph.campusequipmentloan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final EquipmentService equipmentService;
    private final StudentService studentService;
    private final PenaltyStrategy penaltyStrategy;

    public LoanService(LoanRepository loanRepository,
                       EquipmentService equipmentService,
                       StudentService studentService) {
        this.loanRepository = loanRepository;
        this.equipmentService = equipmentService;
        this.studentService = studentService;
        this.penaltyStrategy = new FixedPenaltyStrategy();
    }

    public LoanModel createLoan(Long studentId, Long equipmentId, LocalDate startDate) {
        StudentModel student = studentService.getById(studentId);
        EquipmentModel equipment = equipmentService.getById(equipmentId);

        if (!equipment.isAvailability()) {
            throw new IllegalStateException("Equipment not available.");
        }

        long activeLoans = loanRepository.countActiveLoans(student.getId());
        if (activeLoans >= 2) {
            throw new IllegalStateException("Student already has 2 active loans.");
        }

        LoanModel loan = new LoanModel();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(startDate);  // ✅ custom start date
        loan.setDueDate(startDate.plusDays(7)); // ✅ due date based on custom start
        loan.setStatus(LoanModel.Status.ONGOING);

        loanRepository.save(loan);
        equipmentService.updateAvailability(equipmentId, false);

        return loan;
    }


    public double returnLoan(Long loanId, LocalDate returnDate) {
        LoanModel loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalStateException("Loan not found"));

        // If already returned
        if (loan.getStatus() == LoanModel.Status.RETURNED || loan.getStatus() == LoanModel.Status.OVERDUE) {
            throw new IllegalStateException("Already returned, loan another equipment");
        }

        loan.setReturnDate(returnDate);

        if (returnDate.isAfter(loan.getDueDate())) {
            loan.setStatus(LoanModel.Status.OVERDUE);
        } else {
            loan.setStatus(LoanModel.Status.RETURNED);
        }

        double penalty = penaltyStrategy.calculatePenalty(loan);
        loanRepository.save(loan);

        // Mark equipment as available again
        equipmentService.updateAvailability(loan.getEquipment().getId(), true);

        return penalty;
    }


    public double calculatePenalty(Long loanId) {
        LoanModel loan = loanRepository.findById(loanId).orElseThrow();
        return penaltyStrategy.calculatePenalty(loan);
    }

    public List<LoanModel> getAll() {
        return loanRepository.findAll();
    }
}
