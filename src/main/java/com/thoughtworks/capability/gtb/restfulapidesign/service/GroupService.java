package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repo.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
  private final GroupRepository groupRepository;
  private final StudentRepository studentRepository;

  public GroupService(GroupRepository groupRepository, StudentRepository studentRepository) {
    this.groupRepository = groupRepository;
    this.studentRepository = studentRepository;
  }

  public List<Group> grouping(List<Student> students) {
    List<Group> allGroups = groupRepository.findAllGroupsWithEmptyStudentsList();
    groupingRandomly(allGroups, students);
    return groupRepository.findAllGroups();
  }

  public Group updateGroupName(Integer id, Group group) {
    return groupRepository.updateGroupNameById(id, group.getName());
  }

  public List<Group> getGroups() {
    return groupRepository.getGroups();
  }

  private void groupingRandomly(List<Group> allGroups, List<Student> allStudent) {
    int studentNum = allStudent.size();
    int groupNum = allGroups.size();

    Collections.shuffle(allStudent);

    int remainingStudentNum = studentNum % groupNum;
    int studentNumPerGroup = studentNum / groupNum;

    for(int i = 0; i < groupNum; i++) {
      List<Student> studentInTheGroup = new ArrayList<Student>(allStudent.subList(i * studentNumPerGroup, (i + 1) * studentNumPerGroup));
      if (i < remainingStudentNum) {
        studentInTheGroup.add(allStudent.get(allStudent.size() - 1 - i));
      }
      Group group = allGroups.get(i);
      studentInTheGroup.forEach(student -> {
        groupRepository.addStudentInGroup(group.getId(), student);
        studentRepository.updateGroupInfoOfStudent(student.getId(), group.getId());
      });
    }
  }
}
