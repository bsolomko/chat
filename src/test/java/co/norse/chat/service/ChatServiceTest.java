package co.norse.chat.service;

import co.norse.chat.model.Chat;
import co.norse.chat.model.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @AfterEach
    void clearRepository(){
        chatService.deleteAllChat();
    }

    @Test
    void getAllChatUser() {

        chatService.addChat(new Chat(1L,2L));
        chatService.addChat(new Chat(1L,3L));
        chatService.addChat(new Chat(1L,4L));
        chatService.addChat(new Chat(11L,1L));
        assertEquals(4,chatService.getAllChatUser(1L).size());
    }

    @Test
    void addChat() {
        assertEquals(0,chatService.getAllChat().size());
        chatService.addChat(new Chat(1L,2L));
        assertEquals(1,chatService.getAllChat().size());

    }

    @Test
    void addMessageInChat() {
        Chat chat = new Chat(1L,2L);
        chatService.addChat(chat);
        chatService.addMessageInChat(chat.getId(),new Message(chat.getSenderId(),"Hello Alex",chat.getRecipientId()));
        assertEquals(1,chatService.getAllMessageInChatCount(chat.getId()));

    }

    @Test
    void deleteChatById() {
    }
}
