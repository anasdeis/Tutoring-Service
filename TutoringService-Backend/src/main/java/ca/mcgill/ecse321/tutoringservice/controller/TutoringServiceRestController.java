package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringservice.dto.*;
import ca.mcgill.ecse321.tutoringservice.model.*;
import ca.mcgill.ecse321.tutoringservice.service.*;

@CrossOrigin(origins = "*")
@RestController
public class TutoringServiceRestController {
	@Autowired
	TutoringServiceService service;
	
	/*
	 * helper methods for converting model to dto
	 * 
	 */
	private TutorDto convertToDto(Tutor tutor) {
		if (tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
		TutorDto tutorDto = new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getDateOfBirth(), tutor.getEmail(), tutor.getPhoneNumber(), tutor.getPersonId(), tutor.getIsRegistered(), tutor.getLoginInfo(), tutor.getTutorApplication(), tutor.getOffering(),tutor.getAvaliableSession(), tutor.getTutoringSystem());
		return tutorDto;
	}
	
	private LoginDto convertToDto(Login lg) {
		if (lg == null) {
			throw new IllegalArgumentException("There is no such login information!");
		}
		LoginDto loginDto = new LoginDto(lg.getPassword(), lg.getPassword());
		return loginDto;
	}
	
	private TutoringSystemDto convertToDto(TutoringSystem tutoringSystem) {
		if (tutoringSystem == null) {
			throw new IllegalArgumentException("There is no such TutoringSystem!");
		}
		TutoringSystemDto tutoringSystemDto = new TutoringSystemDto(tutoringSystem.getTutoringSystemID());
		return tutoringSystemDto;
	}
	
