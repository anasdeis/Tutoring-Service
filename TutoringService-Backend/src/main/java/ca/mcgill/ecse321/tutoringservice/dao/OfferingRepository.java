package ca.mcgill.ecse321.tutoringservice.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice.model.*;

public interface OfferingRepository extends CrudRepository<Offering, String> {
    Offering findOfferingById (String offeringID);

    void deleteOfferingById (String offeringID);
}