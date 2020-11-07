package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStudent(@PathVariable("id") Integer id) {
    studentService.deleteStudent(id);
  }

  @GetMapping
  public List<Student> getStudents(@RequestParam(name = "gender", required = false) String gender) {
    return studentService.getStudents(gender);
  }

  @GetMapping("/{id}")
  public Student getStudentById(@PathVariable("id") Integer id) {
    return studentService.getStudentById(id);
  }

  @PatchMapping("/{id}")
  public Student updateStudent(@PathVariable("id") Integer id,
                               @RequestBody Student student) {
    return studentService.updateStudent(id, student);
  }

}
