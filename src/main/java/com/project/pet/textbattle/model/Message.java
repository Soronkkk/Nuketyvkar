package com.project.pet.textbattle.model;

import javax.persistence.*;

/**
 * @author Koloyartsev V.A.
 *
 * Сущность сообщения.
 */
@Entity
@Table(name = "messages")
public class Message {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Техт сообщения.
     */
    @Column(name = "message_text", nullable = false)
    private String messageText;

    public Message() {
    }

    public Message(String messageText) {
        this.messageText = messageText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
