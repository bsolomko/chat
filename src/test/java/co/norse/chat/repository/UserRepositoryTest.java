package co.norse.chat.repository;

import co.norse.chat.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void clearRepository() {
        userRepository.deleteAllUsers();
    }

    @Test
    void addUser() {
        var user = new User();
        assertEquals(0, userRepository.findAllUsers().size());
        userRepository.addUser(user);
        assertEquals(1, userRepository.findAllUsers().size());
    }

    @Test
    void findUserById() {
        var user = new User();
        userRepository.addUser(user);
        assertEquals(user, userRepository.findUserById(user.getId()));

    }

    @Test
    void findByUserName() {
        var user = new User("lkm", "pass", "lkm@gmail.com", "Tim", "Tol", "3122", "user");
        userRepository.addUser(user);
        assertEquals(user, userRepository.findByUserName("lkm"));
    }

    @Test
    void findByEmail() {
        var user = new User("dsk", "pass", "dsk@gmail.com", "Nile", "Bred", "4112", "user");
        userRepository.addUser(user);
        assertEquals(user, userRepository.findByEmail("dsk@gmail.com"));
    }

    @Test
    void findAllUsers() {
        var user1 = new User();
        var user2 = new User();
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        assertEquals(2, userRepository.findAllUsers().size());
    }

    @Test
    void removeUserById() {
        userRepository.deleteAllUsers();
        var user = new User();
        userRepository.addUser(user);
        userRepository.removeUserById(user.getId());
        assertEquals(0, userRepository.findAllUsers().size());
    }

}