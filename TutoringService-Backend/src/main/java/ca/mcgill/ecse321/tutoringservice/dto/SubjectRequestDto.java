package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class SubjectRequestDto{
	private Set<Integer> studentIDs;    //NI
	private ManagerDto manager;
	private Integer requestID;
	private String name;
	private String description;
	private SubjectType subjectType;    //NI
	private TutoringSystemDto tutoringSystem;

	public SubjectRequestDto(Integer requestID, String name, String description, ManagerDto manager, TutoringSystemDto tutoringSystem) {
		this.requestID = requestID;
		this.name = name;
		this.description = description;
		this.manager = manager;
		this.tutoringSystem = tutoringSystem;
	}
	
	
	public Set<Integer> getStudent() {
		return this.studentIDs;
	}

	public void setStudent(Set<Integer> students) {
		this.studentIDs = students;
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
