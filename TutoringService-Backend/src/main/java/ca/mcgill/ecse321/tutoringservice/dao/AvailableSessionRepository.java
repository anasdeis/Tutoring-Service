package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface AvailableSessionRepository extends CrudRepository<AvailableSession, Integer> {
    AvailableSession findAvailableSessionByAvailableSessionID (Integer AvailableSessionID);
    
    
    void deleteAvailableSessionByAvailableSessionID (Integer AvailableSessionID);
    
    boolean existsByAvailableSessionID(Integer AvailableSessionID);
}