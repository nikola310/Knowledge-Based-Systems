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

import com.sbnz.dto.DiagnosisDTO;
import com.sbnz.interfaces.services.DiagnosisServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/diagnosis")
public class DiagnosisController {

	@Autowired
	private DiagnosisServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DiagnosisDTO>> getAll() {

		List<DiagnosisDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<DiagnosisDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<DiagnosisDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiagnosisDTO> get(@PathVariable int id) {
		DiagnosisDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<DiagnosisDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiagnosisDTO> add(@Validated @RequestBody DiagnosisDTO body, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<DiagnosisDTO>(body, HttpStatus.BAD_REQUEST);
		}

		DiagnosisDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiagnosisDTO> edit(@Validated @RequestBody DiagnosisDTO dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiagnosisDTO> delete(@PathVariable int id) {
		DiagnosisDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}
}
