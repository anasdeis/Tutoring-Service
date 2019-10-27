package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface AvaliableSessionRepository extends CrudRepository<AvaliableSession, Integer> {
    AvaliableSession findAvaliableSessionByAvaliableSessionID (Integer avaliableSessionID);
    
    
    void deleteAvaliableSessionByAvaliableSessionID (Integer avaliableSessionID);
    
    boolean existsByAvaliableSessionID(Integer avaliableSessionID);
}