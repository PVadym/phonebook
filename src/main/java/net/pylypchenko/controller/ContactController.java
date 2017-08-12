package net.pylypchenko.controller;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.entity.User;
import net.pylypchenko.service.ContactService;
import net.pylypchenko.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Вадим on 09.08.2017.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    private ContactService contactService;
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(ContactController.class);

    @Autowired
    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    /**
     * The method defines models and view for page to making a new startup.
     *
     * @param userId id of user, that creates the startup.
     * @return models and view for page to making startup.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", new Contact());
        modelAndView.setViewName("addContact");
        return modelAndView;
    }

    /**
     * The method adds a new startup into DB.
     *
     * @param startup       a new investment, startup of {@link Startup}.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of page with all startups if startup was added successfully,
     * or or address for making startup otherwise.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute ("contact") Contact contact, BindingResult bindingResult,
                             @RequestParam ("username") String username) {
        if (bindingResult.hasErrors()) {
            LOGGER.error( "Errors in the add new Contact form");
            return "addContact";
        }
        User user = userService.findByUsername(username);
        contact.setUser(user);
        contact = contactService.add(contact);
        LOGGER.info( "Saving new Contact completed successfully with id = " +contact.getId() );
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editContact");
        modelAndView.addObject("contact", contactService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editContact(@Valid @ModelAttribute ("contact")Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error( "Errors in the edit Contact form");
            return "editContact";
        }
        contactService.update(contact);
        LOGGER.info( "Updating Contact completed successfully with id = " +contact.getId() );
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") int id) {

        contactService.remove(id);
        LOGGER.info( "Contact deleted successfully with id = " + id );
        return "redirect:/";
    }
}
