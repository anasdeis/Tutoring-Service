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
	public Offering createOffering(String offId, String term, double price, Subject subj){
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

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}