package ca.mcgill.ecse321.tutoringservice.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;

import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;

public class StudentDto extends Person{
	private Set<Offering> coursesTaken;
	private Set<SubjectRequest> subjectRequest;
	private Integer numCoursesEnrolled;

	public Set<Offering> getCoursesTaken() {
		return this.coursesTaken;
	}

	public void setCoursesTaken(Set<Offering> coursesTakens) {
		this.coursesTaken = coursesTakens;
	}

	public Set<SubjectRequest> getSubjectRequest() {
		return this.subjectRequest;
	}

	public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
		this.subjectRequest = subjectRequests;
	}

	public void setNumCoursesEnrolled(Integer value) {
		this.numCoursesEnrolled = value;
	}
	public Integer getNumCoursesEnrolled() {
		return this.numCoursesEnrolled;
	}
}
