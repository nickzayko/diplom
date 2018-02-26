package dao;

import entity.MessageEntity;

import java.util.List;

public interface MessageDaoInterface {
    void saveMessage(MessageEntity messageEntity);
    List getMessagesByTopicId(Integer topicId);
}
