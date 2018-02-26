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
            UserEntity userEntity = userService.getUserEntityFromDataBase((Integer) session.getAttribute("userId"));
            model.addAttribute("userEntity", userEntity);
            model.addAttribute("topicEntity", new TopicEntity());
            return "mainPage";
        } else {
            model.addAttribute("userEntity", new UserEntity());
            model.addAttribute("informationAuthorization", "emptyString");
            return "index";
        }
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String authorization(Model model, HttpSession session,
                                @ModelAttribute("userEntity") UserEntity userEntity, BindingResult result) {
        authorizationValidator.validate(userEntity, result);
        if (result.hasErrors()) {
            model.addAttribute("informationAuthorization", "emptyString");
            return "index";
        } else {
            if (userService.isLoginExist(userEntity.getUserLogin())) {
                userEntity = userService.isPasswordCorrect(userEntity.getUserLogin(), userEntity.getUserPassword());
                if (userEntity != null) {
                    session.setAttribute("userId", userEntity.getIdUser());
                    session.setAttribute("userName", userEntity.getUserName());
                    userEntity = userService.getUserEntityFromDataBase(userEntity.getIdUser());
                    model.addAttribute(userEntity);
                    model.addAttribute("topicEntity", new TopicEntity());
                    return "mainPage";
                } else {
                    model.addAttribute("informationAuthorization", "wrongPassword");
                    return "index";
                }
            } else {
                model.addAttribute("informationAuthorization", "doNotExistUser");
                return "index";
            }
        }


    }
}
