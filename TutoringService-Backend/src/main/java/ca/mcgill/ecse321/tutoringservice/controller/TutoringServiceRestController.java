package ca.mcgill.ecse321.tutoringservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
