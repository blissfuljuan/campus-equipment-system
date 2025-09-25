package edu.cit.angus.shayne.campusequipmentloan.controller;

import edu.cit.angus.shayne.campusequipmentloan.model.Student;
import edu.cit.angus.shayne.campusequipmentloan.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student saved = studentService.createStudent(student);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
