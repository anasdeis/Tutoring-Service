package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface CommissionRepository extends CrudRepository<Commission, Integer> {
    Commission findCommissionBycommissionID(Integer commissionID);

    void deleteCommissionBycommissionID(Integer commissionID);
    
    boolean existsByCommissionID(Integer commissionID);
}