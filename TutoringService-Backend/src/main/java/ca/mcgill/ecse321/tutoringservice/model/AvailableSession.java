package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class AvailableSession{
   /**
    * <pre>
    *           1..1     1..1
    * AvailableSession ------------------------> Time
    *           &lt;       startTime
    * </pre>
    */
   private Time startTime;
   
   public void setStartTime(Time value) {
      this.startTime = value;
   }
   
   public Time getStartTime() {
      return this.startTime;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * AvailableSession ------------------------> Time
    *           &lt;       endTime
    * </pre>
    */
   private Time endTime;
   
   public void setEndTime(Time value) {
      this.endTime = value;
   }
   
   public Time getEndTime() {
      return this.endTime;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * AvailableSession ------------------------> Date
    *           &lt;       day
    * </pre>
    */
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
   
   private Integer availableSessionID;

public void setAvailableSessionID(Integer value) {
    this.availableSessionID = value;
}
@Id
public Integer getAvailableSessionID() {
    return this.availableSessionID;
}
}
