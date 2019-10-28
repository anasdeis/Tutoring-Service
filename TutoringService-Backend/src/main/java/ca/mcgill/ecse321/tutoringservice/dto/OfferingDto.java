package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class OfferingDto{
	private String offeringID;
	private Set<StudentDto> studentsEnrolled;
	private SubjectDto subject;
	private String term;
	private double pricePerHour;
	private ClassroomDto classroom;
	private Set<Integer> classTimeIDs;
	private Set<ReviewDto> review;
	private CommissionDto commission;
	private TutorDto tutor;
	private TutoringSystemDto tutoringSystem;

	public OfferingDto() {
		
	}
	
	public OfferingDto(String offId, String term, double price, Set<Integer> classTimeIDs, SubjectDto subject, TutorDto tutor, CommissionDto commission, ClassroomDto classroom, TutoringSystemDto tutoringSystem){
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
	
	public Set<ReviewDto> getReview() {
		return this.review;
	}

	public void setReview(Set<ReviewDto> reviews) {
		this.review = reviews;
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
	
		public Set<StudentDto> getStudentsEnrolled() {
		return this.studentsEnrolled;
	}

	public void setStudentsEnrolled(Set<StudentDto> studentsEnrolleds) {
		this.studentsEnrolled = studentsEnrolleds;
	}

	public SubjectDto getSubject() {
		return this.subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}
	
}
