package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> getAllByPatientId(Long id);
    List<Visit> getAllByDoctorAndVisitDate(User user, LocalDate visitDate);
}
