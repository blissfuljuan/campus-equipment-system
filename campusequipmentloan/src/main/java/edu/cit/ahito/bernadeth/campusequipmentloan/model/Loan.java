package edu.cit.angus.shayne.campusequipmentloan.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Equipment equipment;

    @ManyToOne(optional = false)
    private Student student;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private BigDecimal penalty = BigDecimal.ZERO;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
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

    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }
    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }
}
