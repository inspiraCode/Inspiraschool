package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.AssignmentService;

@ManagedBean
@SessionScoped
public class AssignmentBean extends BaseFacesBean<Assignment> {
  private static final long serialVersionUID = -3401655463406219716L;

  private AssignmentService assignmentService;

  public AssignmentBean() {
    super(Assignment.class);
  }

  public AssignmentService getAssignmentService() {
    return assignmentService;
  }

  public void setAssignmentService(AssignmentService assignmentService) {
    super.setService((BaseService<Assignment>) assignmentService);
    this.assignmentService = assignmentService;
  }
}
