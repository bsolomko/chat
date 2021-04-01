package co.norse.chat.repository;

import co.norse.chat.User;

import java.util.ArrayList;
import java.util.List;

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

    public User findUserByName(String name){
        for (User u:userStore) {
            if (u.getFirstName() == name)
                return u;
        }
        return null;
    }

    public List<User> findAllUsers(){
        return userStore;
    }

}
