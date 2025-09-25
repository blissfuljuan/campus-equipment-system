package edu.cit.sagaral.earljericho.campusequipmentloan.repository;

import edu.cit.sagaral.earljericho.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Custom query: find all equipment that is available
    List<Equipment> findByAvailableTrue();
}