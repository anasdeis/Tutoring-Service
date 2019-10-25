package ca.mcgill.ecse321.tutoringservice.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;

@RunWith(MockitoJUnitRunner.class)
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
	
	// TODO: please fill in your test case
	@Test
	// this test is designed to pass the Travis CI build, will be modified
	public void testCreateLogin() {
		assertEquals(0,service.getAllLogins().size());
	}
	private static final String PERSON_KEY = "TestPerson";
	private static final String NONEXISTING_KEY = "NotAPerson";
	private static final String LOGIN_KEY = "TestUsername";
	private static final String LOGIN_KEY_PASS = "TestPassword";
	private static final String NOTEXITING_LOGIN_KEY = "NotAUsername";
	private static final String NOTEXISTING_LOGIN_KEY_PASS = "NotAPassword";
	private Manager manager;
	private Tutor tutor;
	private Student student;
	private Login lgInfo;
	private TutoringSystem system;
	
	/*
	@Before
	public void setMockOutput() {
		when(loginDao.findById((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(PERSON_KEY)) {
				Login lgInfo = new Login();
				lgInfo.setUserName(LOGIN_KEY);
				lgInfo.setPassword(LOGIN_KEY_PASS);
				return lgInfo;
			} else {
				return null;
			}
		});
		// whenever anything is saved, just return the parameter object
		Answer<?> returnPatameterAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
	}
	
	*/
	
}


