package ca.mcgill.ecse321.tutoringservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
		Login login = loginRepository.findLoginById(userName);
		return login;
	}

	@Transactional
	public List<Login> getAllLogins() {
		return toList(loginRepository.findAll());
	}

	@Transactional
	public void deleteLogin(String userName) {
		loginRepository.deleteLoginById(userName);
	}
	
	@Transactional
	public Commission createCommission(double percentage, int commissionID) {
		String error = "";
		if (percentage == null) {
			error = error + "percentage cannot be null! ";
		}
		if (commissionID == null) {
			error = error + "Commission ID cannot be null! ";
		}
		Commission commission = new Commission();
		commission.setCommissionID(commissionID);
		commission.setPercentage(percentage);
		commissionRepository.save(commission);
		return commission;
	}

	@Transactional
	public Commission getCommission(Integer commissionID) {
		Commission commission = commissionRepository.findCommissionById(commissionID);
		return commission;
	}

	@Transactional
	public List<Commission> getAllCommissions() {
		return toList(commissionRepository.findAll());
	}

	@Transactional
	public void deleteCommisison(Integer commissionID) {
		commissionRepository.deleteCommissionById(commissionID);
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
		Subject subject = subjectRepository.findSubjectById(courseID);
		return subject;
	}

	@Transactional
	public List<Subject> getAllSubjects() {
		return toList(subjectRepository.findAll());
	}
	

	@Transactional
	public void deleteSubject(String courseID) {
		subjectRepository.deleteSeubjectById(courseID);
	}



	@Transactional
	public Subject createSubjectRequest(int requestID, String name, String description, SubjectType subjectType)
	{
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
		subjectrequestRepository.save(subjectrequest);
		return subjectrequest;
	}

	@Transactional
	public SubjectRequest getSubjectRequest(String reqeustID) {
		SubjectRequest subjectrequest = subjectrequestRepository.findSubjectRequestById(requestID);
		return subjectrequest;
	}

	@Transactional
	public List<SubjectRequest> getAllSubjectRequests() {
		return toList(subjectrequestRepository.findAll());
	}

	@Transactional
	public void deleteSubjectRequest(String requestID) {
		subjectRequestRepository.deleteSeubjectRequestById(requestID);
	}
	
	@Transactional
	public Manager createManager(String first, String last, Date dob, String email, int phone, int managerID) {
		Manager manager = new Manager();
		manager.setFirstName(first);
		manager.setLastName(last);
		manager.setDateOfBirth(dob);
		manager.setEmail(email);
		manager.setPhoneNumber(phone);
		manager.setManagerID(managerID);
		managerRepository.save(manager);
		return manager;
	}
	
	@Transactional
	public Manager getManager(int managerID) {
		Manager manager = managerRepository.findManagerById(managerID);
		return manager;
	}
	
	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}

	@Transactional
	public void deleteManager(int managerID) {
		managerRepository.deleteManagerById(managerID);
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
		Offering offering = offeringRepository.findOfferingById(offID);
		return offering;
	}
	
	@Transactional
	public List<Offering> getAllOfferings() {
		return toList(offeringRepository.findAll());
	}

	@Transactional
	public void deleteOffering(String offID) {
		offeringRepository.deleteOfferingById(offID);
	}
	
	
	@Transactional
	public Tutor createTutor(String first, String last, Date dob, String email, int phone, int tutorID, Boolean isRegistered ) {
		Tutor tutor = new Tutor();
		tutor.setFirstName(first);
		tutor.setLastName(last);
		tutor.setDateOfBirth(dob);
		tutor.setEmail(email);
		tutor.setPhoneNumber(phone);
		tutor.setTutorID(tutorID);
		tutor.setIsRegistered(isRegistered);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	@Transactional
	public Tutor getTutor(int tutorID) {
		Tutor tutor = tutorRepository.findTutorById(tutorID);
		return tutor;
	}
	
	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}

	@Transactional
	public void deleteTutor(int tutorID) {
		tutorRepository.deleteTutorById(tutorID);
	}
	
	@Transactional
	public createReview(String comment, Boolean isApproved, Integer reviewID)
	{
		Review review = new Review();
		review.setComment(comment);
		review.setIsApproved(isApproved);
		review.setReviewID(reviewID);
		reviewRepository.save(review);
		return review;
	}

	@Transactional
	public Review getReview(Integer reviewID) {
		Review review = reviewRepository.findReviewById(reviewID);
		return review;
	}

	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public AvailableSession createAvailableSession(Time startTime, Time endTime, Integer availableSessionID, Date day) {
		AvailableSession availableSession = new AvailableSession();
		availableSession.setAvailableSessionID(availableSessionID);
		availableSession.setDay(day);
		availableSession.setStartTime(startTime);
		availableSession.setEndTime(endTime);
		availableSessionRepository.save(availableSession);
		return availableSession;
	}

	@Transactional
	public AvailableSession getAvailableSession(Integer availableSessionID) {
		AvailableSession availableSession = availableSessionRepository.findAvailableSessionByID(availableSessionID);
		return availableSession;
	}

	@Transactional
	public List<AvailableSession> getAllAvailableSessions() {
		return toList(availableSessionRepository.findAll());
	}

	@Transactional
	public Classroom createClassroom(String roomCode, Boolean isBooked, Boolean isBigRoom) {
		Classroom classroom = new Classroom();
		Classroom.setRoomCode(roomCode);
		Classroom.setIsBooked(isBooked);
		Classroom.setIsBigRoom(isBigRoom);
		ClassroomRepository.save(classroom);
		return Classroom;
	}

	@Transactional
	public Classroom getClassroom(String roomCode) {
		Classroom classroom = classroomRepository.findClassroomByID(roomCode);
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