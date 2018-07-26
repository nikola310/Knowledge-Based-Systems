package com.sbnz.doctor.controller;

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

import com.sbnz.doctor.dto.MedicineDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.MedicineServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {

	@Autowired
	private MedicineServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<MedicineDTO>> getAll() {

		List<MedicineDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<MedicineDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<MedicineDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineDTO> get(@PathVariable int id) {
		MedicineDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<MedicineDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MedicineDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineDTO> add(@Validated @RequestBody MedicineDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<MedicineDTO>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<MedicineDTO>(body, HttpStatus.FORBIDDEN);
		}

		MedicineDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<MedicineDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MedicineDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineDTO> edit(@Validated @RequestBody MedicineDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<MedicineDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<MedicineDTO>(dto, HttpStatus.FORBIDDEN);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<MedicineDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<MedicineDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MedicineDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<MedicineDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<MedicineDTO>(HttpStatus.FORBIDDEN);
		}

		MedicineDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<MedicineDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MedicineDTO>(dto, HttpStatus.OK);
	}
}
