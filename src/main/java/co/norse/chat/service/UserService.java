package co.norse.chat.service;

import co.norse.chat.model.User;
import co.norse.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void users() {
        User user1 = new User("scm", "pass", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        User user2 = new User("asd", "pass", "asd@gmail.com", "Colly", "Cold", "31221", "user");
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        User user3 = new User("das", "pass", "dsa@gmail.com", "Rafa", "Cold", "31221", "user");
        user3.setPassword(passwordEncoder.encode(user3.getPassword()));
        User user4 = new User("sfa", "pass", "sfscm@gmail.com", "Sanchez", "Cold", "31221", "user");
        user4.setPassword(passwordEncoder.encode(user4.getPassword()));

        repository.addUser(user1);
        repository.addUser(user2);
        repository.addUser(user3);
        repository.addUser(user4);
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.addUser(user);
    }

    public User findUserByName(String name) {
        return repository.findByUserName(name);
    }

    public User findUserById(long id) {
        return repository.findUserById(id);
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return repository.findAllUsers();
    }

    public void removeUser(long id) {
        repository.removeUserById(id);
    }

    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }


}
