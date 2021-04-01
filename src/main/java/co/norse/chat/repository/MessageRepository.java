package co.norse.chat.repository;

import co.norse.chat.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    private List<Message> messagesStore;

    public MessageRepository() {
        this.messagesStore =  new ArrayList<>();
    }

    public void addMessage(Message message){
        messagesStore.add(message);
    }
    public boolean deleteMessageById(long id){
        for (Message m: messagesStore) {
            if(m.getId() == id){
                messagesStore.remove(m);
                return true;
            }
        }
        return false;
    }

    public List<Message> getAllMessages(){
        return messagesStore;
    }
    public void deleteAllMessages(){
        messagesStore.clear();
    }


}
