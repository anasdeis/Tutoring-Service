package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class TutorApplication {
   private Integer applicationID;
   
   public void setApplicationID(Integer value) {
      this.applicationID = value;
   }
   
   public Integer getApplicationID() {
      return this.applicationID;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * TutorApplication ------------------------> Subject
    *           &gt;       subject
    * </pre>
    */
   private Set<Subject> subject;
   
   public Set<Subject> getSubject() {
      if (this.subject == null) {
         this.subject = new HashSet<Subject>();
      }
      return this.subject;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * TutorApplication ------------------------> Tutor
    *           &lt;       tutor
    * </pre>
    */
   private Tutor tutor;
   
   public void setTutor(Tutor value) {
      this.tutor = value;
   }
   
   public Tutor getTutor() {
      return this.tutor;
   }
   
   private Boolean isAccepted;
   
   public void setIsAccepted(Boolean value) {
      this.isAccepted = value;
   }
   
   public Boolean getIsAccepted() {
      return this.isAccepted;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * TutorApplication ------------------------- TutoringSystem
    *           tutorApplication        &lt;       tutoringSystem
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
