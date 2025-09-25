package edu.cit.angus.shayne.campusequipmentloan.repository;

import edu.cit.angus.shayne.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
    boolean existsBySerialNumber(String serialNumber);
}
