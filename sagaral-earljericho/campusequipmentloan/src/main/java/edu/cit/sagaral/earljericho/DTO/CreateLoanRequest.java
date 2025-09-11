package edu.cit.sagaral.earljericho.campusequipmentloan.dto;

import java.time.LocalDate;

public class CreateLoanRequest {
    private Long equipmentId;
    private Long studentId;
    private LocalDate startDate; // optional, defaults to today in service

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}