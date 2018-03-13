package controllers;

import dto.MessageDTO;
import entity.MessageEntity;
import entity.TopicEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.ChatServiceImpl;
import service.MessageServiceImpl;
import service.UserServiceImpl;
import validators.NewTopicValidation;
import validators.ValidationOfMessageText;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class ChatController {

    private final int ONE = 1;
    private final int START = 0;

    @Autowired
    private ChatServiceImpl chatServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private NewTopicValidation topicValidation;

    @Autowired
    private ValidationOfMessageText validationOfMessageText;

    @RequestMapping(value = "/showMenu", method = RequestMethod.GET)
    public String showMenu(Model model, HttpSession session) {
        UserEntity userEntity = userServiceImpl.getUser((Integer) session.getAttribute("userId"));
        model.addAttribute(userEntity);
        model.addAttribute("topicEntity", new TopicEntity());
        return "mainPage";
    }

    //создание нового чата и переход в него................................................
    @RequestMapping(value = "/createTopic", method = RequestMethod.GET)
    public String createNewTopic(Model model, HttpSession session) {
        if (session.getAttribute("topicId") != null) {
            TopicEntity topicEntity = chatServiceImpl.getTopicById((Integer) session.getAttribute("topicId"));
            sendToPageInformationAboutChat(topicEntity, model);
            return "chatPage";
        } else {
            model.addAttribute("topicEntity", new TopicEntity());
            return "mainPage";
        }
    }

    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public String createNewTopic(Model model, HttpSession session,
                                 @ModelAttribute("topicEntity") TopicEntity topicEntity, BindingResult result) {
        topicValidation.validate(topicEntity, result);
        if (result.hasErrors()) {
            return "mainPage";
        } else {
            if (session.getAttribute("userId") != null && session.getAttribute("userName") != null) {
                topicEntity.setUserEntity(userServiceImpl.getUser((Integer) session.getAttribute("userId")));
                chatServiceImpl.createNewTopic(topicEntity);
                session.setAttribute("topicId", topicEntity.getIdTopic());
                sendToPageInformationAboutChat(topicEntity, model);
                return "chatPage";
            } else {
                return "mainPage";
            }
        }
    }
    //....................................................................

    //поиск чатов .........................................................
    @RequestMapping(value = "/findChat", method = RequestMethod.GET)
    public String findChat(HttpSession session, Model model) {
        List listOfExistChats = chatServiceImpl.findChats(String.valueOf(session.getAttribute("findChatParam")));
        model.addAttribute("listOfExistChats", listOfExistChats);
        return "resultOfFindChats";
    }

    @RequestMapping(value = "/findChat", method = RequestMethod.POST)
    public String findChat(@RequestParam(name = "findChat") String findChatParam, Model model,
                           HttpSession session) {
        List listOfExistChats = chatServiceImpl.findChats(findChatParam);
        model.addAttribute("listOfExistChats", listOfExistChats);
        session.setAttribute("findChatParam", findChatParam);
        return "resultOfFindChats";
    }

    @RequestMapping(value = "/showMyChats", method = RequestMethod.GET)
    public String showMyChats(Model model, HttpSession session) {
        int idUser = (int) session.getAttribute("userId");
        model.addAttribute("listOfExistChats", chatServiceImpl.showMyChats(idUser));
        return "resultOfFindChats";
    }
    //............................................................................

    //выход из приложения..........................................................
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exitFromApplication(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("userEntity", new UserEntity());
//        model.addAttribute("informationAuthorization", "emptyString");
        return "redirect:/authorization";
    }
    //....................................................................................

    //переход в чат, по результатам поиска................................................
    @RequestMapping(value = "/goToChatPage", method = RequestMethod.GET)
    public String goToChatPage(Model model, HttpSession session) {
        TopicEntity topicEntity = chatServiceImpl.getTopicById((Integer) session.getAttribute("topicId"));
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        return "chatPage";
    }

    @RequestMapping(value = "/goToChatPage", method = RequestMethod.POST)
    public String goToChatPage(Model model, HttpSession session,
                               @RequestParam(name = "topic") String requestParam) {
        TopicEntity topicEntity = chatServiceImpl.getTopicByName(requestParam);
        session.setAttribute("topicId", topicEntity.getIdTopic());
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        session.setAttribute("numberOfPages", START);
        return "chatPage";
    }
    //..............................................................................................

    //обработчик сообщений в чате....................................................................
    @RequestMapping(value = "/messageHandler", method = RequestMethod.GET)
    public String messageHandler(Model model, HttpSession session) {
        TopicEntity topicEntity = chatServiceImpl.getTopicById((Integer) session.getAttribute("topicId"));
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        return "chatPage";
    }

    //    блок обработки отрправленных сообщением, для аякса..................................................
    @ResponseBody
    @RequestMapping(value = "/messageHandler", method = RequestMethod.GET, produces = "application/json")
    public MessageDTO messageHandler(Model model, HttpSession session,
                                     @RequestParam("text") String text) {
        UserEntity userEntity = userServiceImpl.getUser((Integer) session.getAttribute("userId"));
        TopicEntity topicEntity = chatServiceImpl.getTopicById((Integer) session.getAttribute("topicId"));
        LocalDateTime localDateTime = LocalDateTime.now();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUserEntity(userEntity);
        messageEntity.setTopicEntity(topicEntity);
        messageEntity.setLocalDateTime(localDateTime);
        model.addAttribute("topicName", topicEntity.getTopicName());
        model.addAttribute("topicCreator", topicEntity.getUserEntity().getUserName());
        session.setAttribute("numberOfPages", START);
        messageEntity.setTextOfMessage(text);
        messageServiceImpl.saveMessage(messageEntity);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserName(messageEntity.getUserEntity().getUserName());
        messageDTO.setTextOfMessage(messageEntity.getTextOfMessage());
        return messageDTO;
    }
    //    ..................................................................................................

    //......для выборки предыдущих сообщений............................................................
    @ResponseBody
    @RequestMapping(value = "/loadPreviousMessages", produces = "application/json", method = RequestMethod.GET)
    public List loadPreviousMessages(HttpSession session) {
        TopicEntity topicEntity = getTopicEntityById(session);
        session.setAttribute("numberOfPages", (Integer)session.getAttribute("numberOfPages") + ONE);
        List previousMessages = messageServiceImpl.getPreviousMessages(topicEntity, (Integer)session.getAttribute("numberOfPages"));
        return getMessagesDTOList(previousMessages);
    }
    //...........................................................................................

    //...........сервер для выборки новых сообщений............................................
    @ResponseBody
    @RequestMapping(value = "/findNewMessages", produces = "application/json")
    public List findNewMessages(HttpSession session, HttpServletRequest request) {

        LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
        if (time != null) {
            TopicEntity topicEntity = getTopicEntityById(session);
            List newMessages = messageServiceImpl.getNewMessages(topicEntity, time);
            return getMessagesDTOList(newMessages);
        }
        return null;
    }
    //.........................................................................................


    //    ниже представлены общие методы, которые вынес....................................................
    private void sendToPageInformationAboutChat(TopicEntity topicEntity, Model model) {
        model.addAttribute("topicName", topicEntity.getTopicName());
        model.addAttribute("topicCreator", topicEntity.getUserEntity().getUserName());
        model.addAttribute("messageEntity", new MessageEntity());
    }

    private void getMessagesForChat(HttpSession session, Model model) {
        TopicEntity topicEntity = getTopicEntityById(session);
        List messageEntityList = messageServiceImpl.getMessagesByTopicEntity(topicEntity);
        if (messageEntityList.size() > 0) {
            model.addAttribute("messagesList", messageEntityList);
            MessageEntity messageEntity = (MessageEntity) messageEntityList.get(messageEntityList.size() - ONE);
            model.addAttribute("lastMessageTime", messageEntity.getLocalDateTime());
        }
    }

    private TopicEntity getTopicEntityById(HttpSession session) {
        return chatServiceImpl.getTopicById((Integer) session.getAttribute("topicId"));
    }

    private List getMessagesDTOList(List listOfMessages) {
        List messagesDTOList = new ArrayList();
        for (int i = 0; i < listOfMessages.size(); i++) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setTextOfMessage(((MessageEntity) listOfMessages.get(i)).getTextOfMessage());
            messageDTO.setUserName(((MessageEntity) listOfMessages.get(i)).getUserEntity().getUserName());
            messagesDTOList.add(messageDTO);
        }
        return messagesDTOList;
    }
    //.............................................................................................................


}
