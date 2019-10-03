package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Tutor extends Person {
   /**
    * <pre>
    *           1..1     1..2
    * Tutor ------------------------- TutorRole
    *           tutor        &gt;       tutorRole
    * </pre>
    */
   private Set<TutorRole> tutorRole;
   
   public Set<TutorRole> getTutorRole() {
      if (this.tutorRole == null) {
         this.tutorRole = new HashSet<TutorRole>();
      }
      return this.tutorRole;
   }
   
   }
