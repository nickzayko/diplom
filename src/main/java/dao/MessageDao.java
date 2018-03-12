package dao;

import entity.MessageEntity;
import entity.TopicEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageDao {
    void saveMessage(MessageEntity messageEntity);

    List getMessagesByTopicEntity(TopicEntity topicEntity);

    List getPreviousMessages(TopicEntity topicEntity, Integer numberOfPages);

    List getNewMessages(TopicEntity topicEntity, LocalDateTime currentTime);
}
