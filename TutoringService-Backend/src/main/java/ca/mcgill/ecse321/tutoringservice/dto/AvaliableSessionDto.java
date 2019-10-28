package ca.mcgill.ecse321.tutoringservice.dto;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;

import ca.mcgill.ecse321.tutoringservice.model.Tutor;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;


public class AvaliableSessionDto{
	private Integer avaliableSessionID;
	private Time startTime;
	private Time endTime;
	private Date day;
	private Set<TutorDto> tutor;
	private TutoringSystemDto tutoringSystem;

	public AvaliableSessionDto() {
		
	}


	public AvaliableSessionDto(Time startTime, Time endTime, Integer AvaliableSessionID, Date day, Set<TutorDto> tutors, TutoringSystemDto tutoringSystem) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.avaliableSessionID = AvaliableSessionID;
		this.day = day;
		this.tutor = tutors;
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

	public Set<TutorDto> getTutor() {
		return this.tutor;
	}

	public void setTutor(Set<TutorDto> tutors) {
		this.tutor = tutors;
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
