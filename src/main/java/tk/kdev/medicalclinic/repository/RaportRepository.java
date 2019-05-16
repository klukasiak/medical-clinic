package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.Raport;

import java.util.List;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {
    List<Raport> getAllByUserId(Long id);
}
