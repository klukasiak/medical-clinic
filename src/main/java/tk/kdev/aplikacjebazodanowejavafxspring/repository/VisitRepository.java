package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
