package edu.cit.yu.rainricrandy.campuseequipmentloan.controller;

import edu.cit.yu.rainricrandy.campuseequipmentloan.model.Student;
import edu.cit.yu.rainricrandy.campuseequipmentloan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
}