package ca.mcgill.ecse321.tutoringservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice.dao.AvailableSessionRepository;
import ca.mcgill.ecse321.tutoringservice.dao.ClassroomRepository;
import ca.mcgill.ecse321.tutoringservice.dao.CommissionRepository;
import ca.mcgill.ecse321.tutoringservice.dao.LoginRepository;
import ca.mcgill.ecse321.tutoringservice.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringservice.dao.OfferingRepository;
import ca.mcgill.ecse321.tutoringservice.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringservice.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringservice.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice.dao.SubjectRequestRepository;
import ca.mcgill.ecse321.tutoringservice.dao.TutorApplicationRepository;
import ca.mcgill.ecse321.tutoringservice.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice.dao.TutoringSystemRepository;
import ca.mcgill.ecse321.tutoringservice.dao.UniversityRepository;
import ca.mcgill.ecse321.tutoringservice.model.Classroom;
import ca.mcgill.ecse321.tutoringservice.model.Commission;
import ca.mcgill.ecse321.tutoringservice.model.Login;
import ca.mcgill.ecse321.tutoringservice.model.Manager;
import ca.mcgill.ecse321.tutoringservice.model.Offering;
import ca.mcgill.ecse321.tutoringservice.model.Review;
import ca.mcgill.ecse321.tutoringservice.model.Student;
import ca.mcgill.ecse321.tutoringservice.model.Subject;
import ca.mcgill.ecse321.tutoringservice.model.SubjectRequest;
import ca.mcgill.ecse321.tutoringservice.model.SubjectType;
import ca.mcgill.ecse321.tutoringservice.model.Tutor;
import ca.mcgill.ecse321.tutoringservice.model.TutoringSystem;
import ca.mcgill.ecse321.tutoringservice.model.University;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringServiceService {

    @Autowired
    private TutoringServiceService service;

    @Autowired
    private AvailableSessionRepository availableSessionRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private OfferingRepository offeringRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectRequestRepository subjectRequestRepository;

    @Autowired
    private TutorApplicationRepository tutorApplicationRepository;

    @Autowired
    private TutoringSystemRepository tutoringSystemRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Before
    @After
    public void clearDatabase() {
        availableSessionRepository.deleteAll();
        classroomRepository.deleteAll();
        commissionRepository.deleteAll();
        loginRepository.deleteAll();
        managerRepository.deleteAll();
        offeringRepository.deleteAll();
        reviewRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        subjectRequestRepository.deleteAll();
        tutorApplicationRepository.deleteAll();
        tutoringSystemRepository.deleteAll();
        tutorRepository.deleteAll();
        universityRepository.deleteAll();
    }

    @Test
    public void testCreateStudent() {
        assertEquals(0, service.getAllStudents().size());
        Integer studentID = 654321;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        Integer numCoursesEnrolled = 100;
        String email = "123456@gmail.com";
        Integer phoneNumber = 45612378;

        try {
            service.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, studentID, numCoursesEnrolled);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Student> allStudents = service.getAllStudents();
        assertEquals(1, allStudents.size());
        assertEquals(studentID, allStudents.get(0).getPersonId());
        assertEquals(firstName, allStudents.get(0).getFirstName());
        assertEquals(lastName, allStudents.get(0).getLastName());
        assertEquals(dateOfBirth, allStudents.get(0).getDateOfBirth());
        assertEquals(email, allStudents.get(0).getEmail());
        assertEquals(phoneNumber, allStudents.get(0).getPhoneNumber());
        assertEquals(numCoursesEnrolled, allStudents.get(0).getNumCoursesEnrolled());
        service.deleteStudent(studentID);
    }

    @Test
    public void testCreateManager() {
        assertEquals(0, service.getAllManagers().size());
        Integer managerID = 123456;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        String email = "123456@gmail.com";
        Integer phoneNumber = 45612378;
        try {
            service.createManager(email, email, dateOfBirth, email, managerID, phoneNumber);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Manager> allManagers = service.getAllManagers();
        assertEquals(1, allManagers.size());
        assertEquals(managerID, allManagers.get(0).getPersonId());
        assertEquals(firstName, allManagers.get(0).getFirstName());
        assertEquals(lastName, allManagers.get(0).getLastName());
        assertEquals(dateOfBirth, allManagers.get(0).getDateOfBirth());
        assertEquals(email, allManagers.get(0).getEmail());
        assertEquals(phoneNumber, allManagers.get(0).getPhoneNumber());
        service.deleteManager(managerID);
    }

    @Test
    public void testCreateTutor() {
        assertEquals(0, service.getAllTutors().size());
        Integer tutorID = 666666;
        boolean isRegistered = false;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        String email = "123456@gmail.com";
        Integer phoneNumber = 45612378;

        try {
            service.createTutor(email, email, dateOfBirth, email, tutorID, phoneNumber, isRegistered);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Tutor> allTutors = service.getAllTutors();

        assertEquals(1, allTutors.size());
        assertEquals(tutorID, allTutors.get(0).getPersonId());
        assertEquals(isRegistered, allTutors.get(0).getIsRegistered());
        assertEquals(firstName, allTutors.get(0).getFirstName());
        assertEquals(lastName, allTutors.get(0).getLastName());
        assertEquals(dateOfBirth, allTutors.get(0).getDateOfBirth());
        assertEquals(email, allTutors.get(0).getEmail());
        assertEquals(phoneNumber, allTutors.get(0).getPhoneNumber());
        service.deleteTutor(tutorID);
    }

    @Test
    public void testCreateCommission() {
        assertEquals(0, service.getAllCommissions().size());
        Integer commissionID = 1234567;
        double percentage = 10.5;

        try {
            service.createCommission(percentage, commissionID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Commission> allCommissions = service.getAllCommissions();

        assertEquals(1, allCommissions.size());
        assertEquals(commissionID, allCommissions.get(0).getCommissionID());
        service.deleteCommisison(commissionID);
    }

    @Test
    public void testCreateLogin() {
        assertEquals(0, service.getAllLogins().size());
        String userName = "Charles Liu";
        String password = "charles";
        try {
            service.createLogin(userName, password);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Login> allLogins = service.getAllLogins();

        assertEquals(1, allLogins.size());
        assertEquals(userName, allLogins.get(0).getUserName());
        assertEquals(password, allLogins.get(0).getPassword());
        service.deleteLogin(userName);
    }

    @Test
    public void testCreateAvailableSession() {
        assertEquals(0, service.getAllAvailableSessions().size());
        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date day = new Date(c.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("09:00");
        c.set(2017, Calendar.MARCH, 16, 10, 30, 0);
        LocalTime endTime = LocalTime.parse("10:30");
        Integer availableSessionID = 5;
        try {
            service.createAvailableSession(Time.valueOf(startTime) , Time.valueOf(endTime), day, availableSessionID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        assertEquals(1, service.getAllAvailableSessions().size());
        assertEquals(availableSessionID, service.getAllAvailableSessions().get(0).getAvaliableSessionID());
        assertEquals(day.toString(), service.getAllAvailableSessions().get(0).getDay().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        assertEquals(startTime.format(formatter).toString(), service.getAllAvailableSessions().get(0).getStartTime().toString());
        assertEquals(endTime.format(formatter).toString(), service.getAllAvailableSessions().get(0).getEndTime().toString());
        service.deleteAvailableSessionByAvaliableSessionID(availableSessionID);
    }
    
    @Test
   	public void testCreateAvailableSessionNull() {
    	assertEquals(0, service.getAllAvailableSessions().size());

   		Integer availableSessionID = null;
   		Date day = null;
   		Time startTime = null;
   		Time endTime = null;

   		String error = null;
   		try {
   			service.createAvailableSession(startTime , endTime, day, availableSessionID);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals(
   				"AvailableSession availableSessionID cannot be empty! AvailableSession day cannot be empty! AvailableSession start time cannot be empty! AvailableSession end time cannot be empty!",
   				error);
   		// check model in memory
   		assertEquals(0, service.getAllAvailableSessions().size());
   	}

   	@Test
   	public void testCreateAvailableSessionEmpty() {
   		assertEquals(0, service.getAllAvailableSessions().size());

   		Integer availableSessionID = null;
   		Calendar c = Calendar.getInstance();
   		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
   		Date day = new Date(c.getTimeInMillis());
   		LocalTime startTime = LocalTime.parse("10:00");
   		c.set(2017, Calendar.FEBRUARY, 16, 11, 30, 0);
   		LocalTime endTime = LocalTime.parse("11:30");
   		String error = null;
   		try {
   			service.createAvailableSession(Time.valueOf(startTime) , Time.valueOf(endTime), day, availableSessionID);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals("AvailableSessionID availableSessionID cannot be empty!", error);
   		// check model in memory
   		assertEquals(0, service.getAllAvailableSessions().size());
   	}

   	@Test
   	public void testCreateAvailableSessionEndTimeBeforeStartTime() {
   		assertEquals(0, service.getAllAvailableSessions().size());

   		Integer availableSessionID = 15;
   		Calendar c = Calendar.getInstance();
   		c.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
   		Date day = new Date(c.getTimeInMillis());
   		LocalTime startTime = LocalTime.parse("09:00");
   		c.set(2016, Calendar.OCTOBER, 16, 8, 59, 59);
   		LocalTime endTime = LocalTime.parse("08:59");

   		String error = null;
   		try {
   			service.createAvailableSession(Time.valueOf(startTime) , Time.valueOf(endTime), day, availableSessionID);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals("AvailableSession end time cannot be before event start time!", error);

   		// check model in memory
   		assertEquals(0, service.getAllAvailableSessions().size());

   	}

    @Test
    public void testCreateTutoringSystem() {
        assertEquals(0, service.getAllTutoringSystem().size());
        Integer tutoringSystemID = 1234567;
        try {
            service.createTutoringSystem(tutoringSystemID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<TutoringSystem> allTutoringSystems = service.getAllTutoringSystems();
        assertEquals(1, allTutoringSystems.size());
        assertEquals(tutoringSystemID, allTutoringSystems.get(0).getTutoringSystemID());
        service.deleteTutorSystem(tutoringSystemID);
    }





    @Test
    public void testCreateOffering() {
        assertEquals(0, service.getAllOfferings().size());
        String offeringID = "FALL19";
        String term = "fall";
        double pricePerHour = 10.0;
        try {
            service.createOffering(offeringID, term,pricePerHour, null);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<Offering> allOfferings = service.getAllOfferings();
        assertEquals(1, allOfferings.size());
        assertEquals(offeringID, allOfferings.get(0).getOfferingID());
        assertEquals(term, allOfferings.get(0).getTerm());

        service.deleteOffering(offeringID);
    }

    @Test
    public void testCreateUniversity() {
        assertEquals(0, service.getAllUniversitys().size());
        String name = "Mcgill";
        try {
            service.createUniversity(name);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<University> allUniversitys = service.getAllUniversitys();
        assertEquals(1, allUniversitys.size());
        assertEquals(name, allUniversitys.get(0).getName());
        service.deleteUniversity(name);
    }

    @Test
    public void testCreateSubjectRequest() {
        assertEquals(0, service.getAllSubjectRequests().size());
        Integer requestID = 789456;
        String name = "Math240";
        String description = "Discrete structures";
        SubjectType subjectType = SubjectType.HIGH_SCHOOL_COURSE;
        try {
            service.createSubjectRequest(requestID, name, description, subjectType);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<SubjectRequest> allSubjectRequests = service.getAllSubjectRequests();
        assertEquals(1, allSubjectRequests.size());
        assertEquals(requestID, allSubjectRequests.get(0).getRequestID());
        assertEquals(name, allSubjectRequests.get(0).getName());
        assertEquals(description, allSubjectRequests.get(0).getDescription());
        assertEquals(subjectType, allSubjectRequests.get(0).getSubjectType());
        service.deleteSubjectRequest(requestID);
    }

    @Test
    public void testCreateSubject() {
        assertEquals(0, service.getAllSubjects().size());
        String name = "Math240";
        String courseID = "MATH240FALL";
        String description = "Discrete structures";
        SubjectType subjectType = SubjectType.HIGH_SCHOOL_COURSE;
        try {
            service.createSubject(name, courseID, description, subjectType);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<Subject> allSubjects = service.getAllSubjects();
        assertEquals(1, allSubjects.size());
        assertEquals(name, allSubjects.get(0).getName());
        assertEquals(courseID, allSubjects.get(0).getCourseID());
        assertEquals(description, allSubjects.get(0).getDescription());
        assertEquals(subjectType, allSubjects.get(0).getSubjectType());
        service.deleteSubject(courseID);
    }

    public void testCreateClassroom() {
        assertEquals(0, service.getAllClassrooms().size());
        String roomCode = "rm1";
        Boolean isBooked = true;
        Boolean isBigRoom = false;
        try {
            service.createClasroom(roomCode, isBooked, isBigRoom);
        } catch (IllegalArgumentException e) {
            // check that no error occurred
            fail();
        }

        List<Classroom> allClassrooms = service.getAllClassrooms();
        assertEquals(1, allClassrooms.size());
        assertEquals(roomCode, allClassrooms.get(0).getRoomCode());
        assertEquals(isBooked, allClassrooms.get(0).getIsBooked());
        assertEquals(isBigRoom, allClassrooms.get(0).getIsBigRoom());
        service.deleteClassroomByRoomCode(roomCode);
    }
    
    @Test
	public void testCreateClassroomNull() {
    	assertEquals(0, service.getAllClassrooms().size());
		
        String roomCode = null;
        Boolean isBooked = null;
        Boolean isBigRoom = null;
		String error = null;

		try {
			service.createClasroom(roomCode, isBooked, isBigRoom);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Classroom roomCode cannot be empty! Classroom isBooked cannot be empty! Classroom isBigRoom cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllClassrooms().size());

	}

	@Test
	public void testCreateClassroomEmpty() {
		assertEquals(0, service.getAllClassrooms().size());

		String comment = "";
		String reviewID = "";
		Boolean isApproved = null;
		String error = null;

		try {
			service.createClasroom(roomCode, isBooked, isBigRoom);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Classroom roomCode cannot be empty! Classroom isBooked cannot be empty! Classroom isBigRoom cannot be empty!", error);


		// check no change in memory
		assertEquals(0, service.getAllClassrooms().size());

	}

	@Test
	public void testCreateClassroomSpaces() {
		assertEquals(0, service.getAllClassrooms().size());

		String comment = " ";
		String reviewID = " ";
		Boolean isApproved = null;
		String error = null;
	
		try {
			service.createClasroom(roomCode, isBooked, isBigRoom);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Classroom roomCode cannot be empty! Classroom isBooked cannot be empty! Classroom isBigRoom cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllClassrooms().size());

	}
	
    @Test
	public void testCreateReview() {
		assertEquals(0, service.getAllReviews().size());


		String comment = "Default comment";
        Boolean isApproved = false;
        Integer reviewID  = 10;

		try {
			Manager m = new Manager();
			Integer managerID = 123456;
	        String firstName = "Charles";
	        String lastName = "Liu";
	        Calendar cal = Calendar.getInstance();
	        cal.set(1999, Calendar.MARCH, 16, 9, 0, 0);
	        Date dateOfBirth = new Date(cal.getTimeInMillis());
	        String email = "123456@gmail.com";
	        Integer phoneNumber = 45612378;
	        
	        
	        m.setDateOfBirth(dateOfBirth);
	        m.setEmail(email);
	        m.setPersonId(managerID);
	        m.setPersonId(phoneNumber);
	        m.setFirstName(firstName);
	        m.setLastName(lastName);
	        m.setCommission(null);
	        m.setSubjectRequest(null);
	        m.setClassroom(null);
	        managerRepository.save(m);
	        
	        Classroom c = new Classroom();
	        c.setIsBigRoom(false);
	        c.setIsBooked(false);
	        c.setManager(m);
	        //c.setOffering(new HashSet<>(Collections.singleton(o)));
	        c.setRoomCode("123");
	        classroomRepository.save(c);
	        
	        
	        Offering o = new Offering();
	        o.setTerm("Fall");
	        o.setClassTime(null);
	        o.setOfferingID("id");
	        o.setPricePerHour(12);
	        o.setClassroom(c);
	        offeringRepository.save(o);
	        
	        
			service.createReview(comment, isApproved, reviewID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Review> allReviews = service.getAllReviews();

		assertEquals(1, allReviews.size());
        assertEquals(comment, allReviews.get(0).getComment());
        assertEquals(isApproved, allReviews.get(0).getIsApproved());
        assertEquals(reviewID, allReviews.get(0).getReviewID());
        service.deleteReviewByReviewID(reviewID);
	}
	
    @Test
	public void testCreateReviewNull() {
		assertEquals(0, service.getAllReviews().size());
		
		String comment = null;
		Integer reviewID = null;
		Boolean isApproved = null;
		String error = null;

		try {
			service.createReview(comment, isApproved, reviewID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review comment cannot be empty! Review reviewID cannot be empty! Review isApproved cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}

	@Test
	public void testCreateReviewEmpty() {
		assertEquals(0, service.getAllReviews().size());

		String comment = "";
		Integer reviewID = 1;
		Boolean isApproved = null;
		String error = null;

		try {
			service.createReview(comment, isApproved, reviewID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review comment cannot be empty! Review reviewID cannot be empty! Review isApproved cannot be empty!", error);


		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}

	@Test
	public void testCreateReviewSpaces() {
		assertEquals(0, service.getAllReviews().size());

		String comment = " ";
		Integer reviewID = 1;
		Boolean isApproved = null;
		String error = null;
	
		try {
			service.createReview(comment, isApproved, reviewID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review comment cannot be empty! Review reviewID cannot be empty! Review isApproved cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}

}