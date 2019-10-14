package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Review;

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

}