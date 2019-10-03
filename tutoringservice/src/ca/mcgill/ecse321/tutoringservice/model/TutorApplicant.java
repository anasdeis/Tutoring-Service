package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class TutorApplicant extends TutorRole {
   /**
    * <pre>
    *           1..1     1..*
    * TutorApplicant ------------------------> Subject
    *           tutorApplicant        &gt;       subject
    * </pre>
    */
   private Set<Subject> subject;
   
   public Set<Subject> getSubject() {
      if (this.subject == null) {
         this.subject = new HashSet<Subject>();
      }
      return this.subject;
   }
   
   }
