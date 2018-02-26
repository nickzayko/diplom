package service;

import dao.ChatDao;
import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatService")
public class ChatService {

    @Autowired
    private ChatDao chatDao;


    public void createNewTopic(TopicEntity topicEntity) {
        chatDao.createNewTopic(topicEntity);
    }

    public boolean isTopicExist(String topicName) {
        return chatDao.isTopicExist(topicName);
    }

    public List checkIfChatExist(String findChatParam) {
        return chatDao.checkIfChatExist(findChatParam);
    }

    public List showAllChats() {
        return chatDao.showAllChats();
    }

    public List showMyChats(int idUser) {
        return chatDao.showMyChats(idUser);
    }


    public TopicEntity getTopicEntityByTopicName(String requestParam) {
        return chatDao.getTopicEntityByTopicName(requestParam);
    }

    public TopicEntity getTopicEntityByTopicId(int topicId) {
        return chatDao.getTopicEntityByTopicId(topicId);
    }
}
