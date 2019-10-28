package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class StudentDto extends PersonDto{
	private Set<OfferingDto> coursesTaken;
	private Set<SubjectRequestDto> subjectRequest;
	private Integer numCoursesEnrolled;

	public StudentDto() {
		
	}

	public StudentDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer studentId, Integer numCoursesEnrolled, LoginDto loginInfo, TutoringSystemDto tutoringSystem) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(studentId);
		this.setNumCoursesEnrolled(numCoursesEnrolled);
		this.setLoginInfo(loginInfo);
		this.setTutoringSystem(tutoringSystem);
	}


	public Set<OfferingDto> getCoursesTaken() {
		return this.coursesTaken;
	}

	public void setCoursesTaken(Set<OfferingDto> coursesTakens) {
		this.coursesTaken = coursesTakens;
	}

	public Set<SubjectRequestDto> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequestDto> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public void setNumCoursesEnrolled(Integer value) {
		this.numCoursesEnrolled = value;
	}
	public Integer getNumCoursesEnrolled() {
		return this.numCoursesEnrolled;
	}
}
