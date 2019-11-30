package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offering{
	private String offeringID;

	public void setOfferingID(String value) {
		this.offeringID = value;
	}
	@Id
	public String getOfferingID() {
		return this.offeringID;
	}
	private Set<Student> studentsEnrolled;

	@ManyToMany
	public Set<Student> getStudentsEnrolled() {
		return this.studentsEnrolled;
	}

	public void setStudentsEnrolled(Set<Student> studentsEnrolleds) {
		this.studentsEnrolled = studentsEnrolleds;
	}

	private Subject subject;

	@ManyToOne(optional=false)
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	private String term;

	public void setTerm(String value) {
		this.term = value;
	}
	public String getTerm() {
		return this.term;
	}
	private double pricePerHour;

	public void setPricePerHour(double value) {
		this.pricePerHour = value;
	}
	public double getPricePerHour() {
		return this.pricePerHour;
	}
	private Classroom classroom;

	@ManyToOne(optional=false)
	public Classroom getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	private Set<AvailableSession> classTime;

	@ManyToMany
	public Set<AvailableSession> getClassTime() {
		return this.classTime;
	}

	public void setClassTime(Set<AvailableSession> classTimes) {
		this.classTime = classTimes;
	}

	private Set<Review> review;

	@OneToMany(mappedBy="offering" )
	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}

	private Commission commission;

	@ManyToOne(optional=false)
	public Commission getCommission() {
		return this.commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	private Tutor tutor;
	private TutoringSystem tutoringSystem;

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	@ManyToOne(optional=false)
	public TutoringSystem getTutoringSystem() {
	   return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
}
