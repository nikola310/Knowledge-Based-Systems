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

import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.UserServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<UserDTO>> getAll() {

		List<UserDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> get(@PathVariable int id) {
		UserDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> add(@Validated @RequestBody UserDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<UserDTO>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<UserDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<UserDTO>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		UserDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<UserDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<UserDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> edit(@Validated @RequestBody UserDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<UserDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<UserDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UserDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'A') {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		UserDTO dto = service.Delete(id);

		if (dto == null) {
			new ResponseEntity<UserDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

}
