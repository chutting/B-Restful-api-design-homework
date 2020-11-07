package com.thoughtworks.capability.gtb.restfulapidesign.repo;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotFoundException;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
  private List<Student> students = new ArrayList<>();

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

  public Student getStudentById(Integer id) {
    Optional<Student> studentById = students.stream().filter(student -> student.getId() == id).findFirst();
    if (!studentById.isPresent()) {
      throw new StudentNotFoundException("student with id is not found");
    }
    return studentById.get();
  }

  public Student updateStudentById(Integer id, Student updatedStudentInfo) {
    Student studentWantToUpdate = getStudentById(id);
    if (updatedStudentInfo.getName() != null) {
      studentWantToUpdate.setName(updatedStudentInfo.getName());
    }
    if (updatedStudentInfo.getGender() != null) {
      studentWantToUpdate.setGender(updatedStudentInfo.getGender());
    }
    if (updatedStudentInfo.getGroupId() != null) {
      studentWantToUpdate.setGroupId(updatedStudentInfo.getGroupId());
    }
    return studentWantToUpdate;
  }

  public void updateGroupInfoOfStudent(Integer id, Integer groupId) {
    Student studentById = getStudentById(id);
    studentById.setGroupId(groupId);
  }
}
