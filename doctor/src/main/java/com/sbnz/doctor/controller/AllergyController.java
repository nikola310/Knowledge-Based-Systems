package com.sbnz.doctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.AllergyDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.AllergyServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/allergy")
public class AllergyController {

	@Autowired
	private AllergyServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<AllergyDTO>> getAll() {

		List<AllergyDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergyDTO> get(@PathVariable int id) {
		AllergyDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<AllergyDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AllergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergyDTO> add(@RequestBody AllergyDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<AllergyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<AllergyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<AllergyDTO>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		AllergyDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<AllergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<AllergyDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergyDTO> edit(@RequestBody AllergyDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<AllergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<AllergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<AllergyDTO>(dto, HttpStatus.NOT_ACCEPTABLE);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<AllergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<AllergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergyDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<AllergyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<AllergyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		AllergyDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<AllergyDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AllergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingr/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<AllergyDTO>> getByIngr(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<AllergyDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<AllergyDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List<AllergyDTO> dtos = service.getByIngredient(id);

		if (dtos == null) {
			return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<AllergyDTO>> getByPatient(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<AllergyDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<AllergyDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List<AllergyDTO> dtos = service.getByPatient(id);

		if (dtos == null) {
			return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<AllergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergyDTO[]> addInBulk(@RequestBody AllergyDTO[] body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<AllergyDTO[]>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<AllergyDTO[]>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<AllergyDTO[]>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		AllergyDTO[] dtos = service.addInBulk(body);

		if (dtos == null) {
			return new ResponseEntity<AllergyDTO[]>(dtos, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<AllergyDTO[]>(dtos, HttpStatus.CREATED);
	}

}
