package com.Gydytojaidiagnozes.web.repository;

import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findById(int id);

    Iterable<Patient> findAll();

    Patient findByTelephoneNumber(String telephoneNumber);

    Patient findByName(String name);

    Patient save(Patient Patient);

    void deleteById(int id);

    void delete(Patient Patient);
}
