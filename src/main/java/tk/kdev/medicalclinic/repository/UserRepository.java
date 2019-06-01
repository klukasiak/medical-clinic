package tk.kdev.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.kdev.medicalclinic.model.Role;
import tk.kdev.medicalclinic.model.Specialization;
import tk.kdev.medicalclinic.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    List<User> findUserBySpecializations(Specialization specialization);
    List<User> findUserByRole(Role role);
}
