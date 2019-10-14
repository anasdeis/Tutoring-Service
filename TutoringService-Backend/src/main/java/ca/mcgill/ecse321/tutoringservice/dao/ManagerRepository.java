package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    Manager findManagerByPersonId(int managerID);

    void deleteManagerByPersonId(int managerID);
}