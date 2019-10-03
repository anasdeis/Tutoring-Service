package ca.mcgill.ecse321.tutoringservice.model;

import ca.mcgill.ecse321.tutoringservice.model.TutorApplicant;
import java.util.Set;
import java.util.HashSet;
import ca.mcgill.ecse321.tutoringservice.model.BigRoom;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
import ca.mcgill.ecse321.tutoringservice.model.RegisteredTutor;
import ca.mcgill.ecse321.tutoringservice.model.Student;

public class Manager extends Person {
/**
    * <pre>
    *           1..1     0..*
    * Manager ------------------------- BigRoom
    *           manager        &lt;       bigRoom
    * </pre>
    */
   private Set<BigRoom> bigRoom;
   
   public Set<BigRoom> getBigRoom() {
      if (this.bigRoom == null) {
         this.bigRoom = new HashSet<BigRoom>();
      }
      return this.bigRoom;
   }
   
   
   
   /**
    * <pre>
    *           1..1     0..*
    * Manager ------------------------- SubjectRequest
    *           manager        &lt;       subjectRequest
    * </pre>
    */
   private Set<SubjectRequest> subjectRequest;
   
   public Set<SubjectRequest> getSubjectRequest() {
      if (this.subjectRequest == null) {
         this.subjectRequest = new HashSet<SubjectRequest>();
      }
      return this.subjectRequest;
   }
   
   }
