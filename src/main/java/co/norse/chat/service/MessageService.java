package co.norse.chat.service;

import co.norse.chat.model.Message;
import co.norse.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;


    public Message getMessageById(long id) {
        Optional<Message> findedMessage = repository.findAll().stream().filter(message -> message.getId() == id).findFirst();
        if (findedMessage.isPresent()) return findedMessage.get();
        else return null;
    }

    public List<Message> getAllMessagesByAuthor(String authorName) {
        List<Message> result = repository.findAll().stream().filter(message -> message.getAuthor().equals(authorName)).collect(Collectors.toList());
        return result;
    }

    public List<Message> getMessagesByСontainingWord(String word) {

        return repository.findAll().stream().filter(message -> message.getMessage().contains(word)).collect(Collectors.toList());
    }

    public void addMessage(Message message) {

        repository.save(message);
    }

    public void deleteAllMessages() {

        repository.deleteAll();
    }

    public void deleteAllMessagesBySenderIdAndRecipientId(Long senderId, Long recipientId) {

        repository.findAll().stream().filter(message -> message.getRecipientId() == recipientId && message.getSenderId() == senderId
                || message.getSenderId() == recipientId && message.getRecipientId() == senderId).forEach(repository::delete);
    }

    public void deleteMessageByID(long id) {

        repository.deleteById(id);
    }

    public void deleteAllMessagesByAuthor(String authorName) {

        repository.deleteAllByAuthor(authorName);
    }

    public List<Message> getAllMessagesAfterDateTime(LocalDateTime dateTime, long senderId) {

        return repository.findAll().stream().filter(message -> message.getCreateDataTime().isAfter(dateTime) && message.getSenderId() == senderId).collect(Collectors.toList());
    }

    public List<Message> getAllMessagesBeforeDataTime(LocalDateTime dateTime, long senderId) {

        return repository.findAll().stream().filter(message -> message.getCreateDataTime().isBefore(dateTime) && message.getSenderId() == senderId).collect(Collectors.toList());
    }

    public List<Message> getAllMessages() {
        return repository.findAll();
    }
}
