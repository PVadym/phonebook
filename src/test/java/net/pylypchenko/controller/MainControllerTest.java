package net.pylypchenko.controller;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.service.ContactService;
import net.pylypchenko.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing {@link MainController}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = MainController.class)
public class MainControllerTest {


    @MockBean
    private UserService userService;
    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mockMvc;

    private Contact contact1;
    private Contact contact2;
    private List<Contact> contacts = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        contact1 = new Contact();
        contact1.setId(1);
        contact1.setLastName("lastname1");
        contact2 = new Contact();
        contact2.setId(2);
        contact2.setLastName("lastname2");
        contacts.add(contact1);
        contacts.add(contact2);
    }

    @Test
    @WithMockUser
    public void getIndexPageAuthenticatedUser() throws Exception {
        when(contactService.getAll()).thenReturn(contacts);
        ResultActions result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect((model().attributeExists("contacts")))
                .andExpect(view().name("index"));

        MvcResult mvcResult= result.andReturn();
        List<Contact> contactList = (List<Contact>) mvcResult.getModelAndView().getModel().get("contacts");
        assertEquals(contactList, Collections.emptyList());
    }

    @Test
    public void getIndexPageUnauthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(401));

    }

}