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

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.DiseaseServiceInterface;

/**
 * 
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/disease")
public class DiseaseController {

	@Autowired
	private DiseaseServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DiseaseDTO>> getAll() {

		List<DiseaseDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<DiseaseDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<DiseaseDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiseaseDTO> get(@PathVariable int id) {
		DiseaseDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<DiseaseDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiseaseDTO> add(@Validated @RequestBody DiseaseDTO body, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<DiseaseDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<DiseaseDTO>(body, HttpStatus.FORBIDDEN);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<DiseaseDTO>(body, HttpStatus.BAD_REQUEST);
		}

		DiseaseDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiseaseDTO> edit(@Validated @RequestBody DiseaseDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.FORBIDDEN);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiseaseDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<DiseaseDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<DiseaseDTO>(HttpStatus.FORBIDDEN);
		}

		DiseaseDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<DiseaseDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DiseaseDTO>(dto, HttpStatus.OK);
	}
}
