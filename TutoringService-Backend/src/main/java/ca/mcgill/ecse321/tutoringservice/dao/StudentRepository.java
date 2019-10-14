package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findStudentByPersonId(int studentID);

    void deleteStudentByPersonId(int studentID);
}