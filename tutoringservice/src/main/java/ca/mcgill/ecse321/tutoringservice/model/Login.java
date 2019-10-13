package ca.mcgill.ecse321.tutoringservice.model;


public class Login {
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
