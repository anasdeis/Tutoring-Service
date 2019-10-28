package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.SubjectType;

public class SubjectRequestDto{
	private Integer requestID;
	private String name;
	private String description;
	private SubjectType subjectType;
	private ManagerDto manager;
	private Set<StudentDto> student;
	private TutoringSystemDto tutoringSystem;

	public SubjectRequestDto() {
		
	}
	
	
	public Set<StudentDto> getStudent() {
		return this.student;
	}

	public void setStudent(Set<StudentDto> students) {
		this.student = students;
	}

	public ManagerDto getManager() {
		return this.manager;
	}

	public void setManager(ManagerDto manager) {
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

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
