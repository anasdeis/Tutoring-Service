package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Classroom;
import ca.mcgill.ecse321.tutoringservice.model.Commission;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.Review;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;


public class ManagerDto extends Person{
	private Set<SubjectRequest> subjectRequest;
	private Set<Review> review;
	private Set<Commission> commission;
	private Set<Classroom> classroom;

	public Set<SubjectRequest> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}

	public Set<Commission> getCommission() {
		return this.commission;
	}

	public void setCommission(Set<Commission> commissions) {
		this.commission = commissions;
	}

	public Set<Classroom> getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Set<Classroom> classrooms) {
		this.classroom = classrooms;
	}

}
