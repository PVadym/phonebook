package net.pylypchenko.controller;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.entity.User;
import net.pylypchenko.service.ContactService;
import net.pylypchenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Вадим on 08.08.2017.
 */
@Controller
public class MainController {

    private UserService userService;
    private ContactService contactService;

    @Autowired
    public MainController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @RequestMapping(
            value = { "", "/", "/index", "home" },
            method = RequestMethod.GET
    )
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getAuthenticatedUser();
        List<Contact> contacts = contactService.getAll()
                .stream()
                .filter(contact -> contact.getUser()!=null)
                .filter(contact -> user.getId()==contact.getUser().getId())
                .collect(Collectors.toList());

        modelAndView.addObject("contacts", contacts);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
