package service;

import entity.MessageEntity;

import java.util.List;

public interface MessageServiceInterface {
    void saveMessage(MessageEntity messageEntity);
    List getMessagesByTopicId(Integer topicId);
}
