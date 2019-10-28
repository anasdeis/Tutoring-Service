package ca.mcgill.ecse321.tutoringservice.dto;

public class ReviewDto{
	private Integer reviewID;
	private String comment;
	private Boolean isApproved;
	private OfferingDto offering;
	private ManagerDto manager;
	private TutoringSystemDto tutoringSystem;

	public ReviewDto() {
		
	}
	
	public ReviewDto(String comment, Boolean isApproved, Integer reviewID, ManagerDto manager, OfferingDto offering, TutoringSystemDto tutoringSystem) {
		this.comment = comment;
		this.isApproved = isApproved;
		this.reviewID = reviewID;
		this.manager = manager;
		this.offering = offering;
		this.tutoringSystem = tutoringSystem;
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

	public OfferingDto getOffering() {
		return this.offering;
	}

	public void setOffering(OfferingDto offering) {
		this.offering = offering;
	}

	public ManagerDto getManager() {
		return this.manager;
	}

	public void setManager(ManagerDto manager) {
		this.manager = manager;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
