package com.sbnz.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.PatientServiceInterface;
import com.sbnz.doctor.monitoring.Heartbeat;
import com.sbnz.doctor.monitoring.PatientModel;

/**
 * 
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/patient")
public class PatientController {

	@Autowired
	private PatientServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<PatientDTO>> getAll() {

		List<PatientDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<PatientDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<PatientDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PatientDTO> get(@PathVariable int id) {
		PatientDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<PatientDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PatientDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PatientDTO> add(@Validated @RequestBody PatientDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<PatientDTO>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<PatientDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<PatientDTO>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		body.setUserId(user.getUserId());

		PatientDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<PatientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<PatientDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PatientDTO> edit(@Validated @RequestBody PatientDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<PatientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<PatientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<PatientDTO>(dto, HttpStatus.NOT_ACCEPTABLE);
		}

		dto.setUserId(user.getUserId());

		if (service.Update(dto) == null) {
			return new ResponseEntity<PatientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<PatientDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PatientDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<PatientDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<PatientDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		PatientDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<PatientDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PatientDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/proba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> proba(@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		@SuppressWarnings("unchecked")
		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");

		KieSession sesija = sesije.get("monitoringSession");
		PatientModel pm = new PatientModel(service.Read(1));
		sesija.insert(pm);
		SessionPseudoClock clock = sesija.getSessionClock();
		for (int i = 0; i < 40; i++) {
			sesija.insert(new Heartbeat(pm.getPatientId()));
		}
		clock.advanceTime(9, TimeUnit.SECONDS);
		sesija.getAgenda().getAgendaGroup("Heartbeat").setFocus();
		int fired = sesija.fireAllRules();
		System.out.println(fired);
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
}
