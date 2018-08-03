/**
 * 
 */
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

import com.sbnz.doctor.dto.SymDiseaseDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.SymDiseaseServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/sym-disease")
public class SymDiseaseController {

	@Autowired
	private SymDiseaseServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<SymDiseaseDTO>> getAll() {

		List<SymDiseaseDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymDiseaseDTO> get(@PathVariable int id) {
		SymDiseaseDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymDiseaseDTO> add(@Validated @RequestBody SymDiseaseDTO body, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<SymDiseaseDTO>(body, HttpStatus.BAD_REQUEST);
		}

		SymDiseaseDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymDiseaseDTO> edit(@Validated @RequestBody SymDiseaseDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymDiseaseDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<SymDiseaseDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		SymDiseaseDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SymDiseaseDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/sym/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<SymDiseaseDTO>> getBySym(@PathVariable int id) {
		List<SymDiseaseDTO> dtos = service.getBySymptom(id);

		if (dtos == null) {
			return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/dis/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<SymDiseaseDTO>> getByDis(@PathVariable int id) {
		List<SymDiseaseDTO> dtos = service.getByDisease(id);

		if (dtos == null) {
			return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<SymDiseaseDTO>>(dtos, HttpStatus.OK);
	}

}
