package edu.cit.angus.shayne.campusequipmentloan.service;

import edu.cit.angus.shayne.campusequipmentloan.model.Equipment;
import edu.cit.angus.shayne.campusequipmentloan.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment createEquipment(Equipment equipment) {
        if (equipmentRepository.existsBySerialNumber(equipment.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists: " + equipment.getSerialNumber());
        }
        return equipmentRepository.save(equipment);
    }
}
