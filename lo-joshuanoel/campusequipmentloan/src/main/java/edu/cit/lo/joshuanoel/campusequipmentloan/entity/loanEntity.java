package edu.cit.lo.joshuanoel.campusequipmentloan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class loanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoanId;
    private String equipment;
    private String student;
    private int startDate;
    private int dueDate;
    private int returnDate;
    private String status;

    public loanEntity(int LoanId, String equipment, String student, int startDate, int dueDate, int returnDate, String status) {
        this.LoanId = LoanId;
        this.equipment = equipment;
        this.student = student;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
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

    public int getStartDate() {
        return startDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
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

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
