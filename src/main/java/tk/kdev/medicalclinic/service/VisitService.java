package tk.kdev.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;
import tk.kdev.medicalclinic.repository.VisitRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllByPatientId(Long id){
        return visitRepository.getAllByPatientId(id);
    }

    public List<Visit> getAllByDateAndDoctor(User user, LocalDate visitDate){
        return visitRepository.getAllByDoctorAndVisitDate(user, visitDate);
    }

    public void addVisit(Visit visit){
        visitRepository.save(visit);
    }
}
