package campusequipmentloan.repository;

import edu.cit.lastname.firstname.campusequipmentloan.model.Loan;
import edu.cit.lastname.firstname.campusequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentIdAndStatus(Long studentId, LoanStatus status);
    List<Loan> findByStatus(LoanStatus status);
}
