package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class RegisteredTutor extends TutorRole {
/**
    * <pre>
    *           0..*     0..*
    * RegisteredTutor ------------------------- AvaliableSession
    *           registeredTutor        &gt;       avaliableSession
    * </pre>
    */
   private Set<AvaliableSession> avaliableSession;
   
   public Set<AvaliableSession> getAvaliableSession() {
      if (this.avaliableSession == null) {
         this.avaliableSession = new HashSet<AvaliableSession>();
      }
      return this.avaliableSession;
   }
   
   /**
    * <pre>
    *           0..*     1..*
    * RegisteredTutor ------------------------- Subject
    *           subjectTutors        &lt;       subjectsTaught
    * </pre>
    */
   private Set<Subject> subjectsTaught;
   
   public Set<Subject> getSubjectsTaught() {
      if (this.subjectsTaught == null) {
         this.subjectsTaught = new HashSet<Subject>();
      }
      return this.subjectsTaught;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * RegisteredTutor ------------------------- Offering
    *           offeringTutors        &gt;       offeringsTaught
    * </pre>
    */
   private Set<Offering> offeringsTaught;
   
   public Set<Offering> getOfferingsTaught() {
      if (this.offeringsTaught == null) {
         this.offeringsTaught = new HashSet<Offering>();
      }
      return this.offeringsTaught;
   }
   
   }
