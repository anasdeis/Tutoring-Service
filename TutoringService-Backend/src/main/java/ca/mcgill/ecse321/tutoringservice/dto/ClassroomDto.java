package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.dto.ManagerDto;
import ca.mcgill.ecse321.tutoringservice.dto.OfferingDto;
import ca.mcgill.ecse321.tutoringservice.dto.TutoringSystemDto;



public class ClassroomDto{
	private String roomCode;
	private TutoringSystemDto tutoringSystem;
	private Set<OfferingDto> offering;
	private Boolean isBooked;
	private ManagerDto manager;
	private Boolean isBigRoom;

	public ClassroomDto() {
		
	}

	public ClassroomDto(String roomCode, Boolean isBooked, Boolean isBigRoom, ManagerDto manager, Set<OfferingDto> offering, TutoringSystemDto tutoringSystem){
		this.roomCode = roomCode;
		this.isBooked = isBooked;
		this.isBigRoom = isBigRoom;
		this.manager = manager;
		this.offering = offering;
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
	
	public Set<OfferingDto> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<OfferingDto> offerings) {
		this.offering = offerings;
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
