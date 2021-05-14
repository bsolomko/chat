package co.norse.chat.service;

import co.norse.chat.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @AfterEach
    void clearRepository() {
        userService.deleteAllUsers();
    }

    @Test
    void addUser() {
        userService.deleteAllUsers();
        assertEquals(0, userService.findAllUsers().size());
        User user = new User("scm", "pass", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        assertEquals(1, userService.findAllUsers().size());
    }

    @Test
    void findUserByName() {
        User user = new User("lkm", "pass", "lkm@gmail.com", "Tim", "Tol", "3122", "user");
        userService.addUser(user);
        assertEquals(user, userService.findUserByName("lkm"));
    }

    @Test
    void findUserById() {
        User user = new User("asd", "pass", "asd@gmail.com", "Mike", "Co", "1232", "user");
        userService.addUser(user);
        assertEquals(user, userService.findUserById(user.getId()));
    }

    @Test
    void findUserByEmail() {
        User user = new User("dsk", "pass", "dsk@gmail.com", "Nile", "Bred", "4112", "user");
        userService.addUser(user);
        assertEquals(user, userService.findUserByEmail("dsk@gmail.com"));
    }

    @Test
    void findAllUsers() {
        User user1 = new User("das", "pass", "das@gmail.com", "Drake", "Paul", "3167", "user");
        User user2 = new User("lkm", "pass", "lkm@gmail.com", "Tim", "Tol", "3122", "user");
        userService.addUser(user1);
        userService.addUser(user2);
        assertEquals(2, userService.findAllUsers().size());
    }

    @Test
    void removeUser() {
        User user = new User("dsk", "pass", "dsk@gmail.com", "Nile", "Bred", "4112", "user");
        userService.addUser(user);
        assertEquals(1, userService.findAllUsers().size());
        userService.removeUser(user.getId());
        assertEquals(0, userService.findAllUsers().size());
    }
}