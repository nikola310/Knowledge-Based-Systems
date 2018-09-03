package com.sbnz.doctor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
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

import com.sbnz.doctor.dto.AllergicRetValDTO;
import com.sbnz.doctor.dto.AllergyDTO;
import com.sbnz.doctor.dto.MedicineallergyDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.AllergyServiceInterface;
import com.sbnz.doctor.interfaces.services.DiseaseServiceInterface;
import com.sbnz.doctor.interfaces.services.MedicineAllergyServiceInterface;
import com.sbnz.doctor.interfaces.services.TherapyServiceInterface;
import com.sbnz.doctor.utils.CheckAllergyObject;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/therapy")
public class TherapyController {

	@Autowired
	private TherapyServiceInterface service;

	@Autowired
	private AllergyServiceInterface allergyService;

	@Autowired
	private MedicineAllergyServiceInterface medAllergyService;

	@Autowired
	private DiseaseServiceInterface diseaseService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<TherapyDTO>> getAll() {

		List<TherapyDTO> dtos = service.ReadAll();

		if (dtos == null) {
			return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> get(@PathVariable int id) {
		TherapyDTO dto = service.Read(id);

		if (dto == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> add(@Validated @RequestBody TherapyDTO body, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<TherapyDTO>(body, HttpStatus.BAD_REQUEST);
		}

		TherapyDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> edit(@Validated @RequestBody TherapyDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (errors.hasErrors()) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<TherapyDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<TherapyDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<TherapyDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<TherapyDTO>(HttpStatus.NOT_ACCEPTABLE);
		}

		TherapyDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<TherapyDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TherapyDTO>(dto, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<AllergicRetValDTO> checkForAllergies(@RequestBody ArrayList<Long> lekovi,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<AllergicRetValDTO>(HttpStatus.BAD_REQUEST);
		}
		if (user.getUserType() != 'D') {
			return new ResponseEntity<AllergicRetValDTO>(HttpStatus.NOT_ACCEPTABLE);
		}
		MyDiagnosisDTO current = (MyDiagnosisDTO) request.getSession().getAttribute("diagnosis");
		if (current == null) {
			return new ResponseEntity<AllergicRetValDTO>(HttpStatus.BAD_REQUEST);
		}

		CheckAllergyObject cao = new CheckAllergyObject();
		for (Long med : lekovi) {
			cao.getMedAllergies().addAll(medAllergyService.getByMedicine(med));
			cao.getAllergies().addAll(allergyService.getByMedicine(med));
		}
		cao.setPatientId(current.getPatientId());

		for (MedicineallergyDTO med : cao.getMedAllergies()) {
			System.out.println(med.getMedicine());
		}
		for (AllergyDTO alergy : cao.getAllergies()) {
			System.out.println(alergy.getIngredientName());
		}
		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");
		KieSession sesija = sesije.get("patientSession");
		System.out.println(cao.getMedAllergies().size());
		System.out.println(cao.getAllergies().size());
		sesija.insert(cao);
		sesija.getAgenda().getAgendaGroup("Allergy").setFocus();
		int fired = sesija.fireAllRules();
		System.out.println("Fired: " + fired);
		System.out.println(cao.getIsAllergic().size());
		System.out.println(cao.getIsMedAllergic().size());
		AllergicRetValDTO retVal = new AllergicRetValDTO();
		for (AllergyDTO al : cao.getIsAllergic()) {
			retVal.getIngredients().add(al.getIngredientName());
		}
		for (MedicineallergyDTO medAl : cao.getIsMedAllergic()) {
			retVal.getMedicine().add(medAl.getMedicine());
		}
		return new ResponseEntity<AllergicRetValDTO>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<TherapyDTO>> addInBulk(@RequestBody List<TherapyDTO> body, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<List<TherapyDTO>>(body, HttpStatus.BAD_REQUEST);
		}

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<TherapyDTO>>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<TherapyDTO>>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		String code = (String) request.getSession().getAttribute("disease");

		if (code == null) {
			return new ResponseEntity<List<TherapyDTO>>(body, HttpStatus.BAD_REQUEST);
		}

		for (TherapyDTO therapyDTO : body) {
			therapyDTO.setUserId(user.getUserId());
			therapyDTO.setDiseaseId(diseaseService.getByCode(code).getDiseaseId());
			therapyDTO.setDiseaseCode(code);
		}

		List<TherapyDTO> dtos = service.addInBulk(body);

		if (dtos == null) {
			return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/mine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<TherapyDTO>> getMine(@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<List<TherapyDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<List<TherapyDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List<TherapyDTO> dtos = service.readByUser(user.getUserId());

		if (dtos == null) {
			return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<TherapyDTO>>(dtos, HttpStatus.OK);
	}
}
