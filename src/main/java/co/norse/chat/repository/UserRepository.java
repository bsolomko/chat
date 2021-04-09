package co.norse.chat.repository;

import co.norse.chat.User;
import co.norse.chat.model.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    List<User> userStore;

    public UserRepository() {
        this.userStore = new ArrayList<>();
    }

    public void addUser(User user) {
        userStore.add(user);
    }

    public User findUserById(long id) {
        for (User u : userStore) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    public User findByUserName(String name) {
        for (User u : userStore) {
            if (u.getUsername() != null) {
                if (u.getUsername().equals(name))
                    return u;
            }
        }
        return null;
    }

    public List<User> findAllUsers() {
        return userStore;
    }

    public User findByEmail(String email) {
        for (User u : userStore) {
            if (u.getEmail() != null) {
                if (u.getEmail().equals(email))
                    return u;
            }
        }
        return null;
    }

    public boolean removeUserById(long id) {
        for (User u : userStore) {
            if (u.getId() == id) {
                userStore.remove(u);
                return true;
            }
        }
        return false;
    }

    public void deleteAllUsers() {
        userStore.clear();
    }
}
