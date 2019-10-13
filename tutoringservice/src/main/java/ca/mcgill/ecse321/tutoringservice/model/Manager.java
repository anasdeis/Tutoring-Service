package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Manager extends Person {
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
   
   /**
    * <pre>
    *           1..1     0..*
    * Manager ------------------------- Review
    *           manager        &lt;       review
    * </pre>
    */
   private Set<Review> review;
   
   public Set<Review> getReview() {
      if (this.review == null) {
         this.review = new HashSet<Review>();
      }
      return this.review;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Manager ------------------------- Commission
    *           manager        &gt;       commission
    * </pre>
    */
   private Set<Commission> commission;
   
   public Set<Commission> getCommission() {
      if (this.commission == null) {
         this.commission = new HashSet<Commission>();
      }
      return this.commission;
   }
   
   private Integer managerID;
   
   public void setManagerID(Integer value) {
      this.managerID = value;
   }
   
   public Integer getManagerID() {
      return this.managerID;
   }
   
   /**
    * <pre>
    *           1..1     0..13
    * Manager ------------------------- Classroom
    *           manager        &gt;       classroom
    * </pre>
    */
   private Set<Classroom> classroom;
   
   public Set<Classroom> getClassroom() {
      if (this.classroom == null) {
         this.classroom = new HashSet<Classroom>();
      }
      return this.classroom;
   }
   
   }
