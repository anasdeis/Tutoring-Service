package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class TutorApplicationDto{
private Integer applicationId;
private Boolean isAccepted;
private Integer tutorID;
private Set<String> courseIDs;
private Integer tutoringSystemID;

public TutorApplicationDto() {
	
}

public TutorApplicationDto(Integer applicationId, Boolean isAccepted,  Integer tutor, Set<String> courseIDs, Integer tutoringSystem) {
	this.setApplicationId(applicationId);
	this.setIsAccepted(isAccepted);
	this.setTutor(tutor);
	this.setSubject(courseIDs);
	this.setTutoringSystem(tutoringSystem);
}
	
	public void setApplicationId(Integer value) {
		this.applicationId = value;
	}
	public Integer getApplicationId() {
		return this.applicationId;
	}

	public Set<String> getSubject() {
		return this.courseIDs;
	}

	public void setSubject(Set<String> courseIDs) {
		this.courseIDs = courseIDs;
	}

	public void setIsAccepted(Boolean value) {
		this.isAccepted = value;
	}
	public Boolean getIsAccepted() {
		return this.isAccepted;
	}
	
	public Integer getTutor() {
		return this.tutorID;
	}

	public void setTutor(Integer tutorID) {
		this.tutorID = tutorID;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

}
