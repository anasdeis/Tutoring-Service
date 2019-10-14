package ca.mcgill.ecse321.tutoringservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice.dao.ReviewRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringServiceService {
	@Autowired
	private TutoringServiceService service;

	@Autowired
	private ReviewRepository reviewRepository;


	@After
	public void clearDatabase() {
		// Fisrt, we clear registrations to avoid exceptions due to inconsistencies
		// Then we can clear the other tables
		reviewRepository.deleteAll();
	}

}