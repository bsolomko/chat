package co.norse.chat.repository;

import co.norse.chat.model.Chat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChatRepository {
    private List<Chat> chatStore;

    public ChatRepository() {
        this.chatStore = new ArrayList<>();
    }


    public void addChat(Chat chat) {
        chatStore.add(chat);
    }

    public void deleteChatByID(Long id) {
        this.chatStore = chatStore.stream().filter(chat -> chat.getId() != id).collect(Collectors.toList());
    }

    public void deleteAllChat(){
        this.chatStore.clear();
    }

    public List<Chat> getAllChat(){
        return chatStore;
    }

    public Chat getChatByID(long id){
        return chatStore.stream().filter(chat -> chat.getId() == id).findAny().orElse(null);
    }
}
