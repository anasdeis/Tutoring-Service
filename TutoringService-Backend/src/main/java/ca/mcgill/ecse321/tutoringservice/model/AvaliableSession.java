package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.sql.Time;
import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class AvaliableSession{
   private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
private Time endTime;

public void setEndTime(Time value) {
    this.endTime = value;
}
public Time getEndTime() {
    return this.endTime;
}
private Date day;

public void setDay(Date value) {
    this.day = value;
}
public Date getDay() {
    return this.day;
}
private Set<Tutor> tutor;

@ManyToMany
public Set<Tutor> getTutor() {
   return this.tutor;
}

public void setTutor(Set<Tutor> tutors) {
   this.tutor = tutors;
}

private TutoringSystem tutoringSystem;

@ManyToOne(optional=false)
public TutoringSystem getTutoringSystem() {
   return this.tutoringSystem;
}

public void setTutoringSystem(TutoringSystem tutoringSystem) {
   this.tutoringSystem = tutoringSystem;
}

private Integer avaliableSessionID;

@Id
public Integer getAvaliableSessionID() {
    return this.avaliableSessionID;
}

public void setAvaliableSessionID(Integer value) {
    this.avaliableSessionID = value;
}
}
