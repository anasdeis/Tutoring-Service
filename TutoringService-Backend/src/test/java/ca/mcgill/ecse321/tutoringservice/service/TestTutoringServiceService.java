package ca.mcgill.ecse321.tutoring_service.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse321.tutoring_service.dao.*;
import ca.mcgill.ecse321.tutoring_service.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringServiceServiceTest {

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
        int studentID = 654321;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        int numCoursesEnrolled = 100;
        String email = "123456@gmail.com";
        int phoneNumber = 45612378;

        try {
            service.createStudent(firstName, lastName, dateOfBirth, email, phoneNumber, studentID, numCoursesEnrolled);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Student> allStudents = service.getAllStudents();
        assertEquals(1, allStudents.size());
        assertEquals(studentID, allStudents.get(0).getStudentID());
        assertEquals(firstName, allStudents.get(0).getFirstName());
        assertEquals(lastName, allStudents.get(0).getLastName());
        assertEquals(dateOfBirth, allStudents.get(0).getDateOfBirth());
        assertEquals(email, allStudents.get(0).getEmail());
        assertEquals(phoneNumber, allStudents.get(0).getPhoneNumber());
        assertEquals(numCoursesEnrolled, allStudents.get(0).getNumCoursesEnrolled());
        service.deleteStudentById(studentID)
    }

    @Test
    public void testCreateManager() {
        assertEquals(0, service.getAllManagers().size());
        int managerID = 123456;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        String email = "123456@gmail.com";
        int phoneNumber = 45612378;
        try {
            service.createManager(managerID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Manager> allManagers = service.getAllManagers();
        assertEquals(1, allManagers.size());
        assertEquals(managerID, allManagers.get(0).getManagerID());
        assertEquals(firstName, allManagers.get(0).getFirstName());
        assertEquals(lastName, allManagers.get(0).getLastName());
        assertEquals(dateOfBirth, allManagers.get(0).getDateOfBirth());
        assertEquals(email, allManagers.get(0).getEmail());
        assertEquals(phoneNumber, allManagers.get(0).getPhoneNumber());
        service.deleteManagerById(managerID)
    }

    @Test
    public void testCreateTutor() {
        assertEquals(0, service.getAllTutors().size());
        int tutorID = 666666;
        boolean isRegistered = false;
        String firstName = "Charles";
        String lastName = "Liu";
        Calendar c = Calendar.getInstance();
        c.set(1999, Calendar.MARCH, 16, 9, 0, 0);
        Date dateOfBirth = new Date(c.getTimeInMillis());
        String email = "123456@gmail.com";
        int phoneNumber = 45612378;

        try {
            service.createTutor(tutorID, isRegistered);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Tutor> allTutors = service.getAllTutors();

        assertEquals(1, allTutors.size());
        assertEquals(tutorID, allTutors.get(0).getTutorID());
        assertEquals(isRegistered, allTutors.get(0).getIsRegistered());
        assertEquals(firstName, allTutors.get(0).getFirstName());
        assertEquals(lastName, allTutors.get(0).getLastName());
        assertEquals(dateOfBirth, allTutors.get(0).getDateOfBirth());
        assertEquals(email, allTutors.get(0).getEmail());
        assertEquals(phoneNumber, allTutors.get(0).getPhoneNumber());
        service.deleteTutorById(tutorID);
    }

    @Test
    public void testCreateCommission() {
        assertEquals(0, service.getAllCommissions().size());
        int commissionID = 1234567;
        double percentage = 10.5;

        try {
            service.createCommission(commissionID, percentage);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        List<Commission> allCommissions = service.getAllCommissions();

        assertEquals(1, allCommissions.size());
        assertEquals(commissionID, allCommissions.get(0).getCommissionID());
        assertEquals(percentage, allCommissions.get(0).getPercentage());
        service.deleteCommissionById(commissionID);
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
        service.deleteLoginById(userName);
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
        int availableSessionID = 5;
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
        service.deleteAvailableSessionById(availableSessionID);
    }

    @Test
    public void testCreateTutoringSystem() {
        assertEquals(0, service.getAllTutoringSystem().size());
        int tutoringSystemID = 1234567;
        try {
            service.createTutoringSystem(tutoringSystemID);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<TutoringSystem> allTutoringSystems = service.getAllTutoringSystems();
        assertEquals(1, allTutoringSystems.size());
        assertEquals(tutoringSystemID, allTutoringSystems.get(0).getTutoringSystemID());
        service.deleteTutorSystemById(tutoringSystemID);
    }





    @Test
    public void testCreateOffering() {
        assertEquals(0, service.getAllOfferings().size());
        String offeringID = "FALL19";
        String term = "fall";
        double pricePerHour = "$10";
        try {
            service.createOffering(offeringID, term,pricePerHour);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<Offering> allOfferings = service.getAllOfferings();
        assertEquals(1, allOfferings.size());
        assertEquals(offeringID, allOfferings.get(0).getOffingID());
        assertEquals(term, allOfferings.get(0).getTerm());
        assertEquals(pricePerHour, allOfferings.get(0).getPricePerHour());

        service.deleteOfferingById(offeringID);
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
        service.deleteUniversityById(name);
    }

    @Test
    public void testCreateSubjectRequest() {
        assertEquals(0, service.getAllSubjectRequests().size());
        int requestID = 789456;
        String name = "Math240";
        String description = "Discrete structures";
        SubjectType subjectType = SubjectType.HIGHSCHOOL_COURSE;
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
        service.deleteSubjectRequestById(requestID);
    }

    @Test
    public void testCreateSubject() {
        assertEquals(0, service.getAllSubjects().size());
        String name = "Math240";
        String courseID = "MATH240FALL"
        String description = "Discrete structures";
        SubjectType subjectType = SubjectType.HIGHSCHOOL_COURSE;
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
        service.deleteSubjectById(courseID);
    }

    @Test
    public void testCreateReview() {
        assertEquals(0, service.getAllReviews().size());
        String comment = "I love my tutors";
        boolean isApproved = false;
        int reviewID  = 10;
        try {
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
        service.deleteReviewyId(reviewID);
    }

    @Test
    public void testCreateTutorApplication() {
        assertEquals(0, service.getAllTutorApplications().size());
        boolean isAccepted = false;
        int applicationID  = 20;
        try {
            service.createTutorApplication(applicationID, isAccepted);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        List<TutorApplication> allTutorApplications = service.getAllTutorApplications();
        assertEquals(1, allTutorApplications.size());
        assertEquals(isAccepted, allTutorApplications.get(0).getIsAccepted());
        assertEquals(applicationID, allTutorApplications.get(0).getApplicationID());
        service.deleteTutorApplicationById(applicationID);
    }

    @Test
    public void testCreateClassroom() {
        assertEquals(0, service.getAllClassrooms().size());
        int roomCode = "rm1";
        boolean isBooked = false;
        boolean isBigRoom = false;
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
        service.deleteClassroomById(roomCode);
    }




}