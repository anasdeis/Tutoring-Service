package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	 */
	private TutorDto convertToDto(Tutor tutor) {
		if (tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
		
		Set<String> offeringIDs = null;
		if(tutor.getOffering() != null)	{
			offeringIDs = new HashSet<String>();
			for(Offering offering : tutor.getOffering()){
				String offeringID = offering.getOfferingID();
				offeringIDs.add(offeringID);
			}
		}
		
		Set<Integer> tutorApplicationsIDs = null;
		if(tutor.getTutorApplication() != null)	{
			tutorApplicationsIDs = new HashSet<Integer>();
			for(TutorApplication tutorApplication : tutor.getTutorApplication()){
				Integer tutorApplicationsID = tutorApplication.getApplicationId();
				tutorApplicationsIDs.add(tutorApplicationsID);
			}
		}
		
		Set<Integer> avaliableSessionIDs = null;
		if(tutor.getTutorApplication() != null)	{
			avaliableSessionIDs = new HashSet<Integer>();
			for(AvaliableSession availableSession : tutor.getAvaliableSession()){
				Integer avaliableSessionID = availableSession.getAvaliableSessionID();
				avaliableSessionIDs.add(avaliableSessionID);
			}
		}
		
		TutorDto tutorDto = new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getDateOfBirth(), tutor.getEmail(), tutor.getPhoneNumber(), tutor.getPersonId(), tutor.getIsRegistered(), convertToDto(tutor.getLoginInfo()), tutorApplicationsIDs, offeringIDs, avaliableSessionIDs, convertToDto(tutor.getTutoringSystem()));
		return tutorDto;
	}

	private LoginDto convertToDto(Login lg) {
		if (lg == null) {
			throw new IllegalArgumentException("There is no such login information!");
		}
		LoginDto loginDto = new LoginDto(lg.getUserName(), lg.getPassword());
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
		ManagerDto managerDto = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getDateOfBirth(), manager.getEmail(), manager.getPhoneNumber(), manager.getPersonId(), convertToDto(manager.getLoginInfo()), convertToDto(manager.getTutoringSystem()));
		return managerDto;
	}

	private OfferingDto convertToDto(Offering offering) {
		if (offering == null) {
			throw new IllegalArgumentException("There is no such Offering!");
		}
		
		Set<Integer> avaliableSessionsIDs = null;
		if(offering.getClassTime() != null)	{
			avaliableSessionsIDs = new HashSet<Integer>();
			for(AvaliableSession avaliableSession : offering.getClassTime()){
				Integer avaliableSessionsID = avaliableSession.getAvaliableSessionID();
				avaliableSessionsIDs.add(avaliableSessionsID);
			}
		}
		
		OfferingDto offeringDto = new OfferingDto(offering.getOfferingID(), offering.getTerm(), offering.getPricePerHour(), avaliableSessionsIDs, convertToDto(offering.getSubject()), convertToDto(offering.getTutor()), convertToDto(offering.getCommission()), convertToDto(offering.getClassroom()), convertToDto(offering.getTutoringSystem()));
		return offeringDto;
	}

	private StudentDto convertToDto(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		StudentDto studentDto = new StudentDto(student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getPersonId(), student.getNumCoursesEnrolled(), convertToDto(student.getLoginInfo()), convertToDto(student.getTutoringSystem()));
		return studentDto; 
	}
	
	private ReviewDto convertToDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("There is no such Review!");
		}
		ReviewDto reviewDto = new ReviewDto(review.getComment(), review.getIsApproved(), review.getReviewID(), convertToDto(review.getManager()), convertToDto(review.getOffering()), convertToDto(review.getTutoringSystem()));
		return reviewDto;
	}
	
	private CommissionDto convertToDto(Commission commission) {
		if (commission == null) {
			throw new IllegalArgumentException("There is no such commission!");
		}
		
		Set<String> offeringIDs = null;
		if(commission.getOffering() != null)	{
			offeringIDs = new HashSet<String>();
			for(Offering offering : commission.getOffering()){
				String offeringID = offering.getOfferingID();
				offeringIDs.add(offeringID);
			}
		}
		
		CommissionDto commissiondto = new CommissionDto(commission.getPercentage(), commission.getCommissionID(), convertToDto(commission.getManager()), offeringIDs, convertToDto(commission.getTutoringSystem()));
		return commissiondto;
	}

	private ClassroomDto convertToDto(Classroom classroom) {
		if (classroom == null) {
			throw new IllegalArgumentException("There is no such classroom!");
		}
		
		Set<String> offeringIDs = null;
		if(classroom.getOffering() != null)	{
			offeringIDs = new HashSet<String>();
			for(Offering offering : classroom.getOffering()){
				String offeringID = offering.getOfferingID();
				offeringIDs.add(offeringID);
			}
		}
		
		ClassroomDto classroomDto = new ClassroomDto(classroom.getRoomCode(), classroom.getIsBooked(), classroom.getIsBigRoom(), convertToDto(classroom.getManager()), offeringIDs, convertToDto(classroom.getTutoringSystem()));
		return classroomDto;
	}
	
	private TutorApplicationDto convertToDto(TutorApplication tutorApplication) {
		if (tutorApplication == null) {
			throw new IllegalArgumentException("There is no such tutor Application!");
		}
		TutorApplicationDto tutorApplicationDto = new TutorApplicationDto(tutorApplication.getApplicationId(), tutorApplication.getIsAccepted(), convertToDto(tutorApplication.getTutor()), convertToDto(tutorApplication.getTutoringSystem()));
		return tutorApplicationDto;
	}
	
	private SubjectDto convertToDto(Subject sb) {
		if (sb == null) {
			throw new IllegalArgumentException("There is no such subject information!");
		}

		SubjectDto subjectDto = new SubjectDto(sb.getName(), sb.getCourseID(), sb.getDescription(), convertSubjectTypeToString(sb.getSubjectType()), convertToDto(sb.getUniversity()));
		return subjectDto;
	}
	
	private SubjectRequestDto convertToDto(SubjectRequest sr) {
		if (sr == null) {
			throw new IllegalArgumentException("There is no such subject information!");
		}

		SubjectRequestDto subjectRequestDto = new SubjectRequestDto(sr.getRequestID(), sr.getName(), sr.getDescription(), convertToDto(sr.getManager()), convertToDto(sr.getTutoringSystem()));
		return subjectRequestDto;
	}
	
	private UniversityDto convertToDto(University university) {
		if (university == null) {
			throw new IllegalArgumentException("There is no such University!");
		}
	
		Set<String> subjectsCourseIDs = null;
		if(university.getSubject() != null)	{
			subjectsCourseIDs = new HashSet<String>();
			for(Subject subject : university.getSubject()){
				String subjectsCourseID = subject.getCourseID();
				subjectsCourseIDs.add(subjectsCourseID);
			}
		}
		UniversityDto universityDto = new UniversityDto(university.getName(), subjectsCourseIDs, convertToDto(university.getTutoringSystem()));
		return universityDto;
		
		
	}
	

	private AvaliableSessionDto convertToDto(AvaliableSession availableSession) {
		if (availableSession == null) {
			throw new IllegalArgumentException("There is no such availableSession!");
		}
		
		Set<Integer> tutorIDs = null;
		if(availableSession.getTutor() != null)	{
			tutorIDs = new HashSet<Integer>();
			for(Tutor tutor : availableSession.getTutor()){
				Integer tutorID = tutor.getPersonId();
				tutorIDs.add(tutorID);
			}
		}

		AvaliableSessionDto avaliableSessionDto = new AvaliableSessionDto(availableSession.getStartTime(), availableSession.getEndTime(), availableSession.getAvaliableSessionID(), availableSession.getDay(),tutorIDs, convertToDto(availableSession.getTutoringSystem()));
		return avaliableSessionDto;
	}
	
	/*
	 * 
	 * Convert Subject Type to String
	 */
	
	private String convertSubjectTypeToString(SubjectType subjectType) {
		String sbType = null;
		if (SubjectType.UNIVERSITY_COURSE.equals(subjectType)) {
			sbType = "University";
		}else if(SubjectType.HIGH_SCHOOL_COURSE.equals(subjectType)) {
			sbType = "HighSchool";
		} else if(SubjectType.CGEP_COURSE.equals(subjectType)) {
			sbType = "CGEP";
		}
		return sbType;
	}

	
	/*										
	 * create methods
	 */
	
	/**
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
	
	/**
	 * @param availableSessionID
	 * @param startTime
	 * @param endTime
	 * @param day
	 * @param tutors (optional)
	 * @param tutoringSystem
	 * @return create availableSession
	 * @sample /availableSession/create/{availableSessionID}?startTime=<startTime>&endTime=<endTime>&day=<day>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = {"/availableSession/create/{availableSessionID}", "/availableSession/create/{availableSessionID}/"})
	public AvaliableSessionDto createAvaliableSession(@PathVariable("availableSessionID") Integer availableSessionID, 
			@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, 
			@RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
			@RequestParam("day") Date day, 
			@RequestParam(name = "tutorIDs", required = false) Set<Integer> tutorIDs,
			@RequestParam("tutoringSystemID") TutoringSystemDto tutoringSystemDto) throws IllegalArgumentException {
		
		Set<Tutor> tutors = null;
		if(tutorIDs != null){
			tutors = new HashSet<Tutor>();
			for (Integer tutorID : tutorIDs) {
				Tutor tutor = service.getTutor(tutorID);
				tutors.add(tutor);
			}
		}

		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		AvaliableSession avaliableSession = service.createAvaliableSession(Time.valueOf(startTime), Time.valueOf(endTime), availableSessionID, day, tutors, tutoringSystem);
		
		return convertToDto(avaliableSession);
	}
	

	/**
	 * @param commissionID
	 * @param percentage
	 * @param managerID
	 * @param offeringID
	 * @param tutoringSystem
	 * @return create commission
	 * @sample /commission/create/{commissionID}?percentage=<percentage>&managerID=<managerID>&offeringIDs=<offeringIDs>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = {"/commission/create/{commissionID}", "/commission/create/{commissionID}/"})
	public CommissionDto createCommission(@PathVariable("commissionID") Integer commissionID, 
			@RequestParam("percentage") double percentage, 
			@RequestParam("managerID") Integer managerID, 
			@RequestParam(name = "offeringID", required = false) Set<String> offeringIDs,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		
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
		Commission commission = service.createCommission(percentage, commissionID, manager, offerings, tutoringSystem);
		
		return convertToDto(commission);
	}
	
	/**
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
	
	/**
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
	@PostMapping(value = { "/tutor/create/{tutorId}", "/tutor/create/{tutorId}/" })
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


	/**
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

	/**
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
	
	/**
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


	/** 
	 * @return create classroom
	 * @param roomcode
	 * @param isBooked
	 * @param isBigRoom
	 * @param managerID
	 * @param offeringIds (optional)
	 * @param tutoringSystem
	 * @sample /classroom/create/{roomCode}?isBooked=<isBooked>&isBigRoom=<isBigRm>&managerID=<managerID>&offeringIDs=<offeringIDs>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = { "/classroom/create/{roomCode}", "/classroom/create/{roomCode}/" })
	public ClassroomDto createClassroom(@PathVariable("roomCode") String roomCode,
			@RequestParam("isBooked") Boolean isBooked,
			@RequestParam("isBigRoom") Boolean isBigRoom,
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
		Classroom classroom = service.createClassroom(roomCode, isBooked, isBigRoom, manager, offerings, tutoringSystem);

		return convertToDto(classroom);
	}
	
	/** 
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

	/**
	 * @param tutorApplicationId
	 * @param isAccepted
	 * @param tutorID
	 * @param tutorSystemID
	 * @return create Tutor Application
	 * @sample /tutorApplication/create/{tutorApplicationId}?IsAccepted=<isAccepted>&&tutorID=<tutorID>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = { "/tutorApplication/create/{tutorApplicationId}","/tutorApplication/create/{tutorApplication}/" })
	public TutorApplicationDto createTutorApplication(@PathVariable("tutorApplicationId") Integer tutorApplicationId, 
			@RequestParam("isAccepted") Boolean isAccepted, 
			@RequestParam("tutorID") Integer tutorID, 
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		
		Tutor tutor = service.getTutor(tutorID);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);

		TutorApplication tutorApplication = service.createTutorApplication(tutorApplicationId, isAccepted, tutor,  tutoringSystem);

		return convertToDto(tutorApplication);

	}

	/**
	 * Student: create student with parameters
	 * @param first
	 * @param last
	 * @param dob
	 * @param email
	 * @param phone
	 * @param studentID
	 * @param login
	 * @param tutoringSystem
 	 * @sample  /student/create/{personId}?firstName=<firstName>&lastName=<lastName>&dateOfBirth=<dateOfBirth>&email=<email>&phoneNumber=<phoneNumber>&tutoringSystem=<tutoringSystem>&numCoursesEnrolled=<numCoursesEnrolled>
	 */
	@PostMapping(value = {"/student/create/{studnetID}", "/student/create/{studnetID}/"})
	public StudentDto createStudent(@PathVariable("studentID") Integer studentID,
			@RequestParam("first") String first,
			@RequestParam("last") String last,
			@RequestParam("dob") Date dob,
			@RequestParam("email") String email,
			@RequestParam("phone") Integer phone,
			@RequestParam("numCoursesEnrolled") Integer numCoursesEnrolled,
			@RequestParam("username") String username,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {

		Login login = service.getLogin(username);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		
		Student student = service.createStudent(first, last, dob, email, phone, studentID, numCoursesEnrolled, login, tutoringSystem);

		return convertToDto(student);

	}
	
	/** Add Subject
	 * @param name
	 * @param courseID
	 * @param description
	 * @param subjectType: University/HighSchool/CGEP
	 * @param university
	 * @param tutoringSystemID
	 * @return subject added
	 * @throws IllegalArgumentException
	 * sample: /subject/create/{name}?courseID=<courseID>&description=<description>&subjectType=<subjectType>&university=<universityName>&tutoringSystemID=<tutoringSystemID>
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
		} else if(subjectType.equals("CGEP")) {
			sbType = SubjectType.CGEP_COURSE;
		}
		
		University uni = service.getUniversity(university);
		Subject subject = service.createSubject(name, courseID, description, sbType, uni, tutoringSystem);
		return convertToDto(subject);
	}	
	
	
	/**
	/*
	 * list methods
	 */
	
	/**
	 * @return list all avaliableSessions
	 * @sample /avaliableSession/list
	 */
	@GetMapping(value = { "/avaliableSession/list", "/avaliableSession/list/" })
	public List<AvaliableSessionDto> getAllAvaliableSessions() {
		List<AvaliableSessionDto> avaliableSessionsDtos = new ArrayList<>();
		for (AvaliableSession avaliableSession : service.getAllAvaliableSessions()) {
			avaliableSessionsDtos.add(convertToDto(avaliableSession));
		}
		return avaliableSessionsDtos;
	}
	
	/**
	 * @return list all subjects
	 * @sample /subject/list
	 */
	@GetMapping(value = { "/subject/list", "/subject/list/" })
	public List<SubjectDto> getAllSubjects() {
		List<SubjectDto> subjectsDtos = new ArrayList<>();
		for (Subject subject : service.getAllSubjects()) {
			subjectsDtos.add(convertToDto(subject));
		}
		return subjectsDtos;
	}

	/**
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
	
	/**
	 * @return list all students
	 * @sample /student/list
	 */
	@GetMapping(value = { "/student/list", "/student/list/" })
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student: service.getAllStudents()) {
			studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}
	
	/**s
	 * @return list all commissions
	 * @sample /commission/list
	 */
	@GetMapping(value = { "/commission/list", "/commission/list/" })
	public List<CommissionDto> getAllCommissions() {
		List<CommissionDto> commissionDtos = new ArrayList<>();
		for (Commission commission: service.getAllCommissions()) {
			commissionDtos.add(convertToDto(commission));
		}
		return commissionDtos;
	}
	
	/**
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
	
	/**
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
	
	/**
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
	
	/**
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
	
	/**
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

	/**
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
	
	/**
	 * @return a list of classrooms
	 * @sample /classroom/list
	 * not makes senses here
	 */
	@GetMapping(value = {"/classroom/list", "/classroom/list/"}) 
	public List<ClassroomDto> getAllRoomSchedules() {
		List<ClassroomDto> classroomDtos = new ArrayList<>();
		for (Classroom classroom : service.getAllClassrooms()) {
			classroomDtos.add(convertToDto(classroom));
		}
		return classroomDtos;
	}

	
	/**
	/*
	 * 	Use Cases
	 */

	/**
	 * @return a list of Registered/Non-Registered Tutors
	 * @sample /tutor/list/<isRegistered>
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
	
	/**
	 * @return a list of Tutor Applications
	 * @sample /tutorApplication/list
	 * 
	 */
	@GetMapping(value = { "/tutorApplication/list", "/tutorApplication/list/" })
	public List<TutorApplicationDto> getAllTutorApplications() {
		List<TutorApplicationDto> tutorApplicationDtos = new ArrayList<>();
		for (TutorApplication tutorApplication : service.getAllTutorApplications()) {
			tutorApplicationDtos.add(convertToDto(tutorApplication));
		}
		return tutorApplicationDtos;
	}
	
	/**
	 * @return Fire tutor
	 * @sample /tutor/delete/<personId>
	 */
	@DeleteMapping(value = {"/tutor/delete/{tutorID}", "/tutor/delete/{tutorID}/"})
	public TutorDto deleteTutor(@PathVariable("tutorID") Integer tutorID) {
		TutorDto tutorDto = convertToDto(service.getTutor(tutorID));
		service.deleteTutor(tutorID);
		return tutorDto;
	}

	/**
	 * @return update tutor as isRegistered
	 * @sample /tutor/list/<isRegistered>
	 */
	@PatchMapping(value = { "/tutor/update/registered/{tutorID}", "/tutor/update/registered/{tutorID}/" })
	public TutorDto updateTutorIsRegistered(@PathVariable("tutorID") Integer tutorID, @RequestParam("isRegistered") Boolean isRegistered) {
		Tutor tutor = (service.setTutorIsRegistered(service.getTutor(tutorID), isRegistered));
		TutorDto tutorDto = convertToDto(tutor);

		return tutorDto;
	}
	
	/**
	 * @return get a list of approved/non-approved reviews
	 * @sample /review/list/<isApproved>
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
	
	
	/**
	 * @return monitor reviews and set approved or non-approved
	 * @sample /review/update/approved/<reviewID>?isApproved=<isApproved>
	 */
	@PatchMapping(value = { "/review/update/approved/{reviewID}", "/review/update/approved/{reviewID}/" })
	public ReviewDto updateReviewIsApproved(@PathVariable("reviewID") Integer reviewID, @RequestParam("isApproved") Boolean isApproved){
		Review review = (service.setReviewIsApproved(service.getReview(reviewID), isApproved));
		ReviewDto reviewDto = convertToDto(review);

		return reviewDto;
	}

	/**
	 * Remove a student
	 * @sample /student/delete/<personID>
	 */
	@RequestMapping(value = {"/student/delete/{studentID}", "/student/delete/{studentID}/"}, method = RequestMethod.DELETE)
	public StudentDto deleteStudent(@PathVariable("studentID") Integer studentID) throws IllegalArgumentException {
		StudentDto studentDto = convertToDto(service.getStudent(studentID));
		service.deleteStudent(studentID);
		return studentDto;
	}
	
	/** Create Review Session
	 * 
	 * @param roomCode
	 * @param managerID
	 * @param offeringID
	 * @param tutoringSystemID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/classroom/review/create/{offeringID}", "/subject/review/create/{offeringID}/" })
	public ClassroomDto createReviewSession(@PathVariable("offeringID") String offeringID,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("roomCode") String roomCode,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {

		Offering offering = service.getOffering(offeringID);

		Boolean isBooked = true;
		Boolean isBigRoom = true;
		
		List<Classroom> classrooms = new ArrayList<Classroom>();
		classrooms = service.getAllClassrooms();
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
		
		Classroom thisClass = service.getClassroom(thisClassID);
		if (thisClass == null) {
			Manager manager = service.getManager(managerID);
			TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
			Set<Offering> thisOffering = new HashSet<Offering>();
			thisOffering.add(offering);
			thisClass = service.createClassroom(roomCode, isBooked, isBigRoom, manager, thisOffering, tutoringSystem);
		}
		
		return convertToDto(thisClass);
	}


	/**
	 * @return booked classroom
	 * @sample /classroom/smallRoom/booked/<roomcode>?isBooked=<isBooked>
	 */
	@GetMapping(value = { "/classroom/smallRoom/booked/{roomcode}", "/classroom/smallRoom/booked/{roomcode}/" })
	public List<ClassroomDto> getBookedRooms(@PathVariable("roomcode") String roomcode, @RequestParam("isBooked") Boolean isBooked){
		List<ClassroomDto> classroomDtos = new ArrayList<>();
		for (Classroom classroom : service.getAllClassrooms()) {
			if(classroom.getIsBooked().booleanValue() == isBooked) {
				classroomDtos.add(convertToDto(classroom));
			}
		}
		return classroomDtos;
	}
}

