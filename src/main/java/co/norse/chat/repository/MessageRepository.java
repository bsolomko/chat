package co.norse.chat.repository;

import co.norse.chat.model.Message;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageRepository {

    private List<Message> messagesStore;

    public MessageRepository() {
        this.messagesStore = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messagesStore.add(message);
    }

    public boolean deleteMessageById(long id) {
        for (Message m : messagesStore) {
            if (m.getId() == id) {
                messagesStore.remove(m);
                return true;
            }
        }
        return false;
    }

    public List<Message> getAllMessages() {
        return messagesStore;
    }

    public void deleteAllMessages() {
        messagesStore.clear();
    }

    public void deleteAllMessageByAuthor(String author){
        messagesStore  = messagesStore.stream().filter(message -> !message.getAuthor().equals(author)).collect(Collectors.toList());

    }


    public void setMessagesStore(List<Message> messagesStore) {
        this.messagesStore = messagesStore;
    }

}
