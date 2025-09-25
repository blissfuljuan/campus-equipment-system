package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan_table")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private EquipmentEntity equipment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentEntity student;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "status", nullable = false)
    private String status; // e.g., ACTIVE, RETURNED, OVERDUE

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


