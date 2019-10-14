package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringservice.model.*;

public interface UniversityRepository extends CrudRepository<University, String> {
    University findUniversityById(String name);

    void deleteUniversityById(String name);
}