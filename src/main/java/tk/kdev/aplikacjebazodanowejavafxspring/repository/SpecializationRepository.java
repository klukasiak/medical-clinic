package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Specialization;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    public Optional<Specialization> findSpecializationBySpecialization(String specialization);
}
