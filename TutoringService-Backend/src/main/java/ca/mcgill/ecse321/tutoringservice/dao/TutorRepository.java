package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {
    Tutor findTutorByPersonId(Integer tutorID);

    void deleteTutorByPersonId(Integer tutorID);
    
    boolean existsByPersonId(Integer tutorID);
}