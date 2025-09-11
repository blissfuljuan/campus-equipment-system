package edu.cit.lo.joshuanoel.campusequipmentloan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class studentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int studId;
    private int studentNo;
    private String name;
    private String email;

    public studentEntity(int studId, int studentNo, String name, String email){
        this.studId = studId;
        this.studentNo = studentNo;
        this.name = name;
        this.email = email;
    }

    public int getStudId(){
        return studId;
    }
    public void setStudId(int studId){
        this.studId = studId;
    }

    public int getStudentNo(){
        return studentNo;
    }

    public void setStudentNo(int studentNo){
        this.studentNo = studentNo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
