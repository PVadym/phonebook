package net.pylypchenko.controller;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.entity.User;
import net.pylypchenko.service.ContactService;
import net.pylypchenko.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing {@link ContactController}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ContactController.class)
@WithMockUser
public class ContactControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private ContactService contactService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAddPage() throws Exception {
        mockMvc.perform(get("/contact/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addContact"));
    }

    @Test
    public void addContactSuccessfully() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(new User());
        when(contactService.add(any())).thenReturn(new Contact());
        mockMvc.perform(post("/contact/add")
                .param("username", "username")
                .param("firstName", "firstName")
                .param("middleName", "middleName")
                .param("lastName", "lastName")
                .param("mobilePhone", "+380(68)8083780")
                .param("email", "a@ukr.net")
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void addContactValidError() throws Exception {
        mockMvc.perform(post("/contact/add")
                .param("username", "username")
                .param("firstName", "firstName")
                .param("middleName", "middleName")
                .param("lastName", "lastName")
                .param("mobilePhone", "+380688083780")
                .param("email", "")
        )
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/addContact.jsp"));
    }


    @Test
    public void getEditPage() throws Exception {
        Contact contact = getContact();
        when(contactService.findById(contact.getId())).thenReturn(contact);
        mockMvc.perform(get("/contact/update/{id}",contact.getId()))
                .andExpect(status().isOk())
                .andExpect((model().attributeExists("contact")))
                .andExpect(view().name("editContact"));
        verify(contactService, times(1)).findById(contact.getId());
    }


    @Test
    public void editContactSuccessfully() throws Exception {

        when(contactService.update(any())).thenReturn(new Contact());
        mockMvc.perform(get("/contact/edit")
                .param("firstName", "firstName")
                .param("middleName", "middleName")
                .param("lastName", "lastName")
                .param("mobilePhone", "+380(68)8083780")
                .param("email", "a@ukr.net")
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"));
        verify(contactService,times(1)).update(any());
    }

    @Test
    public void editContactValidError() throws Exception {

        mockMvc.perform(get("/contact/edit")
                .param("username", "username")
                .param("firstName", "firstName")
                .param("middleName", "middleName")
                .param("lastName", "lastName")
                .param("mobilePhone", "+380688083780")
                .param("email", "a")
        )
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/editContact.jsp"));
    }

    @Test
    public void deleteContact() throws Exception {
        Contact contact = getContact();
        doNothing().when(contactService).remove(contact.getId());
        mockMvc.perform(get("/contact/delete/{id}",contact.getId()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));

        verify(contactService,times(1)).remove(contact.getId());
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setId(1);
        return contact;
    }

}