package dao;

import entity.TopicEntity;

import java.util.List;

public interface ChatDaoInterface {
    void createNewTopic(TopicEntity topicEntity);

    boolean isTopicExist(String topicName);

    List findChats(String findChatParam);

    List showAllChats();

    List showMyChats(int idUser);

    TopicEntity getTopicByName(String requestParam);

    TopicEntity getTopicById(int topicId);
}
