package ca.mcgill.ecse321.tutoringservice.model;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person{
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
private Login loginInfo;

@OneToOne(cascade={CascadeType.ALL}, optional=false)
public Login getLoginInfo() {
   return this.loginInfo;
}

public void setLoginInfo(Login loginInfo) {
   this.loginInfo = loginInfo;
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
private Integer phoneNumber;

public void setPhoneNumber(Integer value) {
    this.phoneNumber = value;
}
public Integer getPhoneNumber() {
    return this.phoneNumber;
}
   private TutoringSystem tutoringSystem;
   
   @ManyToOne(optional=false)
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   public void setTutoringSystem(TutoringSystem tutoringSystem) {
      this.tutoringSystem = tutoringSystem;
   }
   
   }
