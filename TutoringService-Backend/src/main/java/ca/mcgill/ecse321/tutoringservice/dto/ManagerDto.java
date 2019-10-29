package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class ManagerDto extends PersonDto{
	private Set<Integer> subjectRequestIDs;
	private Set<Integer> reviewIDs;
	private Set<Integer> commissionIDs;
	private Set<String> classRoomCodes;

	public ManagerDto() {
		
	}
		
	public ManagerDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer managerID, LoginDto login, Set<Integer> reviewIDs, Set<Integer> commissionIDs, Set<String> classRoomCodes,  Set<Integer> subjectRequestIDs, Integer tutoringSystem) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(managerID);
		this.setLoginInfo(login);
		this.setCommission(commissionIDs);
		this.setClassroom(classRoomCodes);
		this.setReview(reviewIDs);
		this.setSubjectRequest(subjectRequestIDs);
		this.setTutoringSystem(tutoringSystem);
	}
	
	public Set<Integer> getSubjectRequest() {
		return this.subjectRequestIDs;
	}

	public void setSubjectRequest(Set<Integer> subjectRequestIDs) {
		this.subjectRequestIDs = subjectRequestIDs;
	}

	public Set<Integer> getReview() {
		return this.reviewIDs;
	}

	public void setReview(Set<Integer> reviewIDs) {
		this.reviewIDs = reviewIDs;
	}

	public Set<Integer> getCommission() {
		return this.commissionIDs;
	}

	public void setCommission(Set<Integer> commissionIDs) {
		this.commissionIDs = commissionIDs;
	}

	public Set<String> getClassroom() {
		return this.classRoomCodes;
	}

	public void setClassroom(Set<String> classRoomCodes) {
		this.classRoomCodes = classRoomCodes;
	}

}
