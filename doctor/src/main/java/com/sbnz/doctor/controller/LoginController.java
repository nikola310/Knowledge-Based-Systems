package com.sbnz.doctor.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.LoginDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.UserServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
public class LoginController {

	@Autowired
	private UserServiceInterface service;

	@Autowired
	private KieContainer container;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<LoginDTO> login(@Validated @RequestBody LoginDTO body, @Context HttpServletRequest request,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<LoginDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (service == null) {
			System.out.println("service null");
		}

		UserDTO dto = service.getUserByUsername(body.getUsername());

		if (dto == null) {
			return new ResponseEntity<LoginDTO>(body, HttpStatus.NOT_FOUND);
		}

		if (!dto.getUserPassword().equals(body.getPassword())) {
			return new ResponseEntity<LoginDTO>(body, HttpStatus.BAD_REQUEST);
		}

		request.getSession().setAttribute("user", dto);

		KieSession ks = container.newKieSession("rulesSession");
		KieSession ks2 = container.newKieSession("patientSession");
		KieSession ks3 = container.newKieSession("countSession");
		KieSession ks4 = container.newKieSession("reportSession");
		KieSession ks5 = container.newKieSession("monitoringSession");
		HashMap<String, KieSession> sesije = new HashMap<>();
		sesije.put("rulesSession", ks);
		sesije.put("patientSession", ks2);
		sesije.put("countSession", ks3);
		sesije.put("reportSession", ks4);
		sesije.put("monitoringSession", ks5);
		request.getSession().setAttribute("sesije", sesije);
		body.setType(dto.getUserType());
		return new ResponseEntity<LoginDTO>(body, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> logout(@Context HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.BAD_REQUEST);
		}

		request.getSession().invalidate();

		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
}
