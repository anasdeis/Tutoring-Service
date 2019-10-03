package ca.mcgill.ecse321.tutoringservice.model;


public class BigRoom extends Classroom {
private Integer capacity;
   
   public void setCapacity(Integer value) {
      this.capacity = value;
   }
   
   public Integer getCapacity() {
      return this.capacity;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * BigRoom ------------------------- Manager
    *           bigRoom        &gt;       manager
    * </pre>
    */
   private Manager manager;
   
   public void setManager(Manager value) {
      this.manager = value;
   }
   
   public Manager getManager() {
      return this.manager;
   }
   
   }
