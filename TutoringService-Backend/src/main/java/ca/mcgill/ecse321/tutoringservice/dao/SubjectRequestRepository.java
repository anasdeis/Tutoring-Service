package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface SubjectRequestRepository extends CrudRepository<SubjectRequest, Integer> {
    SubjectRequest findSubjectRequestById(Integer requestID);

    void deleteSubjectRequestById(Integer requestID);
}