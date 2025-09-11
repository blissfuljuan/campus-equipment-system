package edu.cit.sagaral.earljericho.campusequipmentloan.controller;

import edu.cit.sagaral.earljericho.campusequipmentloan.model.Equipment;
import edu.cit.sagaral.earljericho.campusequipmentloan.repository.EquipmentRepository;
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