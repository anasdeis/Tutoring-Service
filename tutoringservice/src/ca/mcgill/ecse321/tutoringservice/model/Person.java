package ca.mcgill.ecse321.tutoringservice.model;

import Interface_AnnotationDefinition;

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
   
   private Date dateOfBirth;
   
   public void setDateOfBirth(Date value) {
      this.dateOfBirth = value;
   }
   
   public Date getDateOfBirth() {
      return this.dateOfBirth;
   }
   
   private String email;
   
   public void setEmail(String value) {
      this.email = value;
   }
   
   public String getEmail() {
      return this.email;
   }
   
   private Interface_AnnotationDefinition phoneNumber;
   
   public void setPhoneNumber(Interface_AnnotationDefinition value) {
      this.phoneNumber = value;
   }
   
   public Interface_AnnotationDefinition getPhoneNumber() {
      return this.phoneNumber;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Person ------------------------- TutoringSystem
    *           person        &lt;       tutoringSystem
    * </pre>
    */
   private TutoringSystem tutoringSystem;
   
   public void setTutoringSystem(TutoringSystem value) {
      this.tutoringSystem = value;
   }
   
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   }
