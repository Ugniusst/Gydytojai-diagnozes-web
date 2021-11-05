package com.Gydytojaidiagnozes.web.repository;

import com.Gydytojaidiagnozes.web.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
    Doctor findById(int id);

    Iterable<Doctor> findAll();

    Doctor findByTelephoneNumber(String telephoneNumber);

    Doctor findByName(String name);

    Doctor save(Doctor doctor);

    void deleteById(int id);

    void delete(Doctor doctor);

}
