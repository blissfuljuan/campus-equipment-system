package campusequipmentloan.controller;

import edu.cit.lastname.firstname.campusequipmentloan.dto.LoanRequestDTO;
import edu.cit.lastname.firstname.campusequipmentloan.dto.ReturnResponseDTO;
import edu.cit.lastname.firstname.campusequipmentloan.model.Equipment;
import edu.cit.lastname.firstname.campusequipmentloan.model.Loan;
import edu.cit.lastname.firstname.campusequipmentloan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanRequestDTO req) {
        return ResponseEntity.ok(service.createLoan(req));
    }

    @PostMapping("/loans/{id}/return")
    public ResponseEntity<ReturnResponseDTO> returnLoan(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnLoan(id));
    }

    @GetMapping("/equipment/available")
    public ResponseEntity<List<Equipment>> availableEquipment() {
        return ResponseEntity.ok(service.listAvailableEquipment());
    }
}
