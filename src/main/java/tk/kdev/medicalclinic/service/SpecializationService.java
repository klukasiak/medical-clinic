package tk.kdev.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.medicalclinic.model.Specialization;
import tk.kdev.medicalclinic.repository.SpecializationRepository;

import java.util.Optional;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specialRepo;

    public Optional<Specialization> findSpecializationBySpecialization(String specialization){
        return specialRepo.findSpecializationBySpecialization(specialization);
    }
}
