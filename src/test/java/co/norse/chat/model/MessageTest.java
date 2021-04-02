package co.norse.chat.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageTest {

    @Test
    void checkMessageAfterCreate() {

        Message message = new Message();
        message.setId(1);
        message.setAuthor("Author");
        message.setMessage("test message");


        assertEquals(1, message.getId());
        assertEquals("Author", message.getAuthor());
        assertEquals("test message", message.getMessage());

    }

}