package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface TutoringSystemRepository extends CrudRepository<TutoringSystem, Integer> {
    TutoringSystem findTutoringSystemIDById(int tutoringSystemID);

    void deleteTutoringSystemIDById(int tutoringSystemID);
}