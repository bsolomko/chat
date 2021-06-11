package co.norse.chat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long chatId;

    @Column(name = "sender_id")
    private long senderId;

    @Column(name = "recipient_id")
    private long recipientId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private List<Message> messages;

    public Chat(long senderId, long recipientId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messages = new ArrayList<>();
    }

}
