package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class TutorDto extends PersonDto{
	private Set<TutorApplicationDto> tutorApplications;
	private Boolean isRegistered;
	private Set<OfferingDto> offerings;
	private Set<AvaliableSessionDto> avaliableSession;

	public TutorDto() {
	}
	
	public TutorDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer tutorID, Boolean isRegistered,  LoginDto login, Set<TutorApplicationDto> tutorApplications, Set<OfferingDto> offerings, TutoringSystemDto tutoringSystem) {
		this.isRegistered = isRegistered;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(tutorID);
		this.setLoginInfo(login);
		this.tutorApplications = tutorApplications;
		this.offerings = offerings;
		this.setTutoringSystem(tutoringSystem);
	}
	
	public void setIsRegistered(Boolean value) {
		this.isRegistered = value;
	}
	public Boolean getIsRegistered() {
		return this.isRegistered;
	}
	
	public Set<TutorApplicationDto> getTutorApplication() {
		return this.tutorApplications;
	}

	public void setTutorApplication(Set<TutorApplicationDto> tutorApplications) {
		this.tutorApplications = tutorApplications;
	}

	public Set<OfferingDto> getOffering() {
		return this.offerings;
	}

	public void setOffering(Set<OfferingDto> offerings) {
		this.offerings = offerings;
	}

	public Set<AvaliableSessionDto> getAvaliableSession() {
		return this.avaliableSession;
	}

	public void setAvaliableSession(Set<AvaliableSessionDto> avaliableSessions) {
		this.avaliableSession = avaliableSessions;
	}
	
}
