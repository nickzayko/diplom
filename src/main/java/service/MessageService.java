package service;

import dao.MessageDao;
import entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public void saveMessage(MessageEntity messageEntity) {
        messageDao.saveMessage(messageEntity);
    }

    public List getMessagesFromDataBaseByTopicId(Integer topicId) {
        return messageDao.getMessagesFromDataBaseByTopicId(topicId);
    }
}
