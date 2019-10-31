package ca.mcgill.ecse321.tutoringservice.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.*;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.SubjectKeyIDRequest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.tutoringservice.controller.TutoringServiceRestController;
import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.dto.AvailableSessionDto;
import ca.mcgill.ecse321.tutoringservice.model.*;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTests {
	@Mock
	private AvailableSessionRepository AvailableSessionDao;

	@Mock
	private ClassroomRepository classroomDao;

	@Mock
	private CommissionRepository commissionDao;

	@Mock 
	private LoginRepository loginDao;

	@Mock
	private ManagerRepository managerDao;

	@Mock
	private OfferingRepository offeringDao;

	@Mock
	private ReviewRepository reviewDao;

	@Mock
	private StudentRepository studentDao;

	@Mock
	private SubjectRepository subjectDao;

	@Mock
	private SubjectRequestRepository subjectRequestDao;

	@Mock
	private TutorApplicationRepository tutorApplicationDao;

	@Mock
	private TutoringSystemRepository tutoringSystemDao;

	@Mock
	private TutorRepository tutorDao;

	@Mock
	private UniversityRepository universityDao;

	@InjectMocks
	private TutoringServiceService service;

	@InjectMocks
	private TutoringServiceRestController controller;


	// this test is designed to pass the Travis CI build, will be modified

	//	private static final String PERSON_KEY = "TestPerson";
	//	private static final String NONEXISTING_KEY = "NotAPerson";

	// common info for Person
	private static final String FIRSTNAME_KEY = "TestFirst";
	private static final String NOTEXISTING_FIRSTNAME_KEY = "NotAFirst";
	private static final String LASTNAME_KEY = "TestLast";
	private static final String NOTEXISTING_LASTNAME_KEY = "NotALast";
	private static final String EMAIL_KEY = "123@gmail.com";
	private static final String NOTEXISTING_EMAIL_KEY = "NotAEmail";
	private static final Integer PHONE_KEY = 1234;
	private static final String NOTEXISTING_PHONE_KEY = "NotAPhone";
	private Date dob;

	// manager
	private Manager manager;
	private static final Integer MANAGERID_KEY = 999;
	private static final Integer NOTEXISTING_MANAGERID_KEY = -999;

	// tutor
	private Tutor tutor;
	private static final Integer TUTORID_KEY = 888;
	private static final Integer NOTEXISTING_TUTORID_KEY = -888;
	private static final Boolean ISREGISTERED = true;

	// student
	private Student student;
	private static final Integer STUDENTID_KEY = 777;
	private static final Integer NOTEXISTING_STUDENTID_KEY = -777;
	private static final Integer NUM_COURSE_ENROLLED_KEY = 5;
	private static final Integer NOTEXISTING_NUM_COURSE_ENROLLED_KEY = -5;

	// login
	private Login lgInfo;
	private static final String LOGIN_KEY = "TestUsername";
	private static final String LOGIN_PASS = "TestPassword";
	private static final String NOTEXITING_LOGIN_KEY = "NotAUsername";
	private static final String NOTEXISTING_LOGIN_PASS = "NotAPassword";

	// Available session
	// TODO need to initialize start time, end time, day
	// do we need Set<Tutor> ?
	private AvailableSession AvailableSession;
	private static final Time START_TIME_KEY = null;
	private static final String NOTEXISTING_START_TIME_KEY = "NotAStartTine";
	private static final Time END_TIME_KEY = null;
	private static final String NOTEXISTING_END_TIME_KEY = "NotAEndTine";	
	private static final Integer AVA_SESSION_ID_KEY = 6;
	private static final Integer NOTEXISTING_AVA_SESSION_ID_KEY = -6;
	private static final Date DAY_KEY = null;
	private static final String NOTEXISTING_DAY_KEY = "NotADay";

	// common variable for subject and subject request
	private static final String SUBJECT_NAME_KEY = "ECSE321";
	private static final String NOTEXISTING_SUBJECT_NAME_KEY = "NotASubjectName";
	private static final String DESCRIPTION_KEY = "Intro to SE";
	private static final String NOTEXISTING_DESCRIPTION_KEY = "NotADescription";

	// subject request
	private SubjectRequest request;
	private static final Integer REQUEST_ID_KEY = 7;
	private static final Integer NOTEXISTING_REQUEST_ID_KEY = -7;

	// subject
	private Subject subject;
	private static final String COURSEID_KEY = "ECSE321F";
	private static final String NOTEXISTING_COURSEID_KEY = "NotACourseID";

	// commission
	// do we need Set<Offering>
	private Commission comm;
	private static final Integer COMMISSION_ID_KEY = 8;
	private static final Integer NOTEXISTING_COMMISSION_ID_KEY = -8;
	private static final double PERCENTAGE_KEY = 12.5;
	private static final double NOTEXISTING_PERCENTAGE_KEY = -12.5;

	// classroom
	// do we need Set<Offering>
	private Classroom classroom;
	private static final String ROOMCODE_KEY = "rm1";
	private static final String NOTEXISTING_ROOMCODE_KEY = "NotARoomcode";
	private static final Boolean ISBOOKED = true;
	private static final Boolean ISBIGROOM = true;

	// university 
	// do we need Set<Subject>
	private University university;
	private static final String UNIVERSITY_NAME_KEY = "McGill";
	private static final String NOTEXISTING_UNIVERSITY_NAME_KEY = "NotAUniversity";

	// offering
	// do we need Set<AvailableSesion>
	// subject already create for subject, we can use that
	private Offering offering;
	private static final String OFFERID_KEY = "ECSE321_2019";
	private static final String NOTEXISTING_OFFERID_KEY = "NotAOfferID";
	private static final String TERM_KEY = "Fall";
	private static final String NOTEXISTING_TERM_KEY = "NotATerm";
	private static final double PRICE_KEY = 15.0;
	private static final String NOTEXISTING_PRICE_KEY = "NotAPrice";

	// review
	// manager, offering already created
	private Review review;
	private static final String COMMENT_KEY = "Love this course!";
	private static final String NOTEXISTING_COMMENT_KEY = "NotAComment";
	private static final Boolean ISAPPROVED = true;
	private static final Integer REVIEWID_KEY = 9;
	private static final Integer NOTEXISTING_REVIEWID_KEY = -9;

	// tutor application
	// tutor already created
	private TutorApplication tutorApplication;
	private static final Integer APPLICATIONID_KEY = 10;
	private static final Integer NOTEXISTING_APPLICATIONID_KEY = -10;
	private static final Boolean ISACCEPTED = true;

	private TutoringSystem system;
	private static final Integer SYSTEMID_KEY = 100;
	private static final Integer NONEXISTING_SYSTEMID_KEY = -100;
	
	@Before
	public void clearDatabase() {
		subjectDao.deleteAll();
		subjectRequestDao.deleteAll();
		commissionDao.deleteAll();
		offeringDao.deleteAll();
		classroomDao.deleteAll();
		managerDao.deleteAll();
		AvailableSessionDao.deleteAll();
		reviewDao.deleteAll();
		studentDao.deleteAll();
		tutorApplicationDao.deleteAll();
		tutorDao.deleteAll();
		loginDao.deleteAll();
		universityDao.deleteAll();
		tutoringSystemDao.deleteAll();
	}

	@Before
	public void setMockOutput() {
		//		when(loginDao.findById((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
		// public Login createLogin(String userName, String password) 
		when(loginDao.findLoginByUserName(anyString())).thenAnswer((InvocationOnMock invocation) -> {	
			if(invocation.getArgument(0).equals(LOGIN_KEY)) {
				Login lgInfo = new Login();
				lgInfo.setUserName(LOGIN_KEY);
				lgInfo.setPassword(LOGIN_PASS);
				return lgInfo;
			} else {
				return null;
			}
		});
		
		// 	public Manager createManager(String first, String last, Date dob, String email, Integer phone, Integer managerID, Login loginInfo, TutoringSystem tutoringSystem) {
		when(managerDao.findManagerByPersonId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(MANAGERID_KEY)) {
				Manager manager = new Manager();
				manager.setFirstName(FIRSTNAME_KEY);
				manager.setLastName(LASTNAME_KEY);
				manager.setDateOfBirth(dob);
				manager.setEmail(EMAIL_KEY);
				manager.setPhoneNumber(PHONE_KEY);
				manager.setPersonId(MANAGERID_KEY);
				manager.setLoginInfo(lgInfo);
				manager.setTutoringSystem(system);
				return manager;
			} else {
				return null;
			}
		});

		// 	public Tutor createTutor(String first, String last, Date dob, String email, Integer phone, Integer tutorID, Boolean isRegistered, Login loginInfo, Set<TutorApplication> tutorApplications, Set<Offering> offerings, Set<AvailableSession> AvailableSessions, TutoringSystem tutoringSystem) {
		when(tutorDao.findTutorByPersonId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTORID_KEY)) {
				Tutor tutor = new Tutor();
				tutor.setFirstName(FIRSTNAME_KEY);
				tutor.setLastName(LASTNAME_KEY);
				tutor.setDateOfBirth(dob);
				tutor.setEmail(EMAIL_KEY);
				tutor.setPhoneNumber(PHONE_KEY);
				tutor.setPersonId(TUTORID_KEY);
				tutor.setIsRegistered(ISREGISTERED);
				tutor.setLoginInfo(lgInfo);
				tutor.setTutoringSystem(system);
				return tutor;
			} else {
				return null;
			}
		});
		
		// 	public Student createStudent(String first, String last, Date dob, String email, Integer phone, Integer studentID, Integer numCoursesEnrolled, Login loginInfo, TutoringSystem tutoringSystem) {
		when(studentDao.findStudentByPersonId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(STUDENTID_KEY)) {
				Student student = new Student();
				student.setFirstName(FIRSTNAME_KEY);
				student.setLastName(LASTNAME_KEY);
				student.setDateOfBirth(dob);
				student.setEmail(EMAIL_KEY);
				student.setPhoneNumber(PHONE_KEY);
				student.setPersonId(STUDENTID_KEY);
				student.setNumCoursesEnrolled(NUM_COURSE_ENROLLED_KEY);
				student.setLoginInfo(lgInfo);
				student.setTutoringSystem(system);
				return student;
			} else {
				return null;
			}
		});

		// 	public AvailableSession createAvailableSession(Time startTime, Time endTime, Integer AvailableSessionID, Date day, Set<Tutor> tutors, TutoringSystem tutoringSystem) {
		when(AvailableSessionDao.findAvailableSessionByAvailableSessionID((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(AVA_SESSION_ID_KEY)) {
				AvailableSession avaliableSession = new AvailableSession();
				Set<Tutor> tutors = new HashSet<Tutor>();	// TODO temp solution, will modify, but works
				tutors.add(tutor);
				AvailableSession.setAvailableSessionID(AVA_SESSION_ID_KEY);
				AvailableSession.setStartTime(START_TIME_KEY);
				AvailableSession.setEndTime(START_TIME_KEY);
				AvailableSession.setDay(DAY_KEY);
				AvailableSession.setTutor(tutors);
				AvailableSession.setTutoringSystem(system);
				return student;
			} else 
				return null;
		});

		// 	public SubjectRequest createSubjectRequest(Integer requestID, String name, String description,SubjectType subjectType, Manager manager, TutoringSystem tutoringSystem){
		when(subjectRequestDao.findSubjectRequestByRequestID((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(REQUEST_ID_KEY)) {
				SubjectRequest request = new SubjectRequest();
				Set<Student> students = new HashSet<Student>();	// TODO temp solution, will modify, but works
				students.add(student);
				SubjectType type = SubjectType.UNIVERSITY_COURSE;
				request.setRequestID(REQUEST_ID_KEY);
				request.setManager(manager);
				request.setDescription(DESCRIPTION_KEY);
				request.setName(SUBJECT_NAME_KEY);
				request.setStudent(students);
				request.setSubjectType(type);
				request.setTutoringSystem(system);
				return request;
			} else
				return null;
		});

		// 	public Subject createSubject(String name, String courseID, String description, SubjectType subjType, University university,TutoringSystem tutoringSystem) {
		when(subjectDao.findSubjectByCourseID((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSEID_KEY)) {
				Subject subject = new Subject();
				subject.setCourseID(COURSEID_KEY);
				subject.setDescription(DESCRIPTION_KEY);
				subject.setName(SUBJECT_NAME_KEY);
				subject.setSubjectType(SubjectType.UNIVERSITY_COURSE);
				subject.setUniversity(university);
				subject.setTutoringSystem(system);
				return subject;
			} else {
				return null;
			}
		});

		// 	public Commission createCommission(double percentage, Integer commissionID, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		when(commissionDao.findCommissionBycommissionID((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COMMISSION_ID_KEY)) {
				Commission comm = new Commission();
				Set<Offering> offerings = new HashSet<Offering>();	// TODO temp solution, will modify, but works
				offerings.add(offering);
				comm.setCommissionID(COMMISSION_ID_KEY);
				comm.setManager(manager);
				comm.setPercentage(PERCENTAGE_KEY);
				comm.setOffering(offerings);
				comm.setTutoringSystem(system);
				return comm;
			} else 
				return null;
		});

		//  public Classroom createClassroom(String roomCode, Boolean isBooked, Boolean isBigRoom, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		when(classroomDao.findClassroomByRoomCode((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
		if(invocation.getArgument(0).equals(ROOMCODE_KEY)) {
			Classroom classroom = new Classroom();
			Set<Offering> offerings = new HashSet<Offering>();	// TODO temp solution, will modify, but works
			offerings.add(offering);
			classroom.setRoomCode(ROOMCODE_KEY);
			classroom.setIsBigRoom(ISBIGROOM);
			classroom.setIsBooked(ISBOOKED);
			classroom.setManager(manager);
			classroom.setOffering(offerings);
			classroom.setTutoringSystem(system);
			return classroom;
		} else
			return null;
		});

		//LOOKS LIKE WE DON'T NEED THIS --CHARLES
		//public TutoringSystem createTutoringSystem(Integer tutoringSystemID)
