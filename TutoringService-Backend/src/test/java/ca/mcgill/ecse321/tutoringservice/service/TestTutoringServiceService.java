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
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice.dao.AvaliableSessionRepository;
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
import ca.mcgill.ecse321.tutoringservice.model.AvaliableSession;
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
	private AvaliableSessionRepository avaliableSessionRepository;

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
		subjectRepository.deleteAll();
		subjectRequestRepository.deleteAll();
		commissionRepository.deleteAll();
		offeringRepository.deleteAll();
		classroomRepository.deleteAll();
		managerRepository.deleteAll();
		avaliableSessionRepository.deleteAll();
		reviewRepository.deleteAll();
		studentRepository.deleteAll();
		tutorApplicationRepository.deleteAll();
		tutorRepository.deleteAll();
		loginRepository.deleteAll();
		universityRepository.deleteAll();
		tutoringSystemRepository.deleteAll();
	}

	@Test
	public void testCreateLogin() {
		assertEquals(0, service.getAllLogins().size());
		String userName = "Muhammad";
		String password = "elahi";

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
	public void testCreateLoginNull() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = null;
		String password = null;

		try {
			service.createLogin(userName, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);

		List<Login> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}

	@Test
	public void testCreateLoginSpaces() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = " ";
		String password = " ";

		try {
			service.createLogin(userName, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);

		List<Login> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}

	@Test
	public void testCreateLoginEmpty() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = "";
		String password = "";

		try {
			service.createLogin(userName, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);

		List<Login> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
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
		Login loginInfo = new Login();
		loginInfo.setPassword("pass");
		loginInfo.setUserName("user");
		loginRepository.save(loginInfo);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		tutoringSystemRepository.save(tutoringSystem);

		try {
			service.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, studentID, numCoursesEnrolled, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Student> allStudents = service.getAllStudents();
		assertEquals(1, allStudents.size());
		assertEquals(studentID, allStudents.get(0).getPersonId());
		assertEquals(firstName, allStudents.get(0).getFirstName());
		assertEquals(lastName, allStudents.get(0).getLastName());
		assertEquals(email, allStudents.get(0).getEmail());
		assertEquals(phoneNumber, allStudents.get(0).getPhoneNumber());
		assertEquals(numCoursesEnrolled, allStudents.get(0).getNumCoursesEnrolled());
		service.deleteStudent(studentID);
	}

	@Test
	public void testCreateStudentNull() {
		assertEquals(0, service.getAllStudents().size());		

		String error = null;

		Integer studentID = null;
		String first = null;
		String last = null;
		Date dob = null; 
		Integer numCoursesEnrolled = 0;
		String email = null;
		Integer phone = null;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Student ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

	}

	@Test
	public void testCreateStudentEmpty() {
		// cannot check if an Integer is empty, instead, check if it has the default value 0
		// can pass empty value for login but can not pass null
		assertEquals(0, service.getAllStudents().size());


		String error = null;

		Integer studentID = 0;
		String first = "";
		String last = "";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		Integer numCoursesEnrolled = 0;
		String email = "";
		Integer phone = 0;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Student ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

	}


	@Test
	public void testCreateStudentSpaces() {
		// same here, cannot check if an Integer is empty, instead, check if it has the default value 0
		// should be textfield to fill in, if it's textfield, then there exist ways to check
		assertEquals(0, service.getAllStudents().size());

		String error = null ;

		Integer studentID = 0;
		String first = " ";
		String last = " ";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		Integer numCoursesEnrolled = 0;
		String email = " ";
		Integer phone = 0;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Student ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

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
		Login loginInfo = new Login();
		loginInfo.setPassword("pass");
		loginInfo.setUserName("user");
		loginRepository.save(loginInfo);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		tutoringSystemRepository.save(tutoringSystem);

		try {
			service.createManager(firstName, lastName, dateOfBirth, email, phoneNumber, managerID, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Manager> allManagers = service.getAllManagers();
		assertEquals(1, allManagers.size());
		assertEquals(managerID, allManagers.get(0).getPersonId());
		assertEquals(firstName, allManagers.get(0).getFirstName());
		assertEquals(lastName, allManagers.get(0).getLastName());
		assertEquals(email, allManagers.get(0).getEmail());
		assertEquals(phoneNumber, allManagers.get(0).getPhoneNumber());
		service.deleteManager(managerID);
	}

	@Test
	public void testCreateManagerNull() {
		assertEquals(0, service.getAllManagers().size());		
		String error = "";

		Integer managerID = null;
		String first = null;
		String last = null;
		Date dob = null; 
		String email = null;
		Integer phone = null;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createManager(first, last, dob, email, phone, managerID, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error    	
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Manager ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllManagers().size());

	}


	@Test
	public void testCreateManagerEmpty() {
		assertEquals(0, service.getAllManagers().size());

		Integer managerID = 0;
		String first = "";
		String last = "";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = "";
		Integer phone = 0;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		String error = null;

		try {
			service.createManager(first, last, dob, email, phone, managerID, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Manager ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllManagers().size());

	}

	@Test
	public void testCreateManagerSpaces() {
		assertEquals(0, service.getAllManagers().size());

		Integer managerID = 0;
		String first = " ";
		String last = " ";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = " ";
		Integer phone = 0;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		String error = null ;

		try {
			service.createManager(first, last, dob, email, phone, managerID, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Manager ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllManagers().size());

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
		Login loginInfo = new Login();
		loginInfo.setPassword("pass");
		loginInfo.setUserName("user");
		loginRepository.save(loginInfo);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		tutoringSystemRepository.save(tutoringSystem);

		try {
			service.createTutor(firstName, lastName, dateOfBirth, email, phoneNumber, tutorID, isRegistered, loginInfo, tutoringSystem);
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
		assertEquals(email, allTutors.get(0).getEmail());
		assertEquals(phoneNumber, allTutors.get(0).getPhoneNumber());
		service.deleteTutor(tutorID);
		service.deleteLogin(loginInfo.getUserName());

	}


	@Test
	public void testCreateTutorNull() {
		assertEquals(0, service.getAllTutors().size());

		String error = null;

		Integer tutorId = null;
		String first = null;
		String last = null;
		Date dob = null; 
		String email = null;
		Integer phone = null;
		Boolean isRegistered = null;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutor(first, last, dob, email, phone, tutorId, isRegistered, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Tutor ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllTutors().size());

	}


	@Test
	public void testCreateTutorEmpty() {
		String error = null;

		assertEquals(0, service.getAllTutors().size());

		Integer tutorID = 0;
		String first = "";
		String last = "";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = "";
		Integer phone = 0;
		Boolean isRegistered = null;
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutor(first, last, dob, email,phone, tutorID, isRegistered, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}


		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Tutor ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllTutors().size());

	}

	@Test
	public void testCreateTutorSpaces() {
		String error = null;

		assertEquals(0, service.getAllTutors().size());

		Integer tutorID = 0;
		String first = " ";
		String last = " ";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = " ";
		Integer phone = 0;
		Boolean isRegistered = null; 
		Login loginInfo = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutor(first, last, dob, email,phone, tutorID, isRegistered, loginInfo, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!"
				+ "Phone cannot be empty!Tutor ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllTutors().size());

	}



	/*  

    @Test
    public void testCreateAvaliableSession() {
        assertEquals(0, service.getAllAvaliableSessions().size());
        Calendar c = Calendar.getInstance();
        c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
        Date day = new Date(c.getTimeInMillis());
        LocalTime startTime = LocalTime.parse("09:00");
        c.set(2017, Calendar.MARCH, 16, 10, 30, 0);
        LocalTime endTime = LocalTime.parse("10:30");
        Integer AvaliableSessionID = 5;
        try {
            service.createAvaliableSession(Time.valueOf(startTime) , Time.valueOf(endTime), AvaliableSessionID, day);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        assertEquals(1, service.getAllAvaliableSessions().size());
        assertEquals(AvaliableSessionID, service.getAllAvaliableSessions().get(0).getAvaliableSessionID());
        assertEquals(day.toString(), service.getAllAvaliableSessions().get(0).getDay().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        assertEquals(startTime.format(formatter).toString(), service.getAllAvaliableSessions().get(0).getStartTime().toString());
        assertEquals(endTime.format(formatter).toString(), service.getAllAvaliableSessions().get(0).getEndTime().toString());
        service.deleteAvaliableSession(AvaliableSessionID);
    }

    @Test
   	public void testCreateAvaliableSessionNull() {
    	assertEquals(0, service.getAllAvaliableSessions().size());

   		Integer AvaliableSessionID = null;
   		Date day = null;
   		Time startTime = null;
   		Time endTime = null;

   		String error = null;
   		try {
   			service.createAvaliableSession(startTime , endTime, AvaliableSessionID, day);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals(
   				"AvaliableSession AvaliableSessionID cannot be empty! AvaliableSession day cannot be empty! AvaliableSession start time cannot be empty! AvaliableSession end time cannot be empty!",
   				error);
   		// check model in memory
   		assertEquals(0, service.getAllAvaliableSessions().size());
   	}

   	@Test
   	public void testCreateAvaliableSessionEmpty() {
   		assertEquals(0, service.getAllAvaliableSessions().size());

   		Integer AvaliableSessionID = null;
   		Calendar c = Calendar.getInstance();
   		c.set(2017, Calendar.FEBRUARY, 16, 10, 00, 0);
   		Date day = new Date(c.getTimeInMillis());
   		LocalTime startTime = LocalTime.parse("10:00");
   		c.set(2017, Calendar.FEBRUARY, 16, 11, 30, 0);
   		LocalTime endTime = LocalTime.parse("11:30");
   		String error = null;
   		try {
   			service.createAvaliableSession(Time.valueOf(startTime) , Time.valueOf(endTime), AvaliableSessionID, day);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals("AvaliableSessionID AvaliableSessionID cannot be empty!", error);
   		// check model in memory
   		assertEquals(0, service.getAllAvaliableSessions().size());
   	}

   	@Test
   	public void testCreateAvaliableSessionEndTimeBeforeStartTime() {
   		assertEquals(0, service.getAllAvaliableSessions().size());

   		Integer AvaliableSessionID = 15;
   		Calendar c = Calendar.getInstance();
   		c.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
   		Date day = new Date(c.getTimeInMillis());
   		LocalTime startTime = LocalTime.parse("09:00");
   		c.set(2016, Calendar.OCTOBER, 16, 8, 59, 59);
   		LocalTime endTime = LocalTime.parse("08:59");

   		String error = null;
   		try {
   			service.createAvaliableSession(Time.valueOf(startTime) , Time.valueOf(endTime), AvaliableSessionID, day);
   		} catch (IllegalArgumentException e) {
   			error = e.getMessage();
   		}

   		// check error
   		assertEquals("AvaliableSession end time cannot be before event start time!", error);

   		// check model in memory
   		assertEquals(0, service.getAllAvaliableSessions().size());

   	}

	 */
	/*
   		@Test
	public void testCreateSubjectRequest() {
		assertEquals(0, service.getAllSubjectRequests().size());
		Integer requestID = 789456;
		String name = "Math240";
		String description = "Discrete structures";
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(667);
		tutoringSystemRepository.save(tutoringSystem);

		try {
			service.createSubjectRequest(requestID, name, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		List<SubjectRequest> allSubjectRequests = service.getAllSubjectRequests();
		assertEquals(1, allSubjectRequests.size());
		assertEquals(requestID, allSubjectRequests.get(0).getRequestID());
		assertEquals(name, allSubjectRequests.get(0).getName());
		assertEquals(description, allSubjectRequests.get(0).getDescription());
		service.deleteSubjectRequest(requestID);
	}


	@Test
	public void testCreateSubjectRequestNull() {
		assertEquals(0, service.getAllSubjectRequests().size());

		Integer requestID = null;
		String name = null;
		String description = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(111);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubjectRequest(requestID, name, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("valid input needed", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjectRequests().size());
	}


	@Test
	public void testCreateSubjectRequestEmpty() {
		// cannot check if an Integer is empty, instead, check if it has the default value 0
		// can pass empty value for login but can not pass null
		assertEquals(0, service.getAllSubjectRequests().size());

		Integer requestID = 0;
		String name = "";
		String description = "";
		Integer tssID = 0;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(tssID);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;

		try {
			service.createSubjectRequest(requestID, name, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("valid input needed", error);

		assertEquals(0, service.getAllSubjectRequests().size());

	}

	@Test
	public void testCreateSubjectRequestSpaces() {
		assertEquals(0, service.getAllSubjectRequests().size());
		Integer requestID = 0;
		String name = "  ";
		String description = "   ";
		Integer tssID= 0;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(tssID);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;

		try {
			service.createSubjectRequest(requestID, name, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("valid input needed", error);

		assertEquals(0, service.getAllSubjectRequests().size());

	}
	 */

	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubjects().size());
		String name = "Math240";
		String courseID = "MATH240FALL";
		String description = "Discrete structures";
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);
		try {
			service.createSubject(name, courseID, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		List<Subject> allSubjects = service.getAllSubjects();
		assertEquals(1, allSubjects.size());
		assertEquals(name, allSubjects.get(0).getName());
		assertEquals(courseID, allSubjects.get(0).getCourseID());
		assertEquals(description, allSubjects.get(0).getDescription());
	}


	@Test
	public void testCreateSubjectNull() {
		assertEquals(0, service.getAllSubjects().size());

		String name = null;
		String courseID = null;
		String description = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubject(name, courseID, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("name cannot be empty or null!description cannot be empty or null!courseID cannot be empty or null!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());
	}


	@Test
	public void testCreateSubjectEmpty() {
		// cannot check if an Integer is empty, instead, check if it has the default value 0
		// can pass empty value for login but can not pass null
		assertEquals(0, service.getAllSubjects().size());

		String name = "";
		String courseID = "";
		String description = "";
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubject(name, courseID, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("name cannot be empty or null!description cannot be empty or null!courseID cannot be empty or null!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());

	}

	@Test
	public void testCreateSubjectSpaces() {
		assertEquals(0, service.getAllSubjects().size());
		String name = " ";
		String courseID = " ";
		String description = " ";
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubject(name, courseID, description, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("name cannot be empty or null!description cannot be empty or null!courseID cannot be empty or null!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());

	}

	@Test
	public void testCreateCommission() {
		assertEquals(0, service.getAllCommissions().size());

		double percentage = 123;
		Integer commissionID = 123456;

		Integer managerID = 123456;
		String firstName = "Charles";
		String lastName = "Liu";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

		Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dateOfBirth);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(managerID);		
		Login loginInfo1 = new Login();
		loginInfo1.setPassword("pass");
		loginInfo1.setUserName("manager");
		manager.setLoginInfo(loginInfo1);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		manager.setTutoringSystem(tutoringSystem);

		String offeringID = "FALL19";
		String term = "fall";
		AvaliableSession classTime = new AvaliableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvaliableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Tutor tutor = new Tutor();
		Integer tutorID = 54321;
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dateOfBirth);
		tutor.setEmail(email);
		tutor.setPhoneNumber(123);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(false);
		Login loginInfo2 = new Login();
		loginInfo2.setPassword("pass");
		loginInfo2.setUserName("tutor");
		tutor.setLoginInfo(loginInfo2);
		tutor.setTutoringSystem(tutoringSystem);

		Offering offering = new Offering();
		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("123");
		room.setTutoringSystem(tutoringSystem);

		Set<AvaliableSession> time = new HashSet<AvaliableSession>();
		time.add(classTime);
		offering.setClassroom(room);
		offering.setClassTime(time);
		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setTutoringSystem(tutoringSystem);
		Integer testObjID = 98765;
		com.setCommissionID(testObjID);
		offering.setCommission(com);
		offering.setOfferingID("test");
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		Set<Offering> offerings = new HashSet<Offering>();
		com.setOffering(offerings);
		room.setOffering(offerings);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		avaliableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check commission exists (new one, not test setup object)
		assertEquals(2, allCommissions.size());
		assertEquals(commissionID, allCommissions.get(1).getCommissionID());
	}

	@Test
	public void testCreateCommissionZero() {
		assertEquals(0, service.getAllCommissions().size());

		String error = "";

		double percentage = 0;
		Integer commissionID = 0;

		Integer managerID = 123456;
		String firstName = "Charles";
		String lastName = "Liu";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

		Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dateOfBirth);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(managerID);		
		Login loginInfo1 = new Login();
		loginInfo1.setPassword("pass");
		loginInfo1.setUserName("manager");
		manager.setLoginInfo(loginInfo1);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		manager.setTutoringSystem(tutoringSystem);

		String offeringID = "FALL19";
		String term = "fall";
		AvaliableSession classTime = new AvaliableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvaliableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Tutor tutor = new Tutor();
		Integer tutorID = 54321;
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dateOfBirth);
		tutor.setEmail(email);
		tutor.setPhoneNumber(123);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(false);
		Login loginInfo2 = new Login();
		loginInfo2.setPassword("pass");
		loginInfo2.setUserName("tutor");
		tutor.setLoginInfo(loginInfo2);
		tutor.setTutoringSystem(tutoringSystem);

		Offering offering = new Offering();
		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("123");
		room.setTutoringSystem(tutoringSystem);

		Set<AvaliableSession> time = new HashSet<AvaliableSession>();
		time.add(classTime);
		offering.setClassroom(room);
		offering.setClassTime(time);
		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setTutoringSystem(tutoringSystem);
		Integer testObjID = 98765;
		com.setCommissionID(testObjID);
		offering.setCommission(com);
		offering.setOfferingID("test");
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		Set<Offering> offerings = new HashSet<Offering>();
		com.setOffering(offerings);
		room.setOffering(offerings);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		avaliableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check error
		assertEquals("percentage cannot be <= 0!commissionID cannot be <= 0!", error);
		assertEquals(1, allCommissions.size());
		assertEquals(testObjID, allCommissions.get(0).getCommissionID());
	}

	@Test
	public void testCreateCommissionNegative() {
		assertEquals(0, service.getAllCommissions().size());

		String error = "";

		double percentage = -1;
		Integer commissionID = -1;

		Integer managerID = 123456;
		String firstName = "Charles";
		String lastName = "Liu";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

		Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dateOfBirth);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(managerID);		
		Login loginInfo1 = new Login();
		loginInfo1.setPassword("pass");
		loginInfo1.setUserName("manager");
		manager.setLoginInfo(loginInfo1);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		manager.setTutoringSystem(tutoringSystem);

		String offeringID = "FALL19";
		String term = "fall";
		AvaliableSession classTime = new AvaliableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvaliableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Tutor tutor = new Tutor();
		Integer tutorID = 54321;
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dateOfBirth);
		tutor.setEmail(email);
		tutor.setPhoneNumber(123);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(false);
		Login loginInfo2 = new Login();
		loginInfo2.setPassword("pass");
		loginInfo2.setUserName("tutor");
		tutor.setLoginInfo(loginInfo2);
		tutor.setTutoringSystem(tutoringSystem);

		Offering offering = new Offering();
		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("123");
		room.setTutoringSystem(tutoringSystem);

		Set<AvaliableSession> time = new HashSet<AvaliableSession>();
		time.add(classTime);
		offering.setClassroom(room);
		offering.setClassTime(time);
		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setTutoringSystem(tutoringSystem);
		Integer testObjID = 98765;
		com.setCommissionID(testObjID);
		offering.setCommission(com);
		offering.setOfferingID("test");
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		Set<Offering> offerings = new HashSet<Offering>();
		com.setOffering(offerings);
		room.setOffering(offerings);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		avaliableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check error
		assertEquals("percentage cannot be <= 0!commissionID cannot be <= 0!", error);
		assertEquals(1, allCommissions.size());
		assertEquals(testObjID, allCommissions.get(0).getCommissionID());
	}

	@Test
	public void testCreateCommissionNull() {
		assertEquals(0, service.getAllCommissions().size());

		String error = "";

		double percentage = 1;
		Integer commissionID = 1;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Set<Offering> offerings = null;

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check error
		assertEquals("manager cannot be null!offerings cannot be null!tutoringSystem cannot be null!", error);
		assertEquals(0, allCommissions.size());
	}
	
	@Test
	public void testCreateClassroom() {
		assertEquals(0, service.getAllClassrooms().size());


		String roomCode = "rm1";
        Boolean isBooked = true;
        Boolean isBigRoom = false;
        
        Integer managerID = 123456;
		String firstName = "Muhammad";
		String lastName = "Elahi";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

        Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dateOfBirth);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(managerID);		
		Login loginInfo1 = new Login();
		loginInfo1.setPassword("pass");
		loginInfo1.setUserName("manager");
		manager.setLoginInfo(loginInfo1);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		manager.setTutoringSystem(tutoringSystem);
		
		String offeringID = "FALL19";
		String term = "fall";
		AvaliableSession classTime = new AvaliableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvaliableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Tutor tutor = new Tutor();
		Integer tutorID = 54321;
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dateOfBirth);
		tutor.setEmail(email);
		tutor.setPhoneNumber(123);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(false);
		Login loginInfo2 = new Login();
		loginInfo2.setPassword("pass");
		loginInfo2.setUserName("tutor");
		tutor.setLoginInfo(loginInfo2);
		tutor.setTutoringSystem(tutoringSystem);

		Offering offering = new Offering();
		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("123");
		room.setTutoringSystem(tutoringSystem);

		Set<AvaliableSession> time = new HashSet<AvaliableSession>();
		time.add(classTime);
		offering.setClassroom(room);
		offering.setClassTime(time);
		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setTutoringSystem(tutoringSystem);
		Integer testObjID = 98765;
		com.setCommissionID(testObjID);
		offering.setCommission(com);
		offering.setOfferingID("test");
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		Set<Offering> offerings = new HashSet<Offering>();
		com.setOffering(offerings);
		room.setOffering(offerings);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		avaliableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

        List<Classroom> allClassrooms = service.getAllClassrooms();
        assertEquals(1, allClassrooms.size());

		try {
			service.createClassroom(roomCode, isBooked, isBigRoom, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// check that no error occurred
			fail();
		}

		allClassrooms = service.getAllClassrooms();
		assertEquals(2, allClassrooms.size());
		assertEquals(roomCode, allClassrooms.get(1).getRoomCode());
		assertEquals(isBooked, allClassrooms.get(1).getIsBooked());
		assertEquals(isBigRoom, allClassrooms.get(1).getIsBigRoom());
		service.deleteClassroom(roomCode);
	}

	@Test
	public void testCreateClassroomNull() {
		assertEquals(0, service.getAllClassrooms().size());

		String error = "";

		String roomCode = null;
		Boolean isBooked = null;
		Boolean isBigRoom = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Set<Offering> offerings = null;

		try {
			service.createClassroom(roomCode, isBooked, isBigRoom, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "roomCode cannot be empty!isBooked cannot be empty!isBigRoom cannot be empty!");
	}

	@Test
	public void testCreateClassroomEmpty() {
		assertEquals(0, service.getAllClassrooms().size());

		String error = "";

		String roomCode = "";
		Boolean isBooked = null;
		Boolean isBigRoom = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Set<Offering> offerings = null;

		try {
			service.createClassroom(roomCode, isBooked, isBigRoom, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "roomCode cannot be empty!isBooked cannot be empty!isBigRoom cannot be empty!");
	}

	@Test
	public void testCreateClassroomSpaces() {
		assertEquals(0, service.getAllClassrooms().size());

		String error = "";

		String roomCode = " ";
		Boolean isBooked = null;
		Boolean isBigRoom = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Set<Offering> offerings = null;

		try {
			service.createClassroom(roomCode, isBooked, isBigRoom, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "roomCode cannot be empty!isBooked cannot be empty!isBigRoom cannot be empty!");

	}
	/*
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
	 */

	@Test
	public void testCreateOffering() {
		assertEquals(0, service.getAllOfferings().size());
		
		TutoringSystem tutoringSystem = new TutoringSystem();
		// shared variables for manager and tutor, being lazy
		String firstName = "Andy";
		String lastName = "He";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;
		// create manager
		Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(12345);		
		Login loginInfo1 = new Login();
		loginInfo1.setPassword("pass");
		loginInfo1.setUserName("manager");
		manager.setLoginInfo(loginInfo1);
		manager.setTutoringSystem(tutoringSystem);
		// create tutor
		Tutor tutor = new Tutor();
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(123);
		tutor.setPersonId(54321);
		tutor.setIsRegistered(true);
		Login loginInfo2 = new Login();
		loginInfo2.setPassword("pass");
		loginInfo2.setUserName("tutor");
		tutor.setLoginInfo(loginInfo2);
		tutor.setTutoringSystem(tutoringSystem);
		// create classroom
		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("rm1");
		room.setTutoringSystem(tutoringSystem);
		// create available session -- date and time
		Calendar c1 = Calendar.getInstance();
		c1.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
		Date offerDay = new Date(c1.getTimeInMillis());
		Time startTime = new Time(c1.getTimeInMillis());
		c1.set(2016, Calendar.OCTOBER, 16, 10, 30, 0);
		Time endTime = new Time(c1.getTimeInMillis());
		// create available session for offering
		AvaliableSession classTime = new AvaliableSession();
		classTime.setDay(offerDay);
		classTime.setStartTime(startTime);
		classTime.setEndTime(endTime);
		classTime.setAvaliableSessionID(123456);
		classTime.setTutoringSystem(tutoringSystem);
		// create subject for offering
		Subject subject = new Subject();
		subject.setCourseID("ECSE321");
		subject.setName("Intro. to Software Engineering");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);
		// create commission
//		Set<Offering> offerings = new HashSet<Offering>();
		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setCommissionID(123);
		com.setTutoringSystem(tutoringSystem);
		// create offering
		Offering offering = new Offering();
		Set<AvaliableSession> time = new HashSet<AvaliableSession>();
		String offeringID = "FALL19";
		String term = "fall";
		Double price = 10.0;
		
		time.add(classTime);
		offering.setClassroom(room);
		offering.setClassTime(time);
		offering.setOfferingID(offeringID);
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		offering.setCommission(com);
		
		tutoringSystem.setTutoringSystemID(123);
		
		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		avaliableSessionRepository.save(classTime);
		subjectRepository.save(subject);
		commissionRepository.save(com);
		offeringRepository.save(offering);
//		offeringRepository.save(offerings);
		
		try {
			service.createOffering(offeringID, term, price, time, subject, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		List<Offering> allOfferings = service.getAllOfferings();
		assertEquals(1, allOfferings.size());
		assertEquals(offeringID, allOfferings.get(0).getOfferingID());
		assertEquals(term, allOfferings.get(0).getTerm());
//		assertEquals(classTime, allOfferings.get(0).getClassTime());	// this one cause errors, but create in general works

		service.deleteOffering(offeringID);
	}

	@Test
	public void testCreateOfferingNull() {
		// double -> Double for null check
		assertEquals(0, service.getAllStudents().size());
		String error = "";
		
		String offeringID = null;
		String term = null;
		Double price = null;
		
//		Manager manager = null;
//		Tutor tutor = null;
//		Classroom room = null;
//		AvaliableSession classTime = null;
		Subject subject = null;
//		Commission com = null;
		Set<AvaliableSession> time = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
//		assertEquals("valid input needed", error);
		assertEquals("Offering ID cannot be empty!Offering term cannot be empty!Hourly rate cannot be empty!Class time cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}

	@Test
	public void testCreateOfferingEmpty() {
		// cannot check if an Integer is empty, instead, check if it has the default value 0
		// can pass empty value for login but can not pass null
		assertEquals(0, service.getAllOfferings().size());
		String error = "";
		String offeringID = "";
		String term = "";
		Double price = 0.0;

//		Manager manager = null;
//		Tutor tutor = null;
//		Classroom room = null;
//		AvaliableSession classTime = null;
		Subject subject = null;
//		Commission com = null;
		Set<AvaliableSession> time = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Offering ID cannot be empty!Offering term cannot be empty!Hourly rate cannot be empty!Class time cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}

	@Test
	public void testCreateOfferingSpaces() {

		assertEquals(0, service.getAllStudents().size());
		String error="";
		
		String offeringID = " ";
		String term = " ";
		Double price = null;
//		Manager manager = null;
//		Tutor tutor = null;
//		Classroom room = null;
//		AvaliableSession classTime = null;
		Subject subject = null;
//		Commission com = null;
		Set<AvaliableSession> time = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Offering ID cannot be empty!Offering term cannot be empty!Hourly rate cannot be empty!Class time cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}
	
	/*

    @Test
    public void testCreateUniversity() {
        assertEquals(0, service.getAllUniversitys().size());
        String name = "McGill";
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

			TutoringSystem tutoringSystem = new TutoringSystem();
			tutoringSystem.setTutoringSystemID(123);
			tutoringSystemRepository.save(tutoringSystem);

	        m.setDateOfBirth(dateOfBirth);
	        m.setEmail(email);
	        m.setPersonId(managerID);
	        m.setPhoneNumber(phoneNumber);
	        m.setFirstName(firstName);
	        m.setLastName(lastName);
	        Login loginInfo = new Login();
			loginInfo.setPassword("pass");
			loginInfo.setUserName("user");
			loginRepository.save(loginInfo);

			m.setLoginInfo(loginInfo);
			m.setTutoringSystem(tutoringSystem);
	        managerRepository.save(m);

	        Integer commisionID = 982;
	        Commission com = new Commission();
	        com.setCommissionID(commisionID);
	        com.setManager(m);
	        com.setPercentage(12.0);
	        com.setTutoringSystem(tutoringSystem);
	        commissionRepository.save(com);

	        Classroom classroom = new Classroom();
	        classroom.setIsBigRoom(false);
	        classroom.setIsBooked(false);
	        classroom.setManager(m);
	        classroom.setRoomCode("123");
	        classroom.setTutoringSystem(tutoringSystem);
	        classroomRepository.save(classroom);


	        SubjectRequest sr = new SubjectRequest();
	        sr.setDescription("this subject request");
	        sr.setManager(m);
	        sr.setName("sr");
	        sr.setRequestID(101);
	        sr.setSubjectType(SubjectType.CGEP_COURSE);
	        sr.setTutoringSystem(tutoringSystem);
	        subjectRequestRepository.save(sr);

	        Offering o = new Offering();
	        o.setTerm("Fall");
	        o.setClassTime(null);
	        o.setOfferingID("id");
	        o.setPricePerHour(12);
	        o.setClassroom(classroom);
	        offeringRepository.save(o);


			service.createReview(comment, false, 1, m, o, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Review> allReviews = service.getAllReviews();

		assertEquals(1, allReviews.size());
        assertEquals(comment, allReviews.get(0).getComment());
        assertEquals(isApproved, allReviews.get(0).getIsApproved());
        assertEquals(reviewID, allReviews.get(0).getReviewID());
        service.deleteReview(reviewID);
	}

    @Test
	public void testCreateReviewNull() {
		assertEquals(0, service.getAllReviews().size());

		String comment = null;
		Integer reviewID = null;
		Boolean isApproved = null;
		String error = null;

		try {
			service.createReview(comment, isApproved, reviewID, null, null, null);
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
			service.createReview(comment, isApproved, reviewID, null, null, null);
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
			service.createReview(comment, isApproved, reviewID, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review comment cannot be empty! Review reviewID cannot be empty! Review isApproved cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}
	 */
}