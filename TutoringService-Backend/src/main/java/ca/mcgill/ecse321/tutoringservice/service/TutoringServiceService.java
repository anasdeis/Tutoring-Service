package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;

@Service
public class TutoringServiceService {

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

	/*
	 * TutoringSystem
	 */

	@Transactional
	public TutoringSystem createTutoringSystem(Integer tutoringSystemID) {
		String error = "";

		if (tutoringSystemID == null || tutoringSystemID == 0) {
			error = error + "Tutoring System ID cannot be empty!";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		TutoringSystem tutoringSystem = tutoringSystemRepository.findTutoringSystemByTutoringSystemID(tutoringSystemID);
		if (tutoringSystem == null) {
		tutoringSystem = new TutoringSystem();
		tutoringSystem.setTutoringSystemID(tutoringSystemID);
		tutoringSystemRepository.save(tutoringSystem);
		}
		return tutoringSystem;
	}

	@Transactional

	public TutoringSystem getTutoringSystem(Integer tutoringSystemID) {
		if (tutoringSystemID == null){
			throw new IllegalArgumentException("TutoringSystem tutoringSystemID cannot be empty!");
		}

		TutoringSystem tutoringSystem = tutoringSystemRepository.findTutoringSystemByTutoringSystemID(tutoringSystemID);
		return tutoringSystem;
	}

	@Transactional
	public List<TutoringSystem> getAllTutoringSystems() {
		return toList(tutoringSystemRepository.findAll());
	}


	@Transactional
	public void deleteTutoringSystem(Integer tutoringSystemID) {
		if (tutoringSystemID == null){
			throw new IllegalArgumentException("TutoringSystem tutoringSystemID cannot be empty!");
		}
		tutoringSystemRepository.deleteTutoringSystemByTutoringSystemID(tutoringSystemID);
	}

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
		Login login = loginRepository.findLoginByUserName(userName);
		if (login == null) {
		login = new Login();
		login.setUserName(userName);
		login.setPassword(password);
		loginRepository.save(login);
		}
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
		if (commissionID == null) {
			error += "commissionID cannot be null!";
		}
		else if (commissionID <= 0) {
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
		
		Commission commission = commissionRepository.findCommissionBycommissionID(commissionID);
		if ( commission == null) {
		commission = new Commission();
		commission.setCommissionID(commissionID);
		commission.setPercentage(percentage);
		commission.setManager(manager);
		commission.setOffering(offerings);
		commission.setTutoringSystem(tutoringSystem);
		commissionRepository.save(commission);
		}
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
	public Subject createSubject(String name, String courseID, String description, SubjectType subjType, University university,TutoringSystem tutoringSystem) {
		String error = "";
		if (name == null || name.trim().length() == 0)
			error += "name cannot be empty or null!";
		if (description == null || description.trim().length() == 0)
			error += "description cannot be empty or null!";
		if (courseID == null || courseID.trim().length() == 0)
			error += "courseID cannot be empty or null!";
		if(subjType == null) 
			error += "subjectType cannot be null!";
		if(subjType == SubjectType.UNIVERSITY_COURSE && university == null)
			error += "university must be defined for university course";
		if(subjType != SubjectType.UNIVERSITY_COURSE && university != null)
			error += "cannot assign university to non university course";
		if (tutoringSystem == null) 
			error += "tutoringSystem cannot be null!";

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		
		Subject subject = subjectRepository.findSubjectByCourseID(courseID);
		if(subject == null) {
			subject = new Subject();
			subject.setName(name);
			subject.setSubjectType(subjType);
			subject.setCourseID(courseID);
			subject.setDescription(description);
			subject.setUniversity(university);
			subject.setTutoringSystem(tutoringSystem);
			subjectRepository.save(subject);
		}
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
	public SubjectRequest createSubjectRequest(Integer requestID, String name, String description,SubjectType subjectType, Manager manager, TutoringSystem tutoringSystem){
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "name cannot be null!";
		}
		if (description == null || description.trim().length() == 0) {
			error = error + "Description cannot be null!";
		}
		if (requestID == null || requestID == 0) {
			error = error + "requestID cannot be empty!";
		}
		if (subjectType == null) {
			error = error + "subjectType cannot be empty!";
		}
		if (manager == null) {
			error = error + "manager cannot be empty!";
		}
		if (tutoringSystem == null) {
			error = error + "Tutoring System cannot be empty!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		SubjectRequest subjectrequest = subjectRequestRepository.findSubjectRequestByRequestID(requestID);
		if (subjectrequest == null) {
		subjectrequest = new SubjectRequest();
		subjectrequest.setName(name);
		subjectrequest.setRequestID(requestID);
		subjectrequest.setDescription(description);
		subjectrequest.setSubjectType(subjectType);
		subjectrequest.setManager(manager);
		subjectrequest.setTutoringSystem(tutoringSystem);
		subjectRequestRepository.save(subjectrequest);
		}
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
		Manager manager = managerRepository.findManagerByPersonId(managerID);
		if (manager == null) {
		manager = new Manager();
		manager.setFirstName(first);
		manager.setLastName(last);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phone);
		manager.setPersonId(managerID);
		manager.setLoginInfo(loginInfo);
		manager.setTutoringSystem(tutoringSystem);
		managerRepository.save(manager);
		}
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
		Student student = studentRepository.findStudentByPersonId(studentID);
		if (student == null) {
		student = new Student();
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
		}
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
	public Offering createOffering(String offId, String term, double price, Set<AvailableSession> classTime, Subject subject, Tutor tutor, Commission commission, Classroom classroom, TutoringSystem tutoringSystem){
		String error ="";
		if (offId == null || offId.trim().length() == 0) {
			error = error + "Offering ID cannot be empty!";			
		}
		if (term == null || term.trim().length() == 0) {
			error = error + "Offering term cannot be empty!";
		}
		if (price == 0.0) {
			error = error + "Hourly rate cannot be empty!";
		}
		if (classTime == null) {
			error = error + "Class time cannot be empty!";
		}
/*		if (tutor == null) {
			error = error + "Tutor cannot be empty!";
		} else if (!tutorRepository.existsByPersonId(tutor.getPersonId())) {
			error = error + "Tutor does not exist!";
		}
		if (commission == null) {
			error = error + "Commission cannot be empty!";
		}// else if (!commissionRepository.existsByCommissionID(commission.getCommissionID())) {
		//	error = error + "Commission does not exist!";
		//}
		if (classroom == null) {
			error = error + "Classroom cannot be empty!";
		} //else if (!classroomRepository.existsByRoomCode(classroom.getRoomCode())) {
		//	error = error + "Classroom does not exist!";
		//}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem cannot be empty!";
		}// else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
		//	error = error + "TutoringSystem does not exist!";
		//}
		
 */
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Offering offering = offeringRepository.findOfferingByOfferingID(offId);
		if (offering == null) {
		offering = new Offering();
		offering.setOfferingID(offId);
		offering.setTerm(term);
		offering.setPricePerHour(price);
		// class ca.mcgill.ecse321.tutoringservice.model.AvailableSession cannot be cast to class java.util.Set (ca.mcgill.ecse321.tutoringservice.model.AvailableSession
		//is in unnamed module of loader 'app'; java.util.Set is in module java.base of loader 'bootstrap')
		offering.setClassTime((Set<AvailableSession>) classTime);
		offering.setSubject(subject);
		offering.setTutor(tutor);;
		offering.setCommission(commission);
		offering.setClassroom(classroom);
		offering.setTutoringSystem(tutoringSystem);
		offeringRepository.save(offering);
		}
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
	public Tutor createTutor(String first, String last, Date dob, String email, Integer phone, Integer tutorID, Boolean isRegistered, Login loginInfo, Set<TutorApplication> tutorApplications, Set<Offering> offerings, Set<AvailableSession> AvailableSessions, TutoringSystem tutoringSystem) {
		String error = ""; 

		if (first == null || first.trim().length() == 0) {
			error = error + "First name cannot be empty!";

		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Last name cannot be empty!";
		}
		
		if (dob == null) {
			error = error + "DOB cannot be empty!";
		}
		 
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

		if (tutorApplications != null)
		{
			for (TutorApplication tutorApplication : tutorApplications) {
				if (tutorApplication == null) {
					error = error + "TutorApplications needs to be selected for tutor!";
				} else if (!tutorApplicationRepository.existsByApplicationId(tutorApplication.getApplicationId())) {
					error = error + "TutorApplication does not exist!";
				}
			}
		}
		
		if (offerings != null)
		{
			for (Offering offering : offerings) {
				if (offering == null) {
					error = error + "Offering needs to be selected for tutor!";
				} else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
					error = error + "Offering does not exist!";
				}
			}
		}
		
		if (AvailableSessions != null)
		{
			for (AvailableSession AvailableSession : AvailableSessions) {
				if (AvailableSession == null) {
					error = error + "AvailableSession needs to be selected for tutor!";
				} else if (!AvailableSessionRepository.existsByAvailableSessionID(AvailableSession.getAvailableSessionID())) {
					error = error + "AvailableSession does not exist!";
				}
			}
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Tutor tutor = tutorRepository.findTutorByPersonId(tutorID);
		if (tutor == null) {
		tutor = new Tutor();
		tutor.setFirstName(first);
		tutor.setLastName(last);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phone);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(isRegistered);
		tutor.setLoginInfo(loginInfo);
	    tutor.setTutorApplication(tutorApplications);
	    tutor.setAvailableSession(AvailableSessions);
	    tutor.setOffering(offerings);
		tutor.setTutoringSystem(tutoringSystem);
		tutorRepository.save(tutor);
		}
		return tutor;
	}
	
	@Transactional
	public Tutor setTutorIsRegistered (Tutor tutor, Boolean isRegistered) {
		if (isRegistered == null) {
			throw new IllegalArgumentException("Tutor isRegistered cannot be null!");
		}
		tutor.setIsRegistered(isRegistered);
		
		return tutorRepository.save(tutor);
	}
	
	@Transactional
	public TutorApplication setTutorApplicationIsAccepted (TutorApplication tutorApplication, Boolean isAccepted) {
		if (isAccepted == null) {
			throw new IllegalArgumentException(" isApproved cannot be null!");
		}
		tutorApplication.setIsAccepted(isAccepted);
		
		return tutorApplicationRepository.save(tutorApplication);
	}

	@Transactional
	public Tutor getTutor(Integer tutorID) {
		if (tutorID == null) {
			throw new IllegalArgumentException("Tutor tutorID cannot be null!");
		}
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
		String error = "";

		if (reviewID == null || reviewID == 0) {
			error += "reviewID cannot be empty!";
		}

		if (comment == null || comment.trim().length() == 0) {
			error += "comment cannot be null!";
		}
		if (isApproved == null) {
			error += "isApproved cannot be null!";
		}
		if (manager == null) {
			error += "manager cannot be null!";
		}
		if (offering == null) {
			error += "offering cannot be null!";
		}
		if (tutoringSystem == null) {
			error += "tutoringSystem cannot be null!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Review review = reviewRepository.findReviewByReviewID(reviewID);
		if (review == null) {
		review = new Review();
		review.setComment(comment);
		review.setIsApproved(isApproved);
		review.setReviewID(reviewID);
		review.setManager(manager);
		review.setOffering(offering);
		review.setTutoringSystem(tutoringSystem);
		reviewRepository.save(review);
		}
		return review;
	}
	
	@Transactional
	public Review setReviewIsApproved (Review review, Boolean isApproved) {
		if (isApproved == null) {
			throw new IllegalArgumentException("Review isApproved cannot be null!");
		}
		review.setIsApproved(isApproved);
		
		return reviewRepository.save(review);
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
	 * tutorApplication    
	 */
	@Transactional
	public TutorApplication createTutorApplication(Integer applicationId, Boolean isAccepted, Tutor tutor, TutoringSystem tutoringSystem) {
		String error = "";
		if (applicationId == null || applicationId == 0) {
			error += "applicationId cannot be empty!";
		}

		if (isAccepted == null ) {
			error += "isAccepted cannot be null!";
		}
		if (tutor == null) {
			error += "tutor cannot be null!";
		}

		if (tutoringSystem == null) {
			error += "tutoringSystem cannot be null!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		TutorApplication tutorapplication = tutorApplicationRepository.findTutorApplicationByApplicationId(applicationId);
		if (tutorapplication == null) {
		tutorapplication = new TutorApplication();
		tutorapplication.setApplicationId(applicationId);
		tutorapplication.setTutor(tutor);
		tutorapplication.setIsAccepted(isAccepted);
		tutorapplication.setTutoringSystem(tutoringSystem);
		tutorApplicationRepository.save(tutorapplication);
		}
		return tutorapplication;
	}	
	@Transactional
	public TutorApplication getTutorApplication(Integer applicationId) {
		if (applicationId == null) {
			throw new IllegalArgumentException("TutorApplication applicationId cannot be empty!");
		}
		TutorApplication tutorApplication = tutorApplicationRepository.findTutorApplicationByApplicationId(applicationId);
		return tutorApplication;

	}
	@Transactional
	public List<TutorApplication> getAllTutorApplications() {
		return toList(tutorApplicationRepository.findAll());
	}
	@Transactional
	public void deleteTutorApplication(Integer applicationId) {
		if (applicationId == null) {
			throw new IllegalArgumentException("TutorApplication applicationId cannot be empty!");
		}
		tutorApplicationRepository.deleteTutorApplicationByApplicationId(applicationId);
	}
	/*
	 * Available Session
	 */
	public AvailableSession createAvailableSession(Time startTime, Time endTime, Integer AvailableSessionID, Date day, Set<Tutor> tutors, TutoringSystem tutoringSystem) {
		// Input validation
		String error = "";
		if (AvailableSessionID == null) {
			error = error + "AvailableSession AvailableSessionID cannot be empty!";
		}
		if (startTime == null) {
			error = error + "AvailableSession start time cannot be empty!";
		}
		if (endTime == null) {
			error = error + "AvailableSession end time cannot be empty!";
		}
		if (day == null) {
			error = error + "AvailableSession day cannot be empty!";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "AvailableSession end time cannot be before event start time!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for available session!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		
		if (!(tutors == null || tutors.isEmpty()))
		{
			for (Tutor tutor : tutors) {
				if (tutor == null) {
					error = error + "Tutor needs to be selected for available session!";
				} else if (!tutorRepository.existsByPersonId(tutor.getPersonId())) {
					error = error + "Tutor does not exist!";
				}
			}
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		AvailableSession AvailableSession = AvailableSessionRepository.findAvailableSessionByAvailableSessionID(AvailableSessionID);
		if (AvailableSession == null) {
		AvailableSession = new AvailableSession();
		AvailableSession.setAvailableSessionID(AvailableSessionID);
		AvailableSession.setDay(day);
		AvailableSession.setStartTime(startTime);
		AvailableSession.setEndTime(endTime);
		AvailableSession.setTutoringSystem(tutoringSystem);
		AvailableSession.setTutor(tutors);
		AvailableSessionRepository.save(AvailableSession);
		}
		return AvailableSession;
	}

	@Transactional
	public AvailableSession getAvailableSession(Integer AvailableSessionID) {
		if (AvailableSessionID == null) {
			throw new IllegalArgumentException("AvailableSession AvailableSessionID cannot be empty!");
		}
		AvailableSession AvailableSession = AvailableSessionRepository.findAvailableSessionByAvailableSessionID(AvailableSessionID);
		return AvailableSession;
	}

	@Transactional
	public List<AvailableSession> getAllAvailableSessions() {
		return toList(AvailableSessionRepository.findAll());
	}

	@Transactional
	public void deleteAvailableSession(Integer AvailableSessionID) {
		if (AvailableSessionID == null) {
			throw new IllegalArgumentException("AvailableSession AvailableSessionID cannot be empty!");
		}
		AvailableSessionRepository.deleteAvailableSessionByAvailableSessionID(AvailableSessionID);
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
		Classroom classroom = classroomRepository.findClassroomByRoomCode(roomCode);
		if (classroom == null) {
		classroom = new Classroom();
		classroom.setRoomCode(roomCode);
		classroom.setIsBooked(isBooked);
		classroom.setIsBigRoom(isBigRoom);
		classroom.setManager(manager);
		classroom.setOffering(offerings);
		classroom.setTutoringSystem(tutoringSystem);
		classroomRepository.save(classroom);
		}
		return classroom;
	}
	
	@Transactional
	public Classroom createReviewSession(String offeringID, Integer managerID, String roomCode, Integer tutoringSystemID){
		Offering offering = getOffering(offeringID);

		Boolean isBooked = true;
		Boolean isBigRoom = true;
		
		List<Classroom> classrooms = new ArrayList<Classroom>();
		classrooms = getAllClassrooms();
		String thisClassID = null;
		for(Classroom c : classrooms) {
			if(c.getIsBigRoom()) {
				Set <Offering> currOfferings = new HashSet<Offering>();
				currOfferings.add(offering);
				c.setOffering(currOfferings);
				c.setIsBooked(isBooked);
				thisClassID = c.getRoomCode();
				break;
			}
		}
		
		Classroom thisClass = getClassroom(thisClassID);
		if (thisClass == null) {
			Manager manager = getManager(managerID);
			TutoringSystem tutoringSystem = getTutoringSystem(tutoringSystemID);
			Set<Offering> thisOffering = new HashSet<Offering>();
			thisOffering.add(offering);
			thisClass = createClassroom(roomCode, isBooked, isBigRoom, manager, thisOffering, tutoringSystem);
			classroomRepository.save(thisClass);
		}
		return thisClass;
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


	/*
	 * University
	 */
	@Transactional
	public University createUniversity(String name, Set<Subject> subjects, TutoringSystem tutoringSystem) {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "name cannot be empty!";
		}

		if (subjects != null)
		{
			for (Subject subject : subjects) {
				if (subject == null) {
					error = error + "Subject needs to be selected for university!";
				} else if (!subjectRepository.existsByCourseID(subject.getCourseID())) {
					error = error + "Subject does not exist!";
				}
			}
		}

		if (tutoringSystem == null) {
			error = error + "Tutoring System cannot be empty!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		University university = universityRepository.findUniversityByName(name);
		if(university == null) {
		university = new University();
		university.setName(name);
		university.setSubject(subjects);
		university.setTutoringSystem(tutoringSystem);
		universityRepository.save(university);
		}
		return university;
	}

	@Transactional
	public University getUniversity(String name) {
		if (name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("University name cannot be empty!");
		}
		University university = universityRepository.findUniversityByName(name);
		return university;
	}

	@Transactional
	public List<University> getAllUniversitys() {
		return toList(universityRepository.findAll());
	}

	@Transactional
	public void deleteUniversity(String name) {
		if (name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("University name cannot be empty!");
		}
		universityRepository.deleteUniversityByName(name);
	}


	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}