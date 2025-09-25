package edu.cit.laborada.johnjoseph.campusequipmentloan.controller;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.StudentModel;
import edu.cit.laborada.johnjoseph.campusequipmentloan.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentModel student) {
        try {
            studentService.save(student);
            return ResponseEntity.ok(Map.of(
                    "message", "Student added successfully.",
                    "id", student.getId(),
                    "studentNo", student.getStudentNo(),
                    "name", student.getName(),
                    "email", student.getEmail()
            ));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok(Map.of(
                    "error", e.getMessage(),
                    "studentNo", student.getStudentNo(),
                    "name", student.getName(),
                    "email", student.getEmail()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentModel>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }
}