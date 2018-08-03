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

import com.sbnz.doctor.dto.IngredientMedDTO;
import com.sbnz.doctor.interfaces.services.IngredientMedServiceInterface;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/ingr-med")
public class IngredientMedController {

	@Autowired
	private IngredientMedServiceInterface service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<IngredientMedDTO>> getAll() {

		List<IngredientMedDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientMedDTO> get(@PathVariable int id) {
		IngredientMedDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<IngredientMedDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientMedDTO> add(@Validated @RequestBody IngredientMedDTO body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<IngredientMedDTO>(body, HttpStatus.BAD_REQUEST);
		}

		IngredientMedDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientMedDTO> edit(@Validated @RequestBody IngredientMedDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<IngredientMedDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		IngredientMedDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<IngredientMedDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/ing/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<IngredientMedDTO>> getByIng(@PathVariable int id) {
		List<IngredientMedDTO> dtos = service.getByIngredient(id);

		if (dtos == null) {
			return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/med/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<IngredientMedDTO>> getByMed(@PathVariable int id) {
		List<IngredientMedDTO> dtos = service.getByMedicine(id);

		if (dtos == null) {
			return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<IngredientMedDTO>>(dtos, HttpStatus.OK);
	}

}
