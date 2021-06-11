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

    @Autowired
    private MessageService messageService;

    public List<Chat> getAllChatUser(Long userID) {
        return chatRepository.findAll().stream().filter(chat -> chat.getRecipientId() == userID || chat.getSenderId() == userID).collect(Collectors.toList());
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.findAll().stream().filter(chat -> chat.getChatId() == chatId).findAny().orElse(null);
    }

    public Chat getChatByRecipientAndSenderId(long recipient, long sender) throws Exception {
        return chatRepository.findAll().stream().filter(chat -> chat.getRecipientId() == recipient && chat.getSenderId() == sender
                || chat.getRecipientId() == sender && chat.getSenderId() == recipient).findAny().orElseThrow(() -> new Exception("Don't found chat"));
    }

    public void addChat(Chat chat) {
        chatRepository.save(chat);
    }

    public void addMessageInChat(Long chatId, Message message) {
        Chat chat1 = chatRepository.findAll().
                stream().filter(chat -> chat.getChatId() == chatId).findAny().get();
        messageService.addMessage(message);
    }

    public void deleteChatById(Long id) {
        chatRepository.deleteById(id);
    }

    public void deleteAllChat() {
        chatRepository.deleteAll();
    }

    
    public List<Chat> getAllChat() {
        return chatRepository.findAll();
    }

}
