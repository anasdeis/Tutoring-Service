package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringservice.dao.OfferingRepository;
import ca.mcgill.ecse321.tutoringservice.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Review;
import ca.mcgill.ecse321.tutoringservice.model.Subject;

@Service
public class TutoringServiceService {

	@Autowired
	ReviewRepository reviewRepository;


	@Transactional
	public Review createReview(String name, Manager manager, Offering offering, Integer reviewID) {
		Review review = new Review();
		review.setComment("Default Review");
		review.setIsApproved(false);
		review.setManager(manager);
		review.setOffering(offering);
		review.setReviewID(reviewID);
		reviewRepository.save(review);
		return review;
	}
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	OfferingRepository offeringRepository;
	
	@Transactional
	public Manager createManager(String first, String last, Date dob, String email, int phone, int managerID) {
		Manager manager = new Manager();
		manager.setFirstName(first);
		manager.setLastName(last);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phone);
		manager.setManagerID(managerID);
		managerRepository.save(manager);
		return manager;
	}
	
	@Transactional
	public Manager getManager(int managerID) {
		Manager manager = managerRepository.findManagerById(managerID);
		return manager;
	}
	
	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}

	@Transactional
	public Offering createOffering(String offId, String term, double price, Subject subj){
		Offering offering = new Offering();
		offering.setOfferingID(offId);
		offering.setTerm(term);
		offering.setPricePerHour(price);
		offering.setSubject(subj);
		offeringRepository.save(offering);
		return offering;
	}
	
	@Transactional
	public Offering getOffering(String offID) {
		Offering offering = offeringRepository.findOfferingById(offID);
		return offering;
	}
	
	@Transactional
	public List<Offering> getAllOfferings() {
		return toList(offeringRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	


}