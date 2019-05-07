package tk.kdev.aplikacjebazodanowejavafxspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Specialization;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.SpecializationRepository;

import java.util.Optional;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specialRepo;

    public Optional<Specialization> findSpecializationBySpecialization(String specialization){
        return specialRepo.findSpecializationBySpecialization(specialization);
    }
}
