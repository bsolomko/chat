package co.norse.chat.controller;

import co.norse.chat.model.User;
import co.norse.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/current")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

//    @GetMapping(value = "/users/{id}")
//    public ResponseEntity<User> getUserByID(@PathVariable(name = "id") long id) {
//        final User user = userService.findUserById(id);
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @CrossOrigin
    @GetMapping(value = "/users/byUsername")
    public ResponseEntity<User> getUserByName(@RequestParam(name = "username") String username) {
        final User user = userService.findUserByName(username);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email") String email) {
        final User user = userService.findUserByEmail(email);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable(name = "id") long id) {
        userService.removeUser(id);
    }

}
