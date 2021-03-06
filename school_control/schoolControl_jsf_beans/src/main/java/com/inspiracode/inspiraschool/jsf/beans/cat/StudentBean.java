package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;

import com.inspiracode.inspiraschool.dto.cat.Company;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cat.StudentStatus;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.CompanyService;
import com.inspiracode.inspiraschool.service.cat.GroupService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;
import com.inspiracode.inspiraschool.service.cat.StatusService;
import com.inspiracode.inspiraschool.service.cat.StudentService;

@ManagedBean
@SessionScoped
public class StudentBean extends BaseFacesBean<Student> {
    private static final long serialVersionUID = 8490610255861915518L;
    private static final Logger logger = Logger.getLogger(StudentBean.class.getName());

    @ManagedProperty("#{studentService}")
    private StudentService studentService;

    @ManagedProperty("#{sieGroupService}")
    private SieGroupService sieGroupService;

    @ManagedProperty("#{periodService}")
    private PeriodService periodService;

    @ManagedProperty("#{groupService}")
    private GroupService groupService;

    @ManagedProperty("#{statusService}")
    private StatusService statusService;

    @ManagedProperty("#{companyService}")
    private CompanyService companyService;

    private int periodId;
    private String sieGroupName;
    private Period selectedPeriod;
    private int selectedGroupId = 0;
    private Group selectedGroup;

    private List<SieGroup> availableSies = new ArrayList<SieGroup>();
    private List<GroupAssignment> availableAssignments = new ArrayList<GroupAssignment>();
    private List<GroupAssignment> selectedAssignments = new ArrayList<GroupAssignment>();

    public StudentBean() {
	super(Student.class);
    }

    public List<GroupAssignment> getSelectedAssignments() {
	return selectedAssignments;
    }

    public List<GroupAssignment> getAvailableAssignments() {
	availableAssignments.clear();
	if (selectedGroup == null) {
	    return availableAssignments;
	}
	logger.debug("found " + selectedGroup.getAssignments().size() + " group assignments in database for the selected group");
	for (GroupAssignment ga : selectedGroup.getAssignments()) {
	    logger.debug("Assignment in record: " + ga.getAssignment().getName());
	    if (!availableAssignments.contains(ga)) {
		availableAssignments.add(ga);
	    }
	}

	availableAssignments.removeAll(selectedAssignments);

	logger.debug("found " + availableAssignments.size() + " available assignments to show.");
	return availableAssignments;
    }

    public void groupSelectedListener(AjaxBehaviorEvent event) {
	if (selectedGroupId > 0) {
	    selectedGroup = groupService.getGroupWithAssignments(selectedGroupId);
	}
    }

    @Override
    public void setSelectedItem(Student selectedItem) {
	Set<SieGroup> studentGroups = studentService.getStudentSieGroups(selectedItem);

	if (studentGroups == null) {
	    studentGroups = new HashSet<SieGroup>();
	}

	selectedItem.setSies(studentGroups);

	Set<GroupAssignment> groupAssignments = studentService.getStudentGroupsList(selectedItem);
	selectedAssignments.clear();
	for (GroupAssignment ga : groupAssignments) {
	    selectedGroupId = ga.getGroup().getId();
	    selectedGroup = groupService.getGroupWithAssignments(selectedGroupId);

	    selectedAssignments.add(ga);
	}

	super.setSelectedItem(selectedItem);
    }

    public void uploadSieGroup() {
	logger.debug("Adding sie group to database with period: " + selectedPeriod);
	SieGroup newSieGroup = new SieGroup();
	newSieGroup.setPeriod(selectedPeriod);
	newSieGroup.setSieGroupName(sieGroupName);
	sieGroupService.add(newSieGroup);
    }

    @Override
    protected boolean validate() {
	// TODO: Validate
	Set<GroupAssignment> gaSet = new HashSet<GroupAssignment>();
	gaSet.addAll(selectedAssignments);
	getSelectedItem().setGroups(gaSet);
	// TODO: If the student was scored in the previous group, move the scores to the new group.
	return true;
    }

    public boolean isSieEnabled() {
	if (getSelectedItem().getId() == 0)
	    return true;
	Set<GroupAssignment> groups = studentService.getStudentGroupsList(getSelectedItem());
	if (groups == null)
	    return true;
	for (GroupAssignment ga : groups) {
	    if (ga.getGroup().getCareer().getName().toLowerCase().contains("prepa"))
		return true;
	}
	return false;
    }

    public List<SieGroup> getAvailableSies() {
	logger.debug("Getting available SIE Groups");
	if (availableSies.isEmpty()) {
	    availableSies.addAll(sieGroupService.getAll());
	}
	logger.debug("Got " + availableSies.size() + " from db");
	logger.debug("Got " + getSelectedItem().getSies().size() + " from current selected");
	availableSies.removeAll(getSelectedItem().getSies());
	logger.debug("Got " + availableSies + " from db after removing local");
	return availableSies;
    }

    public List<SieGroup> getAssignedSies() {
	List<SieGroup> result = new ArrayList<SieGroup>();
	result.addAll(getSelectedItem().getSies());
	return result;
    }

    // setter to complete property
    public void setSieEnabled(boolean sieEnabled) {
    }

    public void addAllAssignments(ActionEvent actionEvent) {
	selectedAssignments.addAll(availableAssignments);
	publishInfo("Todas las materias del grupo agregadas al alumno.");
    }

