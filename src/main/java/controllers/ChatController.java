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

import javax.servlet.http.HttpSession;
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

    //создание нового чата и переход в него..............
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
            if (chatService.isTopicExist(topicEntity.getTopicName())) {
                model.addAttribute("informationMainPage", "такая тема создана");
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

    @RequestMapping(value = "/messageHandler", method = RequestMethod.POST)
    public String messageHandler(Model model, HttpSession session,
                                 @ModelAttribute("messageEntity") MessageEntity messageEntity, BindingResult result) {
        validationOfMessageText.validate(messageEntity, result);
        UserEntity userEntity = userService.getUser((Integer) session.getAttribute("userId"));
        TopicEntity topicEntity = chatService.getTopicById((Integer) session.getAttribute("topicId"));
        messageEntity.setUserEntity(userEntity);
        messageEntity.setTopicEntity(topicEntity);
        model.addAttribute("topicName", topicEntity.getTopicName());
        model.addAttribute("topicCreator", topicEntity.getUserEntity().getUserName());
        if (result.hasErrors()) {
            getMessagesForChat(session, model);
            return "chatPage";
        } else {
            messageService.saveMessage(messageEntity);
            getMessagesForChat(session, model);
            return "chatPage";
        }
    }

    //------Для ajax------обновление сообщений-------------
    @ResponseBody
    @RequestMapping(value = "/updateMessages", produces = "application/json")
    public List updateMessages(HttpSession session, Model model) {
        List messagesDTOList = new ArrayList();
        List messageEntityList = messageService.getMessagesByTopicId((Integer) session.getAttribute("topicId"));
        for (int i = 0; i < messageEntityList.size(); i++) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setTextOfMessage(((MessageEntity) messageEntityList.get(i)).getTextOfMessage());
            messageDTO.setUserName(((MessageEntity) messageEntityList.get(i)).getUserEntity().getUserName());
            messagesDTOList.add(messageDTO);
        }
        return messagesDTOList;
    }
    //...........................................................................................

    private void sendToPageInformationAboutChat(TopicEntity topicEntity, Model model) {
        model.addAttribute("topicName", topicEntity.getTopicName());
        model.addAttribute("topicCreator", topicEntity.getUserEntity().getUserName());
        model.addAttribute("messageEntity", new MessageEntity());
    }

    private void getMessagesForChat(HttpSession session, Model model) {
        List messageEntityList = messageService.getMessagesByTopicId((Integer) session.getAttribute("topicId"));
        model.addAttribute("messagesList", messageEntityList);
    }

}
