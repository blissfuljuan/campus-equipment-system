package edu.cit.lo.joshuanoel.campusequipmentloan.service;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.equipmentEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.repository.equipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class equipmentService {
    @Autowired
    equipmentRepository erepo;

    public equipmentService(){

    }

    public equipmentEntity postEquipment(equipmentEntity equipment){
        return erepo.save(equipment);
    }

    public List<equipmentEntity>getAllEquipment(){
        return erepo.findAll();
    }

    public equipmentEntity getEquipmentByName(String equipmentName)throws Exception{
        if(erepo.findByEquipmentName(equipmentName)!=null)
            return erepo.findByEquipmentName(equipmentName);
        else
            throw new Exception("There is no equipment that is named" + equipmentName + " in the list");
    }

    @SuppressWarnings("finally")
    public equipmentEntity putEquipment(int equipmentId, equipmentEntity newEquipmentDetails) {
        equipmentEntity equipment = new equipmentEntity();

        try {
            equipment = erepo.findById(equipmentId).get();
            equipment.setEquipmentName(newEquipmentDetails.getEquipmentName());
        }catch(Exception ex) {
            throw new Exception("There is no admin with the id" + equipmentId + " in the system!");
        }finally {
            return erepo.save(equipment);
        }
    }

    public String deleteEquipment(int equipmentId){
        String msg = "";
        erepo.deleteById(equipmentId);
        msg = "equipment " + equipmentId + " is successfully deleted!";
        return msg;
    }

}
