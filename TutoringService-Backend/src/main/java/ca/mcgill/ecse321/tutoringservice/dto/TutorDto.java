package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Date;
import java.util.Set;

public class TutorDto extends PersonDto{
	private Boolean isRegistered;
	private Set<Integer> tutorApplicationIDs;
	private Set<String> offeringIDs;
	private Set<Integer> avaliableSessionIDs;

	public TutorDto() {
	}
	
	public TutorDto(String firstName, String lastName, Date dateOfBirth, String email, Integer phoneNumber, Integer tutorID, Boolean isRegistered,  LoginDto login, Set<Integer> tutorApplicationIDs, Set<String> offeringIDs, Set<Integer> avaliableSessionIDs, Integer tutoringSystem) {
		this.setIsRegistered(isRegistered);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateOfBirth(dateOfBirth);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPersonId(tutorID);
		this.setLoginInfo(login);
		this.setTutorApplication(tutorApplicationIDs);
		this.setOffering(offeringIDs);
		this.setAvaliableSession(avaliableSessionIDs);
		this.setTutoringSystem(tutoringSystem);
	}
	
	public void setIsRegistered(Boolean value) {
		this.isRegistered = value;
	}
	public Boolean getIsRegistered() {
		return this.isRegistered;
	}
	
	public Set<Integer> getTutorApplication() {
		return this.tutorApplicationIDs;
	}

	public void setTutorApplication(Set<Integer> tutorApplicationIDs) {
		this.tutorApplicationIDs = tutorApplicationIDs;
	}

	public Set<String> getOffering() {
		return this.offeringIDs;
	}

	public void setOffering(Set<String> offeringIDs) {
		this.offeringIDs = offeringIDs;
	}

	public Set<Integer> getAvaliableSession() {
		return this.avaliableSessionIDs;
	}

	public void setAvaliableSession(Set<Integer> avaliableSessionIDs) {
		this.avaliableSessionIDs = avaliableSessionIDs;
	}
	
}
