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
 * The class is responsible of processing {@link Contact} entity related requests
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private ContactService contactService;

    /**
     * An instance of implementation {@link ContactService} interface.
     */
    private UserService userService;

    /**
     * An instance of {@link Logger} for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(ContactController.class);

    /**
     * Constructor.
     *
     * @param contactService an instance of {@link ContactService}.
     * @param userService    an instance of implementation {@link UserService} interface.
     */
    @Autowired
    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    /**
     * The method defines models and view for page to making a new contact.
     *
     * @return models and view for form page of new contact.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", new Contact());
        modelAndView.setViewName("addContact");
        return modelAndView;
    }

    /**
     * The method adds a new contact into database.
     *
     * @param contact       a new contact{@link Contact}.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of main page or form contact page in case errors presents
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult,
                             @RequestParam("username") String username) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Errors in the add new Contact form");
            return "addContact";
        }
        User user = userService.findByUsername(username);
        contact.setUser(user);
        contact = contactService.add(contact);
        LOGGER.info("Saving new Contact completed successfully with id = " + contact.getId());
        return "redirect:/";
    }

    /**
     * The method defines models and view for page to edit contact.
     *
     * @return models and view for form page of contact to edit
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editContact");
        modelAndView.addObject("contact", contactService.findById(id));
        return modelAndView;
    }

    /**
     * The method to edit a contact.
     *
     * @param contact       a instance of {@link Contact}.
     * @param bindingResult an instance of implementation {@link BindingResult}.
     * @return an address of main page or edit form contact page in case errors presents
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Errors in the edit Contact form");
            return "editContact";
        }
        contactService.update(contact);
        LOGGER.info("Updating Contact completed successfully with id = " + contact.getId());
        return "redirect:/";
    }

    /**
     * Method for removing contact.
     *
     * @param id an id of contact to removing.
     * @return an address of main page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") int id) {
        contactService.remove(id);
        LOGGER.info("Contact deleted successfully with id = " + id);
        return "redirect:/";
    }
}
