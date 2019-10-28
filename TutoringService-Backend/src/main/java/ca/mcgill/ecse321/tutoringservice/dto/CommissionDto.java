package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class CommissionDto{
	private double percentage;
	private Integer commissionID;
	private Integer managerID;
	private Set<String> offeringIDs;
	private Integer tutoringSystemID;


	public CommissionDto() {
		
	}
	
	public CommissionDto(double percentage, Integer commissionID, Integer manager, Set<String> offeringIDs, Integer tutoringSystem) {
		this.setCommissionID(commissionID);
		this.setPercentage(percentage);
		this.setManager(manager);
		this.setOffering(offeringIDs);
		this.setTutoringSystem(tutoringSystem);
	}
	
	public void setPercentage(double value) {
		this.percentage = value;
	}
	public double getPercentage() {
		return this.percentage;
	}
	
	public void setCommissionID(Integer value) {
		this.commissionID = value;
	}
	public Integer getCommissionID() {
		return this.commissionID;
	}

	public Integer getManager() {
		return this.managerID;
	}

	public void setManager(Integer managerID) {
		this.managerID = managerID;
	}

	public Set<String> getOffering() {
		return this.offeringIDs;
	}

	public void setOffering(Set<String> offeringIDs) {
		this.offeringIDs = offeringIDs;
	}

	public Integer getTutoringSystem() {
		return this.tutoringSystemID;
	}

	public void setTutoringSystem(Integer tutoringSystemID) {
		this.tutoringSystemID = tutoringSystemID;
	}
}
