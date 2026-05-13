package org.example.service;
// handles business logic
//communicates with repository
//separates controller from database operations
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
//This class is a Spring Service component.
//Spring automatically manages it.
@Service
public class UserService {
    //service communicate with database through repository
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    //Searches database for a user using username.
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public boolean emailExists(String email) {

        return userRepository.existsByEmail(email);
    }

    public User save(User user) {

        return userRepository.save(user);
    }
}
