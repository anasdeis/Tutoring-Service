package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.TutorApplication;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;
import ca.mcgill.ecse321.tutoringservice.model.University;


public class SubjectDto{
	private String name;
	private String courseID;
	private Set<Offering> offering;
	private String description;
	private SubjectType subjectType;
	private University university;
	private Set<TutorApplication> tutorRole;
	private TutoringSystem tutoringSystem;

	public SubjectDto() {
		
	}
	
	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}

	public void setCourseID(String value) {
		this.courseID = value;
	}
	public String getCourseID() {
		return this.courseID;
	}

	public Set<Offering> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<Offering> offerings) {
		this.offering = offerings;
	}

	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}

	public void setSubjectType(SubjectType value) {
		this.subjectType = value;
	}
	public SubjectType getSubjectType() {
		return this.subjectType;
	}

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Set<TutorApplication> getTutorRole() {
		return this.tutorRole;
	}

	public void setTutorRole(Set<TutorApplication> tutorRoles) {
		this.tutorRole = tutorRoles;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
