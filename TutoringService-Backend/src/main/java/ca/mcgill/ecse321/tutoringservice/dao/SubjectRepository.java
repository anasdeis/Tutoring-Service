package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface SubjectRepository extends CrudRepository<Subject, String> {
    Subject findSubjectByCourseID(String courseID);

    void deleteSubjectByCourseID(String courseID);
    
    boolean existsByCourseID(String courseID);
}