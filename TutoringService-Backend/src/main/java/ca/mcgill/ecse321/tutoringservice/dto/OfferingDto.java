package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class OfferingDto{
	private String offeringID;
	private String term;
	private double pricePerHour;
	private Set<Integer> classTimeIDs;
	private String courseID;
	private String roomCode;
	private Integer commissionID;
	private Integer tutorID;
	private Set<Integer> reviewIDs;
	private Set<Integer> studentIDs;
	private Integer tutoringSystemID;

	public OfferingDto() {
		
	}
	
	public OfferingDto(String offId, String term, double price, Set<Integer> classTimeIDs, String subject, Integer tutor, Integer commission, String classroom, Set<Integer> studentIDs, Set<Integer> reviewIDs, Integer tutoringSystem){
		this.setOfferingID(offId);
		this.setTerm(term);
		this.setPricePerHour(pricePerHour);
		this.setClassTime(classTimeIDs);
		this.setSubject(subject);
		this.setTutor(tutor);
		this.setCommission(commission);
		this.setClassroom(classroom);
		this.setTutoringSystem(tutoringSystem);
		this.setStudents(studentIDs);
		this.setReview(reviewIDs);
	}
	
	public void setOfferingID(String value) {
		this.offeringID = value;
	}
	public String getOfferingID() {
		return this.offeringID;
	}

	public void setTerm(String value) {
		this.term = value;
	}
	public String getTerm() {
		return this.term;
	}

	public void setPricePerHour(double value) {
		this.pricePerHour = value;
	}
	public double getPricePerHour() {
		return this.pricePerHour;
	}

	public Set<Integer> getClassTime() {
		return this.classTimeIDs;
	}

	public void setClassTime(Set<Integer> classTimeIDs) {
		this.classTimeIDs = classTimeIDs;
	}

 
  	public String getClassroom() {
		return this.roomCode;
	}

	public void setClassroom(String roomCode) {
		this.roomCode = roomCode;
	}
	
	public Set<Integer> getReview() {
		return this.reviewIDs;
	}

	public void setReview(Set<Integer> reviewIDs) {
		this.reviewIDs = reviewIDs;
	}

	public Integer getCommission() {
		return this.commissionID;
	}

	public void setCommission(Integer commissionID) {
		this.commissionID = commissionID;
	}

	public Integer getTutor() {
		return this.tutorID;
	}

	public void setTutor(Integer tutorID) {
		this.tutorID = tutorID;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}
	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}
	
	public Set<Integer> getStudents() {
		return this.studentIDs;
	}

	public void setStudents(Set<Integer> studentIDs) {
		this.studentIDs = studentIDs;
	}

	public String getSubject() {
		return this.courseID;
	}

	public void setSubject(String courseID) {
		this.courseID = courseID;
	}
	
}
