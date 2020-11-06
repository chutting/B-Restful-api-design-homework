package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("/students")
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @DeleteMapping("/students/{id}")
  public void deleteStudent(@PathVariable("id") Integer id) {
    studentService.deleteStudent(id);
  }

  @GetMapping("/students")
  public List<Student> getStudents(@RequestParam(name = "gender", required = false) String gender) {
    return studentService.getStudents(gender);
  }

  @GetMapping("/students/{id}")
  public Student getStudentById(@PathVariable("id") Integer id) {
    return studentService.getStudentById(id);
  }


}
