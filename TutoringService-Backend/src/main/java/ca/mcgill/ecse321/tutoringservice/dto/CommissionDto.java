package ca.mcgill.ecse321.tutoringservice.dto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;

import javax.persistence.Id;

public class CommissionDto{
	private double percentage;
	private Integer commissionID;
	private Manager manager;
	private Set<Offering> offering;
	private TutoringSystem tutoringSystem;


	public CommissionDto() {
		
	}
	
	public CommissionDto(double percentage, Integer commissionID, Manager manager, Set<Offering> offering, TutoringSystem tutoringSystem) {
		this.commissionID = commissionID;
		this.percentage = percentage;
		this.manager = manager;
		this.offering = offering;
		this.tutoringSystem = tutoringSystem;
	}
	
	public void setPercentage(double value) {
		this.percentage = value;
	}
	public double getPercentage() {
		return this.percentage;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Offering> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<Offering> offerings) {
		this.offering = offerings;
	}

	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

	public void setCommissionID(Integer value) {
		this.commissionID = value;
	}
	public Integer getCommissionID() {
		return this.commissionID;
	}
}
