package com.thoughtworks.capability.gtb.restfulapidesign.repo;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotFoundException;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
  private List<Student> students;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public Student addStudent(Student student) {
    int studentCount = this.students.size();
    int studentId = studentCount == 0 ? 1 : this.students.get(studentCount - 1).getId() + 1;
    student.setId(studentId);
    students.add(student);
    return student;
  }

  public void deleteStudent(Integer id) {
    students.removeIf(student -> student.getId() == id);
  }

  public List<Student> getAllStudents() {
    return students;
  }

  public List<Student> getStudentsByGender(String gender) {
    return students.stream().filter(student -> student.getGender().equals(gender)).collect(Collectors.toList());
  }

  public Optional<Student> getStudentById(Integer id) {
    return students.stream().filter(student -> student.getId() == id).findFirst();
  }

  public Student updateStudentById(Integer id, Student updatedStudentInfo) {
    Optional<Student> studentById = getStudentById(id);
    if (!studentById.isPresent()) {
      throw new StudentNotFoundException("student with id is not found");
    } else {
      Student studentWantToUpdate = studentById.get();
      if (updatedStudentInfo.getName() != null) {
        studentWantToUpdate.setName(updatedStudentInfo.getName());
      }
      if (updatedStudentInfo.getGender() != null) {
        studentWantToUpdate.setGender(updatedStudentInfo.getGender());
      }
      if (updatedStudentInfo.getGroup() != null) {
        studentWantToUpdate.setGroup(updatedStudentInfo.getGroup());
      }
    }
    return studentById.get();
  }

}
