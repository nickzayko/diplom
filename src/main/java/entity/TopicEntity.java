package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity {

    @OneToMany(mappedBy = "topicEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MessageEntity> messageEntityListForTopics;

    @Id
    @Column(name = "id_topics")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTopic;

    @Column(name = "topic_name")
    private String topicName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public TopicEntity(String topicName, UserEntity userEntity) {
        this.topicName = topicName;
        this.userEntity = userEntity;
    }

    public TopicEntity(List<MessageEntity> messageEntityListForTopics, String topicName, UserEntity userEntity) {
        this.messageEntityListForTopics = messageEntityListForTopics;
        this.topicName = topicName;
        this.userEntity = userEntity;
    }

    public TopicEntity() {
    }

    public List<MessageEntity> getMessageEntityListForTopics() {
        return messageEntityListForTopics;
    }

    public void setMessageEntityListForTopics(List<MessageEntity> messageEntityListForTopics) {
        this.messageEntityListForTopics = messageEntityListForTopics;
    }
    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return topicName;
    }
}
