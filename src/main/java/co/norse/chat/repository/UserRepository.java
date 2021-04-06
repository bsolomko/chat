package co.norse.chat.repository;

import co.norse.chat.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    List<User> userStore;

    public UserRepository() {
        this.userStore = new ArrayList();
    }

    public void addUser(User user){
        userStore.add(user);
    }

    public User findUserById(int id){
        for (User u:userStore) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    public User findByUserName(String name){
        for (User u:userStore) {
            if (u.getUsername() != null) {
                if (u.getUsername().equals(name))
                return u; }
        }
        return null;
    }

    public List<User> findAllUsers(){
        return userStore;
    }

}
