package edu.cit.lo.joshuanoel.campusequipmentloan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class equipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;
    private String equipmentName;
    private String equipmentType;
    private int serialNumber;
    private String availability;


    public equipmentEntity(int equipmentId, String equipmentName, String equipmentType, int serialNumber, String availability) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.serialNumber = serialNumber;
        this.availability = availability;
    }

    public equipmentEntity() {

    }


    public int getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
