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

import com.sbnz.doctor.dto.MedicineallergyDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.MedicineAllergyServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/med-allergy")
public class MedicineAllergyController {

	@Autowired
	private MedicineAllergyServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<MedicineallergyDTO>> getAll() {

		List<MedicineallergyDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineallergyDTO> get(@PathVariable int id) {
		MedicineallergyDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<MedicineallergyDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineallergyDTO> add(@RequestBody MedicineallergyDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<MedicineallergyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineallergyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<MedicineallergyDTO>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		MedicineallergyDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineallergyDTO> edit(@RequestBody MedicineallergyDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.NOT_ACCEPTABLE);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineallergyDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineallergyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<MedicineallergyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		MedicineallergyDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MedicineallergyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/med/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<MedicineallergyDTO>> getByIngr(@PathVariable int id,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<MedicineallergyDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<MedicineallergyDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List<MedicineallergyDTO> dtos = service.getByMedicine(id);

		if (dtos == null) {
			return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<MedicineallergyDTO>> getByPatient(@PathVariable int id,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<MedicineallergyDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<MedicineallergyDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List<MedicineallergyDTO> dtos = service.getByPatient(id);

		if (dtos == null) {
			return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<MedicineallergyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineallergyDTO[]> addInBulk(@RequestBody MedicineallergyDTO[] body, Errors errors,
			@Context HttpServletRequest request) {

		if (errors.hasErrors()) {
			return new ResponseEntity<MedicineallergyDTO[]>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineallergyDTO[]>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<MedicineallergyDTO[]>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		MedicineallergyDTO[] dtos = service.addInBulk(body);

		if (dtos == null) {
			return new ResponseEntity<MedicineallergyDTO[]>(dtos, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MedicineallergyDTO[]>(dtos, HttpStatus.CREATED);
	}

}
