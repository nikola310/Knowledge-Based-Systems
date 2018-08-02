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

import com.sbnz.doctor.dto.IngredientDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.IngredientServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/ingredient")
public class IngredientController {

	@Autowired
	private IngredientServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<IngredientDTO>> getAll() {

		List<IngredientDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<IngredientDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<IngredientDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientDTO> get(@PathVariable int id) {
		IngredientDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<IngredientDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<IngredientDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientDTO> add(@Validated @RequestBody IngredientDTO body, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<IngredientDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<IngredientDTO>(body, HttpStatus.FORBIDDEN);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<IngredientDTO>(body, HttpStatus.BAD_REQUEST);
		}

		IngredientDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<IngredientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IngredientDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientDTO> edit(@Validated @RequestBody IngredientDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<IngredientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<IngredientDTO>(dto, HttpStatus.FORBIDDEN);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<IngredientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<IngredientDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IngredientDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<IngredientDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<IngredientDTO>(HttpStatus.FORBIDDEN);
		}

		IngredientDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<IngredientDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<IngredientDTO>(dto, HttpStatus.OK);
	}
}
