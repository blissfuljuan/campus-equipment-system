package edu.cit.angus.shayne.campusequipmentloan.service;

import edu.cit.angus.shayne.campusequipmentloan.model.Student;
import edu.cit.angus.shayne.campusequipmentloan.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        if (studentRepository.existsByStudentNo(student.getStudentNo())) {
            throw new IllegalArgumentException("Student number already exists: " + student.getStudentNo());
        }
        return studentRepository.save(student);
    }
}
