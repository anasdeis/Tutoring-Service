package ca.mcgill.ecse321.tutoringservice.model;


public abstract class TutorRole {
   /**
    * <pre>
    *           1..2     1..1
    * TutorRole ------------------------- Tutor
    *           tutorRole        &lt;       tutor
    * </pre>
    */
   private Tutor tutor;
   
   public void setTutor(Tutor value) {
      this.tutor = value;
   }
   
   public Tutor getTutor() {
      return this.tutor;
   }
   
   }
