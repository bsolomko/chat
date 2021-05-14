package co.norse.chat.controller;

import co.norse.chat.service.UserService;
import co.norse.chat.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    @WithMockUser(username = "scm", password = "pass")
    public void authentication() throws Exception {

        mockMvc.perform(post("/authenticate"))
                .andExpect(authenticated().withUsername("scm"));

        mockMvc.perform(get("/users/current"))
                .andExpect(content().json("{'username':'scm'}"));
    }

    @Test
    @WithMockUser
    public void registration() throws Exception {

        String jsonUser = "{\n" +
                "    \"username\" : \"dima\",\n" +
                "    \"password\" : \"pass\",\n" +
                "    \"email\" : \"dima@gmail.com\",\n" +
                "    \"firstName\" : \"Dima\",\n" +
                "    \"lastName\" : \"Boy\",\n" +
                "    \"imageUrl\" : \"123412\",\n" +
                "    \"type\" : \"user\"\n" +
                "}";

        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON).content(jsonUser))
                .andExpect(jsonPath("$.username", is("dima")))
                .andExpect(jsonPath("$.email", is("dima@gmail.com")))
                .andExpect(jsonPath("$.firstName", is("Dima")))
                .andExpect(jsonPath("$.lastName", is("Boy")))
                .andExpect(jsonPath("$.imageUrl", is("123412")))
                .andExpect(jsonPath("$.type", is("user")));

    }


}