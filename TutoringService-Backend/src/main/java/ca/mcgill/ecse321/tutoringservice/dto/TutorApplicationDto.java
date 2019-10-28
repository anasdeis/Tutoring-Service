package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class TutorApplicationDto{
private Integer applicationId;
private Boolean isAccepted;
private TutorDto tutor;
private Set<String> courseIDs;
private TutoringSystemDto tutoringSystem;

public TutorApplicationDto(Integer applicationId, Boolean isAccepted,  TutorDto tutor, Set<String> courseIDs, TutoringSystemDto tutoringSystem) {
	this.setApplicationId(applicationId);
	this.setIsAccepted(isAccepted);
	this.setTutor(tutor);
	this.setTutoringSystem(tutoringSystem);
	this.setSubject(courseIDs);
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
	
	public TutorDto getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
