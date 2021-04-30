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
//        User user5 = new User("fas", "pass", "fas@gmail.com", "Fas", "Cold", "31221", "user");
//        user5.setPassword("pass");
//        User user6 = new User("sga", "pass", "sga@gmail.com", "Sam", "Cold", "31221", "user");
//        user6.setPassword("pass");
//        User user7 = new User("pom", "pass", "pom@gmail.com", "Perez", "Cold", "31221", "user");
//        user7.setPassword("pass");
//        User user8 = new User("leh", "pass", "leh@gmail.com", "Alex", "Cold", "31221", "user");
//        user8.setPassword("pass");
//        User user9 = new User("dim", "pass", "dim@gmail.com", "Dim", "Cold", "31221", "user");
//        user9.setPassword("pass");
////        User user10 = new User("fox", "pass", "fox@gmail.com", "Fox", "Cold", "31221", "user");
//        user10.setPassword("pass");

        repository.addUser(user1);
        repository.addUser(user2);
        repository.addUser(user3);
        repository.addUser(user4);
//        repository.addUser(user5);
//        repository.addUser(user6);
//        repository.addUser(user7);
//        repository.addUser(user8);
//        repository.addUser(user9);
//        repository.addUser(user10);
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