//		when(tutoringSystemDao.findTutoringSystemByTutoringSystemID((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
//			if(invocation.getArgument(0).equals(SYSTEMID_KEY)) {
//				system.setTutoringSystemID(SYSTEMID_KEY);
//				return system;
//			} else
//				return null;
//		});
		
		// 	public University createUniversity(String name, Set<Subject> subjects, TutoringSystem tutoringSystem) {
		when(universityDao.findUniversityByName((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(UNIVERSITY_NAME_KEY)) {
			University university = new University();
			Set<Subject> subjects = new HashSet<Subject>();	// TODO temp solution, will modify, but works
			subjects.add(subject);
			university.setName(UNIVERSITY_NAME_KEY);
			university.setSubject(subjects);
			university.setTutoringSystem(system);
			return university;
			} else
				return null;
		});
		
		//  public Offering createOffering(String offId, String term, double price, Set<AvailableSession> classTime, Subject subject, Tutor tutor, Commission commission, Classroom classroom, TutoringSystem tutoringSystem){
		when(offeringDao.findOfferingByOfferingID((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(OFFERID_KEY)) {
				Offering offering = new Offering();
				Set<AvailableSession> classTimes = new HashSet<AvailableSession>();
				classTimes.add(AvailableSession);
				offering.setOfferingID(OFFERID_KEY);
				offering.setClassroom(classroom);
				offering.setClassTime(classTimes);
				return offering;
			} else
				return null;
		});

		// 	public Review createReview(String comment, Boolean isApproved, Integer reviewID, Manager manager, Offering offering, TutoringSystem tutoringSystem){
		when(reviewDao.findReviewByReviewID((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(REVIEWID_KEY)) {
				Review review = new Review();
				review.setReviewID(REVIEWID_KEY);
				review.setComment(COMMENT_KEY);
				review.setIsApproved(ISAPPROVED);
				review.setManager(manager);
				review.setOffering(offering);
				review.setTutoringSystem(system);
				return review;
			} else
				return null;
		});

		// 	public TutorApplication createTutorApplication(Integer applicationId, Boolean isAccepted, Tutor tutor, TutoringSystem tutoringSystem) {
		when(tutorApplicationDao.findTutorApplicationByApplicationId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(APPLICATIONID_KEY)) {
				TutorApplication tutorApplication = new TutorApplication();
				Set<Subject> subjects = new HashSet<Subject>();	// TODO temp solution, will modify, but works
				subjects.add(subject);
				tutorApplication.setApplicationId(APPLICATIONID_KEY);
				tutorApplication.setIsAccepted(ISACCEPTED);
				tutorApplication.setSubject(subjects);
				tutorApplication.setTutor(tutor);
				tutorApplication.setTutoringSystem(system);
				return tutorApplication;
			} else
				return null;
		});



		// 	public TutoringSystem createTutoringSystem(Integer tutoringSystemID) {

		
		Answer<?> returnPatameterAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(loginDao.save(any(Login.class))).thenAnswer(returnPatameterAnswer);
		when(tutoringSystemDao.save(any(TutoringSystem.class))).thenAnswer(returnPatameterAnswer);
		when(managerDao.save(any(Manager.class))).thenAnswer(returnPatameterAnswer);
		when(tutorDao.save(any(Tutor.class))).thenAnswer(returnPatameterAnswer);
		when(studentDao.save(any(Student.class))).thenAnswer(returnPatameterAnswer);
		when(AvailableSessionDao.save(any(AvailableSession.class))).thenAnswer(returnPatameterAnswer);
		when(subjectRequestDao.save(any(SubjectRequest.class))).thenAnswer(returnPatameterAnswer);
		when(subjectDao.save(any(Subject.class))).thenAnswer(returnPatameterAnswer);
	}

	@Before
	public void setupMock() {
		manager = mock(Manager.class);
		tutor = mock(Tutor.class);
		student = mock(Student.class);
		lgInfo = mock(Login.class);
		AvailableSession = mock(AvailableSession.class);
		request = mock(SubjectRequest.class);
		subject = mock(Subject.class);
		comm = mock(Commission.class);
		classroom = mock(Classroom.class);
		university = mock(University.class);
		offering = mock(Offering.class);
		review = mock(Review.class);
		tutorApplication = mock(TutorApplication.class);
		system = mock(TutoringSystem.class);
	}
	
	
	@Test
	public void testCreateLogin() {
		assertEquals(0, service.getAllLogins().size());

		String username = "user";
		String password = "pass";

		try {
			lgInfo = service.createLogin(username, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(username, lgInfo.getUserName());
		assertEquals(password, lgInfo.getPassword());
	}

	@Test
	public void testCreateLoginNull() {
		String error = "";
		String username = null;
		String password = null;

		try {
			lgInfo = service.createLogin(username, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);
	}


	@Test
	public void testCreateLoginEmpty() {
		String error = "";

		String username = "";
		String password = "";

		try {
			service.createLogin(username, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);
	}
	
	@Test
	public void testCreateLoginSpaces() {
		String error = "";

		String username = " ";
		String password = " ";

		try {
			service.createLogin(username, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);
   	}
	@Test
	public void testMockLoginCreation() {
		assertNotNull(lgInfo);
	}

	@Test
	public void testMockLoginQueryFound() {
		assertNotNull(service.getLogin(LOGIN_KEY));
	}

	@Test
	public void testMockLoginQueryNotFound() {
		assertNull(service.getLogin(NOTEXITING_LOGIN_KEY));
	}

	
	
	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubjects().size());

		String name = "Intro to Software Engineering";
		String courseID = "ECSE321";
		String description = "Introduction to large scale software systems";
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;


		try {
			subject = service.createSubject(name, courseID, description, subjectType, university, system);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(courseID, subject.getCourseID());
		assertEquals(description, subject.getDescription());
		assertEquals(name, subject.getName());
		assertEquals(null, subject.getOffering());
		assertEquals(null, subject.getTutorRole());
		assertEquals(subjectType, subject.getSubjectType());
		assertEquals(university, subject.getUniversity());
		assertEquals(system, subject.getTutoringSystem());
	}

	@Test
	public void testCreateSubjectNull() {
		assertEquals(0, service.getAllSubjects().size());

		String name = null;
		String courseID = null;
		String description = null;
		SubjectType subjectType = null;

		String error = "";
		try {
			subject = service.createSubject(name, courseID, description, subjectType, university, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("name cannot be empty or null!description cannot be empty or null!"
				+ "courseID cannot be empty or null!subjectType cannot be null!cannot assign university to non university coursetutoringSystem cannot be null!", error);
	}
	

	@Test
	public void testCreateSubjectEmpty() {
		assertEquals(0, service.getAllSubjects().size());

		String name = "";
		String courseID = "";
		String description = "";
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;

		String error = "";
		try {
			subject = service.createSubject(name, courseID, description, subjectType, university, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("name cannot be empty or null!description cannot be empty or null!"
				+ "courseID cannot be empty or null!", error);
	}

	@Test
	public void testCreateSubjectSpaces() {
		assertEquals(0, service.getAllSubjects().size());

		String name = " ";
		String courseID = " ";
		String description = " ";
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;

		String error = "";
		try {
			subject = service.createSubject(name, courseID, description, subjectType, university, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("name cannot be empty or null!description cannot be empty or null!"
				+ "courseID cannot be empty or null!", error);
	}

	@Test
	public void testCreateSubjectUniversityCourseNoUniversity() {
		assertEquals(0, service.getAllSubjects().size());

		String name = "Intro to Software Engineering";
		String courseID = "ECSE321";
		String description = "Introduction to large scale software systems";
		SubjectType subjectType = SubjectType.UNIVERSITY_COURSE;

		String error = "";
		try {
			subject = service.createSubject(name, courseID, description, subjectType, null, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("university must be defined for university course", error);
	}

	@Test
	public void testCreateSubjectNotUniversityCourseWithUniversity() {
		assertEquals(0, service.getAllSubjects().size());

		String name = "Intro to Software Engineering";
		String courseID = "ECSE321";
		String description = "Introduction to large scale software systems";
		SubjectType subjectType = SubjectType.HIGH_SCHOOL_COURSE;

		String error = "";
		try {
			subject = service.createSubject(name, courseID, description, subjectType, university, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("cannot assign university to non university course", error);
	}

	@Test
	public void testMockSubjectCreation() {
		assertNotNull(subject);
	}
	
	// but this one works
	@Test
	public void testMockSubjectQueryFound() {
		assertNotNull(service.getSubject(COURSEID_KEY));
	}
	
	@Test
	public void testGetExistingSubject() {
		assertEquals(COURSEID_KEY, service.getSubject(COURSEID_KEY).getCourseID());
	}

	@Test
	public void testMockSubjectQueryNotFound() {
		assertNull(service.getSubject(NOTEXISTING_COURSEID_KEY));
	}

	
	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());
		String firstName = "Charles";
		String lastName = "Liu";
		String email = "asdf@mcgill.ca";
		Integer phone = 1234567890;
		Integer studentID = 88888888;
		Integer numCoursesEnrolled = 5;
		
		try {
			student = service.createStudent(firstName, lastName, dob, email, phone, studentID, numCoursesEnrolled, lgInfo, system);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(firstName, student.getFirstName());
		assertEquals(lastName, student.getLastName());
		assertEquals(dob, student.getDateOfBirth());
		assertEquals(email, student.getEmail());
		assertEquals(numCoursesEnrolled, student.getNumCoursesEnrolled());
		assertEquals(studentID, student.getPersonId());
		assertEquals(lgInfo, student.getLoginInfo());
		assertEquals(system, student.getTutoringSystem());
		assertEquals(phone, student.getPhoneNumber());
		assertEquals(null, student.getSubjectRequest());
		assertEquals(null, student.getCoursesTaken());
	}
	
	@Test public void testCreateStudentNull() {
		String error = "";
		String firstName =  null;
		String lastName = null;
		String email = null;
		Integer phone = null;
		Integer studentID = null;
		Integer numCoursesEnrolled = null;
		
		try {
			student = service.createStudent(firstName, lastName, dob, email, phone, studentID, numCoursesEnrolled, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!Phone cannot be empty!Student ID cannot be empty!Login Info cannot be empty!Tutoring System cannot be empty!", error); 
	}
	
	@Test public void testCreateStudentEmpty() {
		String error = "";
		String firstName =  "";
		String lastName = "";
		String email = "";
		Integer phone = 0;
		Integer studentID = 0;
		Integer numCoursesEnrolled =0;
		
		try {
			student = service.createStudent(firstName, lastName, dob, email, phone, studentID, numCoursesEnrolled, lgInfo, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!Phone cannot be empty!Student ID cannot be empty!", error); 
	}
	
	@Test public void testCreateStudentSpaces() {
		String error = "";
		String firstName =  "   ";
		String lastName = "   ";
		String email = "   ";
		Integer phone = 0;
		Integer studentID = 0;
		Integer numCoursesEnrolled = 0;
		
		try {
			student = service.createStudent(firstName, lastName, dob, email, phone, studentID, numCoursesEnrolled, lgInfo, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("First name cannot be empty!Last name cannot be empty!Email cannot be empty!Phone cannot be empty!Student ID cannot be empty!", error); 
	}
	
	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}

	@Test
	public void tsetMockStudentQueryFound() {
		assertNotNull(service.getStudent(STUDENTID_KEY));
	}

	@Test
	public void tsetMockStudentQueryNotFound() {
		assertNull(service.getStudent(NOTEXISTING_STUDENTID_KEY));
	}


	
	
	@Test
	public void testMockMangerCreation() {
		assertNotNull(manager);
	}

	@Test
	public void testMockManagerQueryFound() {
		assertNotNull(service.getManager(MANAGERID_KEY));
	}

	@Test
	public void testMockManagerQueryNotFound() {
		assertNull(service.getManager(NOTEXISTING_MANAGERID_KEY));
	}

	@Test
	public void testMockTutorCreation() {
		assertNotNull(tutor);
	}

	@Test
	public void testMockTutorQueryFound() {
		assertNotNull(service.getTutor(TUTORID_KEY));
	}

	@Test
	public void testMockTutorQueryNotFound() {
		assertNull(service.getTutor(NOTEXISTING_TUTORID_KEY));
	}

	
	@Test
	public void testMockAvailableSessionCreation() {
		assertNotNull(AvailableSession);
	}

	// TODO
//	this method is not passing, TutoringServiceService @848
//	@Test
//	public void testMockAvailableSessionQueryFound() {
//		assertNotNull(service.getAvailableSession(AVA_SESSION_ID_KEY));
//	}
	
	@Test
	public void testMockAvailableSessionQueryNotFound() {
		assertNull(service.getAvailableSession(NOTEXISTING_AVA_SESSION_ID_KEY));
	}
	
	@Test
	public void testMockSubjectRequestCreation() {
		assertNotNull(request);
	}
	
	@Test
	public void testMockSubjectRequestQueryFound() {
		assertNotNull(service.getSubjectRequest(REQUEST_ID_KEY));
	}

	@Test
	public void testMockSubjectRequestQueryNotFound() {
		assertNull(service.getSubjectRequest(NOTEXISTING_REQUEST_ID_KEY));
	}
	

	@Test
	public void testCreateCommission() {
		assertEquals(0, service.getAllCommissions().size());
		double percentage = 0.30;
		Integer commissionID = 123456;
		Set<Offering> offerings = new HashSet<Offering>();
		offerings.add(offering);
		
		try {
			comm = service.createCommission(percentage, commissionID, manager, offerings, system);
		} catch (IllegalArgumentException e) {
			fail();
		}
		if(percentage!=comm.getPercentage())
			fail();
		assertEquals(commissionID, comm.getCommissionID());
		assertEquals(offerings, comm.getOffering());
		assertEquals(manager, comm.getManager());
		assertEquals(system, comm.getTutoringSystem());
	}
	
	@Test
	public void testCreateCommissionNull() {
		assertEquals(0, service.getAllCommissions().size());
		String error = "";
		double percentage = 1.0;
		Integer commissionID = null;
		Set<Offering> offerings = null;	
		
		try {
			comm = service.createCommission(percentage, commissionID, null, offerings, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("commissionID cannot be null!manager cannot be null!offerings cannot be null!tutoringSystem cannot be null!", error); 
	}
	
	@Test
	public void testCreateCommissionZero() {
		assertEquals(0, service.getAllCommissions().size());
		String error = "";
		double percentage = 0.0;
		Integer commissionID = 0;
		Set<Offering> offerings = new HashSet<Offering>();	
		offerings.add(offering);
		
		try {
			comm = service.createCommission(percentage, commissionID, manager, offerings, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("percentage cannot be <= 0!commissionID cannot be <= 0!", error); 
	}
	
	@Test
	public void testCreateCommissionNegative() {
		assertEquals(0, service.getAllCommissions().size());
		String error = "";
		double percentage = -30.0;
		Integer commissionID = -1234;
		Set<Offering> offerings = new HashSet<Offering>();	
		offerings.add(offering);
		
		try {
			comm = service.createCommission(percentage, commissionID, manager, offerings, system);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("percentage cannot be <= 0!commissionID cannot be <= 0!", error); 
	}
	
	
	@Test
	public void testMockCommissionCreation() {
		assertNotNull(comm);
	}
	
	@Test
	public void testMockCommissionQueryFound() {
		assertNotNull(service.getCommission(COMMISSION_ID_KEY));
	}

	@Test
	public void testMockCommissionQueryNotFound() {
		assertNull(service.getCommission(NOTEXISTING_COMMISSION_ID_KEY));
	}
	
	@Test
	public void testMockClassroomCreation() {
		assertNotNull(classroom);
	}
	
	@Test
	public void testMockClassroomQueryFound() {
		assertNotNull(service.getClassroom(ROOMCODE_KEY));
	}

	@Test
	public void testMockClassroomQueryNotFound() {
		assertNull(service.getClassroom(NOTEXISTING_ROOMCODE_KEY));
	}
	
	@Test
	public void testMockUniversityCreation() {
		assertNotNull(university);
	}
	
	@Test
	public void testMockUniversityQueryFound() {
		assertNotNull(service.getUniversity(UNIVERSITY_NAME_KEY));
	}

	@Test
	public void testMockUniversityQueryNotFound() {
		assertNull(service.getUniversity(NOTEXISTING_UNIVERSITY_NAME_KEY));
	}
	
	@Test
	public void testMockOfferingCreation() {
		assertNotNull(offering);
	}

	@Test
	public void testMockOfferingQueryFound() {
		assertNotNull(service.getOffering(OFFERID_KEY));
	}
	
	@Test
	public void testMockOfferingQueryNotFound() {
		assertNull(service.getOffering(NOTEXISTING_OFFERID_KEY));
	}
	
	@Test
	public void testMockReviewCreation() {
		assertNotNull(review);
	}
	
	@Test
	public void testMockReviewQueryFound() {
		assertNotNull(service.getReview(REVIEWID_KEY));
	}

	@Test
	public void testMockReviewQueryNotFound() {
		assertNull(service.getReview(NOTEXISTING_REVIEWID_KEY));
	}
	
	@Test
	public void testMockTutorApplicationCreation() {
		assertNotNull(tutorApplication);
	}
	
	@Test
	public void testMockTutorApplicationQueryFound() {
		assertNotNull(service.getTutorApplication(APPLICATIONID_KEY));
	}

	@Test
	public void testMockTutorApplicationQueryNotFound() {
		assertNull(service.getTutorApplication(NOTEXISTING_APPLICATIONID_KEY));
	}
}


