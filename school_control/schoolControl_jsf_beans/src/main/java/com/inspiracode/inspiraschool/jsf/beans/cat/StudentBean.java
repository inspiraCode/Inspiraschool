package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;

import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.StudentService;

@ManagedBean
@SessionScoped
public class StudentBean extends BaseFacesBean<Student> {
    private static final long serialVersionUID = 8490610255861915518L;

    @ManagedProperty("#{studentService}")
    private StudentService studentService;

    public StudentBean() {
	super(Student.class);
    }

    @Override
    protected boolean validate() {
	// TODO: Validate
	return true;
    }

    public boolean isSieEnabled() {
	Set<GroupAssignment> groups = studentService.getStudentGroupsList(getSelectedItem());
	for (GroupAssignment ga : groups) {
	    if (ga.getGroup().getCareer().getName().toLowerCase().contains("prepa"))
		return true;
	}
	return false;
    }

    public List<SieGroup> getAvailableSies() {
	// TODO: Define
	return new ArrayList<SieGroup>();
    }

    public void setAvailableSies(List<SieGroup> availableSies) {
	// TODO: Define
    }

    public List<SieGroup> getAssignedSies() {
	// TODO: Define
	return new ArrayList<SieGroup>();
    }

    public void setAssignedSies(List<SieGroup> assignedSies) {
	// TODO: Define
    }

    // setter to complete property
    public void setSieEnabled(boolean sieEnabled) {
    }

    public void onRemoveSie(DragDropEvent event) {
	HtmlPanelGroup availableSies = (HtmlPanelGroup) event.getComponent().findComponent("selectedSies");
	if (availableSies != null) {
	    availableSies.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		@Override
		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    SieGroup item = draggedItem != null ? (SieGroup) draggedItem.getAttributes().get("sieGroup") : new SieGroup();
		    getSelectedItem().getSies().remove(item);
		}
	    });
	} else {
	    publishError("Error al quitar grupo SIE.");
	}
    }

    public void onAddSie(DragDropEvent event) {
	HtmlPanelGroup availableSies = (HtmlPanelGroup) event.getComponent().findComponent("availableSies");
	if (availableSies != null) {
	    availableSies.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		@Override
		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    SieGroup item = draggedItem != null ? (SieGroup) draggedItem.getAttributes().get("sieGroup") : new SieGroup();
		    getSelectedItem().getSies().add(item);
		}
	    });
	} else {
	    publishError("Error al agregar grupo SIE.");
	}
    }

    public String getStudentGroups(Student item) {
	return studentService.getStudentGroups(item);
    }

    public StudentService getStudentService() {
	return studentService;
    }

    public void setStudentService(StudentService studentService) {
	super.setService((BaseService<Student>) studentService);
	this.studentService = studentService;
    }
}
