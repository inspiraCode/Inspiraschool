package com.inspiracode.inspiraschool.dto.cat;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

@Entity
@Table(name = "cat_student", catalog = "school_control")
public class Student implements BaseDTO, Comparable<Student> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private int id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = true)
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "enroll_number")
    private String enrollNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "enrollment_date", nullable = true)
    private Date enrollmentDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status")
    private StudentStatus studentStatus;

    @Column(name = "clock_id")
    private String clockId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cross_student_group_assignment", catalog = "school_control", joinColumns = { @JoinColumn(name = "id_student") },
	    inverseJoinColumns = { @JoinColumn(name = "id_group_assignment") })
    private Set<GroupAssignment> groups = new HashSet<GroupAssignment>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cross_sie_student", catalog = "school_control", joinColumns = { @JoinColumn(name = "id_student") },
	    inverseJoinColumns = { @JoinColumn(name = "cat_sie_group_id") })
    private Set<SieGroup> sies = new HashSet<SieGroup>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Score> scores = new HashSet<Score>();

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getEnrollNumber() {
	return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
	this.enrollNumber = enrollNumber;
    }

    public Date getEnrollmentDate() {
	return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
	this.enrollmentDate = enrollmentDate;
    }

    public Company getCompany() {
	return company;
    }

    public void setCompany(Company company) {
	this.company = company;
    }

    public StudentStatus getStudentStatus() {
	return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
	this.studentStatus = studentStatus;
    }

    public String getClockId() {
	return clockId;
    }

    public void setClockId(String clockId) {
	this.clockId = clockId;
    }

    public Set<GroupAssignment> getGroups() {
	return groups;
    }

    public void setGroups(Set<GroupAssignment> groups) {
	this.groups = groups;
    }

    @Override
    public String toString() {
	return "id:" + id + ";name:" + name + ";";
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof Student))
	    return false;

	Student oStudent = (Student) obj;
	return this.getId() == oStudent.getId();
    }

    public Set<SieGroup> getSies() {
	return sies;
    }

    public void setSies(Set<SieGroup> sies) {
	this.sies = sies;
    }

    public Set<Score> getScores() {
	return scores;
    }

    public void setScores(Set<Score> scores) {
	this.scores = scores;
    }

    @Override
    public int compareTo(Student o) {
	return Comparators.NAME.compare(this, o);
    }

    public static class Comparators {
	public static Comparator<Student> NAME = new Comparator<Student>() {

	    @Override
	    public int compare(Student o1, Student o2) {
		return o1.name.compareTo(o2.name);
	    }

	};

	public static Comparator<Student> ENROLLMENT = new Comparator<Student>() {

	    @Override
	    public int compare(Student o1, Student o2) {
		return o1.enrollNumber.compareTo(o2.enrollNumber);
	    }

	};

    }

}
