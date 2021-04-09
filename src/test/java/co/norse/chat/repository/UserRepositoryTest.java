package co.norse.chat.repository;

import co.norse.chat.User;
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
        User user1 = new User("scm", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        assertEquals(0, userRepository.findAllUsers().size());
        userRepository.addUser(user1);
        assertEquals(1, userRepository.findAllUsers().size());
    }

    @Test
    void findUserById() {
        User user2 = new User("asd", "asd@gmail.com", "Mike", "Co", "1232", "user");
        userRepository.addUser(user2);
        assertEquals(user2, userRepository.findUserById(user2.getId()));

    }

    @Test
    void findByUserName() {
        User user3 = new User("lkm", "lkm@gmail.com", "Tim", "Tol", "3122", "user");
        userRepository.addUser(user3);
        assertEquals(user3, userRepository.findByUserName("lkm"));
    }

    @Test
    void findByEmail() {
        User user4 = new User("dsk", "dsk@gmail.com", "Nile", "Bred", "4112", "user");
        userRepository.addUser(user4);
        assertEquals(user4, userRepository.findByEmail("dsk@gmail.com"));
    }

    @Test
    void findAllUsers() {
        User user4 = new User("dsk", "dsk@gmail.com", "Nile", "Bred", "4112", "user");
        User user3 = new User("lkm", "lkm@gmail.com", "Tim", "Tol", "3122", "user");
        userRepository.addUser(user3);
        userRepository.addUser(user4);
        assertEquals(2, userRepository.findAllUsers().size());
    }

    @Test
    void removeUserById() {
        User user5 = new User("das", "das@gmail.com", "Drake", "Paul", "3167", "user");
        userRepository.addUser(user5);
        userRepository.removeUserById(user5.getId());
        assertEquals(0, userRepository.findAllUsers().size());
    }

}