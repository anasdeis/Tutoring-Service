package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface CommissionRepository extends CrudRepository<Commission, Integer> {
    Commission findCommissionBycommissionID(int commissionID);

    void deleteCommissionBycommissionID(int commissionID);
}