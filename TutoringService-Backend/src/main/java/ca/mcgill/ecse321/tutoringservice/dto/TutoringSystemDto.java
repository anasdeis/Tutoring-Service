package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.AvaliableSession;
import ca.mcgill.ecse321.tutoringservice.model.Classroom;
import ca.mcgill.ecse321.tutoringservice.model.Commission;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.Review;
import ca.mcgill.ecse321.tutoringservice.model.Subject;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
import ca.mcgill.ecse321.tutoringservice.model.TutorApplication;
import ca.mcgill.ecse321.tutoringservice.model.University;


public class TutoringSystemDto{
	private Set<SubjectRequest> subjectRequest;
	private Set<Subject> subject;
	private Set<Person> person;
	private Set<University> university;
	private Set<TutorApplication> tutorApplication;
	private Set<Review> review;
	private Set<AvaliableSession> avaliableSession;
	private Set<Classroom> classroom;
	private Set<Commission> commission;
	private Integer tutoringSystemID;

	public Set<SubjectRequest> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
	}

	public Set<Person> getPerson() {
		return this.person;
	}

	public void setPerson(Set<Person> persons) {
		this.person = persons;
	}

	public Set<University> getUniversity() {
		return this.university;
	}

	public void setUniversity(Set<University> universitys) {
		this.university = universitys;
	}

	public Set<TutorApplication> getTutorApplication() {
		return this.tutorApplication;
	}

	public void setTutorApplication(Set<TutorApplication> tutorApplications) {
		this.tutorApplication = tutorApplications;
	}

	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}

	public Set<AvaliableSession> getAvaliableSession() {
		return this.avaliableSession;
	}

	public void setAvaliableSession(Set<AvaliableSession> avaliableSessions) {
		this.avaliableSession = avaliableSessions;
	}

	public Set<Classroom> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<Classroom> classrooms) {
		this.classroom = classrooms;
	}

	public Set<Commission> getCommission() {
		return this.commission;
	}

	public void setCommission(Set<Commission> commissions) {
		this.commission = commissions;
	}


	public void setTutoringSystemID(Integer value) {
		this.tutoringSystemID = value;
	}
	public Integer getTutoringSystemID() {
		return this.tutoringSystemID;
	}
}
