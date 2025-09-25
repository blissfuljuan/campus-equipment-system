package edu.cit.reyes.amanda.campusequipmentloan.controller;

import edu.cit.reyes.amanda.campusequipmentloan.model.Equipment;
import edu.cit.reyes.amanda.campusequipmentloan.repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentRepository equipmentRepo;

    public EquipmentController(EquipmentRepository equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepo.findByAvailableTrue();
    }
}


