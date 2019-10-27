package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	TutoringServiceService service;
	
	/* TODO
	 * Login: create with specified parameters
	 * @param username
	 * @param password
	 */
//	@PostMapping(value = { "/userName/{userName}", "/password/{password}"})
	@PostMapping("/login/create")
	public LoginDto createLogin(@RequestParam("username") String username, @RequestParam("password") String password) throws IllegalArgumentException {
		Login loginInfo = service.createLogin(username, password);
		return convertToDto(loginInfo);
	}
	
	private LoginDto convertToDto(Login lg) {
		if (lg == null) {
			throw new IllegalArgumentException("There is no such login information!");
		}
		LoginDto loginDto = new LoginDto(lg.getUserName(), lg.getPassword());
		return loginDto;
		}

	@GetMapping(value = { "/login/list/{username}", "/login/list/{password}"})
	public List<LoginDto> getAllLogins() {

		List<LoginDto> loginDtos = new ArrayList<>();

		for (Login login : service.getAllLogins()) {
			if(login.getUserName() != null)
				loginDtos.add(convertToDto(login));
		}
		return loginDtos;
	}
	/* TODO
	 * Classroom: creation with specified parameters
	 * @param roomcode
	 * @param isBooked
	 * @param isBigRoom
	 * @param managerID
	 * @param lgInfo
	 * @param offeringId
	 * @param tutoringSystem
	 */
	@PostMapping("classroom/create")
	public ClassroomDto createClassroom(
			@RequestParam("roomcode") String roomcode, 
			@RequestParam("isBooked") Boolean isBooked, 
			@PathVariable("isBigRm") Boolean isBigRm,
			@RequestParam("managerID") Integer managerID,
//			@RequestParam("lgInfo") LoginDto loginDto,
			@RequestParam("offeringID") OfferingDto offeringDto,
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto
			) 
	throws IllegalArgumentException {
//		Login login = service.getLogin(loginDto.getUserName());
		TutoringSystem system = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Manager manager = service.getManager(managerID);
		@SuppressWarnings("unchecked")
		Set<Offering> offering = (Set<Offering>) service.getOffering(offeringDto.getOfferingID());
		Classroom classroom = service.createClassroom(roomcode, isBooked, isBigRm, manager, offering, system);
		
		return convertToDto(classroom);
	}
	
	private ClassroomDto convertToDto(Classroom classroom) {
		if (classroom == null) {
			throw new IllegalArgumentException("There is no such classroom!");
		}
		ClassroomDto classroomDto = new ClassroomDto(classroom.getRoomCode(), classroom.getIsBooked(), classroom.getIsBigRoom(), classroom.getManager(), 
				classroom.getOffering(), classroom.getTutoringSystem());
		return classroomDto;
	}
	
	@GetMapping(value = { "/classrooms/{roomcode}", "/classrooms/{roomcode}"})
	public List<ClassroomDto> getAllClassrooms() {
		List<ClassroomDto> classroomDtos = new ArrayList<>();
		for (Classroom classroom : service.getAllClassrooms()) {
			classroomDtos.add(convertToDto(classroom));
		}
		return classroomDtos;
	}
	
	/*
	 * Manager: create manager with specified parameters
	 * @param first
	 * @param last
	 * @param dob
	 * @param email
	 * @param phone
	 * @param managerId
	 * @param login
	 * @param tutoringSystem
	 */
	@PostMapping(value = { "/manager/create/{managerId}" })
	public ManagerDto createManager(@PathVariable("managerId") Integer managerId, 
			@RequestParam("first") String first, 
			@RequestParam("last") String last, 
			@RequestParam Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("loginInfo") LoginDto loginDto, 
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto) throws IllegalArgumentException {
		Login login = service.getLogin(loginDto.getUserName());
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Manager manager = service.createManager(first, last, dob, email, phone, managerId, login, tutoringSystem);

		return convertToDto(manager);
	}
		
	private ManagerDto convertToDto(Manager manager) {
		if (manager == null) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		ManagerDto managerDto = new ManagerDto(manager.getFirstName(), manager.getLastName(), manager.getDateOfBirth(), manager.getEmail(), manager.getPhoneNumber(), manager.getPersonId(), manager.getLoginInfo(), manager.getTutoringSystem());
		return managerDto;
	}
	
	/*
	 * Student: create student with parameters
	 @param first
	 * @param last
	 * @param dob
	 * @param email
	 * @param phone
	 * @param studentID
	 * @param login
	 * @param tutoringSystem
	 */
	@PostMapping("/student/create/{studnetID")
	public StudentDto createStudent(@PathVariable("studentID") Integer studentID,
			@RequestParam("first") String first,
			@RequestParam("last") String last,
			@RequestParam Date dob,
			@RequestParam("email") String email,
			@RequestParam("phone") Integer phone,
			@RequestParam("numCoursesEnrolled") Integer numCoursesEnrolled,
			@RequestParam("loginInfo") LoginDto loginDto,
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto) throws IllegalArgumentException {
		
		Login login = service.getLogin(loginDto.getUserName());
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Student student = service.createStudent(first, last, dob, email, phone, studentID, numCoursesEnrolled, login, tutoringSystem);
		
		return convertToDto(student);
		
	}

	private StudentDto convertToDto(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("");
		} 
		StudentDto studentDto = new StudentDto(student.getFirstName(),student.getLastName(),student.getDateOfBirth(),student.getEmail(),student.getPhoneNumber(),student.getPersonId(), student.getNumCoursesEnrolled(),student.getLoginInfo(),student.getTutoringSystem());
		return studentDto;
	}
	
	/*
	 * @return a list of students
	 */
	@GetMapping(value = { "/student/list/{studentID}", "/student/list/{studentID}/" })
	public List<StudentDto> getAllStudent(@PathVariable("studentID") Integer studentID) {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student : service.getAllStudents()) {	
				studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}

	/*
	 * @return create tutor
	 * @sample /tutor/create/5
	 */
	
	@PostMapping(value = { "/tutor/create/{personId}", "/tutor/create/{personId}/" })
	public TutorDto createTutor(@PathVariable("personId") Integer personId, 
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName, 
			@RequestParam Date dob, 
			@RequestParam("email") String email, 
			@RequestParam("phone") Integer phone,
			@RequestParam("isRegistered") Boolean isRegistered, 
			@RequestParam("loginInfo") LoginDto loginDto, 
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto) throws IllegalArgumentException {
		// @formatter:on
		Login login = service.getLogin(loginDto.getUserName());
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Tutor tutor = service.createTutor(firstName, lastName, dob, email, phone, personId, isRegistered, login, tutoringSystem);

		return convertToDto(tutor);
	}
		
	private TutorDto convertToDto(Tutor tutor) {
		if (tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
		TutorDto tutorDto = new TutorDto(tutor.getFirstName(), tutor.getLastName(), tutor.getDateOfBirth(), tutor.getEmail(), tutor.getPhoneNumber(), tutor.getPersonId(), tutor.getIsRegistered(), tutor.getLoginInfo(), tutor.getTutoringSystem());
		return tutorDto;
	}
	
	/*
	 * @return a list of Registered/Non-Registered Tutors
	 * @sample /tutor/list/true
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
	 * @sample /tutor/delete/5
	 * 
	 */
	@RequestMapping(value = {"/tutor/delete/{personId}", "/tutor/delete/{personId}/"}, method = RequestMethod.DELETE)
    public TutorDto deleteTutor(@PathVariable("personId") Integer personId) throws IllegalArgumentException {
		TutorDto tutorDto = convertToDto(service.getTutor(personId));
		service.deleteTutor(personId);
        return tutorDto;
    }
	
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
		
	private TutoringSystemDto convertToDto(TutoringSystem tutoringSystem) {
		if (tutoringSystem == null) {
			throw new IllegalArgumentException("There is no such TutoringSystem!");
		}
		TutoringSystemDto tutoringSystemDto = new TutoringSystemDto(tutoringSystem.getTutoringSystemID());
		return tutoringSystemDto;
	}

 	
}
