package com.inspiracode.inspiraschool.dto.cross;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.dto.cat.Teacher;

public class GroupAssignment implements BaseDTO {
  private int id;
  private Assignment assignment;
  private Group group;
  private Teacher teacher;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

}
