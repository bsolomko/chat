package co.norse.chat.controller;

import co.norse.chat.User;
import co.norse.chat.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @AfterEach
    void clearChatRepository() {
        userService.deleteAllUsers();
    }

    @Test
    public void addUser() throws Exception {

        String jsonUser = "{\n" +
                "    \"username\" : \"dima\",\n" +
                "    \"email\" : \"dima@gmail.com\",\n" +
                "    \"firstName\" : \"Dima\",\n" +
                "    \"lastName\" : \"Boy\",\n" +
                "    \"imageUrl\" : \"123412\",\n" +
                "    \"type\" : \"user\"\n" +
                "}";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(jsonPath("$.username", is("dima")))
                .andExpect(jsonPath("$.email", is("dima@gmail.com")))
                .andExpect(jsonPath("$.firstName", is("Dima")))
                .andExpect(jsonPath("$.lastName", is("Boy")))
                .andExpect(jsonPath("$.imageUrl", is("123412")))
                .andExpect(jsonPath("$.type", is("user")));
    }

    @Test
    public void getUserById() throws Exception {
        var user = new User();
        user.setUsername("dima");
        userService.addUser(user);
        var id = userService.findAllUsers().get(0).getId();
        mockMvc.perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("dima")));
    }

    @Test
    public void getUserByUsername() throws Exception {
        var user = new User("asd", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        mockMvc.perform(get("/users/byUsername?username=scm"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)));
    }

    @Test
    public void getUserByEmail() throws Exception {
        var user = new User("asd", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        mockMvc.perform(get("/users/byEmail?email=asd@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void removeUser() throws Exception {
        var user = new User("asd", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        var id = userService.findAllUsers().get(0).getId();
        mockMvc.perform(delete("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsers() throws Exception {
        var user = new User("asd", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is("asd")))
                .andExpect(jsonPath("$[0].email", is("asd@gmail.com")))
                .andExpect(jsonPath("$[0].firstName", is("Mike")))
                .andExpect(jsonPath("$[0].lastName", is("Co")))
                .andExpect(jsonPath("$[0].imageUrl", is("1232")))
                .andExpect(jsonPath("$[0].type", is("user")))

                .andExpect(jsonPath("$[1].username", is("scm")))
                .andExpect(jsonPath("$[1].email", is("scm@gmail.com")))
                .andExpect(jsonPath("$[1].firstName", is("Andrew")))
                .andExpect(jsonPath("$[1].lastName", is("Cold")))
                .andExpect(jsonPath("$[1].imageUrl", is("31221")))
                .andExpect(jsonPath("$[1].type", is("user")));
    }

}












