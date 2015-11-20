package com.inspiracode.inspiraschool.dto.cross;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.dto.cat.Teacher;

@Entity
@Table(name = "cross_group_assignment", catalog = "school_control")
public class GroupAssignment implements BaseDTO {
  private static final long serialVersionUID = 6655789129834276921L;
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
