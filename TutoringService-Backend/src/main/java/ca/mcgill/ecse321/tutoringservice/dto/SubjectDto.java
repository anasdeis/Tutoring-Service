package ca.mcgill.ecse321.tutoringservice.dto;


import java.util.Set;

public class SubjectDto{
	private String name;
	private String courseID;
	private String description;
	private String subjectType;
	private String universityName;
	private Set<Integer> tutorApplicationIDs;
	private Set<String> offeringIDs;
	private Integer tutoringSystemID;

	public SubjectDto() {
		
	}
	
	public SubjectDto(String name, String courseID, String description, String subjectType, String universityName, Set<Integer> tutorApplicationIDs, Set<String> offeringIDs, Integer tutoringSystemID) {
		this.setName(name);
		this.setCourseID(courseID);
		this.setDescription(description);
		this.setSubjectType(subjectType);
		this.setUniversity(universityName);
		this.setOffering(offeringIDs);
		this.setTutorRole(tutorApplicationIDs);
		this.setTutoringSystem(tutoringSystemID);
	}
	
	public Integer getTutoringSystem() {
		 return this.tutoringSystemID;
	}
		   
	public void setTutoringSystem(Integer tutoringSystem) {
		 this.tutoringSystemID = tutoringSystem;
	}
	
	public Set<String> getOffering() {
		return this.offeringIDs;
    }

	public void setOffering(Set<String> offeringIDs) {
		this.offeringIDs = offeringIDs;
	}
	
    public Set<Integer> getTutorRole() {
	    return this.tutorApplicationIDs;
	}
	   
	public void setTutorRole(Set<Integer> tutorApplicationIDs) {
	    this.tutorApplicationIDs = tutorApplicationIDs;
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

	public String getUniversity() {
		return this.universityName;
	}

	public void setUniversity(String university) {
		this.universityName = university;
	}

}
