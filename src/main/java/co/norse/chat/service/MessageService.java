package co.norse.chat.service;

import co.norse.chat.model.Message;
import co.norse.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;


    public Message getMessageById(long id) {
        Optional<Message> findedMessage = repository.getAllMessages().stream().filter(message -> message.getId() == id).findFirst();
        if (findedMessage.isPresent()) return findedMessage.get();
        else return null;
    }

    public List<Message> getAllMessagesByAuthor(String authorName) {
        List<Message> result = repository.getAllMessages().stream().filter(message -> message.getAuthor().equals(authorName)).collect(Collectors.toList());
        return result;
    }

    public List<Message> getMessagesByСontainingWord(String word) {

        return repository.getAllMessages().stream().filter(message -> message.getMessage().contains(word)).collect(Collectors.toList());
    }

    public void addMessage(Message message) {

        repository.addMessage(message);
    }

    public void deleteAllMessages() {

        repository.deleteAllMessages();
    }

    public void deleteMessageByID(long id) {

        repository.deleteMessageById(id);
    }

    public void deleteAllMessagesByAuthor(String authorName) {

       repository.deleteAllMessageByAuthor(authorName);
    }

    public List<Message> getAllMessagesAfterDateTime(LocalDateTime dateTime, String author) {

        return repository.getAllMessages().stream().filter(message -> message.getCreateDataTime().isAfter(dateTime) && message.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Message> getAllMessagesBeforeDataTime(LocalDateTime dateTime, String author) {

        return repository.getAllMessages().stream().filter(message -> message.getCreateDataTime().isBefore(dateTime) && message.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Message> getAllMessages() {
        return repository.getAllMessages();
    }
}
