package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
