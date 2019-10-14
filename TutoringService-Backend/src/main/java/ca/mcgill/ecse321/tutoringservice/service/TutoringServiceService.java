package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.tutoringservice.dao.*;
import ca.mcgill.ecse321.tutoringservice.model.*;

@Service
public class TutoringServiceService {

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

	
	@Transactional
	public Login createLogin(String userName, String password) {
		String error = "";
		if (userName == null || userName.trim().length() == 0) {
			error = error + "userName cannot be null! ";
		}
		if (password == null || password.trim().length() == 0) {
			error = error + "password cannot be null! ";
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
	
	@Transactional
	public Commission createCommission(double percentage, int commissionID) {
		String error = "";
		if (percentage <= 0) {
			error = error + "percentage cannot be <= 0";
		}
		if (commissionID <= 0) {
			error = error + "Commission ID cannot be <= 0";
		}
		Commission commission = new Commission();
		commission.setCommissionID(commissionID);
		commission.setPercentage(percentage);
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

	@Transactional
	public Subject createSubject(String name, String courseID, String description, SubjectType subjectType)
	{
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "name cannot be null! ";
		}
		if (courseID == null || courseID.trim().length() == 0) {
			error = error + "course ID cannot be null! ";
		}
		if (description == null || description.trim().length() == 0) {
			error = error + "Description cannot be null! ";
		}

		Subject subject = new Subject();
		subject.setName(name);
		subject.setCourseID(courseID);
		subject.setDescription(description);
		subject.setSubjectType(subjectType);
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



	@Transactional
	public SubjectRequest createSubjectRequest(int requestID, String name, String description, SubjectType subjectType){
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
		subjectrequest.setSubjectType(subjectType);
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
	public Manager createManager(String first, String last, Date dob, String email, int phone, int managerID) {
		Manager manager = new Manager();
		manager.setFirstName(first);
		manager.setLastName(last);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phone);
		manager.setPersonId(managerID);
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
	
	/*
	 * Offering
	 */
	@Transactional
	public void deleteManager(int managerID) {
		managerRepository.deleteManagerByPersonId(managerID);
	}

	@Transactional
	public Offering createOffering(String offId, String term, double price, Subject subj){
		String error = "";
		if (offId == null || offId.trim().length() == 0) {
			error = error + "Offering ID cannot be null! ";
		}
		if (term == null || term.trim().length() == 0) {
			error = error + "Term cannot be null! ";
		}
		if (price == 0.0)
			error = error + "Price cannot be empty! ";
		if (subj == null)
			error = error + "Subject cannot be empty! ";
		Offering offering = new Offering();
		offering.setOfferingID(offId);
		offering.setTerm(term);
		offering.setPricePerHour(price);
		offering.setSubject(subj);
		offeringRepository.save(offering);
		return offering;
	}
	
	@Transactional
	public Offering getOffering(String offID) {
		Offering offering = offeringRepository.findOfferingByOfferingID(offID);
		return offering;
	}
	
	@Transactional
	public List<Offering> getAllOfferings() {
		return toList(offeringRepository.findAll());
	}

	@Transactional
	public void deleteOffering(String offID) {
		offeringRepository.deleteOfferingByOfferingID(offID);
	}
	
	/*
	 *  Review
	 */
	/*
	@Transactional
	public Review createReview(String comment, Manager manager, Offering offering, Integer reviewID) {
		Review review = new Review();
		
		// Input validation
		String error = "";
		if (comment == null || comment.trim().length() == 0) {
			error = error + "Comment cannot be empty! ";
		}
		review.setComment(comment);
		review.setIsApproved(false);
		review.setManager(manager);
		review.setOffering(offering);
		review.setReviewID(reviewID);
		reviewRepository.save(review);
		
		if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		return review;
	}

	@Transactional
	public Optional<Review> getReview(Integer reviewID) {
		Optional<Review> review =  reviewRepository.findById(reviewID);
		return review;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public List<Review> getReviewsByOffering(Offering offering) {
		List<Review> reviewsForOffering = new ArrayList<>();
		for (Review r : reviewRepository.findByOffering(offering)) {
			reviewsForOffering.add(r);
		}
		return reviewsForOffering;
*/
	
	/*
	 * Tutor
	 */
	@Transactional
	public Tutor createTutor(String first, String last, Date dob, String email, int phone, int tutorID, Boolean isRegistered ) {
		Tutor tutor = new Tutor();
		tutor.setFirstName(first);
		tutor.setLastName(last);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phone);
		tutor.setPersonId(tutorID);
		tutor.setIsRegistered(isRegistered);
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
	public Review createReview(String comment, Boolean isApproved, Integer reviewID){
		Review review = new Review();
		review.setComment(comment);
		review.setIsApproved(isApproved);
		review.setReviewID(reviewID);
		reviewRepository.save(review);
		return review;
	}

	@Transactional
	public Review getReview(Integer reviewID) {
		Review review = reviewRepository.findReviewByReviewID(reviewID);
		return review;
	}

	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	/*
	 * Available Session
	 */
	@Transactional
	public AvaliableSession createAvailableSession(Time startTime, Time endTime, Integer availableSessionID, Date day) {
		AvaliableSession availableSession = new AvaliableSession();
		availableSession.setAvaliableSessionID(availableSessionID);
		availableSession.setDay(day);
		availableSession.setStartTime(startTime);
		availableSession.setEndTime(endTime);
		availableSessionRepository.save(availableSession);
		return availableSession;
	}

	@Transactional
	public AvaliableSession getAvailableSession(Integer availableSessionID) {
		AvaliableSession availableSession = availableSessionRepository.findAvailableSessionByAvaliableSessionID(availableSessionID);
		return availableSession;
	}

	@Transactional
	public List<AvaliableSession> getAllAvailableSessions() {
		return toList(availableSessionRepository.findAll());
	}

	/*
	 * Classroom
	 */
	@Transactional
	public Classroom createClassroom(String roomCode, Boolean isBooked, Boolean isBigRoom) {
		Classroom classroom = new Classroom();
		classroom.setRoomCode(roomCode);
		classroom.setIsBooked(isBooked);
		classroom.setIsBigRoom(isBigRoom);
		classroomRepository.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom getClassroom(String roomCode) {
		Classroom classroom = classroomRepository.findClassroomByRoomCode(roomCode);
		return classroom;
	}

	@Transactional
	public List<Classroom> getAllClassrooms() {
		return toList(classroomRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}