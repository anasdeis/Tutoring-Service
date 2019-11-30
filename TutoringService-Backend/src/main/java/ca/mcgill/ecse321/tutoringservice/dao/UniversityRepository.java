package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringservice.model.*;

public interface UniversityRepository extends CrudRepository<University, String> {
    University findUniversityByName(String name);

    void deleteUniversityByName(String name);
    
    boolean existsByName(String name);
}