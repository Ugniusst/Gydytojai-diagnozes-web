package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiagnoseService {

    private final DiagnoseRepository diagnoseRepository;

    public DiagnoseService(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }
    Diagnose findDiagnoseById(int id) {
        return diagnoseRepository.findById(id);
    }

    Iterable<Diagnose> findAllDiagnoses() {
        return  diagnoseRepository.findAll();
    }

    Diagnose findDiagnoseByPatientId(Patient patientId) {
        return diagnoseRepository.findByPatientId(patientId);
    }

    Diagnose findDiagnoseByDoctorId(Doctor doctorId) {
        return diagnoseRepository.findByDoctorId(doctorId);

    }

    Diagnose findDiagnoseByDate(Date date) {
        return diagnoseRepository.findByDate(date);
    }

    Diagnose saveDiagnose(Diagnose Diagnose) {
        return diagnoseRepository.save(Diagnose);
    }

    void deleteDiagnoseById(int id) {
        diagnoseRepository.deleteById(id);
    }

    void deleteDiagnose(Diagnose Diagnose) {
        diagnoseRepository.delete(Diagnose);
    }
}
