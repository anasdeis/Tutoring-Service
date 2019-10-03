package ca.mcgill.ecse321.tutoringservice.model;


public class UniversityCourse extends Subject {
   /**
    * <pre>
    *           0..*     1..1
    * UniversityCourse ------------------------- University
    *           universityCourse        &lt;       university
    * </pre>
    */
   private University university;
   
   public void setUniversity(University value) {
      this.university = value;
   }
   
   public University getUniversity() {
      return this.university;
   }
   
   }
