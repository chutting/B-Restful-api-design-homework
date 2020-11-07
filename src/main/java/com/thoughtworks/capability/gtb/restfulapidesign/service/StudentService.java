package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student addStudent(Student student) {
    return studentRepository.addStudent(student);
  }

  public void deleteStudent(Integer id) {
    studentRepository.deleteStudent(id);
  }

  public List<Student> getStudents(String gender) {
    if (gender == null) {
      return studentRepository.getAllStudents();
    } else {
      return studentRepository.getStudentsByGender(gender);
    }
  }

  public Student getStudentById(Integer id) {
    Optional<Student> studentById = studentRepository.getStudentById(id);
    if (!studentById.isPresent()) {
      throw new StudentNotFoundException("student with id is not found");
    }
    return studentById.get();
  }

  public Student updateStudent(Integer id, Student student) {
    studentRepository.updateStudentById(id, student);
    return null;
  }
}
