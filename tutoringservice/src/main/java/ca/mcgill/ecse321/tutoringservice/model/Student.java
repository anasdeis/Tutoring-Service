package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Student extends Person {
   private Integer studentID;
   
   public void setStudentID(Integer value) {
      this.studentID = value;
   }
   
   public Integer getStudentID() {
      return this.studentID;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Student ------------------------- Offering
    *           studentsEnrolled        &gt;       coursesTaken
    * </pre>
    */
   private Set<Offering> coursesTaken;
   
   public Set<Offering> getCoursesTaken() {
      if (this.coursesTaken == null) {
         this.coursesTaken = new HashSet<Offering>();
      }
      return this.coursesTaken;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Student ------------------------- SubjectRequest
    *           student        &lt;       subjectRequest
    * </pre>
    */
   private Set<SubjectRequest> subjectRequest;
   
   public Set<SubjectRequest> getSubjectRequest() {
      if (this.subjectRequest == null) {
         this.subjectRequest = new HashSet<SubjectRequest>();
      }
      return this.subjectRequest;
   }
   
   private Integer numCoursesEnrolled;
   
   public void setNumCoursesEnrolled(Integer value) {
      this.numCoursesEnrolled = value;
   }
   
   public Integer getNumCoursesEnrolled() {
      return this.numCoursesEnrolled;
   }
   
   }
