package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface CommissionRepository extends CrudRepository<Commission, Integer> {
    Commission findCommissionById(int commissionID);

    void deleteCommissionById(int commissionID);
}