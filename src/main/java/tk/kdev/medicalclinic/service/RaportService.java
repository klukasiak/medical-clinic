package tk.kdev.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.medicalclinic.model.Raport;
import tk.kdev.medicalclinic.repository.RaportRepository;

import java.util.List;

@Service
public class RaportService {

    @Autowired
    private RaportRepository raportRepo;

    public List<Raport> getAllRaportsByUserId(Long id) {
        return raportRepo.getAllByUserId(id);
    }

    public void addRaport(Raport raport){
        raportRepo.save(raport);
    }

    public void deleteRaport(Raport raport){
        raportRepo.delete(raport);
    }
}
