package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Subject;
//import ca.mcgill.ecse321.tutoringservice.model.Tutor;
//import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class TutorApplicationDto{
private Integer applicationId;
private Set<Subject> subject;
//private Tutor tutor;
private Boolean isAccepted;
//private TutoringSystem tutoringSystem;

public TutorApplicationDto(Integer applicationId, Boolean isAccepted) {
	this.applicationId = applicationId;
	this.isAccepted = isAccepted;
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
	
/*	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
*/
}
