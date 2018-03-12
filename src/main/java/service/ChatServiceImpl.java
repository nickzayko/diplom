package service;

import dao.ChatDaoImpl;
import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDaoImpl chatDaoImpl;


    public void createNewTopic(TopicEntity topicEntity) {
        chatDaoImpl.createNewTopic(topicEntity);
    }

    public boolean isTopicExist(String topicName) {
        return chatDaoImpl.isTopicExist(topicName);
    }

    public List findChats(String findChatParam) {
        return chatDaoImpl.findChats(findChatParam);
    }

    public List showAllChats() {
        return chatDaoImpl.showAllChats();
    }

    public List showMyChats(int idUser) {
        return chatDaoImpl.showMyChats(idUser);
    }


    public TopicEntity getTopicByName(String requestParam) {
        return chatDaoImpl.getTopicByName(requestParam);
    }

    public TopicEntity getTopicById(int topicId) {
        return chatDaoImpl.getTopicById(topicId);
    }
}
