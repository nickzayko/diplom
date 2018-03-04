package dao;

import entity.MessageEntity;
import entity.TopicEntity;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageDaoInterface {
    void saveMessage(MessageEntity messageEntity);

    List getMessagesByTopicEntity(TopicEntity topicEntity);

    List getPreviousMessages(TopicEntity topicEntity, HttpSession session);

    List getNewMessages(TopicEntity topicEntity, LocalDateTime currentTime);
}
