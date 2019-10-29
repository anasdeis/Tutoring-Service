package ca.mcgill.ecse321.tutoringservice.dto;

public class ReviewDto{
	private Integer reviewID;
	private String comment;
	private Boolean isApproved;
	private String offeringID;
	private Integer managerID;
	private Integer tutoringSystemID;

	public ReviewDto() {
		
	}
	
	public ReviewDto(String comment, Boolean isApproved, Integer reviewID, Integer manager, String offering, Integer tutoringSystem) {
		this.setComment(comment);
		this.setIsApproved(isApproved);
		this.setReviewID(reviewID);
		this.setManager(manager);
		this.setOffering(offering);
		this.setTutoringSystem(tutoringSystem);
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

	public String getOffering() {
		return this.offeringID;
	}

	public void setOffering(String offeringID) {
		this.offeringID = offeringID;
	}

	public Integer getManager() {
		return this.managerID;
	}

	public void setManager(Integer managerID) {
		this.managerID = managerID;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}

}
