package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class TutoringSystemDto{
	private Integer tutoringSystemID;
	private Set<SubjectRequestDto> subjectRequests;
	private Set<SubjectDto> subjects;
	private Set<PersonDto> persons;
	private Set<UniversityDto> universitys;
	private Set<TutorApplicationDto> tutorApplications;
	private Set<ReviewDto> reviews;
	private Set<AvaliableSessionDto> avaliableSessions;
	private Set<ClassroomDto> classrooms;
	private Set<CommissionDto> commissions;

	public TutoringSystemDto() {
		
	}
	
	public TutoringSystemDto(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}
	
	public TutoringSystemDto(Integer tutoringSystemID, Set<SubjectRequestDto> subjectRequest,  Set<SubjectDto> subject, Set<PersonDto> person, Set<UniversityDto> university,  Set<TutorApplicationDto> tutorApplication, Set<ReviewDto> review,  Set<AvaliableSessionDto> avaliableSession, Set<ClassroomDto>classroom, Set<CommissionDto> commission) {
		this.tutoringSystemID = tutoringSystemID;
		this.subjectRequests = subjectRequest;
		this.subjects = subject;
		this.persons = person;
		this.universitys = university;
		this.tutorApplications = tutorApplication;
		this.reviews = review;
		this.avaliableSessions = avaliableSession;
		this.classrooms = classroom;
		this.commissions = commission;
	}

	public Set<SubjectRequestDto> getSubjectRequest() {
		return this.subjectRequests;
	}

	public void setSubjectRequest(Set<SubjectRequestDto> subjectRequests) {
		this.subjectRequests = subjectRequests;
	}

	public Set<SubjectDto> getSubject() {
		return this.subjects;
	}

	public void setSubject(Set<SubjectDto> subjects) {
		this.subjects = subjects;
	}

	public Set<PersonDto> getPerson() {
		return this.persons;
	}

	public void setPerson(Set<PersonDto> persons) {
		this.persons = persons;
	}

	public Set<UniversityDto> getUniversity() {
		return this.universitys;
	}

	public void setUniversity(Set<UniversityDto> universitys) {
		this.universitys = universitys;
	}

	public Set<TutorApplicationDto> getTutorApplication() {
		return this.tutorApplications;
	}

	public void setTutorApplication(Set<TutorApplicationDto> tutorApplications) {
		this.tutorApplications = tutorApplications;
	}

	public Set<ReviewDto> getReview() {
		return this.reviews;
	}

	public void setReview(Set<ReviewDto> reviews) {
		this.reviews = reviews;
	}

	public Set<AvaliableSessionDto> getAvaliableSession() {
		return this.avaliableSessions;
	}

	public void setAvaliableSession(Set<AvaliableSessionDto> avaliableSessions) {
		this.avaliableSessions = avaliableSessions;
	}

	public Set<ClassroomDto> getClassroom() {
		return this.classrooms;
	}

	public void setClassroom(Set<ClassroomDto> classrooms) {
		this.classrooms = classrooms;
	}

	public Set<CommissionDto> getCommission() {
		return this.commissions;
	}

	public void setCommission(Set<CommissionDto> commissions) {
		this.commissions = commissions;
	}

	public void setTutoringSystemID(Integer value) {
		this.tutoringSystemID = value;
	}
	public Integer getTutoringSystemID() {
		return this.tutoringSystemID;
	}
}

