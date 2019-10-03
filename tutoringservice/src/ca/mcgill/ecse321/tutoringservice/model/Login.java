package ca.mcgill.ecse321.tutoringservice.model;


public class Login {
   private String email;
   
   public void setEmail(String value) {
      this.email = value;
   }
   
   public String getEmail() {
      return this.email;
   }
   
   private Integer phoneNumber;
   
   public void setPhoneNumber(Integer value) {
      this.phoneNumber = value;
   }
   
   public Integer getPhoneNumber() {
      return this.phoneNumber;
   }
   
   private String userName;
   
   public void setUserName(String value) {
      this.userName = value;
   }
   
   public String getUserName() {
      return this.userName;
   }
   
   private String password;
   
   public void setPassword(String value) {
      this.password = value;
   }
   
   public String getPassword() {
      return this.password;
   }
   
   }
