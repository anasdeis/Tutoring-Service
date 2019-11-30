package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface LoginRepository extends CrudRepository<Login, String> {
    Login findLoginByUserName(String userName);

    void deleteLoginByUserName(String userName);
    
    boolean existsByUserName (String userName);
}