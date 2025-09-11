package campusequipmentloan.repository;



import edu.cit.lastname.firstname.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}

