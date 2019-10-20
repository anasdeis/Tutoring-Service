package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.AvaliableSession;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Person;
import ca.mcgill.ecse321.tutoringservice.model.TutorApplication;

public class TutorDto extends Person{
	private Set<TutorApplication> tutorApplication;
	private Boolean isRegistered;
	private Set<Offering> offering;
	private Set<AvaliableSession> avaliableSession;

	public Set<TutorApplication> getTutorApplication() {
		return this.tutorApplication;
	}

	public void setTutorApplication(Set<TutorApplication> tutorApplications) {
		this.tutorApplication = tutorApplications;
	}

	public void setIsRegistered(Boolean value) {
		this.isRegistered = value;
	}
	public Boolean getIsRegistered() {
		return this.isRegistered;
	}

	public Set<Offering> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<Offering> offerings) {
		this.offering = offerings;
	}

	public Set<AvaliableSession> getAvaliableSession() {
		return this.avaliableSession;
	}

	public void setAvaliableSession(Set<AvaliableSession> avaliableSessions) {
		this.avaliableSession = avaliableSessions;
	}

}
