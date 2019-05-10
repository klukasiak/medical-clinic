package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Raport;

public interface RaportRepository extends JpaRepository<Raport, Long> {
}
