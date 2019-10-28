package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class TutoringSystemDto{
	private Set<SubjectRequestDto> subjectRequest;
	private Set<SubjectDto> subject;
	private Set<PersonDto> person;
	private Set<UniversityDto> university;
	private Set<TutorApplicationDto> tutorApplication;
	private Set<ReviewDto> review;
	private Set<AvaliableSessionDto> avaliableSession;
	private Set<ClassroomDto> classroom;
	private Set<CommissionDto> commission;
	private Integer tutoringSystemID;

	public TutoringSystemDto() {
		
	}
	
	public TutoringSystemDto(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

	public Set<SubjectRequestDto> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequestDto> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public Set<SubjectDto> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<SubjectDto> subjects) {
		this.subject = subjects;
	}

	public Set<PersonDto> getPerson() {
		return this.person;
	}

	public void setPerson(Set<PersonDto> persons) {
		this.person = persons;
	}

	public Set<UniversityDto> getUniversity() {
		return this.university;
	}

	public void setUniversity(Set<UniversityDto> universitys) {
		this.university = universitys;
	}

	public Set<TutorApplicationDto> getTutorApplication() {
		return this.tutorApplication;
	}

	public void setTutorApplication(Set<TutorApplicationDto> tutorApplications) {
		this.tutorApplication = tutorApplications;
	}

	public Set<ReviewDto> getReview() {
		return this.review;
	}

	public void setReview(Set<ReviewDto> reviews) {
		this.review = reviews;
	}

	public Set<AvaliableSessionDto> getAvaliableSession() {
		return this.avaliableSession;
	}

	public void setAvaliableSession(Set<AvaliableSessionDto> avaliableSessions) {
		this.avaliableSession = avaliableSessions;
	}

	public Set<ClassroomDto> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<ClassroomDto> classrooms) {
		this.classroom = classrooms;
	}

	public Set<CommissionDto> getCommission() {
		return this.commission;
	}

	public void setCommission(Set<CommissionDto> commissions) {
		this.commission = commissions;
	}

	public void setTutoringSystemID(Integer value) {
		this.tutoringSystemID = value;
	}
	public Integer getTutoringSystemID() {
		return this.tutoringSystemID;
	}
}

