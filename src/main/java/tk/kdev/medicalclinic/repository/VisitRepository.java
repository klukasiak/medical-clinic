package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
