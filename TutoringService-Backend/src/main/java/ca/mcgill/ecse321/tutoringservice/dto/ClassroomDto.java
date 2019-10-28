package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class ClassroomDto{
	private String roomCode;
	private Boolean isBooked;
	private ManagerDto manager;
	private Boolean isBigRoom;
	private Set<String> offeringIDs;
	private TutoringSystemDto tutoringSystem;

	public ClassroomDto() {
		
	}

	public ClassroomDto(String roomCode, Boolean isBooked, Boolean isBigRoom, ManagerDto manager, Set<String> offeringIDs, TutoringSystemDto tutoringSystem){
		this.roomCode = roomCode;
		this.isBooked = isBooked;
		this.isBigRoom = isBigRoom;
		this.manager = manager;
		this.offeringIDs = offeringIDs;
		this.tutoringSystem = tutoringSystem;
	}
	
	public void setRoomCode(String value) {
		this.roomCode = value;
	}

	public String getRoomCode() {
		return this.roomCode;
	}
	
	public Boolean getIsBooked() {
		return this.isBooked;
	}
	
	public void setIsBooked(Boolean value) {
		this.isBooked = value;
	}

	public Boolean getIsBigRoom() {
		return this.isBigRoom;
	}
	
	public void setIsBigRoom(Boolean value) {
		this.isBigRoom = value;
	}
	
	public Set<String> getOffering() {
		return this.offeringIDs;
	}

	public void setOffering(Set<String> offeringIDs) {
		this.offeringIDs = offeringIDs;
	}
	
	public ManagerDto getManager() {
		return this.manager;
	}

	public void setManager(ManagerDto manager) {
		this.manager = manager;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
}
