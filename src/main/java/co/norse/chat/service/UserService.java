package co.norse.chat.service;

import co.norse.chat.User;
import co.norse.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;



    public void addUser(User user){
        repository.addUser(user);
    }

    public User findUserByName(String name){
        return repository.findByUserName(name);
    }

    public User findUserById(int id){
        return repository.findUserById(id);
    }

    public User findUserByEmail(String email){
        return repository.findByEmail(email);
    }

    public List<User> findAllUsers(){
        return repository.findAllUsers();
    }

    public void removeUser(int id){
         repository.removeUserById(id);

    }


}
