package com.sbnz.controller;

import java.util.List;

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

import com.sbnz.dto.SymptomDTO;
import com.sbnz.interfaces.services.SymptomServiceInterface;

/**
 * 
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/symptom")
public class SymptomController {

	@Autowired
	private SymptomServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<SymptomDTO>> getAll() {

		List<SymptomDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<SymptomDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<SymptomDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymptomDTO> get(@PathVariable int id) {
		SymptomDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<SymptomDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SymptomDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymptomDTO> add(@Validated @RequestBody SymptomDTO body, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<SymptomDTO>(body, HttpStatus.BAD_REQUEST);
		}

		SymptomDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<SymptomDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SymptomDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymptomDTO> edit(@Validated @RequestBody SymptomDTO dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<SymptomDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<SymptomDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<SymptomDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<SymptomDTO> delete(@PathVariable int id) {
		SymptomDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<SymptomDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SymptomDTO>(dto, HttpStatus.OK);
	}
}
