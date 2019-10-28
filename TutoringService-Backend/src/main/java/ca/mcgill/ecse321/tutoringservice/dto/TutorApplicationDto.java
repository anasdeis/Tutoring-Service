package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Subject;
import ca.mcgill.ecse321.tutoringservice.dto.TutorDto;
import ca.mcgill.ecse321.tutoringservice.dto.TutoringSystemDto;

public class TutorApplicationDto{
private Integer applicationId;
private Set<Subject> subject;
private TutorDto tutor;
private Boolean isAccepted;
private TutoringSystemDto tutoringSystem;

public TutorApplicationDto(Integer applicationId, Boolean isAccepted,  TutorDto tutor, TutoringSystemDto tutoringSystem) {
	this.applicationId = applicationId;
	this.isAccepted = isAccepted;
	this.tutor = tutor;
	this.tutoringSystem = tutoringSystem;
}
	
	public void setApplicationId(Integer value) {
		this.applicationId = value;
	}
	public Integer getApplicationId() {
		return this.applicationId;
	}

	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
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
