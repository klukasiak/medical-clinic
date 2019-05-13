package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.Raport;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {
}
