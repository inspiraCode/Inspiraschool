package com.inspiracode.inspiraschool.dao.hibernate.cross;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.inspiracode.inspiraschool.dao.cross.GroupAssignmentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public class GroupAssignmentDAOHibernate extends BaseHibernateDAO<GroupAssignment> implements GroupAssignmentDAO {
    private static final long serialVersionUID = 1L;

    public GroupAssignmentDAOHibernate() {
	super(GroupAssignment.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GroupAssignment> getGroupsByTeacher(int teacherId) {
	List<GroupAssignment> result = (List<GroupAssignment>) getHibernateTemplate().find(QUERY_BY_TEACHER_ID, teacherId, periodoActual());
	return result;
    }

    @Override
    public List<Student> getStudentsByGroupId(int groupId) {
	List<Student> result = new ArrayList<Student>();
	GroupAssignment tempGroup = get(groupId);
	for (Student gStudent : tempGroup.getStudents()) {
	    result.add(gStudent);
	}
	return result;
    }

    private String periodoActual() {
	String result = "";
	// Conocer fecha actual
	int mesActual = Calendar.getInstance().get(Calendar.MONTH);
	switch (mesActual) {
	case 0:
	case 1:
	case 2:
	case 3:
	    result = "ENERO-ABRIL";
	    break;
	case 4:
	case 5:
	case 6:
	case 7:
	    result = "MAYO-AGOSTO";
	    break;
	default:
	    result = "SEPTIEMBRE-DICIEMBRE";
	}

	return result;
    }

}
