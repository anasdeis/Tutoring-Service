package ca.mcgill.ecse321.tutoringservice.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;

import ca.mcgill.ecse321.tutoringservice.model.Login;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class StudentDto extends Person{
	private Set<Offering> coursesTaken;
	private Set<SubjectRequest> subjectRequest;
	private Integer numCoursesEnrolled;

	public StudentDto() {
		
	}
	
	public StudentDto(String first, String last, Date dob, String email, Integer phone, Integer studentID, Integer numCoursesEnrolled, Login loginInfo, TutoringSystem tutoringSystem) {
		this.numCoursesEnrolled = numCoursesEnrolled;
		this.setFirstName(first);
		this.setLastName(last);
		this.setDateOfBirth(dob);
		this.setEmail(email);
		this.setPhoneNumber(phone);
		this.setPersonId(studentID);
		this.setLoginInfo(loginInfo);
		this.setTutoringSystem(tutoringSystem);
	}
	
	public Set<Offering> getCoursesTaken() {
		return this.coursesTaken;
	}

	public void setCoursesTaken(Set<Offering> coursesTakens) {
		this.coursesTaken = coursesTakens;
	}

	public Set<SubjectRequest> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public void setNumCoursesEnrolled(Integer value) {
		this.numCoursesEnrolled = value;
	}
	public Integer getNumCoursesEnrolled() {
		return this.numCoursesEnrolled;
	}
}
