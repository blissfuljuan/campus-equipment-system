package edu.cit.laborada.johnjoseph.campusequipmentloan.repository;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, Long> {
}