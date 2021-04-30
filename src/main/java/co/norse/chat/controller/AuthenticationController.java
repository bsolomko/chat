package co.norse.chat.controller;

import co.norse.chat.model.AuthenticationRequest;
import co.norse.chat.model.AuthenticationResponce;
import co.norse.chat.model.User;
import co.norse.chat.service.MyUserDetailService;
import co.norse.chat.service.UserService;
import co.norse.chat.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponce(jwt));
    }

    @PostMapping("/registration")
    public User addUser(@RequestBody User user) {
        User userModel = new User();
        userModel.setPassword(user.getPassword());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setUsername(user.getUsername());
        userModel.setImageUrl(user.getImageUrl());
        userModel.setType(user.getType());
        userService.addUser(userModel);
        return userModel;
    }
}
