package co.norse.chat.repository;

import co.norse.chat.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void addUser() {
        User user1 = new User(1,"scm","scm@gmail.com","Andrew","Cold","21312","user");
        userRepository.addUser(user1);
        List<User> allUsers = userRepository.findAllUsers();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        assertEquals(userList,allUsers);
    }

    @Test
    void findUserById() {
        User user2 = new User(2,"asd","asd@gmail.com","Mike","Co","1232","user");
        userRepository.addUser(user2);
        assertEquals(user2,userRepository.findUserById(2));

    }

    @Test
    void findByUserName() {
        User user3 = new User(3,"lkm","lkm@gmail.com","Tim","Tol","3122","user");
        userRepository.addUser(user3);
        assertEquals(user3,userRepository.findByUserName("lkm"));
    }

    @Test
    void findAllUsers() {
        List<User> allUsers = userRepository.findAllUsers();
        assertEquals(allUsers.size(),3);
    }
}