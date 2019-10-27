package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.AvaliableSession;
import ca.mcgill.ecse321.tutoringservice.model.Classroom;
import ca.mcgill.ecse321.tutoringservice.model.Commission;
import ca.mcgill.ecse321.tutoringservice.model.Review;
import ca.mcgill.ecse321.tutoringservice.model.Student;
import ca.mcgill.ecse321.tutoringservice.model.Subject;
import ca.mcgill.ecse321.tutoringservice.model.Tutor;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class OfferingDto{
	private String offeringID;
	private Set<Student> studentsEnrolled;
	private Subject subject;
	private String term;
	private double pricePerHour;
	private Classroom classroom;
	private Set<AvaliableSession> classTime;
	private Set<Review> review;
	private Commission commission;
	private Tutor tutor;
	private TutoringSystem tutoringSystem;

	public OfferingDto() {
		
	}
	
	public OfferingDto(String offId, String term, double price, Set<AvaliableSession> classTime, Subject subject, Tutor tutor, Commission commission, Classroom classroom, TutoringSystem tutoringSystem){
		this.offeringID = offId;
		this.term = term;
		this.pricePerHour = price;
		this.classTime = classTime;
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

	public Set<Student> getStudentsEnrolled() {
		return this.studentsEnrolled;
	}

	public void setStudentsEnrolled(Set<Student> studentsEnrolleds) {
		this.studentsEnrolled = studentsEnrolleds;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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

	public Classroom getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Set<AvaliableSession> getClassTime() {
		return this.classTime;
	}

	public void setClassTime(Set<AvaliableSession> classTimes) {
		this.classTime = classTimes;
	}

	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}

	public Commission getCommission() {
		return this.commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}
	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
}
