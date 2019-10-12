package ca.mcgill.ecse321.tutoringservice.model;


public abstract class Person {
   private String firstName;
   
   public void setFirstName(String value) {
      this.firstName = value;
   }
   
   public String getFirstName() {
      return this.firstName;
   }
   
   private String lastName;
   
   public void setLastName(String value) {
      this.lastName = value;
   }
   
   public String getLastName() {
      return this.lastName;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Person ------------------------> Login
    *           person        &gt;       loginInfo
    * </pre>
    */
   private Login loginInfo;
   
   public void setLoginInfo(Login value) {
      this.loginInfo = value;
   }
   
   public Login getLoginInfo() {
      return this.loginInfo;
   }
   
   /**
    * <pre>
    *           1..*     1..1
    * Person ------------------------> Date
    *           person        &gt;       dateOfBirth
    * </pre>
    */
   private Date dateOfBirth;
   
   public void setDateOfBirth(Date value) {
      this.dateOfBirth = value;
   }
   
   public Date getDateOfBirth() {
      return this.dateOfBirth;
   }
   
   }
