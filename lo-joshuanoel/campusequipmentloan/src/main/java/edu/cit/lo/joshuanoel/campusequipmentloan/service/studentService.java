package edu.cit.lo.joshuanoel.campusequipmentloan.service;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.studentEntity;
import edu.cit.lo.joshuanoel.campusequipmentloan.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class studentService {

    @Autowired
    private studentRepository studentRepo;

    public List<studentEntity> getAllStudents() {
        return studentRepo.findAll();
    }

    // Get student by ID
    public Optional<studentEntity> getStudentById(int id) {
        return studentRepo.findById(id);
    }

    // Add new student
    public studentEntity addStudent(studentEntity student) {
        return studentRepo.save(student);
    }

    // Update student
    public studentEntity updateStudent(int id, studentEntity updatedStudent) throws Exception {
        studentEntity student = studentRepo.findById(id)
                .orElseThrow(() -> new Exception("Student not found with id " + id));

        student.setStudentNo(updatedStudent.getStudentNo());
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());

        return studentRepo.save(student);
    }

    // Delete student by ID
    public void deleteStudent(int studentId) {
        studentRepo.deleteById(studentId);
    }
}

