package tk.kdev.aplikacjebazodanowejavafxspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findRoleByRole(String role);
}
