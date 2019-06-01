package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.model.Visit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> getAllByPatientId(Long id);
    List<Visit> getAllByDoctorAndVisitDate(User user, LocalDate visitDate);
    Optional<Visit> getVisitByDoctorAndVisitDateAndVisitTime(User doctor, LocalDate visitDate, LocalTime visitTime);
}
