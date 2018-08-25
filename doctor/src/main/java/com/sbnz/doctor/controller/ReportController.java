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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.doctor.dto.DiseaseDTO;
import com.sbnz.doctor.dto.PatientDTO;
import com.sbnz.doctor.dto.ReportDiagnosis;
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
	public ResponseEntity<List<PatientDTO>> getChronic(@Context HttpServletRequest request) {
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		if (dto == null) {
			new ResponseEntity<PatientDTO>(HttpStatus.BAD_REQUEST);
		}

		if (dto.getUserType() != 'D') {
			new ResponseEntity<PatientDTO>(HttpStatus.FORBIDDEN);
		}

		ArrayList<ReportDiagnosis> reports = (ArrayList<ReportDiagnosis>) diagService.getReportDiagnoses();
		ReportEntity re = new ReportEntity();
		re.setDiagnoses(reports);
		re.setPatients((ArrayList<PatientDTO>) patientService.ReadAll());
		re.setDiseases((ArrayList<DiseaseDTO>) diseaseService.ReadAll());
		System.out.println("diags: " + re.getDiagnoses().size() + ", pats: " + re.getPatients().size() + ", dis: "
				+ re.getDiseases().size());

		@SuppressWarnings("unchecked")
		HashMap<String, KieSession> sesije = (HashMap<String, KieSession>) request.getSession().getAttribute("sesije");

		KieSession sesija = sesije.get("reportSession");
		sesija.insert(re);
		sesija.getAgenda().getAgendaGroup("Chronic").setFocus();
		int fired = sesija.fireAllRules();
		System.out.println(fired);
		ArrayList<PatientDTO> pacijenti = new ArrayList<>();
		for (Long pacijent : re.getZavisnici()) {
			pacijenti.add(patientService.Read(pacijent));
		}
		return new ResponseEntity<List<PatientDTO>>(pacijenti, HttpStatus.OK);
	}
}
