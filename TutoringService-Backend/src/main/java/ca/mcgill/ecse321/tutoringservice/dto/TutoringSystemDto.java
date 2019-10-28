package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class TutoringSystemDto{
	private Integer tutoringSystemID;
	private Set<Integer> subjectRequestIDs;
	private Set<String> courseIDs;
	private Set<Integer> personIDs;
	private Set<String> universityNames;
	private Set<Integer> tutorApplicationIDs;
	private Set<Integer> reviewIDs;
	private Set<Integer> avaliableSessionIDs;
	private Set<String> classRoomCodes;
	private Set<Integer> commissionIDs;

	public TutoringSystemDto() {
		
	}
	
	public TutoringSystemDto(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}
	
	public TutoringSystemDto(Integer tutoringSystemID, Set<Integer> subjectRequestIDs,  Set<String> courseIDs, Set<Integer> personIDs, Set<String> universityNames,  Set<Integer> tutorApplicationIDs, Set<Integer> reviewIDs,  Set<Integer> avaliableSessionIDs, Set<String> classRoomCodes, Set<Integer> commissionIDs) {
		this.setTutoringSystemID(tutoringSystemID);
		this.setSubjectRequest(subjectRequestIDs);
		this.setSubject(courseIDs);
		this.setPerson(personIDs);
		this.setUniversity(universityNames);
		this.setTutorApplication(tutorApplicationIDs);
		this.setReview(reviewIDs);
		this.setAvaliableSession(avaliableSessionIDs);
		this.setClassroom(classRoomCodes);
		this.setCommission(commissionIDs);
	}

	public Set<Integer> getSubjectRequest() {
		return this.subjectRequestIDs;
	}

	public void setSubjectRequest(Set<Integer> subjectRequestIDs) {
		this.subjectRequestIDs = subjectRequestIDs;
	}

	public Set<String> getSubject() {
		return this.courseIDs;
	}

	public void setSubject(Set<String> courseIDs) {
		this.courseIDs = courseIDs;
	}

	public Set<Integer> getPerson() {
		return this.personIDs;
	}

	public void setPerson(Set<Integer> personIDs) {
		this.personIDs = personIDs;
	}

	public Set<String> getUniversity() {
		return this.universityNames;
	}

	public void setUniversity(Set<String> universityNames) {
		this.universityNames = universityNames;
	}

	public Set<Integer> getTutorApplication() {
		return this.tutorApplicationIDs;
	}

	public void setTutorApplication(Set<Integer> tutorApplicationIDs) {
		this.tutorApplicationIDs = tutorApplicationIDs;
	}

	public Set<Integer> getReview() {
		return this.reviewIDs;
	}

	public void setReview(Set<Integer> reviewIDs) {
		this.reviewIDs = reviewIDs;
	}

	public Set<Integer> getAvaliableSession() {
		return this.avaliableSessionIDs;
	}

	public void setAvaliableSession(Set<Integer> avaliableSessionIDs) {
		this.avaliableSessionIDs = avaliableSessionIDs;
	}

	public Set<String> getClassroom() {
		return this.classRoomCodes;
	}

	public void setClassroom(Set<String> classRoomCodes) {
		this.classRoomCodes = classRoomCodes;
	}

	public Set<Integer> getCommission() {
		return this.commissionIDs;
	}

	public void setCommission(Set<Integer> commissionIDs) {
		this.commissionIDs = commissionIDs;
	}

	public void setTutoringSystemID(Integer value) {
		this.tutoringSystemID = value;
	}
	public Integer getTutoringSystemID() {
		return this.tutoringSystemID;
	}
}

