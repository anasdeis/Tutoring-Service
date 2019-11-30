package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Review{
   private String comment;

public void setComment(String value) {
    this.comment = value;
}
public String getComment() {
    return this.comment;
}
private Boolean isApproved;

public void setIsApproved(Boolean value) {
    this.isApproved = value;
}
public Boolean getIsApproved() {
    return this.isApproved;
}
private Offering offering;

@ManyToOne(optional=false)
public Offering getOffering() {
   return this.offering;
}

public void setOffering(Offering offering) {
   this.offering = offering;
}

private Manager manager;

@ManyToOne(optional=false)
public Manager getManager() {
   return this.manager;
}

public void setManager(Manager manager) {
   this.manager = manager;
}

private TutoringSystem tutoringSystem;

@ManyToOne(optional=false)
public TutoringSystem getTutoringSystem() {
   return this.tutoringSystem;
}

public void setTutoringSystem(TutoringSystem tutoringSystem) {
   this.tutoringSystem = tutoringSystem;
}

private Integer reviewID;

public void setReviewID(Integer value) {
    this.reviewID = value;
}
@Id
public Integer getReviewID() {
    return this.reviewID;
}
}
