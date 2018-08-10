package com.sbnz.doctor.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.doctor.interfaces.services.DiseaseServiceInterface;
import com.sbnz.doctor.interfaces.services.PatientServiceInterface;
import com.sbnz.doctor.utils.ReportEntity;

/**
 * @author Nikola
 *
 */
@RestController
@RequestMapping(value = "/report")
public class ReportController {

	@Autowired
	private DiseaseServiceInterface diseaseService;

	@Autowired
	private PatientServiceInterface patientService;

	@Autowired
	private DiagnosisServiceInterface diagService;

	@RequestMapping(value = "/chronic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Long>> getChronic(@Context HttpServletRequest request) {
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		if (dto == null) {
			new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}

		if (dto.getUserType() != 'D') {
			new ResponseEntity<Long>(HttpStatus.FORBIDDEN);
		}

		@SuppressWarnings("unchecked")
		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");

		KieSession sesija = sesije.get("proba2"); // container.newKieSession("rulesSession");
		
		ReportEntity rep = new ReportEntity();
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		Date twoYears = cal.getTime();
		rep.setNow(today);
		rep.setYearLimit(twoYears);
		System.out.println(today);
		System.out.println(twoYears);
		System.out.println(today.getTime() > twoYears.getTime());
		rep.setDiagnoses((ArrayList<DiagnosisDTO>) diagService.ReadAll());
		rep.setDiseases((ArrayList<DiseaseDTO>) diseaseService.ReadAll());
		rep.setPatients((ArrayList<PatientDTO>) patientService.ReadAll());
		sesija.insert(rep);
		sesija.getAgenda().getAgendaGroup("Chronic report").setFocus();
		int fired = sesija.fireAllRules();
		System.out.println(fired);
		System.out.println(rep.getZavisnici().size());
		return new ResponseEntity<List<Long>>(rep.getZavisnici(), HttpStatus.OK);
	}
}