    public void removeAllAssignments(ActionEvent actionEvent) {
	selectedAssignments.clear();
	publishInfo("Todas las materias asignadas al alumno han sido retiradas.");
    }

    public void onRemoveAssignment(DragDropEvent event) {
	availableAssignments.clear();
	HtmlPanelGroup selectedGroupAssignmentsPanel = (HtmlPanelGroup) event.getComponent().findComponent("selectedGroupAssignmentsPanel");
	if (selectedGroupAssignmentsPanel != null) {
	    selectedGroupAssignmentsPanel.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		@Override
		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    GroupAssignment item = draggedItem != null ? (GroupAssignment) draggedItem.getAttributes().get("assignmentItem") : new GroupAssignment();
		    selectedAssignments.remove(item);
		    publishInfo("Asignatura " + item.getAssignment().getName() + " desvinculada del alumno.");
		}
	    });
	} else {
	    publishError("Error al quitar asignatura");
	}
    }

    public void onAddAssignment(DragDropEvent event) {
	HtmlPanelGroup groupAssignmentPanel = (HtmlPanelGroup) event.getComponent().findComponent("groupAssignmentsPanel");
	if (groupAssignmentPanel != null) {
	    groupAssignmentPanel.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		@Override
		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    GroupAssignment item = draggedItem != null ? (GroupAssignment) draggedItem.getAttributes().get("assignmentItem") : new GroupAssignment();
		    if (!selectedAssignments.contains(item))
			selectedAssignments.add(item);
		    publishInfo("Asignatura " + item.getAssignment().getName() + " vinculada al alumno con éxito.");
		}
	    });
	} else {
	    publishError("Error al agregar asignatura.");
	}
    }

    public void onRemoveSie(DragDropEvent event) {
	this.availableSies.clear();
	HtmlPanelGroup availableSies = (HtmlPanelGroup) event.getComponent().findComponent("selectedSies");
	if (availableSies != null) {
	    availableSies.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		@Override
		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    SieGroup item = draggedItem != null ? (SieGroup) draggedItem.getAttributes().get("sieGroup") : new SieGroup();
		    logger.debug("Removing sie group from student: " + item);
		    getSelectedItem().getSies().remove(item);
		    publishInfo("Grupo SIE " + item.getSieGroupName() + " desvinculado del alumno.");
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
		    logger.debug("Adding sie group to student: " + item);
		    getSelectedItem().getSies().add(item);
		    publishInfo("Grupo SIE " + item.getSieGroupName() + " vinculado al alumno.");
		}
	    });
	} else {
	    publishError("Error al agregar grupo SIE.");
	}
    }

    @Override
    public String addNew() throws InstantiationException, IllegalAccessException {
	return super.addNew();
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

    public SieGroupService getSieGroupService() {
	return sieGroupService;
    }

    public void setSieGroupService(SieGroupService sieGroupService) {
	this.sieGroupService = sieGroupService;
    }

    public int getPeriodId() {
	return periodId;
    }

    public void setPeriodId(int periodId) {
	logger.debug("Setting period id:" + periodId);
	selectedPeriod = periodService.get(periodId);
	this.periodId = periodId;
    }

    public PeriodService getPeriodService() {
	return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
	this.periodService = periodService;
    }

    public String getSieGroupName() {
	return sieGroupName;
    }

    public void setSieGroupName(String sieGroupName) {
	logger.debug("Setting group name as " + sieGroupName);
	this.sieGroupName = sieGroupName;
    }

    public Period getSelectedPeriod() {
	return selectedPeriod;
    }

    public void setSelectedPeriod(Period selectedPeriod) {
	this.selectedPeriod = selectedPeriod;
    }

    public int getSelectedGroupId() {
	return selectedGroupId;
    }

    public void setSelectedGroupId(int selectedGroupId) {
	this.selectedGroupId = selectedGroupId;
    }

    public int getSelectedStatusId() {
	if (getSelectedItem().getId() == 0)
	    return 0;
	return getSelectedItem().getStudentStatus().getId();
    }

    public void setSelectedStatusId(int selectedStatusId) {
	if (selectedStatusId != 0) {
	    StudentStatus dbStatus = statusService.get(selectedStatusId);
	    getSelectedItem().setStudentStatus(dbStatus);
	}
    }

    public int getSelectedCompanyId() {
	if (getSelectedItem().getId() == 0)
	    return 0;
	if (getSelectedItem().getCompany() == null)
	    return 0;

	return getSelectedItem().getCompany().getId();
    }

    public void setSelectedCompanyId(int selectedCompanyId) {
	if (selectedCompanyId != 0) {
	    // TODO: Setup db company
	    Company dbCompany = companyService.get(selectedCompanyId);
	    getSelectedItem().setCompany(dbCompany);
	}
    }

    public Group getSelectedGroup() {
	return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
	this.selectedGroup = selectedGroup;
    }

    public GroupService getGroupService() {
	return groupService;
    }

    public void setGroupService(GroupService groupService) {
	this.groupService = groupService;
    }

    public void setSelectedAssignments(List<GroupAssignment> selectedAssignments) {
	this.selectedAssignments = selectedAssignments;
    }

    public StatusService getStatusService() {
	return statusService;
    }

    public void setStatusService(StatusService statusService) {
	this.statusService = statusService;
    }

    public CompanyService getCompanyService() {
	return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
    }

}
