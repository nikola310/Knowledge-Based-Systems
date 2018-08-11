package com.sbnz.doctor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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

import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.TherapyServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/therapy")
public class TherapyController {

	@Autowired
	private TherapyServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<TherapyDTO>> getAll() {

		List<TherapyDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> get(@PathVariable int id) {
		TherapyDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> add(@Validated @RequestBody TherapyDTO body, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<TherapyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		TherapyDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> edit(@Validated @RequestBody TherapyDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		TherapyDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<TherapyDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> checkForAllergies(@RequestBody ArrayList<TherapyDTO> lekovi,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
	}
}
