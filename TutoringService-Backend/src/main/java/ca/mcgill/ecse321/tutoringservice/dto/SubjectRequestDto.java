package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Student;
import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class SubjectRequestDto{
	private Set<Student> student;
	private Manager manager;
	private Integer requestID;
	private String name;
	private String description;
	private SubjectType subjectType;
	private TutoringSystem tutoringSystem;

	public SubjectRequestDto() {
		
	}
	
	
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void setRequestID(Integer value) {
		this.requestID = value;
	}
	public Integer getRequestID() {
		return this.requestID;
	}

	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
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

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
