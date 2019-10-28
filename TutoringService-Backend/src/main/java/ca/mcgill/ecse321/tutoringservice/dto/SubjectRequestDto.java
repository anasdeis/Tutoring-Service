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
	private String subjectType;   

	private TutoringSystemDto tutoringSystem;

	public SubjectRequestDto(Integer requestID, String name, String description, String subjectType, ManagerDto manager, TutoringSystemDto tutoringSystem) {
		this.requestID = requestID;              //Integer
		this.name = name;					     //String
		this.description = description;          //String
		this.manager = manager;                  //DTO
		this.tutoringSystem = tutoringSystem;    //DTO
		this.subjectType = subjectType;          //String
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

	public void setSubjectType(String value) {
		this.subjectType = value;
	}
	public String getSubjectType() {
		return this.subjectType;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
