package tk.kdev.aplikacjebazodanowejavafxspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.aplikacjebazodanowejavafxspring.model.Role;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public Optional<Role> findRoleByRole(String role){
        return roleRepo.findRoleByRole(role);
    }

    public Optional<Role> findRoleById(Long id){
        return roleRepo.findById(id);
    }
}