	private ManagerDto convertToDto(Manager manager) {
		if (manager == null) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		ManagerDto managerDto = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getDateOfBirth(), manager.getEmail(), manager.getPhoneNumber(), manager.getPersonId(), manager.getLoginInfo(), manager.getTutoringSystem());
		return managerDto;
	}
	
	private OfferingDto convertToDto(Offering offering) {
		if (offering == null) {
			throw new IllegalArgumentException("There is no such Offering!");
		}
		OfferingDto offeringDto = new OfferingDto(offering.getOfferingID(), offering.getTerm(), offering.getPricePerHour(), offering.getClassTime(), offering.getSubject(), offering.getTutor(), offering.getCommission(), offering.getClassroom(), offering.getTutoringSystem());
		return offeringDto;
	}
	
	private ReviewDto convertToDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("There is no such Review!");
		}
		ReviewDto reviewDto = new ReviewDto(review.getComment(), review.getIsApproved(), review.getReviewID(), review.getManager(), review.getOffering(), review.getTutoringSystem());
		return reviewDto;
	}
	

	private ClassroomDto convertToDto(Classroom classroom) {
		if (classroom == null) {
			throw new IllegalArgumentException("There is no such classroom!");
		}
		ClassroomDto classroomDto = new ClassroomDto(classroom.getRoomCode(), classroom.getIsBooked(), classroom.getIsBigRoom(), classroom.getManager(), classroom.getOffering(), classroom.getTutoringSystem());
		return classroomDto;
	}
	
	private UniversityDto convertToDto(University university) {
		if (university == null) {
			throw new IllegalArgumentException("There is no such University!");
		}
		UniversityDto universityDto = new UniversityDto(university.getName(), university.getSubject(), university.getTutoringSystem());
		return universityDto;
	}
	
	/*										
	 * create methods
	 * 
	 */
	
	/*
	 * @param tutoringSystemId
	 * @return create tutoring system
	 * @sample /tutoringSystem/create/{tutoringSystemId}
	 */
	
	@PostMapping(value = { "/tutoringSystem/create/{tutoringSystemId}", "/tutoringSystem/create/{tutoringSystemId}/" })
	public TutoringSystemDto createTutoringSystem(@PathVariable("tutoringSystemId") Integer tutoringSystemId) throws IllegalArgumentException {
		// @formatter:on
		TutoringSystem tutoringSystem = service.createTutoringSystem(tutoringSystemId);
		
		return convertToDto(tutoringSystem);
	}
	
	/*
	 * @param userName
	 * @param password
	 * @return create login
	 * @sample /login/{username}?password=<password>
	 */
	@PostMapping(value = { "/login/{userName}", "/login/{userName}/"})
	public LoginDto createLogin(@PathVariable("userName") String userName, 
			@RequestParam("password") String password) throws IllegalArgumentException {
		Login loginInfo = service.createLogin(userName, password);
		return convertToDto(loginInfo);
	}
	
	/*
	 * @param tutorId
	 * @param first
	 * @param last
	 * @param dob
	 * @param email 
	 * @param phone
	 * @param isRegistered
	 * @param username 
	 * @param tutoringSystemID
	 * @param applicationIds (optional)
	 * @param offeringIDs (optional)
	 * @param avaliableSessionIDs (optional)
	 * @return create tutor
	 * @sample /tutor/create/5?firstName=anas&lastName=deis&dob=1996-03-19&email=anas.deis@mail.mcgill.ca&phone=911&isRegistered=true&username=adeis&tutoringSystemId=1
	 */
	
	@PostMapping(value = { "/tutor/create/{tuorId}", "/tutor/create/{tutorId}/" })
	public TutorDto createTutor(@PathVariable("tutorId") Integer tutorId, 
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName, 
			@RequestParam("dob") Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("isRegistered") Boolean isRegistered, 
			@RequestParam("username") String username,
			@RequestParam("tutoringSystemId") Integer tutoringSystemId,
			@RequestParam(name = "applicationIds", required = false) Set<Integer> applicationIds,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs,
			@RequestParam(name = "avaliableSessionIDs", required = false) Set<Integer> avaliableSessionIDs) throws IllegalArgumentException {
		// @formatter:on
		
		Login login = service.getLogin(username);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemId);
		
		Set<TutorApplication> tutorApplications = null;
		if(applicationIds != null) {
			tutorApplications = new HashSet<TutorApplication>();
			for (Integer applicationId : applicationIds) {
				TutorApplication tutorApplication = service.getTutorApplication(applicationId);
				tutorApplications.add(tutorApplication);
			}
		}
		
		Set<Offering> offerings = null;
		if(offeringIDs != null){
			offerings = new HashSet<Offering>();
			for (String offeringID : offeringIDs) {
				Offering offering = service.getOffering(offeringID);
				offerings.add(offering);
			}
		}
		
		Set<AvaliableSession> avaliableSessions = null;
		if(avaliableSessionIDs != null){
			avaliableSessions = new HashSet<AvaliableSession>();
			for (Integer avaliableSessionID : avaliableSessionIDs) {
				AvaliableSession avaliableSession = service.getAvaliableSession(avaliableSessionID);
				avaliableSessions.add(avaliableSession);
			}
		}
		
		Tutor tutor = service.createTutor(firstName, lastName, dob, email, phone, tutorId, isRegistered, login, tutorApplications, offerings, avaliableSessions, tutoringSystem);

		return convertToDto(tutor);
	}
	

	/*
	 * @param offeringID
	 * @param term
	 * @param price
	 * @param classTimes
	 * @param courseID 
	 * @param tutorID
	 * @param roomCode
	 * @param tutoringSystemID
	 * @return create offering
	 * @sample /offering/create/{offeringID}?term=<term>&price=<price>&classTime=<classTime1,classTime2,..>&courseID=<courseID>&tutorID=<tutorID>&commissionID=<commissionID>&roomCode=<roomCode>&tutoringSystemId=<tutoringSystemId>
	 */
	
	@PostMapping(value = { "/offering/create/{offeringID}", "/offering/create/{offeringID}/" })
	public OfferingDto createOffering(@PathVariable("offeringID") String offeringID,
			@RequestParam("term") String term,
			@RequestParam("price") double price,
			@RequestParam("classTimes") Set<Integer> classTimes,
			@RequestParam("courseID") String courseID,
			@RequestParam("tutorID") Integer tutorID,
			@RequestParam("commissionID") Integer commissionID,
			@RequestParam("roomCode") String roomCode,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		// @formatter:on
		
		Set<AvaliableSession> avaliableSessions = null;
		if(classTimes != null){
			avaliableSessions = new HashSet<AvaliableSession>();
			for (Integer avaliableSessionID : classTimes) {
				AvaliableSession avaliableSession = service.getAvaliableSession(avaliableSessionID);
				avaliableSessions.add(avaliableSession);
			}
		}
		Subject subject = service.getSubject(courseID);
		Tutor tutor = service.getTutor(tutorID);
		Commission commission = service.getCommission(commissionID);
		Classroom classroom = service.getClassroom(roomCode);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		Offering offering = service.createOffering(offeringID, term, price, avaliableSessions, subject, tutor, commission, classroom, tutoringSystem);
		
		return convertToDto(offering);
	}
	
	/*
	 * @param managerId
	 * @param first
	 * @param last
	 * @param dob
	 * @param email 
	 * @param phone
	 * @param username 
	 * @param tutoringSystemID
	 * @return create manager
	 * @sample /manager/create/{managerId}?first=<first>&last=<last>&dob=<dob>&email=<email>&phone=<phone>&username=<username>&tutoringSystemId=<tutoringSystemID>
	 * 
	 */
	
	@PostMapping(value = { "/manager/create/{managerId}","/manager/create/{managerId}/" })
	public ManagerDto createManager(@PathVariable("managerId") Integer managerId, 
			@RequestParam("first") String first, 
			@RequestParam("last") String last, 
			@RequestParam ("dob") Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("username") String username, 
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		
		
		Login login = service.getLogin(username);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);

		Manager manager = service.createManager(first, last, dob, email, phone, managerId, login, tutoringSystem);

		return convertToDto(manager);

	}
	
	/*
	 * @param reviewID
	 * @param comment
	 * @param isApproved
	 * @param managerID
	 * @param managerID 
	 * @param offeringID
	 * @return create review
	 * @sample /tutor/create/{reviewID}?comment=<comment>&isApproved=<isApproved>&managerID=<managerID>&offering=<offering>&tutoringSystemId=<tutoringSystemId>
	 */

	@PostMapping(value = { "/review/create/{reviewID}", "/review/create/{reviewID}/" })
	public ReviewDto createReview(@PathVariable("reviewID") Integer reviewID,
			@RequestParam("comment") String comment,
			@RequestParam("isApproved") Boolean isApproved,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("offeringID") String offeringID,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		// @formatter:on
		Manager manager = service.getManager(managerID);
		Offering offering = service.getOffering(offeringID);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		Review review = service.createReview(comment, isApproved, reviewID, manager, offering, tutoringSystem);
		
		return convertToDto(review);
	}
	

	/* 
	 * @return create classroom
	 * @param roomcode
	 * @param isBooked
	 * @param isBigRoom
	 * @param managerID
	 * @param offeringIds (optional)
	 * @param tutoringSystem
	 * @sample /classroom/create/{roomCode}?isBooked=<isBooked>&isBigRm=<isBigRm>&managerID=<managerID>&offeringIDs=<offeringIDs>&tutoringSystemID=<tutoringSystemID>
	 */

	@PostMapping(value = { "/classroom/create/{roomCode}", "/classroom/create/{roomCode}/" })
	public ClassroomDto createClassroom(@PathVariable("roomCode") String roomCode,
			@RequestParam("isBooked") Boolean isBooked,
			@PathVariable("isBigRm") Boolean isBigRm,
			@RequestParam("managerID") Integer managerID,
			@RequestParam(name = "offeringID", required = false) Set<String> offeringIDs,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		// @formatter:on
		
		Set<Offering> offerings = null;
		if(offeringIDs != null){
			offerings = new HashSet<Offering>();
			for (String offeringID : offeringIDs) {
				Offering offering = service.getOffering(offeringID);
				offerings.add(offering);
			}
		}
		
		Manager manager = service.getManager(managerID);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		Classroom classroom = service.createClassroom(roomCode, isBooked, isBigRm, manager, offerings, tutoringSystem);
		
		return convertToDto(classroom);
	}
	
	/* 
	 * @return create university
	 * @param name
	 * @param subjects (optional)
	 * @param tutoringSystem
	 * @sample /classroom/create/{name}?subjects=<subjects>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = { "/university/create/{name}", "/university/create/{name}/" })
	public UniversityDto createUniversity(@PathVariable("name") String name,
			@RequestParam(name = "subjects", required = false) Set<String> courseIDs,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		// @formatter:on
		
		Set<Subject> subjects = null;
		if(courseIDs != null){
			subjects = new HashSet<Subject>();
			for (String courseID : courseIDs) {
				Subject subject = service.getSubject(courseID);
				subjects.add(subject);
			}
		}
		
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		University university = service.createUniversity(name, subjects, tutoringSystem);
		
		return convertToDto(university);
	}
	
	/*
	 * list methods
	 * 
	 */
	
	/*
	 * @return list all university
	 * @sample /university/list
	 */
	
	@GetMapping(value = { "/university/list", "/university/list/" })
	public List<UniversityDto> getAllUniversitys() {
		List<UniversityDto> universityDtos = new ArrayList<>();
		for (University university : service.getAllUniversitys()) {
				universityDtos.add(convertToDto(university));
		}
		return universityDtos;
	}
	
	/*
	 * @return list all managers
	 * @sample /manager/list
	 */
	
	@GetMapping(value = { "/manager/list", "/manager/list/" })
	public List<ManagerDto> getAllManagers() {
		List<ManagerDto> managerDtos = new ArrayList<>();
		for (Manager manager : service.getAllManagers()) {
				managerDtos.add(convertToDto(manager));
		}
		return managerDtos;
	}
	
	/*
	 * @return list all reviews
	 * @sample /review/list
	 */
	
	@GetMapping(value = { "/review/list", "/review/list/" })
	public List<ReviewDto> getAllReviews() {
		List<ReviewDto> reviewDtos = new ArrayList<>();
		for (Review review : service.getAllReviews()) {
				reviewDtos.add(convertToDto(review));
		}
		return reviewDtos;
	}
	
	/*
	 * @return list all tutoringSystems
	 * @sample /tutoringSystem/list
	 */
	
	@GetMapping(value = { "/tutoringSystem/list", "/tutoringSystem/list/" })
	public List<TutoringSystemDto> getAllTutoringSystems() {
		List<TutoringSystemDto> tutoringSystemsDtos = new ArrayList<>();
		for (TutoringSystem tutoringSystem : service.getAllTutoringSystems()) {
			tutoringSystemsDtos.add(convertToDto(tutoringSystem));
		}
		return tutoringSystemsDtos;
	}
	
	/*
	 * @return list all logins
	 * @sample /login/list
	 */
	
	@GetMapping(value = { "/login/list", "/login/list/" })
	public List<LoginDto> getAllLoginSystems() {
		List<LoginDto> loginsDtos = new ArrayList<>();
		for (Login login : service.getAllLogins()) {
			loginsDtos.add(convertToDto(login));
		}
		return loginsDtos;
	}
	
	/*
	 * @return a list of all Tutors
	 * @sample /tutor/list/
	 */
	@GetMapping(value = { "/tutor/list", "/tutor/list/" })
	public List<TutorDto> getAllTutors() {
		List<TutorDto> tutorDtos = new ArrayList<>();
		for (Tutor tutor : service.getAllTutors()) {
				tutorDtos.add(convertToDto(tutor));
		}
		return tutorDtos;
	}

	/*
	 * @return list offering
	 * @sample /offering/list
	 */
	
	@GetMapping(value = { "/offering/list", "/offering/list/" })
	public List<OfferingDto> getAllOfferings() {
		List<OfferingDto> offeringDtos = new ArrayList<>();
		for (Offering offering : service.getAllOfferings()) {
				offeringDtos.add(convertToDto(offering));
		}
		return offeringDtos;
	}
	
	/*
	 * 	Use Cases
	 * 
	 */
	
	/*
	 * @return a list of Registered/Non-Registered Tutors
	 * @sample /tutor/list/<isRegistered>
	 * 
	 */
	
	@GetMapping(value = { "/tutor/list/{isRegistered}", "/tutor/list/{isRegistered}/" })
	public List<TutorDto> getAllRegisteredTutors(@PathVariable("isRegistered") Boolean isRegistered) {
		List<TutorDto> tutorDtos = new ArrayList<>();
		for (Tutor tutor : service.getAllTutors()) {
			if(tutor.getIsRegistered().booleanValue() == isRegistered)
				tutorDtos.add(convertToDto(tutor));
		}
		return tutorDtos;
	}
	
	/*
	 * @return Fire tutor
	 * @sample /tutor/delete/<personId>
	 * 
	 */
	
	@DeleteMapping(value = {"/tutor/delete/{personId}", "/tutor/delete/{personId}/"})
    public TutorDto deleteTutor(@PathVariable("personId") Integer personId) {
		TutorDto tutorDto = convertToDto(service.getTutor(personId));
		service.deleteTutor(personId);
        return tutorDto;
    }
	
	/*
	 * @return update tutor as isRegistered
	 * @sample /tutor/list/<isRegistered>
	 * 
	 */
	
	@PatchMapping(value = { "/tutor/update/registered/{personId}", "/tutor/update/registered/{personId}/" })
	public TutorDto updateTutorIsRegistered(@PathVariable("personId") Integer personId, @RequestParam("isRegistered") Boolean isRegistered) {
		Tutor tutor = (service.setTutorIsRegistered(service.getTutor(personId), isRegistered));
		TutorDto tutorDto = convertToDto(tutor);
		
		return tutorDto;
	}
	
	/*
	 * @return get a list of approved/non-approved reviews
	 * @sample /review/list/<isApproved>
	 * 
	 */
	
	@GetMapping(value = { "/review/list/{isApproved}", "/review/list/{isApproved}/" })
	public List<ReviewDto> getAllReviews(@PathVariable("isApproved") Boolean isApproved) {
		List<ReviewDto> reviewDtos = new ArrayList<>();
		for (Review review : service.getAllReviews()) {
			if(review.getIsApproved().booleanValue() == isApproved)
				reviewDtos.add(convertToDto(review));
		}
		return reviewDtos;
	}
	
	
	/*
	 * @return monitor reviews and set approved or non-approved
	 * @sample /review/update/approved/<reviewID>?isApproved=<isApproved>
	 * 
	 */
	
	@PatchMapping(value = { "/review/update/approved/{reviewID}", "/review/update/approved/{reviewID}/" })
	public ReviewDto updateReviewIsApproved(@PathVariable("reviewID") Integer reviewID, @RequestParam("isApproved") Boolean isApproved){
		Review review = (service.setReviewIsApproved(service.getReview(reviewID), isApproved));
		ReviewDto reviewDto = convertToDto(review);
		
		return reviewDto;
	}
}

