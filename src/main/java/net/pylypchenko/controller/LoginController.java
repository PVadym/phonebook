package net.pylypchenko.controller;

import net.pylypchenko.entity.User;
import net.pylypchenko.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Class provides a set of methods for authorization of users in the system
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Controller
public class LoginController {

    /**
     * An instance of implementation {@link UserService} interface.
     */
    private UserService userService;

    /**
     * An instance of {@link PasswordEncoder}.
     */
    private PasswordEncoder passwordEncoder;

    /**
     * An instance of {@link Logger} for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    /**
     * Constructor.
     *
     * @param userService     an instance of implementation {@link UserService} interface.
     * @param passwordEncoder an instance of {@link PasswordEncoder}.
     */
    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method returns page for authorization
     *
     * @return an address of page for authorization
     */
    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public String loginPage() {
        return "login";
    }

    /**
     * Method serves for logout of users from the system
     *
     * @param request  a Http request
     * @param response a Http response
     * @return an address of logout page
     */
    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(
                request, response,
                SecurityContextHolder.getContext().getAuthentication()
        );
        return "redirect:/login?logout";
    }

    /**
     * Method defines models and view for page to add a new user.
     *
     * @return model and view for page to add a new user.
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Method to add a new user.
     *
     * @param user          is a {@link User} entity from request.
     * @param bindingResult is a {@link BindingResult} with information about user.
     * @param model         is a {@link Model}
     * @return an address of main page or registration page in case errors presents in registration form
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            LOGGER.error("Errors in the registration form");
            return "registration";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("username_error", "User with username \"" + user.getUsername() + "\" already exist");
            LOGGER.error("Errors in the registration form. User with username " + user.getUsername() + " already exist");
            return "registration";
        }
        String oldPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPassword));
        user = userService.add(user);

        LOGGER.info("Registration completed successfully with id = " + user.getId());

        return "redirect:/index";
    }
}
