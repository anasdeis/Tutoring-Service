package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;

@Service
public class TutoringServiceService {

	@Autowired
	private AvaliableSessionRepository AvaliableSessionRepository;

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

	/*
	 * Login
	 */
	@Transactional
	public Login createLogin(String userName, String password) {
		String error = "";
		if (userName == null || userName.trim().length() == 0) {
			error += "userName cannot be null or empty!";
		}
		if (password == null || password.trim().length() == 0) {
			error += "password cannot be null or empty!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Login login = new Login();
		login.setUserName(userName);
		login.setPassword(password);
		loginRepository.save(login);
		return login;
	}

	@Transactional
	public Login getLogin(String userName) {
		Login login = loginRepository.findLoginByUserName(userName);
		return login;
	}

	@Transactional
	public List<Login> getAllLogins() {
		return toList(loginRepository.findAll());
	}

	@Transactional
	public void deleteLogin(String userName) {
		loginRepository.deleteLoginByUserName(userName);
	}

	/*
	 * Commission
	 */
	@Transactional
	public Commission createCommission(double percentage, Integer commissionID, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		String error = "";
		if (percentage <= 0) {
			error += "percentage cannot be <= 0!";
		}
		if (commissionID <= 0) {
			error += "commissionID cannot be <= 0!";
		}
		if (manager == null) {
			error += "manager cannot be null!";
		}
		if (offerings == null) {
			error += "offerings cannot be null!";
		}
		if (tutoringSystem == null) {
			error += "tutoringSystem cannot be null!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Commission commission = new Commission();
		commission.setCommissionID(commissionID);
		commission.setPercentage(percentage);
		commission.setManager(manager);
		commission.setOffering(offerings);
		commission.setTutoringSystem(tutoringSystem);
		commissionRepository.save(commission);
		return commission;
	}

	@Transactional
	public Commission getCommission(Integer commissionID) {
		Commission commission = commissionRepository.findCommissionBycommissionID(commissionID);
		return commission;
	}

	@Transactional
	public List<Commission> getAllCommissions() {
		return toList(commissionRepository.findAll());
	}

	@Transactional
	public void deleteCommisison(Integer commissionID) {
		commissionRepository.deleteCommissionBycommissionID(commissionID);
	}

	/*
	 * Subject
	 */
	@Transactional
	public Subject createSubject(String name, String courseID, String description, TutoringSystem tutoringSystem) {
		String error = "";
		if (name == null || name.trim().length() == 0)
			error += "name cannot be empty or null!";
		if (description == null || description.trim().length() == 0)
			error += "description cannot be empty or null!";
		if (courseID == null || courseID.trim().length() == 0)
			error += "courseID cannot be empty or null!";
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Subject subject = new Subject();
		subject.setName(name);
		subject.setCourseID(courseID);
		subject.setDescription(description);
		subject.setTutoringSystem(tutoringSystem);
		subjectRepository.save(subject);
		return subject;
	}

	@Transactional
	public Subject getSubject(String courseID) {
		Subject subject = subjectRepository.findSubjectByCourseID(courseID);
		return subject;
	}

	@Transactional
	public List<Subject> getAllSubjects() {
		return toList(subjectRepository.findAll());
	}


	@Transactional
	public void deleteSubject(String courseID) {
		subjectRepository.deleteSubjectByCourseID(courseID);
	}


	/*
	 * Subject Request
	 */
	@Transactional
	public SubjectRequest createSubjectRequest(Integer requestID, String name, String description, TutoringSystem tutoringSystem){
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "name cannot be null! ";
		}
		if (description == null || description.trim().length() == 0) {
			error = error + "Description cannot be null! ";
		}
		if (requestID == 0)
			error = error + "requestID cannot be empty! ";

		SubjectRequest subjectrequest = new SubjectRequest();
		subjectrequest.setName(name);
		subjectrequest.setRequestID(requestID);
		subjectrequest.setDescription(description);
		subjectrequest.setTutoringSystem(tutoringSystem);
		subjectRequestRepository.save(subjectrequest);
		return subjectrequest;
	}

	@Transactional
	public SubjectRequest getSubjectRequest(Integer requestID) {
		SubjectRequest subjectrequest = subjectRequestRepository.findSubjectRequestByRequestID(requestID);
		return subjectrequest;
	}

	@Transactional
	public List<SubjectRequest> getAllSubjectRequests() {
		return toList(subjectRequestRepository.findAll());
	}

	@Transactional
	public void deleteSubjectRequest(Integer requestID) {
		subjectRequestRepository.deleteSubjectRequestByRequestID(requestID);
	}

	/*
	 * Manager
	 */
	@Transactional
	public Manager createManager(String first, String last, Date dob, String email, Integer phone, Integer managerID, Login loginInfo, TutoringSystem tutoringSystem) {
		String error = "";

		if (first == null || first.trim().length() == 0) {
			error = error + "First name cannot be empty!";
		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Last name cannot be empty!";
		}
		/*
		 * dob will not be checked
		if (dob == null) {
			error = error + "DOB cannot be empty!";
			//			throw new IllegalArgumentException(error+"C");
			//			error = error + "C";
		}
		 */
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be empty!";
		}
		if (phone == null || phone == 0) {
			error = error + "Phone cannot be empty!";
		}
		if (managerID == null || managerID == 0) {
			error = error + "Manager ID cannot be empty!";
		}
		if (loginInfo == null) {
			error = error + "Login Info cannot be empty!";
		}
		if (tutoringSystem == null) {
			error = error + "Tutoring System cannot be empty!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Manager manager = new Manager();
		manager.setFirstName(first);
		manager.setLastName(last);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phone);
		manager.setPersonId(managerID);
		manager.setLoginInfo(loginInfo);
		manager.setTutoringSystem(tutoringSystem);
		managerRepository.save(manager);
		return manager;
	}

