package ca.mcgill.ecse321.tutoringservice.dto;

import java.util.Set;

public class CommissionDto{
	private double percentage;
	private Integer commissionID;
	private ManagerDto manager;
	private Set<String> offeringIDs;
	private TutoringSystemDto tutoringSystem;


	public CommissionDto() {
		
	}
	
	public CommissionDto(double percentage, Integer commissionID, ManagerDto manager, Set<String> offeringIDs, TutoringSystemDto tutoringSystem) {
		this.commissionID = commissionID;
		this.percentage = percentage;
		this.manager = manager;
		this.offeringIDs = offeringIDs;
		this.tutoringSystem = tutoringSystem;
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

	public ManagerDto getManager() {
		return this.manager;
	}

	public void setManager(ManagerDto manager) {
		this.manager = manager;
	}

	public Set<String> getOffering() {
		return this.offeringIDs;
	}

	public void setOffering(Set<String> offeringIDs) {
		this.offeringIDs = offeringIDs;
	}

	public TutoringSystemDto getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystemDto tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}
}
