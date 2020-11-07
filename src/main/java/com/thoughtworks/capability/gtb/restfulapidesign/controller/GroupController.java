package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
  private final GroupService groupService;

  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @PostMapping
  public List<Group> grouping(@RequestBody List<Student> students) {
    return groupService.grouping(students);
  }

  @PatchMapping("/{id}")
  public Group updateGroupName(@PathVariable Integer id,
                               @RequestBody Group group) {
    return groupService.updateGroupName(id, group);
  }

  @GetMapping
  public List<Group> getGroups() {
    return groupService.getGroups();
  }
}
