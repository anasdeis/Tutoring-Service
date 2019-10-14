package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Review createReview(String comment, Manager manager, Offering offering, Integer reviewID) {
		Review review = new Review();
		
		// Input validation
		String error = "";
		if (comment == null || comment.trim().length() == 0) {
			error = error + "Comment cannot be empty! ";
		}
		review.setComment(comment);
		review.setIsApproved(false);
		review.setManager(manager);
		review.setOffering(offering);
		review.setReviewID(reviewID);
		reviewRepository.save(review);
		
		if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		return review;
	}
	
	@Transactional
	public Optional<Review> getReview(Integer reviewID) {
		Optional<Review> review =  reviewRepository.findById(reviewID);
		return review;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public List<Review> getReviewsByOffering(Offering offering) {
		List<Review> reviewsForOffering = new ArrayList<>();
		for (Review r : reviewRepository.findByOffering(offering)) {
			reviewsForOffering.add(r);
		}
		return reviewsForOffering;
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}