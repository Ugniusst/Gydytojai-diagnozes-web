package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DiagnoseService {

    @Autowired
    DiagnoseRepository diagnoseRepository;

    public Diagnose findDiagnoseById(int id) {
        return diagnoseRepository.findById(id);
    }

    public Iterable<Diagnose> findAllDiagnoses() {
        return  diagnoseRepository.findAll();
    }

    public Iterable<Diagnose> findDiagnoseByPatientId(Patient patientId) {
        return diagnoseRepository.findByPatientId(patientId);
    }

    public Iterable<Diagnose> findDiagnoseByDoctorId(Doctor doctorId) {
        return diagnoseRepository.findByDoctorId(doctorId);

    }

    public Iterable<Diagnose> findDiagnoseByDate(String date) {
        return diagnoseRepository.findByDate(date);
    }

    public Diagnose saveDiagnose(Diagnose Diagnose) {
        return diagnoseRepository.save(Diagnose);
    }

    public void updateDiagnose(Diagnose diagnose) {
        Diagnose updatable = diagnoseRepository.findById(diagnose.getId());
        if(updatable != null) {
            updatable.setDiagnoseText(diagnose.getDiagnoseText());
            updatable.setDate(diagnose.getDate());
            updatable.setDoctorId(diagnose.getDoctorId());
            updatable.setPatientId(diagnose.getPatientId());
            diagnoseRepository.save(updatable);
        }
        else {
            diagnoseRepository.save(diagnose);
        }
    }

    public void deleteDiagnoseById(int id) {
        diagnoseRepository.deleteById(id);
    }

    public void deleteDiagnose(Diagnose Diagnose) {
        diagnoseRepository.delete(Diagnose);
    }
}
