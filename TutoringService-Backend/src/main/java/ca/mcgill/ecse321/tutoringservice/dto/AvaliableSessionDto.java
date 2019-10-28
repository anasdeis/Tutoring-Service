package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;


public class AvaliableSessionDto{
	private Integer avaliableSessionID;
	private Time startTime;
	private Time endTime;
	private Date day;
	private Set<Integer> tutorIDs;
	private Integer tutoringSystemID;

	public AvaliableSessionDto() {
		
	}


	public AvaliableSessionDto(Time startTime, Time endTime, Integer AvaliableSessionID, Date day, Set<Integer> tutorIDs, Integer tutoringSystem) {
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setAvaliableSessionID(AvaliableSessionID);
		this.setDay(day);
		this.setTutor(tutorIDs);
		this.setTutoringSystem(tutoringSystem);
	}
	
	public void setStartTime(Time value) {
		this.startTime = value;
	}
	public Time getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Time value) {
		this.endTime = value;
	}
	public Time getEndTime() {
		return this.endTime;
	}

	public void setDay(Date value) {
		this.day = value;
	}
	public Date getDay() {
		return this.day;
	}

	public Set<Integer> getTutor() {
		return this.tutorIDs;
	}

	public void setTutor(Set<Integer> tutorIDs) {
		this.tutorIDs = tutorIDs;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

	public void setAvaliableSessionID(Integer value) {
		this.avaliableSessionID = value;
	}

	public Integer getAvaliableSessionID() {
		return this.avaliableSessionID;
	}
}
