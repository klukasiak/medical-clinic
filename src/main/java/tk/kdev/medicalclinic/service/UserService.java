package tk.kdev.medicalclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.kdev.medicalclinic.exception.UserNotFoundException;
import tk.kdev.medicalclinic.model.User;
import tk.kdev.medicalclinic.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> findByIdOptional(Long id){
        return repo.findById(id);
    }

    public User findById(Long id) throws UserNotFoundException {
        return repo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> findUserByUsernameOptional(String username){
        return repo.findUserByUsername(username);
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        return repo.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public void addUser(User user){
        repo.save(user);
    }
}
