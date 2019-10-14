package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findStudentById(int studentID);

    void deleteStudentById(int studentID);
}