	@Transactional
	public Manager getManager(int managerID) {
		Manager manager = managerRepository.findManagerByPersonId(managerID);
		return manager;
	}

	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}

	@Transactional
	public void deleteManager(int managerID) {
		managerRepository.deleteManagerByPersonId(managerID);
	}

	/*
	 * Student
	 */
	@Transactional
	public Student createStudent(String first, String last, Date dob, String email, Integer phone, Integer studentID, Integer numCoursesEnrolled, Login loginInfo, TutoringSystem tutoringSystem) {
		String error = ""; 

		if (first == null || first.trim().length() == 0) {
			error = error + "First name cannot be empty!";

		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Last name cannot be empty!";
		}
		/*
		 * dob will not be checked
		if (dob == null) {
			//			error = error + "valid input needed";
			error = error + "DOB cannot be empty!";
		}
		 */
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be empty!";
		}
		if (phone == null || phone == 0) {
			error = error + "Phone cannot be empty!";
		}
		if (studentID == null || studentID == 0) {
			error = error + "Student ID cannot be empty!";
		}
		if (loginInfo == null) {
			error = error + "Login Info cannot be empty!";
		}
		if (tutoringSystem == null) {
			error = error + "Tutoring System cannot be empty!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Student student = new Student();
		student.setFirstName(first);
		student.setLastName(last);
		student.setDateOfBirth(dob);
		student.setEmail(email);
		student.setPhoneNumber(phone);
		student.setPersonId(studentID);
		student.setLoginInfo(loginInfo);
		student.setNumCoursesEnrolled(numCoursesEnrolled);
		student.setTutoringSystem(tutoringSystem);
		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student getStudent(int studentID) {
		Student student = studentRepository.findStudentByPersonId(studentID);
		return student;
	}

	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	@Transactional
	public void deleteStudent(int studentID) {
		studentRepository.deleteStudentByPersonId(studentID);
	}

	/*
	 * Offering
	 */
	@Transactional
	public Offering createOffering(String offId, String term, double price, Set<AvaliableSession> classTime, Subject subject, TutoringSystem tutoringSystem){
		if (offId == null || offId.trim().length() == 0) {
			throw new IllegalArgumentException("valid input needed");
		}
		if (term == null || term.trim().length() == 0) {
			throw new IllegalArgumentException("valid input needed");
		}
		if (classTime == null) {
			throw new IllegalArgumentException("valid input needed");
		}
		if (price == 0.0) 
			throw new IllegalArgumentException("valid input needed");


		Offering offering = new Offering();
		offering.setOfferingID(offId);
		offering.setTerm(term);
		offering.setPricePerHour(price);
		offering.setClassTime((Set<AvaliableSession>) classTime);
		offering.setSubject(subject);
		return offering;

	}

	@Transactional
	public Offering getOffering(String offeringID) {
		Offering offering = offeringRepository.findOfferingByOfferingID(offeringID);
		return offering;
	}

	@Transactional
	public List<Offering> getAllOfferings() {
		return toList(offeringRepository.findAll());
	}

	@Transactional
	public void deleteOffering(String offeringID) {
		offeringRepository.deleteOfferingByOfferingID(offeringID);
	}

	/*
	 * Tutor
	 */
	@Transactional
	public Tutor createTutor(String first, String last, Date dob, String email, Integer phone, Integer tutorID, Boolean isRegistered, Login loginInfo, TutoringSystem tutoringSystem) {
		String error = ""; 

		if (first == null || first.trim().length() == 0) {
			error = error + "First name cannot be empty!";

		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Last name cannot be empty!";
		}
		/*
		 * dob will not be checked
		if (dob == null) {
			//			error = error + "valid input needed";
			error = error + "DOB cannot be empty!";
		}
		 */
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be empty!";
		}
		if (phone == null || phone == 0) {
			error = error + "Phone cannot be empty!";
		}
		if (tutorID == null || tutorID == 0) {
			error = error + "Tutor ID cannot be empty!";
		}
		if (loginInfo == null) {
			error = error + "Login Info cannot be empty!";
		}
		if (tutoringSystem == null) {
			error = error + "Tutoring System cannot be empty!";
		}

		//		a new tutor (not exist in the system) has the boolean false, but we can add this tutor into our system and then go back
		//		to change the boolean value
		//		if (isRegistered = false) {
		//			throw new IllegalArgumentException("valid input needed");
		//		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Tutor tutor = new Tutor();
		tutor.setFirstName(first);
		tutor.setLastName(last);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phone);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(isRegistered);
		tutor.setLoginInfo(loginInfo);
		tutor.setTutoringSystem(tutoringSystem);
		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor getTutor(int tutorID) {
		Tutor tutor = tutorRepository.findTutorByPersonId(tutorID);
		return tutor;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}


	@Transactional
	public void deleteTutor(int tutorID) {
		tutorRepository.deleteTutorByPersonId(tutorID);
	}

	/*
	 * Review
	 */
	@Transactional
	public Review createReview(String comment, Boolean isApproved, Integer reviewID, Manager manager, Offering offering, TutoringSystem tutoringSystem){
		Review review = new Review();
		review.setComment(comment);
		review.setIsApproved(isApproved);
		review.setReviewID(reviewID);
		review.setManager(manager);
		review.setOffering(offering);
		review.setTutoringSystem(tutoringSystem);
		reviewRepository.save(review);
		return review;
	}

	@Transactional
	public Review getReview(Integer reviewID) {
		if (reviewID == null) {
			throw new IllegalArgumentException("Review reviewID cannot be empty!");
		}
		Review review = reviewRepository.findReviewByReviewID(reviewID);
		return review;
	}

	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}

	@Transactional
	public void deleteReview(Integer reviewID) {
		if (reviewID == null) {
			throw new IllegalArgumentException("Review reviewID cannot be empty!");
		}
		reviewRepository.deleteReviewByReviewID(reviewID);
	}

	/*
	 * Available Session
	 */
	public AvaliableSession createAvaliableSession(Time startTime, Time endTime, Integer AvaliableSessionID, Date day) {
		// Input validation
		String error = "";
		if (AvaliableSessionID == null) {
			error = error + "AvaliableSession AvaliableSessionID cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "AvaliableSession start time cannot be empty! ";
		}
		if (endTime == null) {
			error = error + "AvaliableSession end time cannot be empty! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "AvaliableSession end time cannot be before event start time!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		AvaliableSession AvaliableSession = new AvaliableSession();
		AvaliableSession.setAvaliableSessionID(AvaliableSessionID);
		AvaliableSession.setDay(day);
		AvaliableSession.setStartTime(startTime);
		AvaliableSession.setEndTime(endTime);
		AvaliableSessionRepository.save(AvaliableSession);
		return AvaliableSession;
	}

	@Transactional
	public AvaliableSession getAvaliableSession(Integer AvaliableSessionID) {
		if (AvaliableSessionID == null) {
			throw new IllegalArgumentException("AvaliableSession AvaliableSessionID cannot be empty!");
		}
		AvaliableSession AvaliableSession = AvaliableSessionRepository.findAvaliableSessionByAvaliableSessionID(AvaliableSessionID);
		return AvaliableSession;
	}

	@Transactional
	public List<AvaliableSession> getAllAvaliableSessions() {
		return toList(AvaliableSessionRepository.findAll());
	}

	@Transactional
	public void deleteAvaliableSession(Integer AvaliableSessionID) {
		if (AvaliableSessionID == null) {
			throw new IllegalArgumentException("AvaliableSession AvaliableSessionID cannot be empty!");
		}
		AvaliableSessionRepository.deleteAvaliableSessionByAvaliableSessionID(AvaliableSessionID);
	}

	/*
	 * Classroom
	 */
	@Transactional
	public Classroom createClassroom(String roomCode, Boolean isBooked, Boolean isBigRoom, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		String error = "";
		if (roomCode == null || roomCode.trim().length() == 0) {
			error = error + "roomCode cannot be empty!";
		}
		if (isBooked == null) {
			error = error + "isBooked cannot be empty!";
		}
		if (isBigRoom == null) {
			error = error + "isBigRoom cannot be empty!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Classroom classroom = new Classroom();
		classroom.setRoomCode(roomCode);
		classroom.setIsBooked(isBooked);
		classroom.setIsBigRoom(isBigRoom);
		classroom.setManager(manager);
		classroom.setOffering(offerings);
		classroom.setTutoringSystem(tutoringSystem);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom getClassroom(String roomCode) {
		if (roomCode == null || roomCode.trim().length() == 0){
			throw new IllegalArgumentException("Classroom roomCode cannot be empty!");
		}
		Classroom classroom = classroomRepository.findClassroomByRoomCode(roomCode);
		return classroom;
	}

	@Transactional
	public List<Classroom> getAllClassrooms() {
		return toList(classroomRepository.findAll());
	}

	@Transactional
	public void deleteClassroom(String roomCode) {
		if (roomCode == null || roomCode.trim().length() == 0){
			throw new IllegalArgumentException("Classroom roomCode cannot be empty!");
		}
		classroomRepository.deleteClassroomByRoomCode(roomCode);
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}