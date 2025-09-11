package edu.cit.lo.joshuanoel.campusequipmentloan.controller;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.equipmentEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.service.equipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment/api")
public class equipmentController {

    @Autowired
    private equipmentService eserv;

    public equipmentController() {
    }

    // Get all equipment
    @GetMapping("/getAllEquipment")
    public List<equipmentEntity> getAllEquipment() {
        return eserv.getAllEquipment();
    }

    // Get equipment by name
    @GetMapping("/getEquipmentName")
    public ResponseEntity<?> getEquipmentByName(@PathVariable String equipmentName) {
        try {
            equipmentEntity equipment = eserv.getEquipmentByName(equipmentName);
            return ResponseEntity.ok(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/postNewEquipment")
    public equipmentEntity postEquipment(@RequestBody equipmentEntity equipment) {
        return eserv.postEquipment(equipment);
    }

    @PutMapping("/putEquipmentId")
    public ResponseEntity<?> updateEquipment(@PathVariable int equipmentId, @RequestBody equipmentEntity newDetails) {
        try {
            equipmentEntity updated = eserv.putEquipment(equipmentId, newDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete equipment by id
    @DeleteMapping("/deleteEquipment/{equipmentId}")
    public String deleteEquipment(@PathVariable int equipmentId) {

        return eserv.deleteEquipment(equipmentId);
    }
}

