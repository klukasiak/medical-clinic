package tk.kdev.aplikacjebazodanowejavafxspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.aplikacjebazodanowejavafxspring.model.User;
import tk.kdev.aplikacjebazodanowejavafxspring.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> findById(Long id){
        return repo.findById(id);
    }

    public Optional<User> findUserByUsername(String username){
        return repo.findUserByUsername(username);
    }
}
