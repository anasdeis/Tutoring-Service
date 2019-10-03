package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class University {
   /**
    * <pre>
    *           1..1     0..*
    * University ------------------------- UniversityCourse
    *           university        &gt;       universityCourse
    * </pre>
    */
   private Set<UniversityCourse> universityCourse;
   
   public Set<UniversityCourse> getUniversityCourse() {
      if (this.universityCourse == null) {
         this.universityCourse = new HashSet<UniversityCourse>();
      }
      return this.universityCourse;
   }
   
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   }
