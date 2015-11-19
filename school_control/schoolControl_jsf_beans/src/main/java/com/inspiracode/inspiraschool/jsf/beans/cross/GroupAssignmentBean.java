package com.inspiracode.inspiraschool.jsf.beans.cross;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cross.GroupAssignmentService;

@ManagedBean
@SessionScoped
public class GroupAssignmentBean extends BaseFacesBean<GroupAssignment> {
  private static final long serialVersionUID = 1901316763469701174L;

  private GroupAssignmentService groupAssignmentService;

  public GroupAssignmentBean() {
    super(GroupAssignment.class);
  }

  public GroupAssignmentService getGroupAssignmentService() {
    return groupAssignmentService;
  }

  public void setGroupAssignmentService(GroupAssignmentService groupAssignmentService) {
    super.setService((BaseService<GroupAssignment>) groupAssignmentService);
    this.groupAssignmentService = groupAssignmentService;
  }
}
