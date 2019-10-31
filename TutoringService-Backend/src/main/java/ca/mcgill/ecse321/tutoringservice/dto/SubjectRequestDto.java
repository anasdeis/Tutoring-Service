package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class SubjectRequestDto{	
	private Integer requestID;
	private String name;
	private String description;
	private String subjectType; 
	private Integer managerID;
	private Set<Integer> studentIDs;
	private Integer tutoringSystemID;

	public SubjectRequestDto(Integer requestID, String name, String description, String subjectType, Integer manager, Set<Integer> studentIDs, Integer tutoringSystem) {
		this.setRequestID(requestID);            //Integer
		this.setName(name);				         //String
		this.setDescription(description);        //String
		this.setManager(manager);                //Integer
		this.setTutoringSystem(tutoringSystem);  //Integer
		this.setSubjectType(subjectType);        //String
		this.setStudent(studentIDs);
	}
	
	
	public Set<Integer> getStudent() {
		return this.studentIDs;
	}

	public void setStudent(Set<Integer> studentIDs) {
		this.studentIDs = studentIDs;
	}

	public Integer getManager() {
		return this.managerID;
	}

	public void setManager(Integer managerID) {
		this.managerID = managerID;
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

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

}
