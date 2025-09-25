package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.controller;

import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.EquipmentEntity;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository.EquipmentRepository;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class LoanController {

    private final EquipmentRepository equipmentRepository;
    private final LoanService loanService;

    public LoanController(EquipmentRepository equipmentRepository, LoanService loanService) {
        this.equipmentRepository = equipmentRepository;
        this.loanService = loanService;
    }

    @GetMapping("/equipment/available")
    public List<EquipmentEntity> listAvailable() {
        return equipmentRepository.findAllAvailable();
    }

    @PostMapping("/loans")
    public ResponseEntity<?> createLoan(@RequestBody Map<String, Long> body) {
        Long productId = body.get("productId");
        Long studentId = body.get("studentId");
        return ResponseEntity.ok(loanService.createLoan(productId, studentId));
    }

    @PostMapping("/loans/{loanId}/return")
    public ResponseEntity<?> returnLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnLoan(loanId));
    }
}



