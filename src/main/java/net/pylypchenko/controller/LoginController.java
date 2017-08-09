package net.pylypchenko.controller;

import net.pylypchenko.entity.User;
import net.pylypchenko.enums.UserRole;
import net.pylypchenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Вадим on 09.08.2017.
 */
@Controller
public class LoginController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method returns page for authorization
     *
     * @return an address  of page for authorization
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult) {
//        userRegisterValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }

        String oldPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(oldPassword));
        userService.add(user);

        return "redirect:/index";
    }
}
