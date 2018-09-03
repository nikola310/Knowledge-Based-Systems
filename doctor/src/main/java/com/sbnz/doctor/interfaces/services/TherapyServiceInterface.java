package com.sbnz.doctor.interfaces.services;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.doctor.dto.TherapyDTO;
import com.sbnz.doctor.dto.TherapyReport;

public interface TherapyServiceInterface extends ServiceInterface<TherapyDTO> {

	public List<TherapyDTO> addInBulk(List<TherapyDTO> dtos);

	public ArrayList<TherapyReport> getInLastSixMonths();

	public ArrayList<TherapyReport> getTherapyReport();

	public List<TherapyDTO> readByUser(long user);
}
