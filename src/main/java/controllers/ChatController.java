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
import service.ChatService;
import service.MessageService;
import service.UserService;
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

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private NewTopicValidation topicValidation;

    @Autowired
    private ValidationOfMessageText validationOfMessageText;

    @RequestMapping(value = "/showMenu", method = RequestMethod.GET)
    public String showMenu(Model model, HttpSession session) {
        UserEntity userEntity = userService.getUser((Integer) session.getAttribute("userId"));
        model.addAttribute(userEntity);
        model.addAttribute("topicEntity", new TopicEntity());
        return "mainPage";
    }

    //создание нового чата и переход в него................................................
    @RequestMapping(value = "/createTopic", method = RequestMethod.GET)
    public String createNewTopic(Model model, HttpSession session) {
        if (session.getAttribute("topicId") != null) {
            TopicEntity topicEntity = chatService.getTopicById((Integer) session.getAttribute("topicId"));
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
                topicEntity.setUserEntity(userService.getUser((Integer) session.getAttribute("userId")));
                chatService.createNewTopic(topicEntity);
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
        List listOfExistChats = chatService.findChats(String.valueOf(session.getAttribute("findChatParam")));
        model.addAttribute("listOfExistChats", listOfExistChats);
        return "resultOfFindChats";
    }

    @RequestMapping(value = "/findChat", method = RequestMethod.POST)
    public String findChat(@RequestParam(name = "findChat") String findChatParam, Model model,
                           HttpSession session) {
        List listOfExistChats = chatService.findChats(findChatParam);
        model.addAttribute("listOfExistChats", listOfExistChats);
        session.setAttribute("findChatParam", findChatParam);
        return "resultOfFindChats";
    }

    @RequestMapping(value = "/showMyChats", method = RequestMethod.GET)
    public String showMyChats(Model model, HttpSession session) {
        int idUser = (int) session.getAttribute("userId");
        model.addAttribute("listOfExistChats", chatService.showMyChats(idUser));
        return "resultOfFindChats";
    }
    //............................................................................

    //выход из приложения..........................................................
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exitFromApplication(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("userEntity", new UserEntity());
        model.addAttribute("informationAuthorization", "emptyString");
        return "index";
    }
    //....................................................................................

    //переход в чат, по результатам поиска................................................
    @RequestMapping(value = "/goToChatPage", method = RequestMethod.GET)
    public String goToChatPage(Model model, HttpSession session) {
        TopicEntity topicEntity = chatService.getTopicById((Integer) session.getAttribute("topicId"));
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        return "chatPage";
    }

    @RequestMapping(value = "/goToChatPage", method = RequestMethod.POST)
    public String goToChatPage(Model model, HttpSession session,
                               @RequestParam(name = "topic") String requestParam) {
        TopicEntity topicEntity = chatService.getTopicByName(requestParam);
        session.setAttribute("topicId", topicEntity.getIdTopic());
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        session.setAttribute("numberOfPages", 0);
        return "chatPage";
    }
    //..............................................................................................

    //обработчик сообщений в чате....................................................................
    @RequestMapping(value = "/messageHandler", method = RequestMethod.GET)
    public String messageHandler(Model model, HttpSession session) {
        TopicEntity topicEntity = chatService.getTopicById((Integer) session.getAttribute("topicId"));
        sendToPageInformationAboutChat(topicEntity, model);
        getMessagesForChat(session, model);
        return "chatPage";
    }

    //    блок обработки отрправленных сообщением, для аякса..................................................
    @ResponseBody
    @RequestMapping(value = "/messageHandler", method = RequestMethod.GET, produces = "application/json")
    public MessageDTO messageHandler(Model model, HttpSession session,
                                     @RequestParam("text") String text) {
        UserEntity userEntity = userService.getUser((Integer) session.getAttribute("userId"));
        TopicEntity topicEntity = chatService.getTopicById((Integer) session.getAttribute("topicId"));
        LocalDateTime localDateTime = LocalDateTime.now();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUserEntity(userEntity);
        messageEntity.setTopicEntity(topicEntity);
        messageEntity.setLocalDateTime(localDateTime);
        model.addAttribute("topicName", topicEntity.getTopicName());
        model.addAttribute("topicCreator", topicEntity.getUserEntity().getUserName());
        session.setAttribute("numberOfPages", 0);
        messageEntity.setTextOfMessage(text);
        messageService.saveMessage(messageEntity);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserName(messageEntity.getUserEntity().getUserName());
        messageDTO.setTextOfMessage(messageEntity.getTextOfMessage());
        return messageDTO;
    }
    //    ..................................................................................................

    //......для выборки предыдущих сообщений............................................................
    @ResponseBody
    @RequestMapping(value = "/loadPreviousMessages", produces = "application/json", method = RequestMethod.GET)
    public List loadPreviousMessages(Model model, HttpSession session) {
        TopicEntity topicEntity = getTopicEntityById(session);
        List previousMessages = messageService.getPreviousMessages(topicEntity, session);
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
            List newMessages = messageService.getNewMessages(topicEntity, time);
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
        List messageEntityList = messageService.getMessagesByTopicEntity(topicEntity);
        if (messageEntityList.size() > 0) {
            model.addAttribute("messagesList", messageEntityList);
            MessageEntity messageEntity = (MessageEntity) messageEntityList.get(messageEntityList.size() - 1);
            model.addAttribute("lastMessageTime", messageEntity.getLocalDateTime());
        }
    }

    private TopicEntity getTopicEntityById(HttpSession session) {
        return chatService.getTopicById((Integer) session.getAttribute("topicId"));
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
