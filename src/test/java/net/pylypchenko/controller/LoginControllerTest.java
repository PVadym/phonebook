package net.pylypchenko.controller;

import net.pylypchenko.entity.User;
import net.pylypchenko.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing {@link LoginController}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void loginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void logoutPage() throws Exception {
        mockMvc.perform(logout("/logout"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/login?logout"));
    }

    @Test
    @WithMockUser
    public void getRegistrationPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    @WithMockUser
    public void registerUserValid() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(null);
        when(userService.add(any())).thenReturn(new User());
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","username")
                .param("password","12345")
                .param("fullName","Username")
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/index"));
    }

    @Test
    @WithMockUser
    public void registerUserValidError() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(null);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","")
                .param("password","12345")
                .param("fullName","Username")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    @WithMockUser
    public void registerUserAlreadyExist() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(new User());
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","username")
                .param("password","12345")
                .param("fullName","Username")
        )
                .andExpect((model().attributeExists("username_error")))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

}