package controllers;

import entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

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
