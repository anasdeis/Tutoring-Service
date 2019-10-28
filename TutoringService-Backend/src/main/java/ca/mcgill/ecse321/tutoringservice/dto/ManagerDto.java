package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class ManagerDto extends PersonDto{
	private Set<SubjectRequestDto> subjectRequest;
	private Set<ReviewDto> review;
	private Set<CommissionDto> commission;
	private Set<ClassroomDto> classroom;
	private LoginDto loginInfo;
	private TutoringSystemDto tutoringSystem;

	public ManagerDto() {
		
	}
		
	public ManagerDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer managerID, LoginDto login, TutoringSystemDto tutoringSystem) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(managerID);
		this.loginInfo = login;
		this.tutoringSystem = tutoringSystem;
	}
	
	public Set<SubjectRequestDto> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequestDto> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public Set<ReviewDto> getReview() {
		return this.review;
	}

	public void setReview(Set<ReviewDto> reviews) {
		this.review = reviews;
	}

	public Set<CommissionDto> getCommission() {
		return this.commission;
	}

	public void setCommission(Set<CommissionDto> commissions) {
		this.commission = commissions;
	}

	public Set<ClassroomDto> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<ClassroomDto> classrooms) {
		this.classroom = classrooms;
	}

}
