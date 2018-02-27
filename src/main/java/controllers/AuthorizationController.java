package controllers;

import entity.TopicEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;
import validators.AuthorizationValidator;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationValidator authorizationValidator;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null && session.getAttribute("userName") != null) {
            UserEntity userEntity = userService.getUser((Integer) session.getAttribute("userId"));
            model.addAttribute("userEntity", userEntity);
            model.addAttribute("topicEntity", new TopicEntity());
            return "mainPage";
        } else {
            model.addAttribute("userEntity", new UserEntity());
            return "index";
        }
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String authorization(Model model, HttpSession session,
                                @ModelAttribute("userEntity") UserEntity userEntity, BindingResult result) {
        authorizationValidator.validate(userEntity, result);
        if (result.hasErrors()) {
            return "index";
        } else {
            userEntity = userService.findPassword(userEntity.getUserLogin(), userEntity.getUserPassword());
            session.setAttribute("userId", userEntity.getIdUser());
            session.setAttribute("userName", userEntity.getUserName());
            userEntity = userService.getUser(userEntity.getIdUser());
            model.addAttribute(userEntity);
            model.addAttribute("topicEntity", new TopicEntity());
            return "mainPage";
        }
    }

}
