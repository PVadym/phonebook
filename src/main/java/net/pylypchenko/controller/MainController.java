package net.pylypchenko.controller;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.entity.User;
import net.pylypchenko.service.ContactService;
import net.pylypchenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for initial processing of user's requests
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * An instance of implementation {@link ContactService} interface.
     */
    private ContactService contactService;


    /**
     * Constructor.
     *
     * @param userService    an instance of implementation {@link UserService} interface.
     * @param contactService an instance of {@link ContactService}.
     */
    @Autowired
    public MainController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    /**
     * Method defines models and view for index page
     *
     * @return model and view of index page
     */
    @RequestMapping(
            value = {"", "/", "/index", "home"},
            method = RequestMethod.GET
    )
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getAuthenticatedUser();
        List<Contact> contacts = contactService.getAll()
                .stream()
                .filter(contact -> contact.getUser() != null)
                .filter(contact -> user.getId() == contact.getUser().getId())
                .collect(Collectors.toList());

        modelAndView.addObject("contacts", contacts);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
