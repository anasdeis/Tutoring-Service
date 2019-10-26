package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
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


	@PostMapping(value = { "/events/{name}", "/events/{name}/" })
	public SubjectDto createSubject(@PathVariable("name") String name, 
			@RequestParam("courseID") String courseID, 
			@RequestParam("description") String description, 
			@RequestParam("tutoringSystem") TutoringSystemDto tutoringSystemDto)
					throws IllegalArgumentException {
		TutoringSystem tutoringSystem = service.getTutoringSystem(tutoringSystemDto.getTutoringSystemID());
		Subject subject = service.createSubject(name, courseID, description, tutoringSystem);
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
