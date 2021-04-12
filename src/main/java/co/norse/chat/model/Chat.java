package co.norse.chat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Chat {

    private long chatId;
    private long senderId;
    private long recipientId;
    private List<Message> messages;
    private AtomicLong aLong = new AtomicLong();


    public Chat(long senderId, long recipientId) {
        this.chatId = aLong.incrementAndGet();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messages =   new ArrayList<>();
    }


    public void addMessageInChat(Message message) {
        this.messages.add(message);
    }



    public long getId() {
        return chatId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }
    public int getCountMessage(){
        return messages.size();
    }

    public List<Message> getMessages() {
        return messages;
    }
}
