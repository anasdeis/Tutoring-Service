package ca.mcgill.ecse321.tutoringservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Review findReviewByReviewID(int reviewID);
    
    List<Review> findByOffering(Offering offering);
    
    void deleteReviewByReviewID(int reviewID);
}