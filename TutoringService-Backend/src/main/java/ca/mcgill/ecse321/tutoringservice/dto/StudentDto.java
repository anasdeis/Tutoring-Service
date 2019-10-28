package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class StudentDto extends PersonDto{
	private Integer numCoursesEnrolled;
	private Set<String> coursesTakenIDs;
	private Set<Integer> subjectRequestIDs;
	
	public StudentDto() {
		
	}

	public StudentDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer studentId, Integer numCoursesEnrolled, LoginDto loginInfo, Set<String> coursesTakenIDs, Set<Integer> subjectRequestIDs, Integer tutoringSystem) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(studentId);
		this.setNumCoursesEnrolled(numCoursesEnrolled);
		this.setLoginInfo(loginInfo);
		this.setTutoringSystem(tutoringSystem);
		this.setCoursesTaken(coursesTakenIDs);
		this.setSubjectRequest(subjectRequestIDs);
	}

	public Set<String> getCoursesTaken() {
		return this.coursesTakenIDs;
	}

	public void setCoursesTaken(Set<String> coursesTakenIDs) {
		this.coursesTakenIDs = coursesTakenIDs;
	}

	public Set<Integer> getSubjectRequest() {
		return this.subjectRequestIDs;
	}

	public void setSubjectRequest(Set<Integer> subjectRequestIDs) {
		this.subjectRequestIDs = subjectRequestIDs;
	}

	public void setNumCoursesEnrolled(Integer value) {
		this.numCoursesEnrolled = value;
	}
	public Integer getNumCoursesEnrolled() {
		return this.numCoursesEnrolled;
	}
}
