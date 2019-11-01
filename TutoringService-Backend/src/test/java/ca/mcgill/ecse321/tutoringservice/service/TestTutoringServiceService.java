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


import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringServiceService {

	@Autowired
	private TutoringServiceService service;

	@Autowired
	private AvailableSessionRepository AvailableSessionRepository;

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
		AvailableSessionRepository.deleteAll();
		reviewRepository.deleteAll();
		studentRepository.deleteAll();
		tutorApplicationRepository.deleteAll();
		tutorRepository.deleteAll();
		loginRepository.deleteAll();
		universityRepository.deleteAll();
		tutoringSystemRepository.deleteAll();
	}

	private void setTutor(Tutor tutor, String first, String last, Date dob, String email, Integer phone, Integer tutorID, Boolean isRegistered, Login loginInfo, Set<TutorApplication> tutorApplications, Set<AvailableSession> availableSessions, Set<Offering> offerings,TutoringSystem tutoringSystem) {
		tutor.setFirstName(first);
		tutor.setLastName(last);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phone);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(isRegistered);
		tutor.setLoginInfo(loginInfo);
		tutor.setAvailableSession(availableSessions);
		tutor.setTutorApplication(tutorApplications);
		tutor.setOffering(offerings);
		tutor.setTutoringSystem(tutoringSystem);
	}

	private void setTutoringSystem(TutoringSystem tutoringSystem, Integer tutoringSystemID) {
		tutoringSystem.setTutoringSystemID(tutoringSystemID);
	}

	private void setLogin(Login login, String userName, String password) {
		login.setUserName(userName);
		login.setPassword(password);
	}

	/*
	 * Login
	 */
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

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

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

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

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

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

		List<Login> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}
	
	/*
	 * Student
	 */
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
			service.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, studentID, numCoursesEnrolled, loginInfo, null, null, tutoringSystem);
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
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Student firstname cannot be empty!Student lastname cannot be empty!Student DOB cannot be empty!Student email cannot be empty!"
				+ "Student phone cannot be empty!Student studentID cannot be empty!Login needs to be selected for Student!TutoringSystem needs to be selected for Student!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

	}

	@Test
	public void testCreateStudentEmpty() {
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
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Student firstname cannot be empty!Student lastname cannot be empty!Student email cannot be empty!"
				+ "Student phone cannot be empty!Student studentID cannot be <= 0!Login needs to be selected for Student!TutoringSystem needs to be selected for Student!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

	}


	@Test
	public void testCreateStudentSpaces() {
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
			service.createStudent(first, last, dob, email,phone, studentID, numCoursesEnrolled, loginInfo, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Student firstname cannot be empty!Student lastname cannot be empty!Student email cannot be empty!Student phone cannot be empty!Student studentID cannot be <= 0!Login needs to be selected for Student!TutoringSystem needs to be selected for Student!", error);

		// check no change in memory
		assertEquals(0, service.getAllStudents().size());

	}

	/*
	 * Manager
	 */
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
			service.createManager(firstName, lastName, dateOfBirth, email, phoneNumber, managerID, loginInfo, null, null, null, null, tutoringSystem);
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
			service.createManager(first, last, dob, email, phone, managerID, loginInfo, null, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error    	
		assertEquals("Manager firstname cannot be empty!Manager lastname cannot be empty!Manager date of birth cannot be empty!Manager email cannot be empty!Manager phone cannot be empty!"
				+ "Manager managerID cannot be empty!Login needs to be selected for Manager!TutoringSystem needs to be selected for Manager!", error);

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
			service.createManager(first, last, dob, email, phone, managerID, loginInfo,  null, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Manager firstname cannot be empty!Manager lastname cannot be empty!Manager email cannot be empty!Manager phone cannot be <= 0!"
				+ "Manager managerID cannot be empty!Login needs to be selected for Manager!TutoringSystem needs to be selected for Manager!", error);
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
			service.createManager(first, last, dob, email, phone, managerID, loginInfo, null, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Manager firstname cannot be empty!Manager lastname cannot be empty!Manager email cannot be empty!"
				+ "Manager phone cannot be <= 0!Manager managerID cannot be empty!Login needs to be selected for Manager!TutoringSystem needs to be selected for Manager!", error);

		// check no change in memory
		assertEquals(0, service.getAllManagers().size());

	}

	/*
	 * Tutor
	 */
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
			service.createTutor(firstName, lastName, dateOfBirth, email, phoneNumber, tutorID, isRegistered, loginInfo, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(1, allTutors.size());
		assertEquals(tutorID, allTutors.get(0).getPersonId());
		assertEquals(dateOfBirth.toString(), allTutors.get(0).getDateOfBirth().toString());
		assertEquals(isRegistered, allTutors.get(0).getIsRegistered());
		assertEquals(firstName, allTutors.get(0).getFirstName());
		assertEquals(lastName, allTutors.get(0).getLastName());
		assertEquals(email, allTutors.get(0).getEmail());
		assertEquals(phoneNumber, allTutors.get(0).getPhoneNumber());
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
			service.createTutor(first, last, dob, email, phone, tutorId, isRegistered, loginInfo, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error

		assertEquals("Tutor firstname cannot be empty!Tutor lastname cannot be empty!Tutor DOB cannot be empty!Tutor email cannot be empty!Tutor phone cannot be empty!"
				+ "Tutor tutorID cannot be empty!Login needs to be selected for Tutor!TutoringSystem  needs to be selected for Tutor!isRegistered cannot be empty", error);

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
			service.createTutor(first, last, dob, email,phone, tutorID, isRegistered, loginInfo, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}


		// check error
		assertEquals("Tutor firstname cannot be empty!Tutor lastname cannot be empty!Tutor email cannot be empty!Tutor phone cannot be <= 0!"
				+ "Tutor tutorID cannot be empty!Login needs to be selected for Tutor!TutoringSystem  needs to be selected for Tutor!isRegistered cannot be empty", error);
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
			service.createTutor(first, last, dob, email,phone, tutorID, isRegistered, loginInfo, null, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Tutor firstname cannot be empty!Tutor lastname cannot be empty!Tutor email cannot be empty!Tutor phone cannot be <= 0!"
				+ "Tutor tutorID cannot be empty!Login needs to be selected for Tutor!TutoringSystem  needs to be selected for Tutor!isRegistered cannot be empty", error);

		// check no change in memory
		assertEquals(0, service.getAllTutors().size());

	}

	/*
	 * Available Session
	 */
	@Test
	public void testCreateAvailableSession() {
		assertEquals(0, service.getAllAvailableSessions().size());

		Calendar c = Calendar.getInstance();

		//tutoringSystem
		Integer tutoringSystemID = 5;
		TutoringSystem tutoringSystem = new TutoringSystem();
		setTutoringSystem(tutoringSystem, tutoringSystemID);

		//login
		String userName = "adeis";
		String password = "locked";
		Login login  = new Login();
		setLogin(login, userName, password);

		//availableSession
		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date day = new Date(c.getTimeInMillis());
		LocalTime startTime = LocalTime.parse("09:00");
		c.set(2017, Calendar.MARCH, 16, 10, 30, 0);
		LocalTime endTime = LocalTime.parse("10:30");
		Integer AvailableSessionID = 5;

		//tutors
		String first = "Anas";
		String last = "Deis";
		Date dob = new Date(c.getTimeInMillis());
		String email = "anas.deis@mail.mcgill.ca";
		Integer phone = 514;
		Integer tutorID = 1254;
		Boolean isRegistered = false;
		Tutor tutor = new Tutor();
		setTutor(tutor, first, last, dob, email, phone, tutorID, isRegistered, login, null, null, null, tutoringSystem);
		Set<Tutor> tutors = new HashSet<Tutor>();
		tutors.add(tutor);

		//save
		loginRepository.save(login);
		tutoringSystemRepository.save(tutoringSystem);
		tutorRepository.save(tutor);

		try {
			service.createAvailableSession(Time.valueOf(startTime), Time.valueOf(endTime), AvailableSessionID, day, tutors, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		//Available Session
		assertEquals(1, service.getAllAvailableSessions().size());
		assertEquals(AvailableSessionID, service.getAllAvailableSessions().get(0).getAvailableSessionID());
		assertEquals(day.toString(), service.getAllAvailableSessions().get(0).getDay().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		assertEquals(startTime.format(formatter).toString(), service.getAllAvailableSessions().get(0).getStartTime().toString());
		assertEquals(endTime.format(formatter).toString(), service.getAllAvailableSessions().get(0).getEndTime().toString());

		// Need to assert by ID using foreign key for TutoringSystem (in this case tutoringSystemsID @ManyToOne
		//No foreign key for Tutor
		assertEquals(service.getAllTutoringSystems().get(0).getTutoringSystemID(), service.getAllAvailableSessions().get(0).getTutoringSystem().getTutoringSystemID());

		//Tutor
		assertEquals(1, service.getAllTutors().size());
		assertEquals(first, service.getAllTutors().get(0).getFirstName());
		assertEquals(last, service.getAllTutors().get(0).getLastName());
		assertEquals(dob.toString(), service.getAllTutors().get(0).getDateOfBirth().toString());
		assertEquals(email, service.getAllTutors().get(0).getEmail());
		assertEquals(phone, service.getAllTutors().get(0).getPhoneNumber());
		assertEquals(tutorID, service.getAllTutors().get(0).getPersonId());
		assertEquals(isRegistered, service.getAllTutors().get(0).getIsRegistered());
		assertEquals(login.getPassword(), service.getAllTutors().get(0).getLoginInfo().getPassword());
		assertEquals(login.getUserName(), service.getAllTutors().get(0).getLoginInfo().getUserName());
		assertEquals(tutoringSystemID, service.getAllTutors().get(0).getTutoringSystem().getTutoringSystemID());

		//TutoringSystem
		assertEquals(1, service.getAllTutoringSystems().size());
		assertEquals(tutoringSystemID, service.getAllTutoringSystems().get(0).getTutoringSystemID());
	}

	@Test
	public void testCreateAvailableSessionNull() {
		assertEquals(0, service.getAllAvailableSessions().size());

		//tutoringSystem
		TutoringSystem tutoringSystem = null;
		assertEquals(0, service.getAllTutoringSystems().size());

		//tutors
		Tutor tutor = null;
		assertEquals(0, service.getAllTutors().size());

		Set<Tutor> tutors = new HashSet<Tutor>();
		tutors.add(tutor);

		//availableSession
		Date day = null;
		Time startTime = null;
		Time endTime = null;
		Integer AvailableSessionID = null;


		String error = null;
		try {
			service.createAvailableSession(startTime, endTime, AvailableSessionID, day, tutors, tutoringSystem); 
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("AvailableSession availableSessionID cannot be empty!AvailableSession start time cannot be empty!AvailableSession end time cannot be empty!AvailableSession day cannot be empty!TutoringSystem needs to be selected for AvailableSession!Tutor needs to be selected for AvailableSession!",error);
		// check model in memory
		assertEquals(0, service.getAllAvailableSessions().size());
		assertEquals(0, service.getAllTutors().size());
		assertEquals(0, service.getAllTutoringSystems().size());
	}


	@Test
	public void testCreateAvailableSessionEndTimeBeforeStartTime() {
		assertEquals(0, service.getAllAvailableSessions().size());

		Calendar c = Calendar.getInstance();

		//tutoringSystem
		Integer tutoringSystemID = 5;
		TutoringSystem tutoringSystem = new TutoringSystem();
		setTutoringSystem(tutoringSystem, tutoringSystemID);

		//login
		String userName = "adeis";
		String password = "locked";
		Login login  = new Login();
		setLogin(login, userName, password);

		//availableSession
		Integer AvailableSessionID = 15;
		c.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
		Date day = new Date(c.getTimeInMillis());
		LocalTime startTime = LocalTime.parse("09:00");
		c.set(2016, Calendar.OCTOBER, 16, 8, 59, 59);
		LocalTime endTime = LocalTime.parse("08:59");

		//tutors
		String first = "Anas";
		String last = "Deis";
		Date dob = new Date(c.getTimeInMillis());
		String email = "anas.deis@mail.mcgill.ca";
		Integer phone = 514;
		Integer tutorID = 1254;
		Boolean isRegistered = false;
		Tutor tutor = new Tutor();
		setTutor(tutor, first, last, dob, email, phone, tutorID, isRegistered, login, null, null, null, tutoringSystem);
		Set<Tutor> tutors = new HashSet<Tutor>();
		tutors.add(tutor);

		//save
		loginRepository.save(login);
		tutoringSystemRepository.save(tutoringSystem);
		tutorRepository.save(tutor);

		String error = null;
		try {
			service.createAvailableSession(Time.valueOf(startTime), Time.valueOf(endTime), AvailableSessionID, day, tutors, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("AvailableSession end time cannot be before event start time!", error);
		// check model in memory
		assertEquals(0, service.getAllAvailableSessions().size());

		//Tutor
		assertEquals(1, service.getAllTutors().size());
		assertEquals(first, service.getAllTutors().get(0).getFirstName());
		assertEquals(last, service.getAllTutors().get(0).getLastName());
		assertEquals(dob.toString(), service.getAllTutors().get(0).getDateOfBirth().toString());
		assertEquals(email, service.getAllTutors().get(0).getEmail());
		assertEquals(phone, service.getAllTutors().get(0).getPhoneNumber());
		assertEquals(tutorID, service.getAllTutors().get(0).getPersonId());
		assertEquals(isRegistered, service.getAllTutors().get(0).getIsRegistered());
		assertEquals(login.getPassword(), service.getAllTutors().get(0).getLoginInfo().getPassword());
		assertEquals(login.getUserName(), service.getAllTutors().get(0).getLoginInfo().getUserName());
		assertEquals(tutoringSystemID, service.getAllTutors().get(0).getTutoringSystem().getTutoringSystemID());

		//TutoringSystem
		assertEquals(1, service.getAllTutoringSystems().size());
		assertEquals(tutoringSystemID, service.getAllTutoringSystems().get(0).getTutoringSystemID());
	}

	@Test
	public void testAvailableSessionTutorAndTutoringSystemDoNotExist() {
		assertEquals(0, service.getAllAvailableSessions().size());

		Calendar c = Calendar.getInstance();

		//tutoringSystem
		Integer tutoringSystemID = 5;
		TutoringSystem tutoringSystem = new TutoringSystem();
		setTutoringSystem(tutoringSystem, tutoringSystemID);
		assertEquals(0, service.getAllTutoringSystems().size());

		//availableSession
		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date day = new Date(c.getTimeInMillis());
		LocalTime startTime = LocalTime.parse("09:00");
		c.set(2017, Calendar.MARCH, 16, 10, 30, 0);
		LocalTime endTime = LocalTime.parse("10:30");
		Integer AvailableSessionID = 5;

		//login
		String userName = "adeis";
		String password = "locked";
		Login login  = new Login();
		setLogin(login, userName, password);

		//tutors
		String first = "Anas";
		String last = "Deis";
		Date dob = new Date(c.getTimeInMillis());
		String email = "anas.deis@mail.mcgill.ca";
		Integer phone = 514;
		Integer tutorID = 1254;
		Boolean isRegistered = false;
		Tutor tutor = new Tutor();
		setTutor(tutor, first, last, dob, email, phone, tutorID, isRegistered, login, null, null, null, tutoringSystem);
		Set<Tutor> tutors = new HashSet<Tutor>();
		tutors.add(tutor);
		assertEquals(0, service.getAllTutors().size());

		String error = null;
		try {
			service.createAvailableSession(Time.valueOf(startTime), Time.valueOf(endTime), AvailableSessionID, day, tutors, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("TutoringSystem does not exist!Tutor does not exist!", error);

		// check model in memory
		assertEquals(0, service.getAllAvailableSessions().size());
		assertEquals(0, service.getAllTutors().size());
		assertEquals(0, service.getAllTutoringSystems().size());
	}

	/*
	 * Subject Request
	 */
	@Test
	public void testCreateSubjectRequest() {
		assertEquals(0, service.getAllSubjectRequests().size());
		Integer requestID = 789456;
		String name = "Math240";
		String description = "Discrete structures";

		Integer managerID = 100200;
		String firstName = "Tom";
		String lastName = "Jerry";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MAY, 20, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 1234567890;

		Manager manager = new Manager();
		manager.setFirstName(firstName);
		manager.setLastName(lastName);
		manager.setDateOfBirth(dateOfBirth);
		manager.setEmail(email);
		manager.setPhoneNumber(phoneNumber);
		manager.setPersonId(managerID);		
		Login loginInfo = new Login();
		loginInfo.setPassword("pass");
		loginInfo.setUserName("manager");
		manager.setLoginInfo(loginInfo);
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(1234);
		manager.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo);
		managerRepository.save(manager);


		try {
			service.createSubjectRequest(requestID, name, description, subjectType, manager, null,  tutoringSystem);
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
		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		SubjectType subjectType = null;

		String error = null;
		try {
			service.createSubjectRequest(requestID, name, description, subjectType, manager, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check no change in memory
		assertEquals(0, service.getAllSubjectRequests().size());

		// check error
		assertEquals("SubjectRequest name cannot be empty!SubjectRequest description cannot be empty!SubjectRequest requestID cannot be empty!SubjectRequest subjectType cannot be empty!Manager needs to be selected for SubjectRequest!TutoringSystem needs to be selected for SubjectRequest!", error);
	}


	@Test
	public void testCreateSubjectRequestEmpty() {
		assertEquals(0, service.getAllSubjectRequests().size());

		Integer requestID = 0;
		String name = "";
		String description = "";
		Manager manager = null;
		Integer tssID= 123;
		SubjectType subjectType = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(tssID);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;

		try {
			service.createSubjectRequest(requestID, name, description,subjectType, manager, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(0, service.getAllSubjectRequests().size());

		// check error
		assertEquals("SubjectRequest name cannot be empty!SubjectRequest description cannot be empty!SubjectRequest requestID cannot be <= 0!"
				+ "SubjectRequest subjectType cannot be empty!Manager needs to be selected for SubjectRequest!", error);
	}


	@Test
	public void testCreateSubjectRequestSpaces() {
		assertEquals(0, service.getAllSubjectRequests().size());

		Integer requestID = 0;
		String name = "   ";
		String description = "   ";
		Manager manager = null;
		Integer tssID= 456;
		SubjectType subjectType = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(tssID);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;

		try {
			service.createSubjectRequest(requestID, name, description,subjectType,  manager, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(0, service.getAllSubjectRequests().size());

		// check error
		assertEquals("SubjectRequest name cannot be empty!SubjectRequest description cannot be empty!SubjectRequest requestID cannot be <= 0!SubjectRequest subjectType cannot be empty!Manager needs to be selected for SubjectRequest!", error);
	}	

	/*
	 * Subject
	 */
	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubjects().size());
		String name = "Math240";
		String courseID = "MATH240FALL";
		String description = "Discrete structures";
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		University university = new University();
		university.setName("McGill");
		university.setTutoringSystem(tutoringSystem);
		tutoringSystemRepository.save(tutoringSystem);
		universityRepository.save(university);
		try {
			service.createSubject(name, courseID, description, subjectType, university,  null, null, tutoringSystem);
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
		SubjectType subjectType = null;
		University university = null;
		TutoringSystem tutoringSystem = null;

		String error = null;
		try {
			service.createSubject(name, courseID, description, subjectType, university, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Subject name cannot be empty!Subject description cannot be empty!Subject courseID cannot be empty!Subject subjectType cannot be empty!TutoringSystem needs to be selected for Subject!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());
	}


	@Test
	public void testCreateSubjectEmpty() {
		assertEquals(0, service.getAllSubjects().size());

		String name = "";
		String courseID = "";
		String description = "";
		SubjectType subjectType = null;
		University university = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubject(name, courseID, description, subjectType, university, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Subject name cannot be empty!Subject description cannot be empty!Subject courseID cannot be empty!Subject subjectType cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());

	}

	@Test
	public void testCreateSubjectSpaces() {
		assertEquals(0, service.getAllSubjects().size());
		String name = " ";
		String courseID = " ";
		String description = " ";
		SubjectType subjectType = null;
		University university = null;
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(778);
		tutoringSystemRepository.save(tutoringSystem);

		String error = null;
		try {
			service.createSubject(name, courseID, description, subjectType, university,  null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Subject name cannot be empty!Subject description cannot be empty!Subject courseID cannot be empty!Subject subjectType cannot be empty!", error);

		// check no change in memory
		assertEquals(0, service.getAllSubjects().size());

	}

	/*
	 * Commission
	 */
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
		AvailableSession classTime = new AvailableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvailableSessionID(123456);
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

		Set<AvailableSession> time = new HashSet<AvailableSession>();
//		time.add(classTime);
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
		offering.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		AvailableSessionRepository.save(classTime);
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
		AvailableSession classTime = new AvailableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvailableSessionID(1234);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setSubjectType(SubjectType.HIGH_SCHOOL_COURSE);
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
		room.setRoomCode("rm123");
		room.setTutoringSystem(tutoringSystem);

		Set<AvailableSession> time = new HashSet<AvailableSession>();
//		time.add(classTime);
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
		offering.setTutoringSystem(tutoringSystem);
		
		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		AvailableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check error
		assertEquals("Commission percentage cannot be <= 0!Commission commissionID cannot be <= 0!", error);
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
		AvailableSession classTime = new AvailableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvailableSessionID(123456);
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

		Set<AvailableSession> time = new HashSet<AvailableSession>();
//		time.add(classTime);
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
		offering.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		AvailableSessionRepository.save(classTime);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Commission> allCommissions = service.getAllCommissions();

		// check error
		assertEquals("Commission percentage cannot be <= 0!Commission commissionID cannot be <= 0!", error);
		assertEquals(1, allCommissions.size());
		assertEquals(testObjID, allCommissions.get(0).getCommissionID());
	}

	@Test
	public void testCreateCommissionNull() {
		assertEquals(0, service.getAllCommissions().size());

		String error = "";

		double percentage = 1;
		Integer commissionID = null;

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
		assertEquals("Commission commissionID cannot be empty!Manager needs to be selected for Commission!TutoringSystem needs to be selected for Commission!", error);
		assertEquals(0, allCommissions.size());
	}

	/*
	 * Classroom
	 */
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
		AvailableSession classTime = new AvailableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvailableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setDescription("None");
		subject.setSubjectType(SubjectType.HIGH_SCHOOL_COURSE);
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

		Set<AvailableSession> time = new HashSet<AvailableSession>();
//		time.add(classTime);
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
		offering.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		subjectRepository.save(subject);
		AvailableSessionRepository.save(classTime);
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
		assertEquals(error, "Classroom roomCode cannot be empty!Classroom isBooked cannot be empty!Classroom isBigRoom cannot be empty!Manager needs to be selected for Classroom!TutoringSystem needs to be selected for Classroom!");
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
		assertEquals(error, "Classroom roomCode cannot be empty!Classroom isBooked cannot be empty!Classroom isBigRoom cannot be empty!Manager needs to be selected for Classroom!TutoringSystem needs to be selected for Classroom!");
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
		assertEquals(error, "Classroom roomCode cannot be empty!Classroom isBooked cannot be empty!Classroom isBigRoom cannot be empty!Manager needs to be selected for Classroom!TutoringSystem needs to be selected for Classroom!");
	}

	/* 
	 * University
	 */
	@Test
	public void testCreateUniversity() {
		assertEquals(0, service.getAllUniversitys().size());
		//First create a TutoringSystem object, every object created from now on show link to this object (except for Offering)
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);

		Subject subject = new Subject();
		String subjectName = "Math240";
		String courseID = "MATH240FALL";
		String description = "Discrete structures";
		subject.setCourseID(courseID);
		subject.setDescription(description);
		subject.setName(subjectName);
		subject.setTutoringSystem(tutoringSystem);
		Set<Subject> subjects = new HashSet<Subject>();
		subjects.add(subject);

		tutoringSystemRepository.save(tutoringSystem);
		subjectRepository.save(subject);

		//Apparently I don't have to create Offering object to pass the test. 
		//I don't know why. I will leave this for future use. 
		/*
		//First create an University object
        University university = new University();
        university.setName(universityName);
        university.setTutoringSystem(tutoringSystem);	//set TutoringSystem
        //There are three associations in University class, we have covered two, 
        //the last one is covered below.  

		// University 1--* Subject
        // we need to create a set of Subjects
        //There are 6 associations in Subject class, TutoringSystem (covered here), SubjectType & TutorApplication (Don't care for now), 
        // university (covered below), model(don't care), offering (not covered)
        Subject subject = new Subject();
        String subjectName = "Math240";
		String courseID = "MATH240FALL";
		String description = "Discrete structures";
		subject.setTutoringSystem(tutoringSystem);
		Set<Subject> subjects = new HashSet<Subject>();
		subjects.add(subject);
		university.setSubject(subjects);


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
		manager.setTutoringSystem(tutoringSystem);	

		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("123");
		room.setTutoringSystem(tutoringSystem);


		Offering offering = new Offering();
		offering.setClassroom(room);
		offering.setClassTime(time);
		offering.setSubject(subject);
		 */


		try {
			service.createUniversity(subjectName, subjects, tutoringSystem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		List<University> allUniversitys = service.getAllUniversitys();
		assertEquals(1, allUniversitys.size());
		assertEquals(subjectName, allUniversitys.get(0).getName());
		service.deleteUniversity(subjectName);
	}


	@Test
	public void testCreateUniversityNull() {
		assertEquals(0, service.getAllUniversitys().size());

		String error = "";

		String name = null;
		TutoringSystem tutoringSystem = null;
		Set<Subject> subjects = null;

		try {
			service.createUniversity(name, subjects, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "University name cannot be empty!TutoringSystem needs to be selected for University!");
	}


	@Test
	public void testCreateUniversityEmpty() {
		assertEquals(0, service.getAllUniversitys().size());

		String error = "";

		String name = "";
		TutoringSystem tutoringSystem = null;
		Set<Subject> subjects = null;

		try {
			service.createUniversity(name, subjects, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "University name cannot be empty!TutoringSystem needs to be selected for University!");
	}


	@Test
	public void testCreateUniversitySpaces() {
		assertEquals(0, service.getAllUniversitys().size());

		String error = "";

		String name = "   ";
		TutoringSystem tutoringSystem = null;
		Set<Subject> subjects = null;

		try {
			service.createUniversity(name, subjects, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Classroom>allClassrooms = service.getAllClassrooms();
		assertEquals(0, allClassrooms.size());
		assertEquals(error, "University name cannot be empty!TutoringSystem needs to be selected for University!");
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

	/* 
	 * Offering
	 */
	@Test
	public void testCreateOffering() {
		assertEquals(0, service.getAllOfferings().size());

		TutoringSystem tutoringSystem = new TutoringSystem();
//		tutoringSystem.setTutoringSystemID(123);


		String firstName = "Andy";
		String lastName = "He";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

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

		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("rm1");
		room.setTutoringSystem(tutoringSystem);

		Calendar c1 = Calendar.getInstance();
		c1.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
		Date offerDay = new Date(c1.getTimeInMillis());
		Time startTime = new Time(c1.getTimeInMillis());
		c1.set(2016, Calendar.OCTOBER, 16, 10, 30, 0);
		Time endTime = new Time(c1.getTimeInMillis());

		AvailableSession classTime = new AvailableSession();
		classTime.setDay(offerDay);
		classTime.setStartTime(startTime);
		classTime.setEndTime(endTime);
		classTime.setAvailableSessionID(123456);
		classTime.setTutoringSystem(tutoringSystem);

		Subject subject = new Subject();
		subject.setCourseID("ECSE321");
		subject.setName("Intro. to Software Engineering");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setCommissionID(123);
		com.setTutoringSystem(tutoringSystem);
		
		Offering offering = new Offering();
		Set<AvailableSession> time = new HashSet<AvailableSession>();
		String offeringID = "FALL19";
		String term = "fall";
		Double price = 10.0;

//		time.add(classTime);
		//offering.setClassroom(room);
		offering.setClassTime(time);
		offering.setOfferingID(offeringID);
		offering.setSubject(subject);
		offering.setTerm(term);
		//offering.setTutor(tutor);
		//offering.setCommission(com);
		offering.setTutoringSystem(tutoringSystem);
		
		tutoringSystem.setTutoringSystemID(123);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		AvailableSessionRepository.save(classTime);
		subjectRepository.save(subject);
		commissionRepository.save(com);

		try {
			service.createOffering(offeringID, term, price, time, subject, tutor, com, room,  null, null, tutoringSystem);
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
	public void testCreateOfferingNull() {
		assertEquals(0, service.getAllStudents().size());
		String error = "";

		String offeringID = null;
		String term = null;
		double price = 0.0;
		Subject subject = null;
		Set<AvailableSession> time = null;
		Tutor tutor = null;
		Classroom room = null;
		Commission com = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject,tutor, com, room, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Offering offeringID cannot be empty!Offering term cannot be empty!Offering price per hour cannot be <= 0!Offering class time cannot be empty!Subject needs to be selected for Offering!"
				+ "Tutor needs to be selected for Offering!Commission needs to be selected for Offering!Classroom needs to be selected for Offering!TutoringSystem needs to be selected for Offering!", error);

		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}

	@Test
	public void testCreateOfferingEmpty() {
		assertEquals(0, service.getAllOfferings().size());
		String error = "";
		String offeringID = "";
		String term = "";
		double price = 0.0;
		Subject subject = null;
		Set<AvailableSession> time = null;
		Tutor tutor = null;
		Classroom room = null;
		Commission com = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject, tutor, com, room, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Offering offeringID cannot be empty!Offering term cannot be empty!Offering price per hour cannot be <= 0!Offering class time cannot be empty!Subject needs to be selected for Offering!"
				+ "Tutor needs to be selected for Offering!Commission needs to be selected for Offering!Classroom needs to be selected for Offering!TutoringSystem needs to be selected for Offering!", error);
		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}

	@Test
	public void testCreateOfferingSpaces() {

		assertEquals(0, service.getAllStudents().size());
		String error="";

		String offeringID = " ";
		String term = " ";
		double price = 0.0;
		Subject subject = null;
		Set<AvailableSession> time = null;
		Tutor tutor = null;
		Classroom room = null;
		Commission com = null;

		TutoringSystem tutoringSystem = null;

		try {
			service.createOffering(offeringID, term, price, time, subject, tutor, com, room, null, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Offering offeringID cannot be empty!Offering term cannot be empty!Offering price per hour cannot be <= 0!Offering class time cannot be empty!Subject needs to be selected for Offering!Tutor needs to be selected for Offering!Commission needs to be selected for Offering!"
				+ "Classroom needs to be selected for Offering!TutoringSystem needs to be selected for Offering!", error);

		// check no change in memory
		assertEquals(0, service.getAllOfferings().size());

	}

	/*
	 * Review
	 */
	@Test
	public void testCreateReview() {
		assertEquals(0, service.getAllReviews().size());

		String comment = "Default comment";
		Boolean isApproved = false;
		Integer reviewID  = 10;

		Integer managerID = 123456;
		String firstName = "omar";
		String lastName = "noor";
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
		AvailableSession classTime = new AvailableSession();
		classTime.setDay(dateOfBirth);
		classTime.setTutoringSystem(tutoringSystem);
		classTime.setAvailableSessionID(123456);
		Subject subject = new Subject();
		subject.setCourseID(offeringID);
		subject.setName("12233");
		subject.setSubjectType(SubjectType.HIGH_SCHOOL_COURSE);
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

		Set<AvailableSession> time = new HashSet<AvailableSession>();
//		time.add(classTime);
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
		offering.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);  //ok , through offering
		classroomRepository.save(room); //ok , through offering
		subjectRepository.save(subject); //ok , through offering
		AvailableSessionRepository.save(classTime); // ok , IN offering
		commissionRepository.save(com);
		offeringRepository.save(offering);  

		try {   
			service.createReview(comment, isApproved, reviewID, manager, offering, tutoringSystem);
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

		String error = "";
		String comment = null;
		Integer reviewID = null;
		Boolean isApproved = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Offering offering = null;

		try {
			service.createReview(comment, isApproved, reviewID, manager, offering, tutoringSystem );
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review reviewID cannot be empty!Review comment cannot be empty!Review isApproved cannot be empty!Manager needs to be selected for Review!Offering needs to be selected for Review!tutoringSystem needs to be selected for Review!", error);

		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}

	@Test
	public void testCreateReviewEmpty() {
		assertEquals(0, service.getAllReviews().size());

		String error = "";
		String comment = "";
		Integer reviewID = null;
		Boolean isApproved = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Offering offering = null;

		try {
			service.createReview(comment, isApproved, reviewID, manager, offering, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review reviewID cannot be empty!Review comment cannot be empty!Review isApproved cannot be empty!Manager needs to be selected for Review!Offering needs to be selected for Review!tutoringSystem needs to be selected for Review!", error);


		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}

	@Test
	public void testCreateReviewSpaces() {
		assertEquals(0, service.getAllReviews().size());

		String error = "";
		String comment = " ";
		Integer reviewID = null;
		Boolean isApproved = null;

		Manager manager = null;
		TutoringSystem tutoringSystem = null;
		Offering offering = null;

		try {
			service.createReview(comment, isApproved, reviewID, manager, offering, tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Review reviewID cannot be empty!Review comment cannot be empty!Review isApproved cannot be empty!Manager needs to be selected for Review!Offering needs to be selected for Review!tutoringSystem needs to be selected for Review!", error);

		// check no change in memory
		assertEquals(0, service.getAllReviews().size());

	}
	
	/*
	 * Tutor Application
	 */
	@Test
	public void testCreateTutorApplication() {
		assertEquals(0, service.getAllTutorApplications().size());

		Integer applicationId = 123;
		Boolean isAccepted = false;

		Integer tutorID = 54321;
		String firstName = "omar";
		String lastName = "noor";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dateOfBirth = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

		Tutor tutor = new Tutor();
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setDateOfBirth(dateOfBirth);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phoneNumber);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(false);
		Login loginInfo = new Login();
		loginInfo.setPassword("pass");
		loginInfo.setUserName("tutor");
		tutor.setLoginInfo(loginInfo);
		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);
		tutor.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo);
		tutorRepository.save(tutor);

		try {
			service.createTutorApplication(applicationId,isAccepted,tutor, null, tutoringSystem);
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<TutorApplication> allTutorApplications = service.getAllTutorApplications();
		assertEquals(1, allTutorApplications.size());
		assertEquals(applicationId, allTutorApplications.get(0).getApplicationId());
		assertEquals(isAccepted, allTutorApplications.get(0).getIsAccepted());
		service.deleteTutorApplication(applicationId);
	}

	@Test
	public void testCreateTutorApplicationNull() {
		assertEquals(0, service.getAllTutorApplications().size());
		String error = "";
		Integer applicationId = null;
		Boolean isAccepted = null;		
		Tutor tutor = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutorApplication(applicationId,isAccepted,tutor,null,tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}


		// check error
		assertEquals("TutorApplication applicationId cannot be empty!TutorApplication isAccepted cannot be null!Tutor needs to be selected for TutorApplication!TutoringSystem needs to be selected for TutorApplication!", error);

		// check no change in memory
		assertEquals(0, service.getAllTutorApplications().size());
	}

	@Test
	public void testCreateTutorApplicationEmpty() {
		assertEquals(0, service.getAllTutorApplications().size());
		String error = "";
		Integer applicationId = null;
		Boolean isAccepted = null;		
		Tutor tutor = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutorApplication(applicationId,isAccepted,tutor,null,tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}


		// check error
		assertEquals("TutorApplication applicationId cannot be empty!TutorApplication isAccepted cannot be null!Tutor needs to be selected for TutorApplication!TutoringSystem needs to be selected for TutorApplication!", error);

		// check no change in memory
		assertEquals(0, service.getAllTutorApplications().size());	
	}

	@Test
	public void testCreateTutorApplicationSpaces() {
		assertEquals(0, service.getAllTutorApplications().size());
		String error = "";
		Integer applicationId = null;
		Boolean isAccepted = null;		
		Tutor tutor = null;
		TutoringSystem tutoringSystem = null;

		try {
			service.createTutorApplication(applicationId,isAccepted,tutor,null,tutoringSystem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}


		// check error
		assertEquals("TutorApplication applicationId cannot be empty!TutorApplication isAccepted cannot be null!Tutor needs to be selected for TutorApplication!TutoringSystem needs to be selected for TutorApplication!", error);

		// check no change in memory
		assertEquals(0, service.getAllTutorApplications().size());
	}
	
	@Test
	public void testCreateTutoringSystem() {
		assertEquals(0, service.getAllLogins().size());
	
		Integer tsID = 123;
		
		try {
			service.createTutoringSystem(tsID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<TutoringSystem> allTutoringSystem = service.getAllTutoringSystems();
		assertEquals(tsID, allTutoringSystem.get(0).getTutoringSystemID());
		service.deleteTutoringSystem(tsID);
	}
	
	@Test
	public void testCreateTutoringSystemNull() {
		assertEquals(0, service.getAllLogins().size());
		String error = "";
		Integer tsID = null;
		
		try {
			service.createTutoringSystem(tsID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("TutoringSystem tutoringSystemID cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllTutoringSystems().size());
	}
	
	@Test
	public void testCreateTutoringSystemEmpty() {
		assertEquals(0, service.getAllLogins().size());
		String error = "";
		// cannnot check "" with Integer, instead, check input 0
		Integer tsID = 0;
		
		try {
			service.createTutoringSystem(tsID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("TutoringSystem tutoringSystemID cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllTutoringSystems().size());
	}
	
	@Test
	public void testCreateTutoringSystemSpace() {
		assertEquals(0, service.getAllLogins().size());
		String error = "";
		// cannnot check " " with Integer, instead, check input 0
		Integer tsID = 0;
		
		try {
			service.createTutoringSystem(tsID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("TutoringSystem tutoringSystemID cannot be empty!", error);
		// check no change in memory
		assertEquals(0, service.getAllTutoringSystems().size());
	}
	
	
	@Test
	public void testCreateReviewSession() {

		TutoringSystem tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(123);

		String firstName = "Andy";
		String lastName = "He";
		Calendar c = Calendar.getInstance();
		c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
		Date dob = new Date(c.getTimeInMillis());
		String email = "123456@gmail.com";
		Integer phoneNumber = 45612378;

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

		Classroom room = new Classroom();
		room.setIsBigRoom(false);
		room.setIsBooked(false);
		room.setManager(manager);
		room.setRoomCode("rm1");
		room.setTutoringSystem(tutoringSystem);

		Calendar c1 = Calendar.getInstance();
		c1.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
		Date offerDay = new Date(c1.getTimeInMillis());
		Time startTime = new Time(c1.getTimeInMillis());
		c1.set(2016, Calendar.OCTOBER, 16, 10, 30, 0);
		Time endTime = new Time(c1.getTimeInMillis());

		AvailableSession classTime = new AvailableSession();
		classTime.setDay(offerDay);
		classTime.setStartTime(startTime);
		classTime.setEndTime(endTime);
		classTime.setAvailableSessionID(123456);
		classTime.setTutoringSystem(tutoringSystem);

		Subject subject = new Subject();
		subject.setCourseID("ECSE321");
		subject.setName("Intro. to Software Engineering");
		subject.setDescription("None");
		subject.setTutoringSystem(tutoringSystem);

		Commission com = new Commission();
		com.setManager(manager);
		com.setPercentage(12.0);
		com.setCommissionID(123);
		com.setTutoringSystem(tutoringSystem);
		
		Offering offering = new Offering();
		Set<AvailableSession> time = new HashSet<AvailableSession>();
		String offeringID = "FALL19";
		String term = "fall";
		Double price = 10.0;

		offering.setClassTime(time);
		offering.setOfferingID(offeringID);
		offering.setSubject(subject);
		offering.setTerm(term);
		offering.setTutor(tutor);
		offering.setCommission(com);
		offering.setTutoringSystem(tutoringSystem);
				
		offering = new Offering();
		offering.setOfferingID(offeringID);
		offering.setTerm(term);
		offering.setPricePerHour(price);
		offering.setClassTime(time);
		offering.setSubject(subject);
		offering.setTutor(tutor);;
		offering.setCommission(com);
		offering.setClassroom(room);
		offering.setTutoringSystem(tutoringSystem);

		tutoringSystemRepository.save(tutoringSystem);
		loginRepository.save(loginInfo1);
		loginRepository.save(loginInfo2);
		managerRepository.save(manager);
		tutorRepository.save(tutor);
		classroomRepository.save(room);
		AvailableSessionRepository.save(classTime);
		subjectRepository.save(subject);
		commissionRepository.save(com);
		offeringRepository.save(offering);

		try {
			service.createReviewSession(offeringID, manager.getPersonId(), room.getRoomCode(), tutoringSystem.getTutoringSystemID());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Classroom> allClassrooms = service.getAllClassrooms();
		assertEquals(room.getRoomCode(), allClassrooms.get(0).getRoomCode());
		assertEquals(manager.getPersonId(), allClassrooms.get(0).getManager().getPersonId());
		assertEquals(tutoringSystem.getTutoringSystemID(), allClassrooms.get(0).getTutoringSystem().getTutoringSystemID());
	}
	
	@Test
	public void testCreateReviewSessionNull() {
		Integer managerID = null;
		String offeringID = null;
		String roomCode = null;
		Integer tutoringSystemID = null;

		String error = "";
		try {
			service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("offeringID cannot be empty!managerID cannot be empty or <= 0!"
				+ "roomCode cannot be empty!tutoringSystemID cannot be empty or <= 0!", error);
	}
	
	@Test
	public void testCreateReviewSessionEmpty() {
		Integer managerID = 123;
		String offeringID = "";
		String roomCode = "";
		Integer tutoringSystemID = 1;

		String error = "";
		try {
			service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("offeringID cannot be empty!roomCode cannot be empty!", error);
	}

	@Test
	public void testCreateReviewSessionSpaces() {
		Integer managerID = 123;
		String offeringID = " ";
		String roomCode = " ";
		Integer tutoringSystemID = 1;

		String error = "";
		try {
			service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("offeringID cannot be empty!roomCode cannot be empty!", error);
	}

	@Test
	public void testCreateReviewZero() {
		Integer managerID = 0;
		String offeringID = "ECSE321F19";
		String roomCode = "rm1";
		Integer tutoringSystemID = 0;

		String error = "";
		try {
			service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("managerID cannot be empty or <= 0!tutoringSystemID cannot be empty or <= 0!", error);
	}
	
	@Test
	public void testCreateReviewNegative() {
		Integer managerID = -1;
		String offeringID = "ECSE321F19";
		String roomCode = "rm1";
		Integer tutoringSystemID = -1;

		String error = "";
		try {
			service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("managerID cannot be empty or <= 0!tutoringSystemID cannot be empty or <= 0!", error);
	}




}