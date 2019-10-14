package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface TutorApplicationRepository extends CrudRepository<TutorApplication, Integer> {
    TutorApplication findTutorApplicationById(int applicationID);

    void deleteTutorApplicationById(int applicationID);
}