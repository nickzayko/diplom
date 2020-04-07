package controllers;

import entity.TopicEntity;
import entity.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserServiceImpl;
import utils.ReCaptchaValidator;
import validators.AuthorizationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    private String UUID = String.valueOf(java.util.UUID.randomUUID());

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthorizationValidator authorizationValidator;

    private final Logger LOGGER = Logger.getLogger(AuthorizationController.class);

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        LOGGER.info("You are in authorization controller");
        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String authorization(Model model, HttpSession session,
                                @ModelAttribute("userEntity") UserEntity userEntity, BindingResult result,
                                HttpServletRequest request) {
        authorizationValidator.validate(userEntity, result);
//        Из запроса получаем ReCaptcha
        String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println("gReCaptchaResponse: " + gReCaptchaResponse);
        boolean valid = ReCaptchaValidator.verify(gReCaptchaResponse);
        System.out.println("valid: " + valid);

        if (result.hasErrors() || !valid) {
            return "index";

        } else {

            userEntity = userServiceImpl.getUserByLoginAndPassword(userEntity.getUserLogin(), userEntity.getUserPassword());
            session.setAttribute("userId", userEntity.getIdUser());
            session.setAttribute("userName", userEntity.getUserName());
            model.addAttribute(userEntity);
            model.addAttribute("topicEntity", new TopicEntity());
            return "redirect:/menu/showMenu";

        }
    }

}
