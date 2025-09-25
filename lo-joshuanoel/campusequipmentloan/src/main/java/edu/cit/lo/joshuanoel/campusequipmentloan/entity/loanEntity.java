package edu.cit.lo.joshuanoel.campusequipmentloan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class loanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoanId;
    private String equipment;
    private String student;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double penalty;

    public loanEntity(){

    }

    public loanEntity(int LoanId, String equipment, String student, LocalDate startDate, LocalDate dueDate, LocalDate returnDate, String status, double penalty) {
        this.LoanId = LoanId;
        this.equipment = equipment;
        this.student = student;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.penalty = penalty;
    }

    public int getLoanId() {
        return LoanId;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getStudent() {
        return student;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public double getPenalty(){
        return penalty;
    }

    public void setLoanId(int LoanId) {
        this.LoanId = LoanId;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPenalty(double penalty){
        this.penalty = penalty;
    }
}