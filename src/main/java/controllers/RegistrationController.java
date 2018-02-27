package controllers;

import dao.UserDao;
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
import validators.RegistrationValidator;

import javax.servlet.http.HttpSession;


@Controller
public class RegistrationController {

    @Autowired
    public UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, HttpSession session,
                               @ModelAttribute("userEntity") UserEntity userEntity, BindingResult result) {
        registrationValidator.validate(userEntity, result);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.createUser(userEntity);
            model.addAttribute("topicEntity", new TopicEntity());
            session.setAttribute("userId", userEntity.getIdUser());
            session.setAttribute("userName", userEntity.getUserName());
            return "mainPage";
        }
    }
}

