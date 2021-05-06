package co.norse.chat.controller;

import co.norse.chat.model.User;
import co.norse.chat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    void clearChatRepository() {
        userService.deleteAllUsers();
    }

    @Test
    @WithMockUser
    public void getUserById() throws Exception {
        var user = new User("btc", "pass", "btc@gmail.com", "Btc", "Btc", "1232", "user");
        userService.addUser(user);
        var id = (int) userService.findAllUsers().get(0).getId();
        mockMvc.perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("btc")));
    }

    @Test
    @WithMockUser
    public void getUserByUsername() throws Exception {
        var user = new User("wes", "pass", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("qwe", "pass", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        mockMvc.perform(get("/users/byUsername?username=wes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) userService.findUserByName("wes").getId())));
    }

    @Test
    @WithMockUser
    public void getUserByEmail() throws Exception {
        var user = new User("asd", "pass", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "pass", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        mockMvc.perform(get("/users/byEmail?email=asd@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) userService.findUserByEmail("asd@gmail.com").getId())));
    }

    @Test
    @WithMockUser
    public void removeUser() throws Exception {
        var user = new User("asd", "pass", "asd@gmail.com", "Mike", "Co", "1232", "user");
        var user1 = new User("scm", "pass", "scm@gmail.com", "Andrew", "Cold", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);
        var id = userService.findAllUsers().get(0).getId();
        mockMvc.perform(delete("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllUsers() throws Exception {
        var user = new User("aim", "pass", "aim@gmail.com", "Aim", "Aim", "1232", "user");
        var user1 = new User("dr", "pass", "dr@gmail.com", "Dr", "Dr", "31221", "user");
        userService.addUser(user);
        userService.addUser(user1);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is("aim")))
                .andExpect(jsonPath("$[0].email", is("aim@gmail.com")))
                .andExpect(jsonPath("$[0].firstName", is("Aim")))
                .andExpect(jsonPath("$[0].lastName", is("Aim")))
                .andExpect(jsonPath("$[0].imageUrl", is("1232")))
                .andExpect(jsonPath("$[0].type", is("user")))

                .andExpect(jsonPath("$[1].username", is("dr")))
                .andExpect(jsonPath("$[1].email", is("dr@gmail.com")))
                .andExpect(jsonPath("$[1].firstName", is("Dr")))
                .andExpect(jsonPath("$[1].lastName", is("Dr")))
                .andExpect(jsonPath("$[1].imageUrl", is("31221")))
                .andExpect(jsonPath("$[1].type", is("user")));
    }

}












