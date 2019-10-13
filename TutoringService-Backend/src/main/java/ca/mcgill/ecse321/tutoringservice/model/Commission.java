package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Commission{
   private double percentage;

public void setPercentage(double value) {
    this.percentage = value;
}
public double getPercentage() {
    return this.percentage;
}
private Manager manager;

@ManyToOne(optional=false)
public Manager getManager() {
   return this.manager;
}

public void setManager(Manager manager) {
   this.manager = manager;
}

private Set<Offering> offering;

@OneToMany(mappedBy="commission" )
public Set<Offering> getOffering() {
   return this.offering;
}

public void setOffering(Set<Offering> offerings) {
   this.offering = offerings;
}

private TutoringSystem tutoringSystem;

@ManyToOne(optional=false)
public TutoringSystem getTutoringSystem() {
   return this.tutoringSystem;
}

public void setTutoringSystem(TutoringSystem tutoringSystem) {
   this.tutoringSystem = tutoringSystem;
}

private Integer commissionID;

public void setCommissionID(Integer value) {
    this.commissionID = value;
}
@Id
public Integer getCommissionID() {
    return this.commissionID;
}
}
