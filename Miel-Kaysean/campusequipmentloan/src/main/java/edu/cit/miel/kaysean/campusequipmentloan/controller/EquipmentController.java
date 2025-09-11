package edu.cit.miel.kaysean.campusequipmentloan.controller;

import edu.cit.miel.kaysean.campusequipmentloan.model.Equipment;
import edu.cit.miel.kaysean.campusequipmentloan.service.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailableTrue();
    }
}
