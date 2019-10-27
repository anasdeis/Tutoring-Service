package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	
	@PostMapping(value = { "/userName/{userName}", "/password/{password}"})
	public LoginDto createLogin(@PathVariable("userName") String username, @PathVariable("password") String password) throws IllegalArgumentException {
		Login loginInfo = service.createLogin(username, password);
		return convertToDto(loginInfo);
	}
	
	private LoginDto convertToDto(Login lg) {
		if (lg == null) {
			throw new IllegalArgumentException("There is no such login information!");
		}
		LoginDto loginDto = new LoginDto(lg.getPassword(), lg.getPassword());
		return loginDto;
		}
	
	/*
	 * @return create studentmanag
	 */
	@PostMapping(value = { "/student/create/{personId}", "/student/create/{personId}"})
	public StudentDto createStudent(@PathVariable("personId") Integer personId, 
	@RequestParam("firstName") String firstName, 
	@RequestParam("lastName") String lastName, 
	@RequestParam("loginInfo") LoginDto loginDto, 
	@RequestParam("dateOfBirth") Date dob, 
	@RequestParam("email") String email, 
	@RequestParam("phoneNumber") Integer phone, 
	@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto, 
	@RequestParam("numCoursesEnrolled") Integer numCoursesEnrolled) throws IllegalArgumentException{
		Login login = service.getLogin(loginDto.getUserName());
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Student student = service.createStudent(firstName, lastName, dob, email, phone, personId, numCoursesEnrolled, login, tutoringSystem);
		return convertToDto(student);
	}
	
	private StudentDto convertToDto(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		StudentDto studentDto = new StudentDto(student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getPersonId(), student.getNumCoursesEnrolled(), student.getLoginInfo(), student.getTutoringSystem());
		return studentDto; 
	}
	
	@RequestMapping(value = {"/student/delete/{personId}", "/student/delete/{personId}/"}, method = RequestMethod.DELETE)
	public StudentDto deleteStudent(@PathVariable("personId") Integer personId) throws IllegalArgumentException {
		StudentDto studentDto = convertToDto(service.getStudent(personId));
		service.deleteStudent(personId);
		return studentDto;
	}
	
	/*
	 * @return create commission
	 */
	@PostMapping(value = {"/commission/create/{commissionID}", "/commission/create/{commissionID}"})
	public CommissionDto createCommission(@PathVariable("commissionID") Integer commissionID, 
			@RequestParam("percentage") Double percentage, 
			@RequestParam("manager") ManagerDto managerDto, 
			@RequestParam("offering") Offering offering, 
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto) throws IllegalArgumentException {
		return null;
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
