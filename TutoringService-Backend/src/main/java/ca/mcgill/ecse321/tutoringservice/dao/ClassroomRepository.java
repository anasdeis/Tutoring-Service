package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface ClassroomRepository extends CrudRepository<Classroom, String> {
    Classroom findClassroomById(String roomCode);

    void deleteClassroomById(String roomCode);
}