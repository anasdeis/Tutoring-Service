package ca.mcgill.ecse321.tutoringservice.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;

//import ca.mcgill.ecse321.tutoringservice.model.Login;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
//import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class StudentDto extends Person{
	//private Set<Offering> coursesTaken;
	//private Set<SubjectRequest> subjectRequest;
	private Integer numCoursesEnrolled;

	public StudentDto() {
		
	}

	public StudentDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber,
			Integer studentId, Integer numCoursesEnrolled) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(studentId);
		this.setNumCoursesEnrolled(numCoursesEnrolled);
	}

/*
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
*/
	public void setNumCoursesEnrolled(Integer value) {
		this.numCoursesEnrolled = value;
	}
	public Integer getNumCoursesEnrolled() {
		return this.numCoursesEnrolled;
	}
}
