package ca.mcgill.ecse321.tutoringservice.dto;

//import ca.mcgill.ecse321.tutoringservice.model.Manager;
//import ca.mcgill.ecse321.tutoringservice.model.Offering;
//import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

public class ReviewDto{
	private String comment;
	private Boolean isApproved;
	//private Offering offering;
	//private Manager manager;
	//private TutoringSystem tutoringSystem;
	private Integer reviewID;

	public ReviewDto() {
		
	}
	
	public ReviewDto(String comment, Boolean isApproved, Integer reviewID) {
		this.comment = comment;
		this.isApproved = isApproved;
		this.reviewID = reviewID;
	}
	
	public void setComment(String value) {
		this.comment = value;
	}
	public String getComment() {
		return this.comment;
	}

	public void setIsApproved(Boolean value) {
		this.isApproved = value;
	}
	public Boolean getIsApproved() {
		return this.isApproved;
	}

	public void setReviewID(Integer value) {
		this.reviewID = value;
	}
	
	public Integer getReviewID() {
		return this.reviewID;
	}
/*
	public Offering getOffering() {
		return this.offering;
	}

	public void setOffering(Offering offering) {
		this.offering = offering;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
*/
}
