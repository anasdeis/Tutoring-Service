package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface TutorApplicationRepository extends CrudRepository<TutorApplication, Integer> {
    TutorApplication findTutorApplicationByApplicationId(Integer applicationID);

    void deleteTutorApplicationByApplicationId(Integer applicationID);
    
    boolean existsByApplicationId(Integer applicationId);
}