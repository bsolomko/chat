package co.norse.chat.service;

import co.norse.chat.model.Chat;
import co.norse.chat.model.Message;
import co.norse.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getAllChatUser(Long userID) {
        return chatRepository.getAllChats().stream().filter(chat -> chat.getRecipientId() == userID || chat.getSenderId() == userID).collect(Collectors.toList());
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.getAllChats().stream().filter(chat -> chat.getId() == chatId).findAny().orElse(null);
    }

    public void addChat(Chat chat) {
        chatRepository.addChat(chat);
    }

    public void addMessageInChat(Long chatId, Message message) {
        chatRepository.getAllChats().stream().filter(chat -> chat.getId() == chatId).findAny().get().addMessageInChat(message);
    }

    public void deleteChatById(Long id) {
        chatRepository.deleteChatByID(id);
    }

    public void deleteAllChat() {
        chatRepository.deleteAllChats();
    }

    public List<Chat> getAllChat() {
        return chatRepository.getAllChats();
    }

    public int getAllMessageInChatCount(long chatID) {
        return chatRepository.getChatById(chatID).getCountMessage();
    }
}
