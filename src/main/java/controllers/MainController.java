package controllers;

import entity.TopicEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String main(Model model, HttpSession session) {
        model.addAttribute("userEntity", new UserEntity());
        model.addAttribute("informationAuthorization", "emptyString");
        if (session.getAttribute("userId") != null && session.getAttribute("userName") != null) {
            return "redirect:/menu/showMenu";
        } else {
            return "index";
        }
    }
}
