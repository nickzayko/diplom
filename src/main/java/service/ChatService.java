package service;

import dao.ChatDao;
import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatService")
public class ChatService implements ChatServiceInterface {

    @Autowired
    private ChatDao chatDao;


    public void createNewTopic(TopicEntity topicEntity) {
        chatDao.createNewTopic(topicEntity);
    }

    public boolean isTopicExist(String topicName) {
        return chatDao.isTopicExist(topicName);
    }

    public List findChats(String findChatParam) {
        return chatDao.findChats(findChatParam);
    }

    public List showAllChats() {
        return chatDao.showAllChats();
    }

    public List showMyChats(int idUser) {
        return chatDao.showMyChats(idUser);
    }


    public TopicEntity getTopicByName(String requestParam) {
        return chatDao.getTopicByName(requestParam);
    }

    public TopicEntity getTopicById(int topicId) {
        return chatDao.getTopicById(topicId);
    }
}
