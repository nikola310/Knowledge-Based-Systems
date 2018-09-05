package com.sbnz.doctor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.PatientSymptomsDTO;
import com.sbnz.doctor.dto.SymptomDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.doctor.interfaces.services.DiseaseServiceInterface;
import com.sbnz.doctor.interfaces.services.PatientServiceInterface;
import com.sbnz.doctor.interfaces.services.SymptomServiceInterface;
import com.sbnz.doctor.utils.MapUtils;
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
	private SymptomServiceInterface symService;

	@Autowired
	private PatientServiceInterface patientService;

	@Autowired
	private DiseaseServiceInterface diseaseService;

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

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");

		if (user == null) {
			return new ResponseEntity<DiagnosisDTO>(body, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			return new ResponseEntity<DiagnosisDTO>(body, HttpStatus.NOT_ACCEPTABLE);
		}

		body.setUserId(user.getUserId());
		body.setDiagnosisActive(true);
		DiagnosisDTO dto = service.Create(body);

		if (dto == null) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		MyDiagnosisDTO current = (MyDiagnosisDTO) request.getSession().getAttribute("diagnosis");
		current.setPatientId(dto.getPatientId());
		request.getSession().setAttribute("diagnosis", current);
		request.getSession().setAttribute("disease", current.getDiseaseCode());

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
			return new ResponseEntity<List<MyDiagnosisDTO>>(HttpStatus.BAD_REQUEST);
		}

		if (dto.getUserType() != 'D') {
			return new ResponseEntity<List<MyDiagnosisDTO>>(HttpStatus.UNAUTHORIZED);
		}

		List<MyDiagnosisDTO> lista = service.getDiagnoses(dto);
		return new ResponseEntity<List<MyDiagnosisDTO>>(lista, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<MyDiagnosisDTO> getMostLikelyDisease(@RequestBody PatientSymptomsDTO dto,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (user == null) {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}

		if (user.getUserType() != 'D') {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.FORBIDDEN);
		}

		if (service.hadFever(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("PRG60");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasHipertension(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("HIPT6");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasDiabetes(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("DIANW");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasHighTem(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("TEM14");
			dto.getSymptoms().add(newSym);
		}
		if (service.receivedAntibiotics(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("ANB21");
			dto.getSymptoms().add(newSym);
		}
		for (SymptomDTO sym : dto.getSymptoms()) {
			System.out.println(sym.getSymCode());
		}
		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");

		KieSession sesija = sesije.get("rulesSession");
		SymptomList sl = new SymptomList(dto.getSymptoms());
		sesija.insert(sl);
		sesija.getAgenda().getAgendaGroup("Diseases").setFocus();

		int fired = sesija.fireAllRules();
		System.out.println(fired);
		MyDiagnosisDTO retVal = new MyDiagnosisDTO();

		if (sl.getMostLikelyDisease().size() == 0) {
			retVal.setSuccess(false);
			return new ResponseEntity<MyDiagnosisDTO>(retVal, HttpStatus.OK);
		}

		HashMap<String, Double> sortedMap = (HashMap<String, Double>) MapUtils
				.sortByComparator(sl.getMostLikelyDisease(), MapUtils.DESC);

		MapUtils.printMap(sortedMap);

		Map.Entry<String, Double> entry = sortedMap.entrySet().iterator().next();
		String key = entry.getKey();
		if (key == null) {
			retVal.setSuccess(false);
			return new ResponseEntity<MyDiagnosisDTO>(retVal, HttpStatus.OK);
		}
		retVal.setSuccess(true);
		retVal.setDiseaseCode(key);
		retVal.setPatientId(dto.getPatientId());
		retVal.setDate(new Date());
		PatientDTO pacijent = patientService.Read(dto.getPatientId());
		retVal.setPatient(pacijent.getPatientName() + " " + pacijent.getPatientSurname());
		DiseaseDTO bolest = diseaseService.getByCode(key);
		retVal.setDisease(bolest.getDiseaseName());
		retVal.setDiseaseId(bolest.getDiseaseId());
		request.getSession().setAttribute("diagnosis", retVal);
		// System.out.println("dijagnoza: " + retVal.getDiseaseCode());
		return new ResponseEntity<MyDiagnosisDTO>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<MyDiagnosisDTO> getCurrentDiagnosis(@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (user == null) {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}
		if (user.getUserType() != 'D') {
			new ResponseEntity<MyDiagnosisDTO>(HttpStatus.FORBIDDEN);
		}
		MyDiagnosisDTO current = (MyDiagnosisDTO) request.getSession().getAttribute("diagnosis");
		if (current == null) {
			return new ResponseEntity<MyDiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MyDiagnosisDTO>(current, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/process/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Map<String, Double>> getDiseaseList(@RequestBody PatientSymptomsDTO dto,
			@Context HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (user == null) {
			new ResponseEntity<Map<String, Double>>(HttpStatus.BAD_REQUEST);
		}
		if (user.getUserType() != 'D') {
			new ResponseEntity<Map<String, Double>>(HttpStatus.FORBIDDEN);
		}
		if (service.hadFever(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("PRG60");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasHipertension(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("HIPT6");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasDiabetes(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("DIANW");
			dto.getSymptoms().add(newSym);
		}
		if (service.hasHighTem(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("TEM14");
			dto.getSymptoms().add(newSym);
		}
		if (service.receivedAntibiotics(dto.getPatientId())) {
			SymptomDTO newSym = symService.getByCode("ANB21");
			dto.getSymptoms().add(newSym);
		}

//		for (SymptomDTO tmp : dto.getSymptoms()) {
//			System.out.println(tmp.getSymCode());
//		}

		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");
		KieSession sesija = sesije.get("countSession");
		SymptomList sl = new SymptomList(dto.getSymptoms());
		sesija.insert(sl);
		sesija.getAgenda().getAgendaGroup("Count diseases").setFocus();
		sesija.fireAllRules();
		HashMap<String, Double> retVal = new HashMap<>();
		if (sl.getMostLikelyDisease().size() == 0) {
			return new ResponseEntity<Map<String, Double>>(HttpStatus.BAD_REQUEST);
		}

		HashMap<String, Double> sortedMap = (HashMap<String, Double>) MapUtils
				.sortByComparator(sl.getMostLikelyDisease(), MapUtils.DESC);

		// MapUtils.printMap(sortedMap);

		for (String key : sortedMap.keySet()) {
			if (sortedMap.get(key) != 0.0) {
				DiseaseDTO bolest = diseaseService.getByCode(key);
				retVal.put(bolest.getDiseaseName(), sortedMap.get(key));
			}
		}
		Map<String, Double> sortedRetVal = MapUtils.sortByComparator(retVal, MapUtils.DESC);
		return new ResponseEntity<Map<String, Double>>(sortedRetVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/process/mine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public ResponseEntity<DiagnosisDTO> setMine(@RequestParam("patientId") long patientId,
			@RequestParam("diseaseCode") String diseaseCode, @Context HttpServletRequest request) {

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (user == null) {
			new ResponseEntity<DiagnosisDTO>(HttpStatus.BAD_REQUEST);
		}
		if (user.getUserType() != 'D') {
			new ResponseEntity<DiagnosisDTO>(HttpStatus.FORBIDDEN);
		}

		DiseaseDTO bolest = diseaseService.getByCode(diseaseCode);
		DiagnosisDTO toSave = new DiagnosisDTO(0, new Date(), bolest.getDiseaseId(), patientId, user.getUserId());
		toSave.setDiagnosisActive(true);
		DiagnosisDTO dto = service.Create(toSave);
		if (dto == null) {
			return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.BAD_REQUEST);
		}
		MyDiagnosisDTO serverSave = new MyDiagnosisDTO(dto);
		serverSave.setSuccess(true);
		request.getSession().setAttribute("disease", diseaseCode);
		request.getSession().setAttribute("diagnosis", serverSave);
		return new ResponseEntity<DiagnosisDTO>(dto, HttpStatus.OK);
	}

}
