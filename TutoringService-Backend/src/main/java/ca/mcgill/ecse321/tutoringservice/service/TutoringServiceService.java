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

	/*
	 * TutoringSystem
	 */

	@Transactional
	public TutoringSystem createTutoringSystem(Integer tutoringSystemID) {
		String error = "";

		if (tutoringSystemID == null || tutoringSystemID <= 0) {
			error = error + "TutoringSystem tutoringSystemID cannot be empty!";
		}
		else if (tutoringSystemID <= 0) {
			error += "TutoringSystem tutoringSystemID cannot be <= 0!";
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
		if (tutoringSystemID == null ){
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
	 * Login DONE
	 */
	@Transactional
	public Login createLogin(String userName, String password) {
		String error = "";
		if (userName == null || userName.trim().length() == 0) {
			error += "Login userName cannot be empty!";
		}
		if (password == null || password.trim().length() == 0) {
			error += "Login password cannot be empty!";
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
		if (userName == null || userName.trim().length() == 0){
			throw new IllegalArgumentException("Login userName cannot be empty!");
		}
		Login login = loginRepository.findLoginByUserName(userName);
		return login;
	}

	@Transactional
	public List<Login> getAllLogins() {
		return toList(loginRepository.findAll());
	}

	@Transactional
	public void deleteLogin(String userName) {
		if (userName == null || userName.trim().length() == 0){
			throw new IllegalArgumentException("Login userName cannot be empty!");
		}
		loginRepository.deleteLoginByUserName(userName);
	}

	/*
	 * Commission DONE
	 */
	@Transactional
	public Commission createCommission(double percentage, Integer commissionID, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		String error = "";
		if (percentage <= 0) {
			error += "Commission percentage cannot be <= 0!";
		}
		if (commissionID == null) {
			error += "Commission commissionID cannot be empty!";
		}
		else if (commissionID <= 0) {
			error += "Commission commissionID cannot be <= 0!";
		}
		if (manager == null) {
			error += "Manager needs to be selected for Commission!";
		}else if (!managerRepository.existsByPersonId(manager.getPersonId())) {
			error = error + "Manager does not exist!";
		}
		if (tutoringSystem == null) {
			error += "TutoringSystem needs to be selected for Commission!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		if (!(offerings == null || offerings.isEmpty()))
		{
			for (Offering offering : offerings) {
				if (offering == null) {
					error = error + "Offering needs to be selected for Commission!";
				} else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
					error = error + "Offering does not exist!";
				}
			}
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
		if (commissionID == null){
			throw new IllegalArgumentException("Commission commissionID cannot be empty!");
		}
		Commission commission = commissionRepository.findCommissionBycommissionID(commissionID);
		return commission;
	}

	@Transactional
	public List<Commission> getAllCommissions() {
		return toList(commissionRepository.findAll());
	}

	@Transactional
	public void deleteCommisison(Integer commissionID) {
		if (commissionID == null){
			throw new IllegalArgumentException("Commission commissionID cannot be empty!");
		}
		commissionRepository.deleteCommissionBycommissionID(commissionID);
	}

	/*
	 * Subject DONE
	 */
	@Transactional
	public Subject createSubject(String name, String courseID, String description, SubjectType subjType, University university, Set<Offering> offerings, Set<TutorApplication> tutorApplications, TutoringSystem tutoringSystem) {
		String error = "";
		if (name == null || name.trim().length() == 0)
			error += "Subject name cannot be empty!";
		if (description == null || description.trim().length() == 0)
			error += "Subject description cannot be empty!";
		if (courseID == null || courseID.trim().length() == 0)
			error += "Subject courseID cannot be empty!";
		if(subjType == null) 
			error += "Subject subjectType cannot be empty!";
		if(subjType == SubjectType.UNIVERSITY_COURSE && university == null)
			error += "university must be defined for university course";
		if(subjType != SubjectType.UNIVERSITY_COURSE && university != null)
			error += "cannot assign university to non university course";
		if (tutoringSystem == null) 
			error += "TutoringSystem needs to be selected for Subject!";
		//1->*
		if (!(offerings == null || offerings.isEmpty()))
		{
			for (Offering offering : offerings) {
				if (offering == null) {
					error = error + "Offering needs to be selected for Subject!";
				} else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
					error = error + "Offering does not exist!";
				}
			}
		}
		//*->*
		if (!(tutorApplications == null || tutorApplications.isEmpty()))
		{
			for (TutorApplication tutorApplication : tutorApplications) {
				if (tutorApplication == null) {
					error = error + "TutorApplication needs to be selected for Subject!";
				} else if (!tutorApplicationRepository.existsByApplicationId(tutorApplication.getApplicationId())) {
					error = error + "TutorApplication does not exist!";
				}
			}
		}
		
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
			subject.setOffering(offerings);
			subject.setTutorRole(tutorApplications);
			subject.setTutoringSystem(tutoringSystem);
			subjectRepository.save(subject);
		}
		return subject;
	}

	@Transactional
	public Subject getSubject(String courseID) {
		if (courseID == null){
			throw new IllegalArgumentException("Subject courseID cannot be empty!");
		}
		Subject subject = subjectRepository.findSubjectByCourseID(courseID);
		return subject;
	}

	@Transactional
	public List<Subject> getAllSubjects() {
		return toList(subjectRepository.findAll());
	}


	@Transactional
	public void deleteSubject(String courseID) {
		if (courseID == null){
			throw new IllegalArgumentException("Subject courseID cannot be empty!");
		}
		subjectRepository.deleteSubjectByCourseID(courseID);
	}

	/*
	 * Subject Request DONE
	 */
	@Transactional
	public SubjectRequest createSubjectRequest(Integer requestID, String name, String description,SubjectType subjectType, Manager manager, Set<Student> students, TutoringSystem tutoringSystem){
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "SubjectRequest name cannot be empty!";
		}
		if (description == null || description.trim().length() == 0) {
			error = error + "SubjectRequest description cannot be empty!";
		}
		if (requestID == null) {
			error = error + "SubjectRequest requestID cannot be empty!";
		}
		else if (requestID <= 0) {
			error = error + "SubjectRequest requestID cannot be <= 0!";
		}
		if (subjectType == null) {
			error = error + "SubjectRequest subjectType cannot be empty!";
		}
		if (manager == null) {
			error = error + "Manager needs to be selected for SubjectRequest!";
		}else if (!managerRepository.existsByPersonId(manager.getPersonId())) {
			error = error + "Manager does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for SubjectRequest!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		//*->*
		if (!(students == null || students.isEmpty()))
		{
			for (Student student : students) {
				if (student == null) {
					error = error + "Student needs to be selected for SubjectRequest!";
				} else if (!studentRepository.existsByPersonId(student.getPersonId())) {
					error = error + "Student does not exist!";
				}
			}
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
			subjectrequest.setStudent(students);
			subjectrequest.setTutoringSystem(tutoringSystem);
			subjectRequestRepository.save(subjectrequest);
		}
		return subjectrequest;
	}

	@Transactional
	public SubjectRequest getSubjectRequest(Integer requestID) {
		if (requestID == null){
			throw new IllegalArgumentException("SubjectRequest requestID cannot be empty!");
		}
		SubjectRequest subjectrequest = subjectRequestRepository.findSubjectRequestByRequestID(requestID);
		return subjectrequest;
	}

	@Transactional
	public List<SubjectRequest> getAllSubjectRequests() {
		return toList(subjectRequestRepository.findAll());
	}

	@Transactional
	public void deleteSubjectRequest(Integer requestID) {
		if (requestID == null){
			throw new IllegalArgumentException("SubjectRequest requestID cannot be empty!");
		}
		subjectRequestRepository.deleteSubjectRequestByRequestID(requestID);
	}

	/*
	 * Manager DONE
	 */
	@Transactional
	public Manager createManager(String first, String last, Date dob, String email, Integer phone, Integer managerID, Login loginInfo, Set<SubjectRequest> subjectRequests, Set<Review> reviews, Set<Commission> commissions, Set<Classroom> classrooms, TutoringSystem tutoringSystem) {
		String error = "";

		if (first == null || first.trim().length() == 0) {
			error = error + "Manager firstname cannot be empty!";
		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Manager lastname cannot be empty!";
		}
		
		if (dob == null) {
			error = error + "Manager date of birth cannot be empty!";
		}
		
		if (email == null || email.trim().length() == 0) {
			error = error + "Manager email cannot be empty!";
		}
		if (phone == null) {
			error = error + "Manager phone cannot be empty!";
		}
		else if (phone <= 0) {
			error = error + "Manager phone cannot be <= 0!";
		}
		if (managerID == null || managerID == 0) {
			error = error + "Manager managerID cannot be empty!";
		}
		if (loginInfo == null) {
			error = error + "Login needs to be selected for Manager!";
		}else if (!loginRepository.existsByUserName(loginInfo.getUserName())) {
			error = error + "Login Info does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for Manager!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		
		//1->*
		if (!(subjectRequests == null || subjectRequests.isEmpty()))
		{
			for (SubjectRequest subjectRequest : subjectRequests) {
				if (subjectRequest == null) {
					error = error + "SubjectRequest needs to be selected for Manager!";
				} else if (!subjectRequestRepository.existsByRequestID(subjectRequest.getRequestID())) {
					error = error + "SubjectRequest does not exist!";
				}
			}
		}
		//1->*
		if (!(reviews == null || reviews.isEmpty()))
		{
			for (Review review : reviews) {
				if (review == null) {
					error = error + "Review needs to be selected for Manager!";
				} else if (!reviewRepository.existsByReviewID(review.getReviewID())) {
					error = error + "Review does not exist!";
				}
			}
		}
		//1->*
		if (!(commissions == null || commissions.isEmpty()))
		{
			for (Commission commission : commissions) {
				if (commission == null) {
					error = error + "Commission needs to be selected for Manager!";
				} else if (!commissionRepository.existsByCommissionID(commission.getCommissionID())) {
					error = error + "Commission does not exist!";
				}
			}
		}
		//1->*
		if (!(classrooms == null || classrooms.isEmpty()))
		{
			for (Classroom classroom : classrooms) {
				if (classroom == null) {
					error = error + "Classroom needs to be selected for Manager!";
				} else if (!classroomRepository.existsByRoomCode(classroom.getRoomCode())) {
					error = error + "Classroom does not exist!";
				}
			}
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
			manager.setClassroom(classrooms);
			manager.setCommission(commissions);
			manager.setReview(reviews);
			manager.setSubjectRequest(subjectRequests);
			manager.setTutoringSystem(tutoringSystem);
			managerRepository.save(manager);
		}
		return manager;
	}

	@Transactional
	public Manager getManager(Integer managerID) {
		if (managerID == null){
			throw new IllegalArgumentException("Manager managerID cannot be empty!");
		}
		Manager manager = managerRepository.findManagerByPersonId(managerID);
		return manager;
	}

	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}

	@Transactional
	public void deleteManager(Integer managerID) {
		if (managerID == null){
			throw new IllegalArgumentException("Manager managerID cannot be empty!");
		}
		managerRepository.deleteManagerByPersonId(managerID);
	}

	/*
	 * Student DONE
	 */
	@Transactional
	public Student createStudent(String first, String last, Date dob, String email, Integer phone, Integer studentID, Integer numCoursesEnrolled, Login loginInfo, Set<SubjectRequest> subjectRequests, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		String error = ""; 

		if (first == null || first.trim().length() == 0) {
			error = error + "Student firstname cannot be empty!";

		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Student lastname cannot be empty!";
		}
		
		if (dob == null) {
			error = error + "Student DOB cannot be empty!";
		}
		 
		if (email == null || email.trim().length() == 0) {
			error = error + "Student email cannot be empty!";
		}
		if (phone == null || phone == 0) {
			error = error + "Student phone cannot be empty!";
		}
		if (studentID == null) {
			error = error + "Student studentID cannot be empty!";
		}
		else if (studentID <= 0) {
			error = error + "Student studentID cannot be <= 0!";
		}
		if (loginInfo == null) {
			error = error + "Login needs to be selected for Student!";
		}else if (!loginRepository.existsByUserName(loginInfo.getUserName())) {
			error = error + "Login Info does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for Student!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		
		//*->*
		if (!(subjectRequests == null || subjectRequests.isEmpty()))
		{
			for (SubjectRequest subjectRequest : subjectRequests) {
				if (subjectRequest == null) {
					error = error + "SubjectRequest needs to be selected for Student!";
				} else if (!subjectRequestRepository.existsByRequestID(subjectRequest.getRequestID())) {
					error = error + "SubjectRequest does not exist!";
				}
			}
		}
		
		//*->*
		if (!(offerings == null || offerings.isEmpty()))
		{
			for (Offering offering : offerings) {
				if (offering == null) {
					error = error + "Offering needs to be selected for Student!";
				} else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
					error = error + "Offering does not exist!";
				}
			}
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
			student.setSubjectRequest(subjectRequests);
			student.setCoursesTaken(offerings);
			student.setTutoringSystem(tutoringSystem);
		studentRepository.save(student);
		}
		return student;
	}

	@Transactional
	public Student getStudent(Integer studentID) {
		if (studentID == null){
			throw new IllegalArgumentException("Student studentID cannot be empty!");
		}
		Student student = studentRepository.findStudentByPersonId(studentID);
		return student;
	}

	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	@Transactional
	public void deleteStudent(Integer studentID) {
		if (studentID == null){
			throw new IllegalArgumentException("Student studentID cannot be empty!");
		}
		studentRepository.deleteStudentByPersonId(studentID);
	}

	/*
	 * Offering
	 */
	@Transactional
	public Offering createOffering(String offId, String term, double price, Set<AvailableSession> classTime, Subject subject, Tutor tutor, Commission commission, Classroom classroom, Set<Student> students, Set<Review> reviews, TutoringSystem tutoringSystem){
		String error ="";
		if (offId == null || offId.trim().length() == 0) {
			error = error + "Offering offeringID cannot be empty!";			
		}
		if (term == null || term.trim().length() == 0) {
			error = error + "Offering term cannot be empty!";
		}

		if (price <= 0.0) {
			error = error + "Offering price per hour cannot be <= 0!";
		}
			
		if (classTime == null) {
			error = error + "Offering class time cannot be empty!";
		}
		if (subject == null) {
			error = error + "Subject needs to be selected for Offering!";
		} else if (!subjectRepository.existsByCourseID(subject.getCourseID())) {
			error = error + "Subject does not exist!";
		}
		if (tutor == null) {
			error = error + "Tutor needs to be selected for Offering!";
		} else if (!tutorRepository.existsByPersonId(tutor.getPersonId())) {
			error = error + "Tutor does not exist!";
		}
		if (commission == null) {
			error = error + "Commission needs to be selected for Offering!";
		} else if (!commissionRepository.existsByCommissionID(commission.getCommissionID())) {
			error = error + "Commission does not exist!";
		}
		if (classroom == null) {
			error = error + "Classroom needs to be selected for Offering!";
		} else if (!classroomRepository.existsByRoomCode(classroom.getRoomCode())) {
			error = error + "Classroom does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for Offering!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		
		//*->*
		if (!(students == null || students.isEmpty()))
		{
			for (Student student : students) {
				if (student == null) {
					error = error + "Student needs to be selected for SubjectRequest!";
				} else if (!studentRepository.existsByPersonId(student.getPersonId())) {
					error = error + "Student does not exist!";
				}
			}
		}
		
		//1->*
		if (!(reviews == null || reviews.isEmpty()))
		{
			for (Review review : reviews) {
				if (review == null) {
					error = error + "Review needs to be selected for Manager!";
				} else if (!reviewRepository.existsByReviewID(review.getReviewID())) {
					error = error + "Review does not exist!";
				}
			}
		}
 
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
			offering.setStudentsEnrolled(students);
			offering.setReview(reviews);
			offering.setTutoringSystem(tutoringSystem);
			offeringRepository.save(offering);
		}
		return offering;
	}

	@Transactional
	public Offering getOffering(String offeringID) {
		if (offeringID == null){
			throw new IllegalArgumentException("Offering offeringID cannot be empty!");
		}
		Offering offering = offeringRepository.findOfferingByOfferingID(offeringID);
		return offering;
	}

	@Transactional
	public List<Offering> getAllOfferings() {
		return toList(offeringRepository.findAll());
	}

	@Transactional
	public void deleteOffering(String offeringID) {
		if (offeringID == null){
			throw new IllegalArgumentException("Offering offeringID cannot be empty!");
		}
		offeringRepository.deleteOfferingByOfferingID(offeringID);
	}

	/*
	 * Tutor DONE
	 */
	@Transactional
	public Tutor createTutor(String first, String last, Date dob, String email, Integer phone, Integer tutorID, Boolean isRegistered, Login loginInfo, Set<TutorApplication> tutorApplications, Set<Offering> offerings, Set<AvailableSession> availableSessions, TutoringSystem tutoringSystem) {
		String error = ""; 

		if (first == null || first.trim().length() == 0) {
			error = error + "Tutor firstname cannot be empty!";
		}
		if (last == null || last.trim().length() == 0) {
			error = error + "Tutor lastname cannot be empty!";
		}
		
		if (dob == null) {
			error = error + "Tutor DOB cannot be empty!";
		}

		if (email == null || email.trim().length() == 0) {
			error = error + "Tutor email cannot be empty!";
		}
		if (phone == null) {
			error = error + "Tutor phone cannot be empty!";
		}
		else if (phone <= 0) {
			error = error + "Tutor phone cannot be <= 0!";
		}
		if (tutorID == null || tutorID == 0) {
			error = error + "Tutor tutorID cannot be empty!";
		}
		if (loginInfo == null) {
			error = error + "Login needs to be selected for Tutor!";
		}else if (!loginRepository.existsByUserName(loginInfo.getUserName())) {
			error = error + "Login Info does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem  needs to be selected for Tutor!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		if (isRegistered == null) {
			error = error + "isRegistered cannot be empty";
		}
		//1->*
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
		//1->*
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
		//1..*->*
		if (availableSessions != null)

		{
			for (AvailableSession AvailableSession : availableSessions) {
				if (AvailableSession == null) {
					error = error + "AvailableSession needs to be selected for tutor!";
				} else if (!availableSessionRepository.existsByAvailableSessionID(AvailableSession.getAvailableSessionID())) {
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
		    tutor.setAvailableSession(availableSessions);
		    tutor.setOffering(offerings);
			tutor.setTutoringSystem(tutoringSystem);
			tutorRepository.save(tutor);
		}
		return tutor;
	}
	
	@Transactional
	public Tutor setTutorIsRegistered (Tutor tutor, Boolean isRegistered) {
		if (isRegistered == null) {
			throw new IllegalArgumentException("Tutor isRegistered cannot be empty!");
		}
		tutor.setIsRegistered(isRegistered);
		
		return tutorRepository.save(tutor);
	}
	
	@Transactional
	public TutorApplication setTutorApplicationIsAccepted (TutorApplication tutorApplication, Boolean isAccepted) {
		if (isAccepted == null) {
			throw new IllegalArgumentException(" isApproved cannot be empty!");
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
	public void deleteTutor(Integer tutorID) {
		if (tutorID == null) {
			throw new IllegalArgumentException("Tutor tutorID cannot be null!");
		}
		tutorRepository.deleteTutorByPersonId(tutorID);
	}

	/*
	 * Review DONE
	 */
	@Transactional
	public Review createReview(String comment, Boolean isApproved, Integer reviewID, Manager manager, Offering offering, TutoringSystem tutoringSystem){
		String error = "";

		if (reviewID == null) {
			error += "Review reviewID cannot be empty!";
		}
		else if (reviewID <= 0){
			error += "Review reviewID cannot be <= 0!";
		}

		if (comment == null || comment.trim().length() == 0) {
			error += "Review comment cannot be empty!";
		}
		if (isApproved == null) {
			error += "Review isApproved cannot be empty!";
		}
		if (manager == null) {
			error += "Manager needs to be selected for Review!";
		}else if (!managerRepository.existsByPersonId(manager.getPersonId())) {
			error = error + "Manager does not exist!";
		}
		if (offering == null) {
			error += "Offering needs to be selected for Review!";
		}else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
			error = error + "Offering does not exist!";
		}
		if (tutoringSystem == null) {
			error += "tutoringSystem needs to be selected for Review!";
		}else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
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
			throw new IllegalArgumentException("Review isApproved cannot be empty!");
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
	 * TutorApplication DONE   
	 */
	@Transactional
	public TutorApplication createTutorApplication(Integer applicationId, Boolean isAccepted, Tutor tutor, Set<Subject> subjects, TutoringSystem tutoringSystem) {
		String error = "";
		if (applicationId == null) {
			error += "TutorApplication applicationId cannot be empty!";
		}
		else if (applicationId <= 0) {
			error += "TutorApplication applicationId cannot be <= 0!";
		}

		if (isAccepted == null ) {
			error += "TutorApplication isAccepted cannot be null!";
		}
		if (tutor == null) {
			error += "Tutor needs to be selected for TutorApplication!";
		}else if (!tutorRepository.existsByPersonId(tutor.getPersonId())) {
			error = error + "Tutor does not exist!";
		}

		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for TutorApplication!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}

		//*->*
		if (!(subjects == null || subjects.isEmpty()))
		{
			for (Subject subject : subjects) {
				if (subject == null) {
					error = error + "Subject needs to be selected for TutorApplication!";
				} else if (!subjectRepository.existsByCourseID(subject.getCourseID())) {
					error = error + "Subject does not exist!";
				}
			}
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
	 * Available Session DONE
	 */
	public AvailableSession createAvailableSession(Time startTime, Time endTime, Integer availableSessionID, Date day, Set<Tutor> tutors, TutoringSystem tutoringSystem) {
		// Input validation
		String error = "";
		if (availableSessionID == null) {
			error = error + "AvailableSession availableSessionID cannot be empty!";
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
			error = error + "TutoringSystem needs to be selected for AvailableSession!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		//*->1..*
		if (!(tutors == null || tutors.isEmpty()))
		{
			for (Tutor tutor : tutors) {
				if (tutor == null) {
					error = error + "Tutor needs to be selected for AvailableSession!";
				} else if (!tutorRepository.existsByPersonId(tutor.getPersonId())) {
					error = error + "Tutor does not exist!";
				}
			}
		} else {
			error = error + "Tutor needs to be selected for AvailableSession!";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		AvailableSession AvailableSession = availableSessionRepository.findAvailableSessionByAvailableSessionID(availableSessionID);
		if (AvailableSession == null) {
			AvailableSession = new AvailableSession();
			AvailableSession.setAvailableSessionID(availableSessionID);
			AvailableSession.setDay(day);
			AvailableSession.setStartTime(startTime);
			AvailableSession.setEndTime(endTime);
			AvailableSession.setTutoringSystem(tutoringSystem);
			AvailableSession.setTutor(tutors);
			availableSessionRepository.save(AvailableSession);
		}
		return AvailableSession;
	}

	@Transactional
	public AvailableSession getAvailableSession(Integer availableSessionID) {
		if (availableSessionID == null) {
			throw new IllegalArgumentException("AvailableSession availableSessionID cannot be empty!");
		}
		AvailableSession AvailableSession = availableSessionRepository.findAvailableSessionByAvailableSessionID(availableSessionID);
		return AvailableSession;
	}

	@Transactional
	public List<AvailableSession> getAllAvailableSessions() {
		return toList(availableSessionRepository.findAll());
	}

	@Transactional
	public void deleteAvailableSession(Integer AvailableSessionID) {
		if (AvailableSessionID == null) {
			throw new IllegalArgumentException("AvailableSession AvailableSessionID cannot be empty!");
		}
		availableSessionRepository.deleteAvailableSessionByAvailableSessionID(AvailableSessionID);
	}

	/*
	 * Classroom DONE
	 */
	@Transactional
	public Classroom createClassroom(String roomCode, Boolean isBooked, Boolean isBigRoom, Manager manager, Set<Offering> offerings, TutoringSystem tutoringSystem) {
		String error = "";
		if (roomCode == null || roomCode.trim().length() == 0) {
			error = error + "Classroom roomCode cannot be empty!";
		}
		if (isBooked == null) {
			error = error + "Classroom isBooked cannot be empty!";
		}
		if (isBigRoom == null) {
			error = error + "Classroom isBigRoom cannot be empty!";
		}
		if (manager == null) {
			error = error + "Manager needs to be selected for Classroom!";
		} else if (!managerRepository.existsByPersonId(manager.getPersonId())) {
			error = error + "Manager does not exist!";
		}
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for Classroom!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}
		//1->*
		if (!(offerings == null || offerings.isEmpty()))
		{
			for (Offering offering : offerings) {
				if (offering == null) {
					error = error + "Offering needs to be selected for classroom!";
				} else if (!offeringRepository.existsByOfferingID(offering.getOfferingID())) {
					error = error + "Offering does not exist!";
				}
			}
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
		String error = "";
		if (offeringID == null || offeringID.trim().length() == 0) {
			error = error + "offeringID cannot be empty!";
		}
		if (managerID == null || managerID <= 0) {
			error = error + "managerID cannot be empty or <= 0!";
		}
		if (roomCode == null || roomCode.trim().length() == 0) {
			error = error + "roomCode cannot be empty!";
		}
		if (tutoringSystemID == null || tutoringSystemID <= 0) {
			error = error + "tutoringSystemID cannot be empty or <= 0!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		
		Offering offering = getOffering(offeringID);

		Boolean isBooked = true;
		Boolean isBigRoom = true;
		
		List<Classroom> classrooms = new ArrayList<Classroom>();
		classrooms = getAllClassrooms();
		Classroom thisClass = null;
		for(Classroom c : classrooms) {
			if(c.getIsBigRoom()) {
				Set <Offering> currOfferings = new HashSet<Offering>();
				currOfferings.add(offering);
				c.setOffering(currOfferings);
				c.setIsBooked(isBooked);
				thisClass = getClassroom(c.getRoomCode());
				break;
			}
		}
		
		if (thisClass == null) {
			Manager manager = getManager(managerID);
			TutoringSystem tutoringSystem = getTutoringSystem(tutoringSystemID);
			Set<Offering> thisOffering = new HashSet<Offering>();
			thisOffering.add(offering);
			thisClass = createClassroom(roomCode, isBooked, isBigRoom, manager, thisOffering, tutoringSystem);
		}
		classroomRepository.save(thisClass);
		return thisClass;
	}

	@Transactional
	public Classroom getClassroom(String roomCode) {
		if (roomCode == null){
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
		if (roomCode == null){
			throw new IllegalArgumentException("Classroom roomCode cannot be empty!");
		}
		classroomRepository.deleteClassroomByRoomCode(roomCode);
	}


	/*
	 * University DONE
	 */
	@Transactional
	public University createUniversity(String name, Set<Subject> subjects, TutoringSystem tutoringSystem) {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "University name cannot be empty!";
		}
		
		if (tutoringSystem == null) {
			error = error + "TutoringSystem needs to be selected for University!";
		} else if (!tutoringSystemRepository.existsByTutoringSystemID(tutoringSystem.getTutoringSystemID())) {
			error = error + "TutoringSystem does not exist!";
		}

		//0..1->*
		if (!(subjects == null || subjects.isEmpty()))
		{
			for (Subject subject : subjects) {
				if (subject == null) {
					error = error + "Subject needs to be selected for university!";
				} else if (!subjectRepository.existsByCourseID(subject.getCourseID())) {
					error = error + "Subject does not exist!";
				}
			}
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
		if (name == null){
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
		if (name == null){
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