package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Raport;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {
}
