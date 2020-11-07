package com.thoughtworks.capability.gtb.restfulapidesign.repo;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepository {
  private List<Group> groups = new ArrayList<>();

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  public List<Group> findAllGroupsWithEmptyStudentsList() {
    generateGroups();
    return this.groups;
  }

  public void addStudentInGroup(Integer groupId, Student student) {
    Group groupById = getGroupById(groupId);
    List<Student> currentStudentsInGroup = groupById.getStudents();
    currentStudentsInGroup.add(student);
    groupById.setStudents(currentStudentsInGroup);
  }

  public List<Group> findAllGroups() {
    return groups;
  }

  public Group updateGroupNameById(Integer id, String name) {
    Group groupById = getGroupById(id);
    groupById.setName(name);
    return groupById;
  }

  private Group getGroupById(int id) {
    Optional<Group> groupById = groups.stream().filter(group -> group.getId() == id).findFirst();
    if (!groupById.isPresent()) {
      throw new GroupNotFoundException("group is not found by Id");
    }
    return groupById.get();
  }

  private void generateGroups() {
    this.groups.clear();
    for (int i = 1; i <= 6; i++) {
      Group newGroup = new Group(i, String.valueOf(i), new ArrayList<>());
      this.groups.add(newGroup);
    }
  }
}
