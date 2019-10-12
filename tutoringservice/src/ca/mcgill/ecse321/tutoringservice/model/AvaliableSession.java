package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class AvaliableSession {
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
   
   /**
    * <pre>
    *           1..1     1..*
    * AvaliableSession ------------------------> Tutor
    *           &lt;       tutor
    * </pre>
    */
   private Set<Tutor> tutor;
   
   public Set<Tutor> getTutor() {
      if (this.tutor == null) {
         this.tutor = new HashSet<Tutor>();
      }
      return this.tutor;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * AvaliableSession ------------------------- TutoringSystem
    *           avaliableSession        &lt;       tutoringSystem
    * </pre>
    */
   private TutoringSystem tutoringSystem;
   
   public void setTutoringSystem(TutoringSystem value) {
      this.tutoringSystem = value;
   }
   
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   }
