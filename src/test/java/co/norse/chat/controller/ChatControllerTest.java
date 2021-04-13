package co.norse.chat.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import co.norse.chat.model.Chat;
import co.norse.chat.model.Message;
import co.norse.chat.service.ChatService;
import co.norse.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;


    @AfterEach
    void clearChatRepository() {
        chatService.deleteAllChat();
    }

    @Test
    void createChat() throws Exception {

        mockMvc.perform(post("/api/user/1/chat/add/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"senderId\": 1,\n" +
                        "    \"recipientId\": 4,\n" +
                        "    \"messages\": [],\n" +
                        "    \"id\": 1,\n" +
                        "    \"countMessage\": 0\n" +
                        "}"));
    }

    @Test
    void sendMessage() throws Exception {

        Chat chat = new Chat(1, 3);
        chatService.addChat(chat);
        String jsonMessage = "{\n" +
                "    \"message\": \"Hello Test\"\n" +
                "}";
        mockMvc.perform(post("/api/user/" + chat.getId() + "/chat/1/message/send/3")
                .contentType(MediaType.APPLICATION_JSON).content(jsonMessage))
                .andExpect(jsonPath("$.senderId", is(1)))
                .andExpect(jsonPath("$.recipientID", is(3)))
                .andExpect(jsonPath("$.message", is(notNullValue())));
    }

    @Test
    void getChat() throws Exception {
        Chat chat = new Chat(1, 3);
        Message m1 = new Message(1, "message1", 3);
        Message m2 = new Message(1, "message2", 3);
        Message m3 = new Message(3, "message3", 1);

        chatService.addChat(chat);
        chatService.addMessageInChat(chat.getId(), m1);
        chatService.addMessageInChat(chat.getId(), m2);
        chatService.addMessageInChat(chat.getId(), m3);


        mockMvc.perform(get("/api/user/1/chat/" + chat.getId() + "/message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].senderId", is(1)))
                .andExpect(jsonPath("$[0].message", is(notNullValue())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].senderId", is(1)))
                .andExpect(jsonPath("$[1].message", is(notNullValue())))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].senderId", is(3)))
                .andExpect(jsonPath("$[2].message", is(notNullValue())));
    }
}

