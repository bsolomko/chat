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
import java.util.Optional;

@CrossOrigin
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
        if (chatService.getChatById(chat.getChatId()) == null) throw new Exception("Error chat ID");

        Optional<User> userSender = userService.findUserById(message.getSenderId());

        Message messageModel = new Message();
        messageModel.setMessage(message.getMessage());
        messageModel.setSenderId(chat.getSenderId());
        messageModel.setRecipientId(chat.getRecipientId());
        messageModel.setAuthor(userSender.get().getFirstName());
        messageService.addMessage(messageModel);
        chatService.addMessageInChat(chat.getChatId(), messageModel);
        return messageModel;
    }

    @GetMapping("/user/{id}/chat/{recipientId}/message")
    public List<Message> getMessagesFromChat(@PathVariable Long id, @PathVariable Long recipientId) throws Exception {
        Chat chatModel = chatService.getChatByRecipientAndSenderId(id, recipientId);
        return chatModel.getMessages();
    }

    @GetMapping("/user/{id}/chat/recipient/{recipientId}")
    public Chat getChat(@PathVariable Long id, @PathVariable Long recipientId) throws Exception {
        Chat chatModel = chatService.getChatByRecipientAndSenderId(id, recipientId);
        System.out.println(chatModel);
        if (chatModel == null) return null;
        else return chatModel;
    }

    @DeleteMapping("/user/{id}/chat/delete")
    public void deleteChat(@RequestBody Chat chat) throws Exception {
        Chat chatModel = chatService.getChatById(chat.getChatId());
        if (chatModel != null) chatService.deleteChatById(chatModel.getChatId());
        else throw new Exception("Error chat ID");
    }

    @DeleteMapping("/user/{id}/chat/message/delete")
    public void deleteAllMessage(@RequestBody Chat chat) throws Exception {
        messageService.deleteAllMessagesBySenderIdAndRecipientId(chat.getSenderId(), chat.getRecipientId());

    }
}
