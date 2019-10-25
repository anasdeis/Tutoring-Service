package ca.mcgill.ecse321.tutoringservice.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * @sample /tutor/personId=5
	 */
	
	@PostMapping(value = { "/tutor/{personId}", "/tutor/{personId}/" })
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
	 * @sample /tutor/isRegistered=true
	 */
	@GetMapping(value = { "/tutor/{isRegistered}", "/tutor/{isRegistered}/" })
	public List<TutorDto> getAllRegisteredTutors(@PathVariable("isRegistered") Boolean isRegistered) {
		List<TutorDto> tutorDtos = new ArrayList<>();
		for (Tutor tutor : service.getAllTutors()) {
			if(tutor.getIsRegistered().booleanValue() == isRegistered)
				tutorDtos.add(convertToDto(tutor));
		}
		return tutorDtos;
	}
	
	/*
	 * @return create tutoring system
	 * @sample /tutoringSystem/tutoringSystemId=3
	 */
	
	@PostMapping(value = { "/tutoringSystem/{tutoringSystemId}", "/tutoringSystem/{tutoringSystemId}/" })
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
