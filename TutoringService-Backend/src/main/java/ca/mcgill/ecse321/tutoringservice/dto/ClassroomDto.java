package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class ClassroomDto{
	private String roomCode;
	private Boolean isBooked;
	private Integer managerID;
	private Boolean isBigRoom;
	private Set<String> offeringIDs;
	private Integer tutoringSystemID;

	public ClassroomDto() {
		
	}

	public ClassroomDto(String roomCode, Boolean isBooked, Boolean isBigRoom, Integer manager, Set<String> offeringIDs, Integer tutoringSystem){
		this.setRoomCode(roomCode);
		this.setIsBooked(isBooked);
		this.setIsBigRoom(isBigRoom);
		this.setManager(manager);
		this.setOffering(offeringIDs);
		this.setTutoringSystem(tutoringSystem);
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
	
	public Integer getManager() {
		return this.managerID;
	}

	public void setManager(Integer managerID) {
		this.managerID = managerID;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}
}
