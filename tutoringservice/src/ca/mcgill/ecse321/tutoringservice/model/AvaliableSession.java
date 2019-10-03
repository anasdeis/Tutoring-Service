package ca.mcgill.ecse321.tutoringservice.model;
import java.util.HashSet;
import java.util.Set;


public class AvaliableSession {
/**
    * <pre>
    *           0..*     0..*
    * AvaliableSession ------------------------- RegisteredTutor
    *           avaliableSession        &lt;       registeredTutor
    * </pre>
    */
   private Set<RegisteredTutor> registeredTutor;
   
   public Set<RegisteredTutor> getRegisteredTutor() {
      if (this.registeredTutor == null) {
         this.registeredTutor = new HashSet<RegisteredTutor>();
      }
      return this.registeredTutor;
   }
   
   private String startTime;
   
   public void setStartTime(String value) {
      this.startTime = value;
   }
   
   public String getStartTime() {
      return this.startTime;
   }
   
   private String endTime;
   
   public void setEndTime(String value) {
      this.endTime = value;
   }
   
   public String getEndTime() {
      return this.endTime;
   }
   
   }
