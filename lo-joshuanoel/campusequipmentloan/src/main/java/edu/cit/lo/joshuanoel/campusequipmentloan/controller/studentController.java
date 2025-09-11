package edu.cit.lo.joshuanoel.campusequipmentloan.controller;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.studentEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/api")
public class studentController {

    @Autowired
    private studentService studentService;

    // Get all students
    @GetMapping("/all")
    public List<studentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Add new student
    @PostMapping("/add")
    public studentEntity addStudent(@RequestBody studentEntity student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody studentEntity updatedStudent) {
        try {
            studentEntity student = studentService.updateStudent(studentId, updatedStudent);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Student not found");
        }
    }
}

