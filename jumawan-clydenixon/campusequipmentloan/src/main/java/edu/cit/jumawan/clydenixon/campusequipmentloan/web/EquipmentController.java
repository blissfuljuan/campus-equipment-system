package edu.cit.jumawan.clydenixon.campusequipmentloan.web;
import edu.cit.jumawan.clydenixon.campusequipmentloan.dto.EquipmentDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final LoanService loanService;
    public EquipmentController(LoanService loanService) {
        this.loanService = loanService;
    }
    @GetMapping("/available")
    public List<EquipmentDTO> listAvailable() {
        return loanService.listAvailableEquipment();
    }
}