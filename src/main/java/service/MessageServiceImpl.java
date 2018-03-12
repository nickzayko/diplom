package service;

import dao.MessageDaoImpl;
import entity.MessageEntity;
import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDaoImpl messageDaoImpl;

    public void saveMessage(MessageEntity messageEntity) {
        messageDaoImpl.saveMessage(messageEntity);
    }

    public List getMessagesByTopicEntity(TopicEntity topicEntity) {
        return messageDaoImpl.getMessagesByTopicEntity(topicEntity);
    }

    @Override
    public List getPreviousMessages(TopicEntity topicEntity, Integer numberOfPages) {
        return messageDaoImpl.getPreviousMessages(topicEntity, numberOfPages);
    }

    @Override
    public List getNewMessages(TopicEntity topicEntity, LocalDateTime currentTime) {
        return messageDaoImpl.getNewMessages(topicEntity, currentTime);
    }
}
