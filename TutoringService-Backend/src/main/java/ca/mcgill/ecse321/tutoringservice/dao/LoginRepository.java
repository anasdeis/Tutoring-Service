package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface LoginRepository extends CrudRepository<Login, String> {
    Login findLoginById(String userName);

    void deleteLoginById(String userName);
}