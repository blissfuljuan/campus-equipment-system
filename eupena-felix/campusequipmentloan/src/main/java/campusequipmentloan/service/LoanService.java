package campusequipmentloan.service;

import edu.cit.lastname.firstname.campusequipmentloan.dto.LoanRequestDTO;
import edu.cit.lastname.firstname.campusequipmentloan.dto.ReturnResponseDTO;
import edu.cit.lastname.firstname.campusequipmentloan.model.Equipment;
import edu.cit.lastname.firstname.campusequipmentloan.model.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanRequestDTO request);
    ReturnResponseDTO returnLoan(Long loanId);
    List<Equipment> listAvailableEquipment();
}
