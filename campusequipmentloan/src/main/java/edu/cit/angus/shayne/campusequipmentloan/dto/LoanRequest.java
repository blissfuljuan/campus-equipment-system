package edu.cit.angus.shayne.campusequipmentloan.dto;

public class LoanRequest {
    private Long studentId;
    private Long equipmentId;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }
}
