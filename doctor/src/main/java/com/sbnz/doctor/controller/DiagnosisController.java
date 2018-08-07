package com.sbnz.doctor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieContainer;
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

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.SymptomDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.doctor.interfaces.services.DiseaseServiceInterface;
import com.sbnz.doctor.utils.SymptomList;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/diagnosis")
public class DiagnosisController {

	@Autowired
	private DiagnosisServiceInterface service;

	@Autowired
	private DiseaseServiceInterface diseaseService;

	@Autowired
	private KieContainer container;

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
	public ResponseEntity<DiagnosisDTO> add(@Validated @RequestBody DiagnosisDTO body, Errors errors,
			@Context HttpServletRequest request) {
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
	public ResponseEntity<DiagnosisDTO> edit(@Validated @RequestBody DiagnosisDTO dto, Errors errors,
			@Context HttpServletRequest request) {
		if (errors.hasErrors()) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		if (service.Update(dto) == null) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<DiagnosisDTO> delete(@PathVariable int id, @Context HttpServletRequest request) {
		DiagnosisDTO dto = service.Delete(id);
		if (dto == null) {
			new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/mine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<MyDiagnosisDTO>> getMine(@Context HttpServletRequest request) {
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		if (dto == null) {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}

		if (dto.getUserType() != 'D') {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.FORBIDDEN);
		}

		List<MyDiagnosisDTO> lista = service.getDiagnoses(dto);
		return new ResponseEntity<List<MyDiagnosisDTO>>(lista, HttpStatus.OK);
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<MyDiagnosisDTO> getMostLikelyDisease(@RequestBody List<SymptomDTO> symptoms,
			@Context HttpServletRequest request) {
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		if (dto == null) {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}

		if (dto.getUserType() != 'D') {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.FORBIDDEN);
		}

//		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");

		KieSession sesija = container.newKieSession("rulesSession");// sesije.get("proba");

		SymptomList sl = new SymptomList((ArrayList<SymptomDTO>) symptoms);
		sesija.insert(sl);
		int fired = sesija.fireAllRules();
		System.out.println(fired);
		sesija.getAgenda().getAgendaGroup("Disease group 1").setFocus();
		fired = sesija.fireAllRules();
		double max = 0;
		String code = "";
		if (sl.getMostLikelyDisease().size() > 0) {
			for (String key : sl.getMostLikelyDisease().keySet()) {
				if (sl.getMostLikelyDisease().get(key) > max) {
					max = sl.getMostLikelyDisease().get(key);
					code = key;
				}
			}
		}

		DiseaseDTO retVal = diseaseService.getByCode(code);
		MyDiagnosisDTO myDto = new MyDiagnosisDTO();
		myDto.setDisease(retVal.getDiseaseName());
		myDto.setDiseaseCode(retVal.getDiseaseCode());
		myDto.setDiseaseId(retVal.getDiseaseId());

		return new ResponseEntity<MyDiagnosisDTO>(myDto, HttpStatus.OK);
	}
}
