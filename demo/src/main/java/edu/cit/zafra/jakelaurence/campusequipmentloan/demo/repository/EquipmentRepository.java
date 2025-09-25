package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository;

import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {

    @Query("select e from EquipmentEntity e where e.availablity = true")
    List<EquipmentEntity> findAllAvailable();
}



