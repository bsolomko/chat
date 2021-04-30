package co.norse.chat.service;

import co.norse.chat.model.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @AfterEach
    void clearService() {
        messageService.deleteAllMessages();
    }

    @Test
    void getMessageByID() {


        Message message = new Message(1, "message test", 2);
        messageService.addMessage(message);
        long id = message.getId();
        assertEquals(message, messageService.getMessageById(id));

    }


    @Test
    void getMessagesByСontainingWord() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        assertEquals(3, messageService.getMessagesByСontainingWord("test").size());
    }

    @Test
    void addMessage() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        assertEquals(3, messageService.getAllMessages().size());
    }

    @Test
    void deleteAllMessages() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        assertEquals(3, messageService.getAllMessages().size());
        messageService.deleteAllMessages();
        assertEquals(0, messageService.getAllMessages().size());
    }

    @Test
    void deleteMessageByID() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        assertEquals(3, messageService.getAllMessages().size());
        long id = m1.getId();
        messageService.deleteMessageByID(id);
        assertEquals(2, messageService.getAllMessages().size());
    }


    @Test
    void getAllMessagesAfterDateTime() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        Message m4 = new Message(1, "Hello test3", 2);
        LocalDateTime testDataTime = LocalDateTime.now().minusDays(1);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        messageService.addMessage(m4);
        assertEquals(4, messageService.getAllMessagesAfterDateTime(testDataTime, 1L).size());
        assertEquals(0, messageService.getAllMessagesAfterDateTime(testDataTime.plusDays(2), 1L).size());

    }

    @Test
    void getAllMessagesBeforeDataTime() {
        Message m1 = new Message(1, "Hello test", 2);
        Message m2 = new Message(1, "Hello test2", 2);
        Message m3 = new Message(1, "Hello test3", 2);
        Message m4 = new Message(1, "Hello test3", 2);
        LocalDateTime testDataTime = LocalDateTime.now().plusDays(1);
        messageService.addMessage(m1);
        messageService.addMessage(m2);
        messageService.addMessage(m3);
        messageService.addMessage(m4);
        assertEquals(4, messageService.getAllMessagesBeforeDataTime(testDataTime, 1L).size());
        assertEquals(0, messageService.getAllMessagesBeforeDataTime(testDataTime.minusDays(4), 1L).size());

    }
}