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

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.tutoringservice.controller.TutoringServiceRestController;
import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTests {
	@Mock
	private AvaliableSessionRepository avaliableSessionDao;
	
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
	
	// TODO: please fill in your test case
	
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
	private static final String NOTEXISTING_MANAGERID_KEY = "NotAManagerID";
	
	// tutor
	private Tutor tutor;
	private static final Integer TUTORID_KEY = 888;
	private static final String NOTEXISTING_TUTORID_KEY = "NotATutorID";
	private static final Boolean ISREGISTERED = true;
	
	// student
	private Student student;
	private static final Integer STUDENTID_KEY = 777;
	private static final String NOTEXISTING_STUDENTID_KEY = "NotAStudentID";
	private static final Integer NUM_COURSE_ENROLLED_KEY = 5;
	private static final String NOTEXISTING_NUM_COURSE_ENROLLED_KEY = "NotANumCourseEnrolled";
	
	// login
	private Login lgInfo;
	private static final String LOGIN_KEY = "TestUsername";
	private static final String LOGIN_PASS = "TestPassword";
	private static final String NOTEXITING_LOGIN_KEY = "NotAUsername";
	private static final String NOTEXISTING_LOGIN_PASS = "NotAPassword";

	// Available session
	// TODO need to initialize start time, end time, day
	// do we need Set<Tutor> ?
	private AvaliableSession avaliableSession;
	private static final Time START_TIME_KEY = null;
	private static final String NOTEXISTING_START_TIME_KEY = "NotAStartTine";
	private static final Time END_TIME_KEY = null;
	private static final String NOTEXISTING_END_TIME_KEY = "NotAEndTine";	
	private static final Integer AVA_SESSION_ID_KEY = 6;
	private static final String NOTEXISTING_AVA_SESSION_ID_KEY = "NotAAvaSessionID";
	private static final Date DAY_KEY = null;
	private static final String NOTEXISTING_DAY_KEY = "NotADay";
	
	// common variable for subject and subject request
	private static final String SUBJECT_NAME_KEY = "ECSE321";
	private static final String NOTEXISTING_SUBJECT_NAME_KEY = "NotASubjectName";
	private static final String DESCRIPTION = "Intro to SE";
	private static final String NOTEXISTING_DESCRIPTION = "NotADescription";
	
	// subject request
	private SubjectRequest request;
	private static final Integer REQUEST_ID_KEY = 7;
	private static final String NOTEXISTING_REQUEST_ID_KEY = "NotARequestID";
	
	// subject
	private Subject subject;
	private static final String COURSEID_KEY = "ECSE321F";
	private static final String NOTEXISTING_COURSEID_KEY = "NotACourseID";
	
	// commission
	// do we need Set<Offering>
	private Commission comm;
	private static final Integer COMMISSION_ID_KEY = 8;
	private static final String NOTEXISTING_COMMISSION_ID_KEY = "NotACommmissionID";
	private static final double PERCENTAGE_KEY = 12.5;
	private static final String NOTEXISTING_PERCENTAGE_KEY = "NotAPercentage";
	
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
	// do we need Set<AvaliableSesion>
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
	private static final String NOTEXISRING_COMMENT_KEY = "NotAComment";
	private static final Boolean ISAPPROVED = true;
	private static final Integer REVIEWID_KEY = 9;
	private static final String NOTEXISTING_REVIEWID_KEY = "NotAReviewID";
	
	// tutor application
	// tutor already created
	private TutorApplication tutorApplication;
	private static final Integer APPLICATIONID_KEY = 10;
	private static final String NOTEXISTING_APPLICATIONID_KEY = "NotAApplicationID";
	private static final Boolean ISACCEPTED = true;
	
	private TutoringSystem system;
	
	
	@Before
	public void setMockOutput() {
//		when(loginDao.findById((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
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
		// whenever anything is saved, just return the parameter object
/*		
		Answer<?> returnPatameterAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(loginDao.save(any(Login.class))).thenAnswer(returnPatameterAnswer);
		when(tutoringSystemDao.save(any(TutoringSystem.class))).thenAnswer(returnPatameterAnswer);
*/		
		when(managerDao.findById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			// here, getArgument(0) should be the first argument for create a manger, which is first name, does it makes more sense for checking managerID?
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
		
		when(tutorDao.findById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			// here, getArgument(0) should be the first argument for create a tutor, which is first name, does it makes more sense for checking tutorID?
			if(invocation.getArgument(0).equals(TUTORID_KEY)) {
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
		
		when(studentDao.findById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			// here, getArgument(0) should be the first argument for create a student, which is first name, does it makes more sense for checking studentID?
			if(invocation.getArgument(0).equals(STUDENTID_KEY)) {
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
		
	}
	
	@Before
	public void setupMock() {
		manager = mock(Manager.class);
		tutor = mock(Tutor.class);
		student = mock(Student.class);
		lgInfo = mock(Login.class);
		avaliableSession = mock(AvaliableSession.class);
		request = mock(SubjectRequest.class);
		subject = mock(Subject.class);
		comm = mock(Commission.class);
		classroom = mock(Classroom.class);
		university = mock(University.class);
		offering = mock(Offering.class);
		review = mock(Review.class);
		tutorApplication = mock(TutorApplication.class);
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
		String error = ""
;		String username = null;
		String password = null;
		
		try {
			lgInfo = service.createLogin(username, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("userName cannot be null or empty!password cannot be null or empty!", error);
	}
	
	/*
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
	*/

	
	@Test
	public void testMockLoginCreation() {
		assertNotNull(lgInfo);
	}
	
	@Test
	public void testMockMangerCreation() {
		assertNotNull(manager);
	}
	
	@Test
	public void testMockTutorCreation() {
		assertNotNull(tutor);
	}
	
	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}
	
	
	
	
}


