package co.norse.chat.controller;

import co.norse.chat.model.Chat;
import co.norse.chat.model.Message;
import co.norse.chat.model.User;
import co.norse.chat.service.ChatService;
import co.norse.chat.service.MessageService;
import co.norse.chat.service.UserService;
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
    @Autowired
    private UserService userService;

    @PostMapping("/user/{id}/chat/add/{recipientId}")
    public Chat createChat(@PathVariable Long id, @PathVariable Long recipientId) {
        Chat chat = new Chat(id, recipientId);
        chatService.addChat(chat);
        return chat;
    }

    @PostMapping("/user/{id}/message/send")
    public Message sendMessage(@RequestBody Message message) throws Exception {

        Chat chat = chatService.getAllChat().stream().filter(chat1 -> message.getSenderId() == chat1.getSenderId() && message.getRecipientId() == chat1.getRecipientId()
                || message.getSenderId() == chat1.getRecipientId() && message.getRecipientId() == chat1.getSenderId()).findAny().orElse(null);
        if (chatService.getChatById(chat.getId()) == null) throw new Exception("Error chat ID");

        User userSender = userService.findUserById(message.getSenderId());

        Message messageModel = new Message();
        messageModel.setMessage(message.getMessage());
        messageModel.setSenderId(chat.getSenderId());
        messageModel.setRecipientID(chat.getRecipientId());
        messageModel.setAuthor(userSender.getFirstName());
        messageService.addMessage(messageModel);
        chatService.addMessageInChat(chat.getId(), messageModel);
        return messageModel;
    }

    @GetMapping("/user/{id}/chat/message")
    public List<Message> getChat(@RequestBody Chat chat) {
        Chat chatModel = chatService.getChatById(chat.getId());
        return chatModel.getMessages();
    }

    @DeleteMapping("/user/{id}/chat/delete")
    public void deleteChat(@RequestBody Chat chat) throws Exception {
        Chat chatModel = chatService.getChatById(chat.getId());
        if (chatModel != null) chatService.deleteChatById(chatModel.getId());
        else throw new Exception("Error chat ID");
    }

    @DeleteMapping("/user/{id}/chat/message/delete")
    public void deleteAllMessage(@RequestBody Chat chat) throws Exception {
        Chat chatModel = chatService.getChatById(chat.getId());
        if (chatModel != null) {
            chatModel.deleteAllMessages();
        } else throw new Exception("Error chat ID");
    }
}
