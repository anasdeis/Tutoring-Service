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
import org.springframework.web.bind.annotation.PutMapping;
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
		
		Set<Integer> AvailableSessionIDs = null;
		if(tutor.getAvailableSession() != null)	{
			AvailableSessionIDs = new HashSet<Integer>();
			for(AvailableSession availableSession : tutor.getAvailableSession()){
				Integer AvailableSessionID = availableSession.getAvailableSessionID();
				AvailableSessionIDs.add(AvailableSessionID);
			}
		}
		
		TutorDto tutorDto = new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getDateOfBirth(), tutor.getEmail(), tutor.getPhoneNumber(), tutor.getPersonId(), tutor.getIsRegistered(), convertToDto(tutor.getLoginInfo()), tutorApplicationsIDs, offeringIDs, AvailableSessionIDs, tutor.getTutoringSystem().getTutoringSystemID());
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
		
		Set<Integer> commissionIDs = null;
		if(tutoringSystem.getCommission() != null)	{
			commissionIDs = new HashSet<Integer>();
			for(Commission commission : tutoringSystem.getCommission()){
				Integer commissionID = commission.getCommissionID();
				commissionIDs.add(commissionID);
			}
		}
		
		Set<Integer> reviewIDs = null;
		if(tutoringSystem.getReview() != null)	{
			reviewIDs = new HashSet<Integer>();
			for(Review review : tutoringSystem.getReview()){
				Integer reviewID = review.getReviewID();
				reviewIDs.add(reviewID);
			}
		}
		
		Set<Integer> subjectRequestIDs = null;
		if(tutoringSystem.getSubjectRequest() != null)	{
			subjectRequestIDs = new HashSet<Integer>();
			for(SubjectRequest subjectRequest : tutoringSystem.getSubjectRequest()){
				Integer subjectRequestID = subjectRequest.getRequestID();
				subjectRequestIDs.add(subjectRequestID);
			}
		}
		
		Set<String> universityNames = null;
		if(tutoringSystem.getUniversity() != null)	{
			universityNames = new HashSet<String>();
			for(University university : tutoringSystem.getUniversity()){
				String universityName = university.getName();
				universityNames.add(universityName);
			}
		}
		
		Set<String> classRoomCodes = null;
		if(tutoringSystem.getClassroom() != null)	{
			classRoomCodes = new HashSet<String>();
			for(Classroom classroom : tutoringSystem.getClassroom()){
				String classRoomCode = classroom.getRoomCode();
				classRoomCodes.add(classRoomCode);
			}
		}
		
		Set<Integer> tutorApplicationsIDs = null;
		if(tutoringSystem.getTutorApplication() != null)	{
			tutorApplicationsIDs = new HashSet<Integer>();
			for(TutorApplication tutorApplication : tutoringSystem.getTutorApplication()){
				Integer tutorApplicationsID = tutorApplication.getApplicationId();
				tutorApplicationsIDs.add(tutorApplicationsID);
			}
		}
		
		Set<Integer> AvailableSessionIDs = null;
		if(tutoringSystem.getAvailableSession() != null)	{
			AvailableSessionIDs = new HashSet<Integer>();
			for(AvailableSession availableSession : tutoringSystem.getAvailableSession()){
				Integer AvailableSessionID = availableSession.getAvailableSessionID();
				AvailableSessionIDs.add(AvailableSessionID);
			}
		}
		
		Set<String> subjectsCourseIDs = null;
		if(tutoringSystem.getSubject() != null)	{
			subjectsCourseIDs = new HashSet<String>();
			for(Subject subject : tutoringSystem.getSubject()){
				String subjectsCourseID = subject.getCourseID();
				subjectsCourseIDs.add(subjectsCourseID);
			}
		}
		
		Set<Integer> personIDs = null;
		if(tutoringSystem.getPerson() != null)	{
			personIDs = new HashSet<Integer>();
			for(Person person : tutoringSystem.getPerson()){
				Integer personID = person.getPersonId();
				personIDs.add(personID);
			}
		}
		
		TutoringSystemDto tutoringSystemDto = new TutoringSystemDto(tutoringSystem.getTutoringSystemID(), subjectRequestIDs, subjectsCourseIDs, personIDs, universityNames,  tutorApplicationsIDs , reviewIDs,  AvailableSessionIDs, classRoomCodes, commissionIDs);
		return tutoringSystemDto;
	}

	private ManagerDto convertToDto(Manager manager) {
		if (manager == null) {				
			throw new IllegalArgumentException("There is no such Manager!");
		}
		
		Set<String> classRoomCodes = null;
		if(manager.getClassroom() != null)	{
			classRoomCodes = new HashSet<String>();
			for(Classroom classroom : manager.getClassroom()){
				String classRoomCode = classroom.getRoomCode();
				classRoomCodes.add(classRoomCode);
			}
		}
		
		Set<Integer> commissionIDs = null;
		if(manager.getCommission() != null)	{
			commissionIDs = new HashSet<Integer>();
			for(Commission commission : manager.getCommission()){
				Integer commissionID = commission.getCommissionID();
				commissionIDs.add(commissionID);
			}
		}
		
		Set<Integer> reviewIDs = null;
		if(manager.getReview() != null)	{
			reviewIDs = new HashSet<Integer>();
			for(Review review : manager.getReview()){
				Integer reviewID = review.getReviewID();
				reviewIDs.add(reviewID);
			}
		}
		
		Set<Integer> subjectRequestIDs = null;
		if(manager.getSubjectRequest() != null)	{
			subjectRequestIDs = new HashSet<Integer>();
			for(SubjectRequest subjectRequest : manager.getSubjectRequest()){
				Integer subjectRequestID = subjectRequest.getRequestID();
				subjectRequestIDs.add(subjectRequestID);
			}
		}
		
		ManagerDto managerDto = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getDateOfBirth(), manager.getEmail(), manager.getPhoneNumber(), manager.getPersonId(), convertToDto(manager.getLoginInfo()), reviewIDs, commissionIDs, classRoomCodes, subjectRequestIDs, manager.getTutoringSystem().getTutoringSystemID());
		return managerDto;
	}

	private OfferingDto convertToDto(Offering offering) {
		if (offering == null) {
			throw new IllegalArgumentException("There is no such Offering!");
		}
		
		Set<Integer> AvailableSessionsIDs = null;
		if(offering.getClassTime() != null)	{
			AvailableSessionsIDs = new HashSet<Integer>();
			for(AvailableSession AvailableSession : offering.getClassTime()){
				Integer AvailableSessionsID = AvailableSession.getAvailableSessionID();
				AvailableSessionsIDs.add(AvailableSessionsID);
			}
		}
		
		Set<Integer> studentIDs = null;
		if(offering.getStudentsEnrolled() != null)	{
			studentIDs = new HashSet<Integer>();
			for(Student student : offering.getStudentsEnrolled()){
				Integer studentID = student.getPersonId();
				studentIDs.add(studentID);
			}
		}
		
		Set<Integer> reviewIDs = null;
		if(offering.getReview() != null)	{
			reviewIDs = new HashSet<Integer>();
			for(Review review : offering.getReview()){
				Integer reviewID = review.getReviewID();
				reviewIDs.add(reviewID);
			}
		}
		
		OfferingDto offeringDto = new OfferingDto(offering.getOfferingID(), offering.getTerm(), offering.getPricePerHour(), AvailableSessionsIDs, offering.getSubject().getCourseID(), offering.getTutor().getPersonId(), offering.getCommission().getCommissionID(), offering.getClassroom().getRoomCode(), studentIDs, reviewIDs, offering.getTutoringSystem().getTutoringSystemID());
		return offeringDto;
	}

	private StudentDto convertToDto(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		
		Set<String> offeringIDs = null;
		if(student.getCoursesTaken() != null)	{
			offeringIDs = new HashSet<String>();
			for(Offering offering : student.getCoursesTaken()){
				String offeringID = offering.getOfferingID();
				offeringIDs.add(offeringID);
			}
		}
		
		Set<Integer> subjectRequestIDs = null;
		if(student.getSubjectRequest() != null)	{
			subjectRequestIDs = new HashSet<Integer>();
			for(SubjectRequest subjectRequest : student.getSubjectRequest()){
				Integer subjectRequestID = subjectRequest.getRequestID();
				subjectRequestIDs.add(subjectRequestID);
			}
		}
		
		StudentDto studentDto = new StudentDto(student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getPersonId(), student.getNumCoursesEnrolled(), convertToDto(student.getLoginInfo()), offeringIDs, subjectRequestIDs, student.getTutoringSystem().getTutoringSystemID());
		return studentDto; 
	}
	
	private ReviewDto convertToDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("There is no such Review!");
		}
		ReviewDto reviewDto = new ReviewDto(review.getComment(), review.getIsApproved(), review.getReviewID(), review.getManager().getPersonId(), review.getOffering().getOfferingID(), review.getTutoringSystem().getTutoringSystemID());
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
		
		CommissionDto commissiondto = new CommissionDto(commission.getPercentage(), commission.getCommissionID(), commission.getManager().getPersonId(), offeringIDs, commission.getTutoringSystem().getTutoringSystemID());
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
		
		ClassroomDto classroomDto = new ClassroomDto(classroom.getRoomCode(), classroom.getIsBooked(), classroom.getIsBigRoom(), classroom.getManager().getPersonId(), offeringIDs, classroom.getTutoringSystem().getTutoringSystemID());
		return classroomDto;
	}
	
	private TutorApplicationDto convertToDto(TutorApplication tutorApplication) {
		if (tutorApplication == null) {
			throw new IllegalArgumentException("There is no such tutor Application!");
		}
		
		Set<String> subjectsCourseIDs = null;
		if(tutorApplication.getSubject() != null)	{
			subjectsCourseIDs = new HashSet<String>();
			for(Subject subject : tutorApplication.getSubject()){
				String subjectsCourseID = subject.getCourseID();
				subjectsCourseIDs.add(subjectsCourseID);
			}
		}
		
		TutorApplicationDto tutorApplicationDto = new TutorApplicationDto(tutorApplication.getApplicationId(), tutorApplication.getIsAccepted(), tutorApplication.getTutor().getPersonId(), subjectsCourseIDs, tutorApplication.getTutoringSystem().getTutoringSystemID());
		return tutorApplicationDto;
	}
	
	private SubjectDto convertToDto(Subject sb) {
		if (sb == null) {
			throw new IllegalArgumentException("There is no such subject information!");
		}
		
		Set<String> offeringIDs = null;
		if(sb.getOffering() != null)	{
			offeringIDs = new HashSet<String>();
			for(Offering offering : sb.getOffering()){
				String offeringID = offering.getOfferingID();
				offeringIDs.add(offeringID);
			}
		}
		
		Set<Integer> tutorApplicationsIDs = null;
		if(sb.getTutorRole() != null)	{
			tutorApplicationsIDs = new HashSet<Integer>();
			for(TutorApplication tutorApplication : sb.getTutorRole()){
				Integer tutorApplicationsID = tutorApplication.getApplicationId();
				tutorApplicationsIDs.add(tutorApplicationsID);
			}
		}
		
		University university = sb.getUniversity();
		String universityName = "";
		if (university != null) {
			universityName = university.getName();
		}

		SubjectDto subjectDto = new SubjectDto(sb.getName(), sb.getCourseID(), sb.getDescription(), convertSubjectTypeToString(sb.getSubjectType()), universityName, tutorApplicationsIDs, offeringIDs, sb.getTutoringSystem().getTutoringSystemID());
		return subjectDto;
	}
	
	private SubjectRequestDto convertToDto(SubjectRequest sr) {
		if (sr == null) {
			throw new IllegalArgumentException("There is no such subject Request!");
		}

		Set<Integer> studentIDs = null;
		if(sr.getStudent() != null)	{
			studentIDs = new HashSet<Integer>();
			for(Student student : sr.getStudent()){
				Integer studentID = student.getPersonId();
				studentIDs.add(studentID);
			}
		}
		
		SubjectRequestDto subjectRequestDto = new SubjectRequestDto(sr.getRequestID(), sr.getName(), sr.getDescription(),convertSubjectTypeToString(sr.getSubjectType()),sr.getManager().getPersonId(), studentIDs, sr.getTutoringSystem().getTutoringSystemID());
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
		UniversityDto universityDto = new UniversityDto(university.getName(), subjectsCourseIDs, university.getTutoringSystem().getTutoringSystemID());
		return universityDto;
		
		
	}
	

	private AvailableSessionDto convertToDto(AvailableSession availableSession) {
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

		AvailableSessionDto AvailableSessionDto = new AvailableSessionDto(availableSession.getStartTime(), availableSession.getEndTime(), availableSession.getAvailableSessionID(), availableSession.getDay(),tutorIDs, availableSession.getTutoringSystem().getTutoringSystemID());
		return AvailableSessionDto;
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
	 * @sample /tutoringSystem/create/{tutoringSystemID}
	 */
	@PostMapping(value = { "/tutoringSystem/create/{tutoringSystemID}", "/tutoringSystem/create/{tutoringSystemID}/" })
	public TutoringSystemDto createTutoringSystem(@PathVariable("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {

		TutoringSystem tutoringSystem = service.createTutoringSystem(tutoringSystemID);

		return convertToDto(tutoringSystem);
	}
	
	/**
	 * @param availableSessionID
	 * @param startTime
	 * @param endTime
	 * @param day
	 * @param tutorIDs (optional)
	 * @param tutoringSystemID
	 * @return create availableSession
	 * @sample /availableSession/create/{availableSessionID}?startTime=<startTime>&endTime=<endTime>&day=<day>&tutorIDs=<tutorIDs..>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = {"/availableSession/create/{availableSessionID}", "/availableSession/create/{availableSessionID}/"})
	public AvailableSessionDto createAvailableSession(@PathVariable("availableSessionID") Integer availableSessionID, 
			@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, 
			@RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
			@RequestParam("day") Date day, 
			@RequestParam("tutorIDs") Set<Integer> tutorIDs,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
		
		Set<Tutor> tutors = null;
		if(tutorIDs != null){
			tutors = new HashSet<Tutor>();
			for (Integer tutorID : tutorIDs) {
				Tutor tutor = service.getTutor(tutorID);
				tutors.add(tutor);
			}
		}

		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		AvailableSession availableSession = service.createAvailableSession(Time.valueOf(startTime), Time.valueOf(endTime), availableSessionID, day, tutors, tutoringSystem);
		
		return convertToDto(availableSession);
	}
	

	/**
	 * @param commissionID
	 * @param percentage
	 * @param managerID
	 * @param offeringID
	 * @param tutoringSystem
	 * @return create commission
	 * @sample /commission/create/{commissionID}?percentage=<percentage>&managerID=<managerID>&tutoringSystemID=<tutoringSystemID>[&offeringIDs=<offeringIDs..>]
	 */
	@PostMapping(value = {"/commission/create/{commissionID}", "/commission/create/{commissionID}/"})
	public CommissionDto createCommission(@PathVariable("commissionID") Integer commissionID, 
			@RequestParam("percentage") double percentage, 
			@RequestParam("managerID") Integer managerID, 
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs) throws IllegalArgumentException {
		
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
	 * @sample /login/{userName}?password=<password>
	 */
	@PostMapping(value = { "/login/{userName}", "/login/{userName}/"})
	public LoginDto createLogin(@PathVariable("userName") String userName, 
			@RequestParam("password") String password) throws IllegalArgumentException {
		Login loginInfo = service.createLogin(userName, password);
		return convertToDto(loginInfo);
	}
	
	/**
	 * @param tutorID
	 * @param first
	 * @param last
	 * @param dob
	 * @param email 
	 * @param phone
	 * @param isRegistered
	 * @param username 
	 * @param tutoringSystemID
	 * @param applicationIDs (optional)
	 * @param offeringIDs (optional)
	 * @param AvailableSessionIDs (optional)
	 * @return create tutor
	 * @sample /tutor/create/5?firstName=<firstName>&lastName=<lastName>&dob=<dob>&email=<email>&phone=<phone>&isRegistered=<isRegistered>&userName=<userName>&tutoringSystemID=<tutoringSystemID>[&applicationIDs=<applicationIDs..>&offeringIDs=<offeringIDs..>&availableSessionIDs=<availableSessionIDs..>]
	 */
	@PostMapping(value = { "/tutor/create/{tutorID}", "/tutor/create/{tutorID}/" })
	public TutorDto createTutor(@PathVariable("tutorID") Integer tutorID, 
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName, 
			@RequestParam("dob") Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("isRegistered") Boolean isRegistered, 
			@RequestParam("userName") String userName,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "applicationIDs", required = false) Set<Integer> applicationIDs,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs,
			@RequestParam(name = "availableSessionIDs", required = false) Set<Integer> availableSessionIDs) throws IllegalArgumentException {

		Login login = service.getLogin(userName);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);

		Set<TutorApplication> tutorApplications = null;
		if(applicationIDs != null) {
			tutorApplications = new HashSet<TutorApplication>();
			for (Integer applicationId : applicationIDs) {
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

		Set<AvailableSession> availableSessions = null;
		if(availableSessionIDs != null){
			availableSessions = new HashSet<AvailableSession>();
			for (Integer availableSessionID : availableSessionIDs) {
				AvailableSession availableSession = service.getAvailableSession(availableSessionID);
				availableSessions.add(availableSession);
			}
		}

		Tutor tutor = service.createTutor(firstName, lastName, dob, email, phone, tutorID, isRegistered, login, tutorApplications, offerings, availableSessions, tutoringSystem);

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
	 * @param studentIDs (optional)
	 * @param reviewIDs (optional)
	 * @return create offering
	 * @sample /offering/create/{offeringID}?term=<term>&price=<price>&classTimes=<classTimes..>&courseID=<courseID>&tutorID=<tutorID>&commissionID=<commissionID>&roomCode=<roomCode>&tutoringSystemID=<tutoringSystemID>[&studentIDs=<studentIDs..>&reviewIDs=<reviewIDs..>]
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
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "studentIDs", required = false) Set<Integer> studentIDs,
			@RequestParam(name = "reviewIDs", required = false) Set<Integer> reviewIDs) throws IllegalArgumentException {

		Set<AvailableSession> AvailableSessions = null;
		if(classTimes != null){
			AvailableSessions = new HashSet<AvailableSession>();
			for (Integer AvailableSessionID : classTimes) {
				AvailableSession AvailableSession = service.getAvailableSession(AvailableSessionID);
				AvailableSessions.add(AvailableSession);
			}
		}
		
		Set<Student> students = null;
		if(studentIDs != null){
			students = new HashSet<Student>();
			for (Integer studentID : studentIDs) {
				Student student = service.getStudent(studentID);
				students.add(student);
			}
		}
		
		Set<Review> reviews = null;
		if(reviewIDs != null){
			reviews = new HashSet<Review>();
			for (Integer reviewID : reviewIDs) {
				Review review = service.getReview(reviewID);
				reviews.add(review);
			}
		}
		
		Subject subject = service.getSubject(courseID);
		Tutor tutor = service.getTutor(tutorID);
		Commission commission = service.getCommission(commissionID);
		Classroom classroom = service.getClassroom(roomCode);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		Offering offering = service.createOffering(offeringID, term, price, AvailableSessions, subject, tutor, commission, classroom, students, reviews, tutoringSystem);

		return convertToDto(offering);
	}

	/**
	 * @param managerID
	 * @param first
	 * @param last
	 * @param dob
	 * @param email 
	 * @param phone
	 * @param username 
	 * @param tutoringSystemID
	 * @param reviewIDs (optional)
	 * @param requestIDs (optional)
	 * @param commissionIDs (optional)
	 * @param roomCodes (optional)
	 * @return create manager
	 * @sample /manager/create/{managerID}?first=<first>&last=<last>&dob=<dob>&email=<email>&phone=<phone>&userName=<userName>&tutoringSystemID=<tutoringSystemID>[&reviewIDs=<reviewIDs>&requestIDs=<requestIDs>&commissionIDs=<commissionIDs>&roomCodes=<roomCodes>]
	 * 
	 */
	@PostMapping(value = { "/manager/create/{managerID}","/manager/create/{managerID}/" })
	public ManagerDto createManager(@PathVariable("managerID") Integer managerID, 
			@RequestParam("first") String first, 
			@RequestParam("last") String last, 
			@RequestParam ("dob") Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("userName") String userName, 
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "reviewIDs", required = false) Set<Integer> reviewIDs,
			@RequestParam(name = "requestIDs", required = false) Set<Integer> requestIDs,
			@RequestParam(name = "commissionIDs", required = false) Set<Integer> commissionIDs,
			@RequestParam(name = "roomCodes", required = false) Set<String> roomCodes) throws IllegalArgumentException {
		
		Set<Review> reviews = null;
		if(reviewIDs != null){
			reviews = new HashSet<Review>();
			for (Integer reviewID : reviewIDs) {
				Review review = service.getReview(reviewID);
				reviews.add(review);
			}
		}
		
		Set<SubjectRequest> requests = null;
		if(requestIDs != null){
			requests = new HashSet<SubjectRequest>();
			for (Integer requestID : requestIDs) {
				SubjectRequest request = service.getSubjectRequest(requestID);
				requests.add(request);
			}
		}
		
		Set<Commission> commissions = null;
		if(commissionIDs != null){
			commissions = new HashSet<Commission>();
			for (Integer commissionID : commissionIDs) {
				Commission commission = service.getCommission(commissionID);
				commissions.add(commission);
			}
		}
		
		Set<Classroom> classrooms = null;
		if(roomCodes != null){
			classrooms = new HashSet<Classroom>();
			for (String roomCode : roomCodes) {
				Classroom classroom = service.getClassroom(roomCode);
				classrooms.add(classroom);
			}
		}
		
		Login login = service.getLogin(userName);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);

		Manager manager = service.createManager(first, last, dob, email, phone, managerID, login, requests, reviews, commissions, classrooms, tutoringSystem);

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
	 * @sample /tutor/create/{reviewID}?comment=<comment>&isApproved=<isApproved>&managerID=<managerID>&offering=<offering>&tutoringSystemID=<tutoringSystemID>
	 */
	@PostMapping(value = { "/review/create/{reviewID}", "/review/create/{reviewID}/" })
	public ReviewDto createReview(@PathVariable("reviewID") Integer reviewID,
			@RequestParam("comment") String comment,
			@RequestParam("isApproved") Boolean isApproved,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("offeringID") String offeringID,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {
	
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
	 * @param tutoringSystemID
	 * @param offeringIDs (optional)
	 * @sample /classroom/create/{roomCode}?isBooked=<isBooked>&isBigRoom=<isBigRoom>&managerID=<managerID>&tutoringSystemID=<tutoringSystemID>[&offeringIDs=<offeringIDs..>]
	 */
	@PostMapping(value = { "/classroom/create/{roomCode}", "/classroom/create/{roomCode}/" })
	public ClassroomDto createClassroom(@PathVariable("roomCode") String roomCode,
			@RequestParam("isBooked") Boolean isBooked,
			@RequestParam("isBigRoom") Boolean isBigRoom,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs) throws IllegalArgumentException {

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
	 * @param tutoringSystemID
	 * @param courseIDs (optional)
	 * @sample /university/create/{name}?tutoringSystemID=<tutoringSystemID>[&courseIDs=<courseIDs..>]
	 */
	@PostMapping(value = { "/university/create/{name}", "/university/create/{name}/" })
	public UniversityDto createUniversity(@PathVariable("name") String name,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "courseIDs", required = false) Set<String> courseIDs) throws IllegalArgumentException {
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
	 * @param tutorApplicationID
	 * @param isAccepted
	 * @param tutorID
	 * @param tutorSystemID
	 * @param courseIDs (optional)
	 * @return create Tutor Application
	 * @sample /tutorApplication/create/{tutorApplicationID}?IsAccepted=<isAccepted>&tutorID=<tutorID>&tutoringSystemID=<tutoringSystemID>[&courseIDs=<courseIDs..>]
	 */
	@PostMapping(value = { "/tutorApplication/create/{tutorApplicationID}","/tutorApplication/create/{tutorApplicationID}/" })
	public TutorApplicationDto createTutorApplication(@PathVariable("tutorApplicationID") Integer tutorApplicationID, 
			@RequestParam("isAccepted") Boolean isAccepted, 
			@RequestParam("tutorID") Integer tutorID, 
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "courseIDs", required = false) Set<String> courseIDs) throws IllegalArgumentException {
		
		Set<Subject> subjects = null;
		if(courseIDs != null){
			subjects = new HashSet<Subject>();
			for (String courseID : courseIDs) {
				Subject subject = service.getSubject(courseID);
				subjects.add(subject);
			}
		}
		
		Tutor tutor = service.getTutor(tutorID);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);

		TutorApplication tutorApplication = service.createTutorApplication(tutorApplicationID, isAccepted, tutor, subjects, tutoringSystem);

		return convertToDto(tutorApplication);

	}

	/**
	 * Student: create student with parameters
	 * @param firstName
	 * @param lastName
	 * @param dob
	 * @param email
	 * @param phone
	 * @param numCoursesEnrolled
	 * @param userName
	 * @param tutoringSystemID
	 * @param offeringIDs (optional)
	 * @param requestIDs (optional)
 	 * @sample  /student/create/{personId}?firstName=<firstName>&lastName=<lastName>&dob=<dob>&email=<email>&phone=<phone>&tutoringSystemID=<tutoringSystemID>[&offeringIDs=<offeringIDs..>&requestIDs=<requestIDs..>]
	 */
	@PostMapping(value = {"/student/create/{studentID}", "/student/create/{studentID}/"})
	public StudentDto createStudent(@PathVariable("studentID") Integer studentID,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("dob") Date dob,
			@RequestParam("email") String email,
			@RequestParam("phone") Integer phone,
			@RequestParam("userName") String userName,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs,
			@RequestParam(name = "requestIDs", required = false) Set<Integer> requestIDs) throws IllegalArgumentException {

		Integer numCoursesEnrolled = 0;
		Set<SubjectRequest> requests = null;
		if(requestIDs != null){
			requests = new HashSet<SubjectRequest>();
			for (Integer requestID : requestIDs) {
				SubjectRequest request = service.getSubjectRequest(requestID);
				requests.add(request);
			}
		}
		
		Set<Offering> offerings = null;
		if(offeringIDs != null){
			offerings = new HashSet<Offering>();
			for (String offeringID : offeringIDs) {
				Offering offering = service.getOffering(offeringID);
				offerings.add(offering);
			}
			numCoursesEnrolled = offerings.size();
		}
		
		Login login = service.getLogin(userName);
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		
		Student student = service.createStudent(firstName, lastName, dob, email, phone, studentID, numCoursesEnrolled, login, requests, offerings, tutoringSystem);

		return convertToDto(student);

	}
	
	/** Add Subject
	 * @param name
	 * @param courseID
	 * @param description
	 * @param subjectType: University/HighSchool/CGEP
	 * @param tutoringSystemID
	 * @param university (optional)
	 * @param offeringIDs (optional)
	 * @param tutorApplicationIDs (optional)
	 * @return subject added
	 * @throws IllegalArgumentException
	 * sample: /subject/create/{name}?courseID=<courseID>&description=<description>&subjectType=<subjectType>&tutoringSystemID=<tutoringSystemID>[&university=<university>&offeringIDs=<offeringIDs..>&tutorApplicationIDs=<tutorApplicationIDs..>]
	 */
	@PostMapping(value = { "/subject/create/{name}", "/subject/create/{name}/" })
	public SubjectDto createSubject(@PathVariable("name") String name, 
			@RequestParam("courseID") String courseID, 
			@RequestParam("description") String description, 
			@RequestParam("subjectType") String subjectType,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "university", required = false) String university,
			@RequestParam(name = "offeringIDs", required = false) Set<String> offeringIDs,
			@RequestParam(name = "tutorApplicationIDs", required = false) Set<Integer> tutorApplicationIDs) throws IllegalArgumentException {
		
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		SubjectType sbType = null;
		University uni = null;
		if(subjectType != null)
		{
			if (subjectType.equals("University") && university != null) {
				sbType = SubjectType.UNIVERSITY_COURSE;
				uni = service.getUniversity(university);
			}else if(subjectType.equals("HighSchool")) {
				sbType = SubjectType.HIGH_SCHOOL_COURSE;
			} else if(subjectType.equals("CGEP")) {
				sbType = SubjectType.CGEP_COURSE;
			} else {
				sbType = null;
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
		
		Set<TutorApplication> tutorApplications = null;
		if(tutorApplicationIDs != null){
			tutorApplications = new HashSet<TutorApplication>();
			for (Integer tutorApplicationID : tutorApplicationIDs) {
				TutorApplication tutorApplication = service.getTutorApplication(tutorApplicationID);
				tutorApplications.add(tutorApplication);
			}
		}

		Subject subject = service.createSubject(name, courseID, description, sbType, uni, offerings, tutorApplications, tutoringSystem);
		return convertToDto(subject);
	}	
	
	/** Add SubjectRequest
	 * @param requestID
	 * @param name
	 * @param description
	 * @param subjectType: University/HighSchool/CGEP
	 * @param manager	
	 * @param tutoringSystemID
	 * @param studentIDs (optional)
	 * @return subjectRequest is added
	 * sample: /subjectRequest/create/{name}?requestID=<requestID>&description=<description>&subjectType=<subjectType>&manager=<managerID>&tutoringSystemID=<tutoringSystemID>[&studentIDs=<studentIDs..>]
	 */
	@PostMapping(value = { "/subjectRequest/create/{name}", "/subjectRequest/create/{name}/" })
	public SubjectRequestDto createSubjectRequest(@PathVariable("name") String name, 
			@RequestParam("requestID") Integer requestID, 
			@RequestParam("description") String description, 
			@RequestParam("subjectType") String subjectType,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID,
			@RequestParam(name = "studentIDs", required = false) Set<Integer> studentIDs) throws IllegalArgumentException {

		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemID);
		Manager manager = service.getManager(managerID);

		SubjectType subjectType1 = null;
		if (subjectType.equals("University")) {
			subjectType1 = SubjectType.UNIVERSITY_COURSE;
		}else if(subjectType.equals("HighSchool")) {
			subjectType1 = SubjectType.HIGH_SCHOOL_COURSE;
		} else if(subjectType.equals("CGEP")) {
			subjectType1  = SubjectType.CGEP_COURSE;
		}
		
		Set<Student> students = null;
		if(studentIDs != null){
			students = new HashSet<Student>();
			for (Integer studentID : studentIDs) {
				Student student = service.getStudent(studentID);
				students.add(student);
			}
		}
	
		SubjectRequest subjectRequest = service.createSubjectRequest(requestID, name, description, subjectType1, manager, students, tutoringSystem);
		return convertToDto(subjectRequest);
	}	
	
	/**
	/*
	 * list methods
	 */
	
	/**
	 * @return list all availableSessions
	 * @sample /availableSession/list
	 */
	@GetMapping(value = { "/availableSession/list", "/availableSession/list/" })
	public List<AvailableSessionDto> getAllAvailableSessions() {
		List<AvailableSessionDto> availableSessionsDtos = new ArrayList<>();
		for (AvailableSession availableSession : service.getAllAvailableSessions()) {
			availableSessionsDtos.add(convertToDto(availableSession));
		}
		return availableSessionsDtos;
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
	 * @return list of subjectRequests
	 * @sample /subjectRequest/list
	 */
	@GetMapping(value = { "/subjectRequest/list", "/subjectRequest/list/" })
	public List<SubjectRequestDto> getAllSubjectRequests() {
		List<SubjectRequestDto> subjectRequestDtos = new ArrayList<>();
		for (SubjectRequest subjectRequest : service.getAllSubjectRequests()) {
			subjectRequestDtos.add(convertToDto(subjectRequest));
		}
		return subjectRequestDtos;
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
	 * @return login if it exists
	 * @sample /login/check/omar?password=123
	 */
		@GetMapping(value = { "/login/check/{userName}", "/login/{userName}/" })
		public LoginDto getLogin(@PathVariable("userName") String userName,
				@RequestParam("password") String password) throws IllegalArgumentException{
			String error = "";
			LoginDto loginDto = new LoginDto();
			Login login= service.getLogin(userName);
			String databasepassword = login.getPassword();
			
			if (!(databasepassword.equals(password))) {
				error += "Login does not exist!";
			}
			error = error.trim();
			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			
			loginDto = convertToDto(login);
			return loginDto;
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
	
	@GetMapping(value = { "/tutor/{tutorID}", "/tutor/{tutorID}/" })
	public TutorDto getTutor(Integer tutorID) {
		TutorDto tutorDto = new TutorDto();
		Tutor tutor = service.getTutor(tutorID);
		tutorDto = convertToDto(tutor);
		return tutorDto;
		
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
	
	@GetMapping(value = { "/tutorApplication/{tutorApplicationID}", "/tutorApplication/{tutorApplicationID}/" })
	public TutorApplicationDto getTutorApplication(@PathVariable("tutorApplicationID") Integer tutorApplicationID) {
		TutorApplicationDto tutorApplicationDto = new TutorApplicationDto();
		TutorApplication tutorApplication = service.getTutorApplication(tutorApplicationID);
		tutorApplicationDto = convertToDto(tutorApplication);
		return tutorApplicationDto;
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
	 * @sample /tutor/update/registered/<tutorID>
	 */
	@PatchMapping(value = { "/tutor/update/registered/{tutorID}", "/tutor/update/registered/{tutorID}/" })
	public TutorDto updateTutorIsRegistered(@PathVariable("tutorID") Integer tutorID, @RequestParam("isRegistered") Boolean isRegistered) {
		Tutor tutor = (service.setTutorIsRegistered(service.getTutor(tutorID), isRegistered));
		TutorDto tutorDto = convertToDto(tutor);

		return tutorDto;
	}
	
	/**
	 * @return Update tutor Application to isAccepted
	 * @sample /tutorApplication/update/{tutorApplicationID}?isAccepted=<isAccepted>
	 */
	@PatchMapping(value = { "/tutorApplication/update/{tutorApplicationID}", "/tutorApplication/update/{tutorApplicationID}/" })
	public TutorApplicationDto updateTutorApplicationisAccepted(@PathVariable("tutorApplicationID") Integer tutorApplicationID,
			@RequestParam("isAccepted") Boolean isAccepted) {
		TutorApplication tutorApplication = (service.setTutorApplicationIsAccepted(service.getTutorApplication(tutorApplicationID), isAccepted));
		TutorApplicationDto tutorApplicationDto = convertToDto(tutorApplication);

		return tutorApplicationDto;
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
	 * @return review session classroom
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/classroom/review/create/{offeringID}", "/subject/review/create/{offeringID}/" })
	public ClassroomDto createReviewSession(@PathVariable("offeringID") String offeringID,
			@RequestParam("managerID") Integer managerID,
			@RequestParam("roomCode") String roomCode,
			@RequestParam("tutoringSystemID") Integer tutoringSystemID) throws IllegalArgumentException {

		Classroom thisClass = service.createReviewSession(offeringID, managerID, roomCode, tutoringSystemID);
		
		return convertToDto(thisClass);
	}


	/**
	 * @return list of booked/non-booked and bigroom/smallroom classroom
	 * @sample /classroom/isBigRoom/{isBigRoom}/isBooked/{isBooked}
	 */
	@GetMapping(value = { "/classroom/isBigRoom/{isBigRoom}/isBooked/{isBooked}", "/classroom/isBigRoom/{isBigRoom}/isBooked/{isBooked}/" })
	public List<ClassroomDto> getBookedRooms(@PathVariable("isBigRoom") Boolean isBigRoom, @PathVariable("isBooked") Boolean isBooked){
		List<ClassroomDto> classroomDtos = new ArrayList<>();
		for (Classroom classroom : service.getAllClassrooms()) {
			if(classroom.getIsBooked().booleanValue() == isBooked && classroom.getIsBigRoom().booleanValue() == isBigRoom) {
				classroomDtos.add(convertToDto(classroom));
			}
		}
		return classroomDtos;
	}
	
	/**
	 * @return Remove subject request
	 * @sample /subjectRequest/delete/<requestID>
	 */
	@DeleteMapping(value = {"/subjectRequest/delete/{requestID}", "/subjectRequest/delete/{requestID}/"})
	public SubjectRequestDto deleteSubjectRequest(@PathVariable("requestID") Integer requestID) {
		SubjectRequestDto subjectRequestDto = convertToDto(service.getSubjectRequest(requestID));
		service.deleteSubjectRequest(requestID);
		return subjectRequestDto;
	}
	
	/**
	 * @return Remove subject 
	 * @sample /subject/delete/<courseID>
	 */
	@DeleteMapping(value = {"/subject/delete/{courseID}", "/subject/delete/{courseID}/"})
	public SubjectDto deleteSubject(@PathVariable("courseID") String courseID) {
		SubjectDto subjectDto = convertToDto(service.getSubject(courseID));
		service.deleteSubject(courseID);
		return subjectDto;
	}
	
	/**
	 * @return Remove commission 
	 * @sample /commission/delete/<commissionID>
	 */
	@DeleteMapping(value = {"/commission/delete/{commissionID}", "/commission/delete/{commissionID}/"})
	public CommissionDto deleteCommission(@PathVariable("commissionID") Integer commissionID) {
		CommissionDto commissionDto = convertToDto(service.getCommission(commissionID));
		service.deleteCommisison(commissionID);
		return commissionDto;
	}
	
	/**
	 * @return Remove tutorApplication 
	 * @sample /tutorApplcation/delete/<applicationId>
	 */
	@DeleteMapping(value = {"/tutorApplication/delete/{applicationId}", "/tutorApplication/delete/{applicationId}/"})
	public TutorApplicationDto deleteTutorApplication(@PathVariable("applicationId") Integer applicationId) {
		TutorApplicationDto tutorApplicationDto = convertToDto(service.getTutorApplication(applicationId));
		service.deleteTutorApplication(applicationId);
		return tutorApplicationDto;
	}
	
	/**
	 * @return Remove review 
	 * @sample /tutorApplcation/delete/<applicationId>
	 */
	@DeleteMapping(value = {"/review/delete/{reviewID}", "/review/delete/{reviewID}/"})
	public ReviewDto deleteReview(@PathVariable("reviewID") Integer reviewID) {
		ReviewDto reviewDto = convertToDto(service.getReview(reviewID));
		service.deleteReview(reviewID);
		return reviewDto;
	}
	
	/**
	 * @return Remove offering 
	 * @sample /offering/delete/<offeringID>
	 */
	@DeleteMapping(value = {"/offering/delete/{offeringID}", "/offering/delete/{offeringID}/"})
	public OfferingDto deleteOffering(@PathVariable("offeringID") String offeringID) {
		OfferingDto offeringDto = convertToDto(service.getOffering(offeringID));
		service.deleteOffering(offeringID);
		return offeringDto;
	}
	
	/**
	 * @return Set review offering for classroom 
	 * @sample /classroom/review/<roomCode>?offeringID=<offeringID>
	 */
	@PatchMapping(value = { "/classroom/review/{roomCode}", "/classroom/review/{roomCode}/" })
	public ClassroomDto setClassroomReviewSession(@PathVariable("roomCode") String roomCode, @RequestParam("offeringID") String offeringID) {
		Classroom classroom = (service.setClassroomReviewSession(roomCode, offeringID));
		ClassroomDto classroomDto = convertToDto(classroom);

		return classroomDto;
	}
	
	/**
	 * @return Add student to offering 
	 * @sample /offering/addstudent/{offeringID}
	 */
	@PatchMapping(value = { "/offering/addstudent/{offeringID}", "/offering/addstudent/{offeringID}/" })
	public OfferingDto setOfferingStudent(@PathVariable("offeringID") String offeringID, @RequestParam("studentID") Integer studentID) {
		Offering offering = (service.setOfferingStudent(offeringID, studentID));
		OfferingDto offeringDto = convertToDto(offering);

		return offeringDto;
	}
	
	/**
	 * @return Add offering to student 
	 * @sample /student/offering/{studentID}
	 */
	@PatchMapping(value = { "/student/offering/{studentID}", "/student/offering/{studentID}/" })
	public StudentDto setStudentOffering(@PathVariable("studentID") Integer studentID, @RequestParam("offeringID") String offeringID) {
		Student student = (service.setStudentOffering(studentID, offeringID));
		StudentDto studentDto = convertToDto(student);

		return studentDto;
	}
	
	/**
	 * @return Remove available session
	 * @sample /availableSession/delete/{availableSessionID}
	 */
	@DeleteMapping(value = { "/availableSession/delete/{availableSessionID}", "/availableSession/delete/{availableSessionID}/" })
	public AvailableSessionDto deleteAvailableSession(@PathVariable("availableSessionID") Integer availableSessionID) {
		AvailableSessionDto availableSessionDto = convertToDto(service.getAvailableSession(availableSessionID));
		service.deleteAvailableSession(availableSessionID);
		return availableSessionDto;
	}
	
	/**
	 * @return Remove available session
	 * @sample /availableSession/delete/{availableSessionID}
	 */
	@DeleteMapping(value = { "/classroom/delete/{roomCode}", "/classroom/delete/{roomCode}/" })
	public ClassroomDto deleteClassroom(@PathVariable("roomCode") String roomCode) {
		ClassroomDto classroomDto = convertToDto(service.getClassroom(roomCode));
		service.deleteClassroom(roomCode);
		return classroomDto;
	}
}



