package com.inspiracode.inspiraschool.jsf.beans.cross;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dto.cat.Teacher;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.TeacherService;
import com.inspiracode.inspiraschool.service.cross.GroupAssignmentService;

@ManagedBean
@SessionScoped
public class GroupAssignmentBean extends BaseFacesBean<GroupAssignment> {
    private static final long serialVersionUID = 1901316763469701174L;
    private static final Logger logger = Logger.getLogger(GroupAssignmentBean.class.getName());

    @ManagedProperty("#{groupAssignmentService}")
    private GroupAssignmentService groupAssignmentService;

    @ManagedProperty("#{teacherService}")
    private TeacherService teacherService;

    public GroupAssignmentBean() {
	super(GroupAssignment.class);
    }

    public String fontAwesome(GroupAssignment item) {
	String result = "fa-flask";
	String prefijoMateria = item.getAssignment().getName().toLowerCase().substring(0, 4);
	logger.debug("evaluating " + prefijoMateria + " for font awesome.");
	switch (prefijoMateria) {
	case "mate":
	    result = "fa-calculator";
	    break;
	case "quím":
	case "quim":
	    result = "fa-flask";
	    break;
	case "etic":
	case "étic":
	    result = "fa-balance-scale";
	    break;
	case "int.":
	    result = "fa-leaf";
	    break;
	case "lect":
	    result = "fa-book";
	    break;
	case "leng":
	    result = "fa-language";
	    break;
	case "info":
	    result = "fa-desktop";
	    break;
	case "hist":
	    result = "fa-history";
	    break;
	case "biol":
	    result = "fa-heartbeat";
	    break;
	case "físi":
	case "fisi":
	    result = "fa-magnet";
	    break;
	case "lite":
	    result = "fa-tripadvisor";
	    break;
	case "est.":
	    result = "fa-diamond";
	    break;
	case "psic":
	    result = "fa-yahoo";
	    break;
	case "geog":
	    result = "fa-map";
	    break;
	case "calc":
	    result = "fa-calculator";
	    break;
	case "admi":
	    result = "fa-user";
	    break;
	case "t.se":
	    result = "fa-heartbeat";
	    break;
	case "tema":
	    result = "fa-gavel";
	    break;
	case "filo":
	    result = "fa-lightbulb-o";
	    break;
	case "ecol":
	    result = "fa-tree";
	    break;
	case "meto":
	    result = "fa-user-secret";
	    break;
	default:
	    result = "fa-flask";
	}
	logger.debug("result:" + result);
	return result;
    }

    public List<GroupAssignment> getGroupsByLoggedTeacher() {
	String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
	Teacher loggedTeacher = teacherService.getTeacherByUserName(userName);
	return groupAssignmentService.getGroupsByTeacher(loggedTeacher.getId());
    }
    
    public String groupSelected(GroupAssignment item){
	logger.debug("item selected, forwarding to [calificar]");
	logger.debug(item);
	setSelectedItem(item);
	return "calificar";
    }

    public GroupAssignmentService getGroupAssignmentService() {
	return groupAssignmentService;
    }

    public void setGroupAssignmentService(GroupAssignmentService groupAssignmentService) {
	super.setService((BaseService<GroupAssignment>) groupAssignmentService);
	this.groupAssignmentService = groupAssignmentService;
    }

    public TeacherService getTeacherService() {
	return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
	this.teacherService = teacherService;
    }
}
