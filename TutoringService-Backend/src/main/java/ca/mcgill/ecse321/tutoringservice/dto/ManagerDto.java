package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

//import ca.mcgill.ecse321.tutoringservice.model.Classroom;
//import ca.mcgill.ecse321.tutoringservice.model.Commission;
//import ca.mcgill.ecse321.tutoringservice.model.Login;
import ca.mcgill.ecse321.tutoringservice.model.Person;
//import ca.mcgill.ecse321.tutoringservice.model.Review;
//import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
//import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;


public class ManagerDto extends Person{
	//private Set<SubjectRequest> subjectRequest;
	//private Set<Review> review;
	//private Set<Commission> commission;
	//private Set<Classroom> classroom;

	public ManagerDto() {
		
	}
		
	public ManagerDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer managerID) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(managerID);
	}
/*	
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
*/
}
