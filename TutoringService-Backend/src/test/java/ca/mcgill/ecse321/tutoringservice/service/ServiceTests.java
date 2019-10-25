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
import org.mockito.junit.MockitoJUnitRunner;

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
}


