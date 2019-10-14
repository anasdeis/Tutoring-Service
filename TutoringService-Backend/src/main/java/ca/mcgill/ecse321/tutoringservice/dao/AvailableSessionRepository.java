package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface AvailableSessionRepository extends CrudRepository<AvaliableSession, Integer> {
    AvaliableSession findAvailableSessionById (int availableSessionID);

    void deleteAvailableSessionById (int availableSessionID);
}