package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.List;
import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;



public class ClassroomDto{
	private String roomCode;
	private TutoringSystem tutoringSystem;
	private Set<Offering> offering;
	private Boolean isBooked;
	private Manager manager;
	private Boolean isBigRoom;

	public ClassroomDto() {
		
	}

	public ClassroomDto(String roomCode, Boolean isBooked, Boolean isBigRoom, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem){
		this.roomCode = roomCode;
		this.isBooked = isBooked;
		this.isBooked = isBigRoom;
		this.manager = manager;
		this.offering = offerings;
		this.tutoringSystem = tutoringSystem;
	}
	
	public void setRoomCode(String value) {
		this.roomCode = value;
	}

	public String getRoomCode() {
		return this.roomCode;
	}

	public Set<Offering> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<Offering> offerings) {
		this.offering = offerings;
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
	
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
