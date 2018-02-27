package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @Column(name = "id_messages")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;

    @Column(name = "text_of_message")
    private String textOfMessage;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    public MessageEntity(String textOfMessage, UserEntity userEntity, TopicEntity topicEntity) {
        this.textOfMessage = textOfMessage;
        this.userEntity = userEntity;
        this.topicEntity = topicEntity;
    }

    public MessageEntity() {
    }

    @Override
    public String toString() {
        return textOfMessage;
    }
}

