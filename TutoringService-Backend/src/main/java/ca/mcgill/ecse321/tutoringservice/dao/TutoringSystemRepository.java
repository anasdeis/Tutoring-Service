package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface TutoringSystemRepository extends CrudRepository<TutoringSystem, Integer> {
    TutoringSystem findTutoringSystemByTutoringSystemID(Integer tutoringSystemID);

    void deleteTutoringSystemByTutoringSystemID(Integer tutoringSystemID);
    
    boolean existsByTutoringSystemID(Integer tutoringSystemID);
}