package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;


public class AvaliableSessionDto{
	private Integer avaliableSessionID;
	private Time startTime;
	private Time endTime;
	private TutoringSystemDto tutoringSystem;
	private Date day;
	private Set<Integer> tutorIDs;


	public AvaliableSessionDto() {
		
	}


	public AvaliableSessionDto(Time startTime, Time endTime, Integer AvaliableSessionID, Date day, Set<Integer> tutorIDs, TutoringSystemDto tutoringSystem) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.avaliableSessionID = AvaliableSessionID;
		this.day = day;
		this.tutorIDs = tutorIDs;
		this.tutoringSystem = tutoringSystem;
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

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

	public void setAvaliableSessionID(Integer value) {
		this.avaliableSessionID = value;
	}

	public Integer getAvaliableSessionID() {
		return this.avaliableSessionID;
	}
}
