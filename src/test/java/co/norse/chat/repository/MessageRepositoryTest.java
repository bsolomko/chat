package co.norse.chat.repository;

import co.norse.chat.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageRepositoryTest {

    @Test
    void checkForAddMessage() {

        MessageRepository repository = new MessageRepository();
        Message message = new Message();
        assertEquals(0, repository.getAllMessages().size());
        repository.addMessage(message);
        assertEquals(1, repository.getAllMessages().size());
    }

    @Test
    void checkForDeleteMessageByID() {
        MessageRepository repository = new MessageRepository();
        Message message = new Message();
        repository.addMessage(message);
        assertEquals(1, repository.getAllMessages().size());
        repository.deleteMessageById(message.getId());
        assertEquals(0, repository.getAllMessages().size());
    }

    @Test
    void checkForGetAllMessages() {
        Message m1 = new Message();
        Message m2 = new Message();
        Message m3 = new Message();
        MessageRepository repository = new MessageRepository();
        repository.addMessage(m1);
        repository.addMessage(m2);
        repository.addMessage(m3);
        assertEquals(3, repository.getAllMessages().size());
    }

    @Test
    void checkForDeleteAllMessages(){
        Message m1 = new Message();
        Message m2 = new Message();
        Message m3 = new Message();
        MessageRepository repository = new MessageRepository();
        repository.addMessage(m1);
        repository.addMessage(m2);
        repository.addMessage(m3);
        assertEquals(3, repository.getAllMessages().size());
        repository.deleteAllMessages();
        assertEquals(0, repository.getAllMessages().size());

    }
}