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
		TutorDto tutorDto = new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getDateOfBirth(), tutor.getEmail(), tutor.getPhoneNumber(), tutor.getPersonId(), tutor.getIsRegistered(), tutor.getLoginInfo(), tutor.getTutoringSystem());
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
		OfferingDto offeringDto = new OfferingDto(offering.getOfferingID(), offering.getTerm(), offering.getPricePerHour(), offering.getClassTime(), offering.getSubject(), offering.getTutoringSystem());
		return offeringDto;
	}
	
	private ReviewDto convertToDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("There is no such Review!");
		}
		ReviewDto reviewDto = new ReviewDto(review.getComment(), review.getIsApproved(), review.getReviewID(), review.getManager(), review.getOffering(), review.getTutoringSystem());
		return reviewDto;
	}
	
	/*										
	 * create methods
	 * 
	 */
	
	/*
	 * @return create tutoring system
	 * @sample /tutoringSystem/create/10
	 */
	
	@PostMapping(value = { "/tutoringSystem/create/{tutoringSystemId}", "/tutoringSystem/create/{tutoringSystemId}/" })
	public TutoringSystemDto createTutoringSystem(@PathVariable("tutoringSystemId") Integer tutoringSystemId) throws IllegalArgumentException {
		// @formatter:on
		TutoringSystem tutoringSystem = service.createTutoringSystem(tutoringSystemId);
		
		return convertToDto(tutoringSystem);
	}
	
	/*
	 * @return create login
	 * @sample /login/<username>?password=<password>
	 */
	@PostMapping(value = { "/login/{userName}", "/login/{userName}/"})
	public LoginDto createLogin(@PathVariable("userName") String userName, 
			@RequestParam("password") String password) throws IllegalArgumentException {
		Login loginInfo = service.createLogin(userName, password);
		return convertToDto(loginInfo);
	}
	
	/*
	 * @return create tutor
	 * @sample /tutor/create/5?firstName=anas&lastName=deis&dob=1996-03-19&email=anas.deis@mail.mcgill.ca&phone=911&isRegistered=true&username=adeis&tutoringSystemId=1
	 */
	
	@PostMapping(value = { "/tutor/create/{personId}", "/tutor/create/{personId}/" })
	public TutorDto createTutor(@PathVariable("personId") Integer personId, 
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
		
		Tutor tutor = service.createTutor(firstName, lastName, dob, email, phone, personId, isRegistered, login, tutorApplications, offerings, avaliableSessions, tutoringSystem);

		return convertToDto(tutor);
	}
	
	/*
	 * @return create review
	 * @sample /tutor/create/<reviewID>?comment=<comment>&isApproved=<isApproved>&managerID=<managerID>&offering=<offering>=tutoringSystemId=<tutoringSystemId>
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
	 * list methods
	 * 
	 */
	
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

	/** Add Subject
	 * 
	 * @param name
	 * @param courseID
	 * @param description
	 * @param subjectType: University/HighSchool/CGEP
	 * @param university
	 * @param tutoringSystemID
	 * @return subject added
	 * @throws IllegalArgumentException
	 * 
	 * sample: /subject/create/{name}?courseID=<courseID>&description=<description>&subjectType=<subjectType>&university=<universityName>&tutoringSystemI=<tutoringSystemId>
	 */
	@PostMapping(value = { "/subject/create/{name}", "/subject/create/{name}/" })
	public SubjectDto createSubject(@PathVariable("name") String name, 
			@RequestParam("courseID") String courseID, 
			@RequestParam("description") String description, 
			@RequestParam("subjectType") String subjectType,
			@RequestParam("university") String university,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		SubjectType sbType = null;
		if (subjectType.equals("University")) {
			sbType = SubjectType.UNIVERSITY_COURSE;
		}else if(subjectType.equals("HighSchool")) {
			sbType = SubjectType.HIGH_SCHOOL_COURSE;
		} else if(subjectType.equals("CPEG")) {
			sbType = SubjectType.CGEP_COURSE;
		}
		
		University uni = service.getUniversity(university);
		Subject subject = service.createSubject(name, courseID, description, sbType, uni, tutoringSystem);
		return convertToDto(subject);
	}

	private SubjectDto convertToDto(Subject sb) {
		if (sb == null) {
			throw new IllegalArgumentException("There is no such subject information!");
		}

		SubjectDto subjectDto = new SubjectDto(sb.getName(), sb.getCourseID(), sb.getDescription(), sb.getSubjectType(), sb.getUniversity());

		return subjectDto;
	}

}

