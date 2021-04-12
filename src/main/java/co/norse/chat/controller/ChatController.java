package co.norse.chat.controller;

import co.norse.chat.model.Chat;
import co.norse.chat.model.Message;
import co.norse.chat.model.User;
import co.norse.chat.service.ChatService;
import co.norse.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/user/{id}/chat/add/{recipientId}")
    public Chat createChat(@PathVariable Long id, @PathVariable Long recipientId) {
        Chat chat = new Chat(id, recipientId);
        chatService.addChat(chat);
        return chat;
    }

    @PostMapping("/user/{id}/chat/{chatId}/message/send/{recipientId}")
    public Message sendMessage(@PathVariable Long id, @PathVariable Long chatId, @PathVariable Long recipientId, @RequestBody Message message) throws Exception {

        if(chatService.getChatById(chatId) == null) throw new Exception("Error chat ID");
        Message _message = new Message();
        _message.setMessage(message.getMessage());
        _message.setSenderId(id);
        _message.setRecipientID(recipientId);
        messageService.addMessage(_message);
        chatService.addMessageInChat(chatId, _message);
        return _message;
    }
    @GetMapping("/user/{id}/chat/{chatId}/message")
    public List<Message> getChat(@PathVariable Long id, @PathVariable Long chatId){
    Chat chat =  chatService.getChatById(chatId);
    List<Message> messages = chat.getMessages();
    return messages;
    }
}
