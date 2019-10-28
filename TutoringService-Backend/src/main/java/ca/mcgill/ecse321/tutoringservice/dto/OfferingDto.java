package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class OfferingDto{
	private String offeringID;
	private String term;
	private double pricePerHour;
	private Set<Integer> classTimeIDs;
	private SubjectDto subject;
	private ClassroomDto classroom;
	private CommissionDto commission;
	private TutorDto tutor;
	private Set<Integer> reviewIDs;
	private Set<Integer> studentIDs;
	private TutoringSystemDto tutoringSystem;

	public OfferingDto() {
		
	}
	
	public OfferingDto(String offId, String term, double price, Set<Integer> classTimeIDs, SubjectDto subject, TutorDto tutor, CommissionDto commission, ClassroomDto classroom, Set<Integer> studentIDs, Set<Integer> reviewIDs, TutoringSystemDto tutoringSystem){
		this.offeringID = offId;
		this.term = term;
		this.pricePerHour = price;
		this.classTimeIDs = classTimeIDs;
		this.subject = subject;
		this.tutor = tutor;
		this.commission = commission;
		this.classroom = classroom;
		this.tutoringSystem = tutoringSystem;
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

 
  	public ClassroomDto getClassroom() {
		return this.classroom;
	}

	public void setClassroom(ClassroomDto classroom) {
		this.classroom = classroom;
	}
	
	public Set<Integer> getReview() {
		return this.reviewIDs;
	}

	public void setReview(Set<Integer> reviewIDs) {
		this.reviewIDs = reviewIDs;
	}

	public CommissionDto getCommission() {
		return this.commission;
	}

	public void setCommission(CommissionDto commission) {
		this.commission = commission;
	}

	public TutorDto getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}
	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
	
	public Set<Integer> getStudents() {
		return this.studentIDs;
	}

	public void setStudents(Set<Integer> studentIDs) {
		this.studentIDs = studentIDs;
	}

	public SubjectDto getSubject() {
		return this.subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}
	
}
