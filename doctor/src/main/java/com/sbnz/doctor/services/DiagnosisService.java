package com.sbnz.doctor.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbnz.doctor.dto.DiagnosisDTO;
import com.sbnz.doctor.dto.MyDiagnosisDTO;
import com.sbnz.doctor.dto.ReportDiagnosis;
import com.sbnz.doctor.dto.UserDTO;
import com.sbnz.doctor.interfaces.converters.DiagnosisConverterInterface;
import com.sbnz.doctor.interfaces.converters.UserConverterInterface;
import com.sbnz.doctor.interfaces.services.DiagnosisServiceInterface;
import com.sbnz.doctor.model.Diagnosis;
import com.sbnz.doctor.model.Disease;
import com.sbnz.doctor.model.Patient;
import com.sbnz.doctor.model.Symptomdisease;
import com.sbnz.doctor.model.Therapy;
import com.sbnz.doctor.repository.DiagnosisRepository;
import com.sbnz.doctor.repository.DiseaseRepository;
import com.sbnz.doctor.repository.PatientRepository;
import com.sbnz.doctor.repository.SymptomDiseaseRepository;
import com.sbnz.doctor.repository.TherapyRepository;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class DiagnosisService implements DiagnosisServiceInterface {

	@Autowired
	private DiagnosisRepository repository;

	@Autowired
	private DiagnosisConverterInterface converter;

	@Autowired
	private UserConverterInterface userConverter;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private DiseaseRepository diseaseRepo;

	@Autowired
	private SymptomDiseaseRepository symDiseaseRepo;

	@Autowired
	private TherapyRepository therapyRepo;

	@Override
	public DiagnosisDTO Create(DiagnosisDTO dto) {
		try {

			Diagnosis entity = converter.DtoToEntity(dto);
			repository.save(entity);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DiagnosisDTO Read(long id) {
		try {
			return converter.entityToDto(repository.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DiagnosisDTO> ReadAll() {
		ArrayList<DiagnosisDTO> list = new ArrayList<DiagnosisDTO>();

		try {
			for (Diagnosis entity : repository.findAll()) {
				list.add(converter.entityToDto(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	@Override
	public DiagnosisDTO Update(DiagnosisDTO dto) {
		try {

			Diagnosis entity = repository.getOne(dto.getDiagnosisId());

			if (entity != null) {
				entity.setDisease(diseaseRepo.getOne(dto.getDiseaseId()));
				entity.setDiagnosisActive(dto.isDiagnosisActive());
				repository.save(entity);
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public DiagnosisDTO Delete(long id) {
		Diagnosis entity = repository.getOne(id);
		if (entity == null) {
			throw new IllegalArgumentException("Tried to delete non-existing entity");
		}

		repository.delete(entity);

		return converter.entityToDto(entity);
	}

	@Override
	public List<MyDiagnosisDTO> getDiagnoses(UserDTO user) {
		ArrayList<MyDiagnosisDTO> list = new ArrayList<MyDiagnosisDTO>();
		try {
			for (Diagnosis entity : repository.getDiagnosisByUser(userConverter.DtoToEntity(user))) {
				MyDiagnosisDTO tmp = new MyDiagnosisDTO();
				tmp.setDate(entity.getDiagnosisDate());
				tmp.setDiagnosisId(entity.getDiagnosisId());
				tmp.setDisease(entity.getDisease().getDiseaseName());
				Patient p = entity.getPatient();
				tmp.setPatient(p.getPatientName() + " " + p.getPatientSurname());
				list.add(tmp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean hadFever(long patient) {
		ArrayList<Diagnosis> dijagnoze = (ArrayList<Diagnosis>) repository
				.getDiagnosisByPatient(patientRepo.getOne(patient));
		Disease fever = diseaseRepo.getDiseaseByDiseaseCode("GROZN");
		Disease cold = diseaseRepo.getDiseaseByDiseaseCode("PREHL");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -60);
		Date days60 = cal.getTime();
		if (dijagnoze.size() > 0) {
			for (Diagnosis d : dijagnoze) {
				if (d.getDiagnosisDate().getTime() >= days60.getTime()) {
					if (d.getDisease().getDiseaseId() == fever.getDiseaseId()
							|| d.getDisease().getDiseaseId() == cold.getDiseaseId()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasHighTem(long patient) {
		ArrayList<Diagnosis> dijagnoze = (ArrayList<Diagnosis>) repository
				.getDiagnosisByPatient(patientRepo.getOne(patient));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -14);
		Date days14 = cal.getTime();
		if (dijagnoze.size() > 0) {
			for (Diagnosis d : dijagnoze) {
				if (d.getDiagnosisDate().getTime() >= days14.getTime()) {
					List<Symptomdisease> lista = symDiseaseRepo.findByDisease(d.getDisease());
					for (Symptomdisease syd : lista) {
						if (syd.getSymptom().getSymCode().equals("TEM38")
								|| syd.getSymptom().getSymCode().equals("T4041")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean receivedAntibiotics(long patient) {
		ArrayList<Therapy> terapije = (ArrayList<Therapy>) therapyRepo.findByPatient(patientRepo.getOne(patient));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -21);
		Date days21 = cal.getTime();
		if (terapije.size() > 0) {
			for (Therapy t : terapije) {
				if (t.getMedicine().getMedicineType() == 'A' && t.getTherapyDate().getTime() >= days21.getTime()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasHipertension(long patient) {
		ArrayList<Diagnosis> dijagnoze = (ArrayList<Diagnosis>) repository
				.getDiagnosisByPatient(patientRepo.getOne(patient));
		Disease hipte = diseaseRepo.getDiseaseByDiseaseCode("HIPTE");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		Date months6 = cal.getTime();
		if (dijagnoze.size() > 0) {
			for (Diagnosis d : dijagnoze) {
				if (d.getDiagnosisDate().getTime() < months6.getTime() && d.isDiagnosisActive() == true
						&& d.getDisease().getDiseaseId() == hipte.getDiseaseId()) {
					System.out.println("HIPTE IF");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasDiabetes(long patient) {
		ArrayList<Diagnosis> dijagnoze = (ArrayList<Diagnosis>) repository
				.getDiagnosisByPatient(patientRepo.getOne(patient));
		Disease dijbe = diseaseRepo.getDiseaseByDiseaseCode("DIJBE");
		if (dijagnoze.size() > 0) {
			for (Diagnosis d : dijagnoze) {
				if (d.getDisease().getDiseaseId() == dijbe.getDiseaseId() && d.isDiagnosisActive() == true) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<ReportDiagnosis> getReportDiagnoses() {
		ArrayList<ReportDiagnosis> list = new ArrayList<ReportDiagnosis>();
		try {
			for (Diagnosis entity : repository.findAll()) {
				list.add(new ReportDiagnosis(entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

}
