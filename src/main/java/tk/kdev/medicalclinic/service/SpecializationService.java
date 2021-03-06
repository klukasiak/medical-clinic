package tk.kdev.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.medicalclinic.model.Specialization;
import tk.kdev.medicalclinic.repository.SpecializationRepository;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specialRepo;

    public Specialization findSpecializationBySpecialization(String specialization) {
        return specialRepo.findSpecializationBySpecialization(specialization);
    }

    public List<Specialization> getAllSpecializations() {
        return specialRepo.findAll();
    }

    public void addSpecialization(Specialization specialization){
        specialRepo.save(specialization);
    }

    public void deleteSpecialization(Specialization specialization){
        specialRepo.delete(specialization);
    }
}
