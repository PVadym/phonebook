package net.pylypchenko.service;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Class for testing {@link ContactServiceImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

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

    @Test(expected = NullPointerException.class)
    public void findById() throws Exception {

        when(contactRepository.findById(contact1.getId())).thenReturn(contact1);
        assertEquals(contactService.findById(contact1.getId()), contact1);
        when(contactRepository.findById(anyInt())).thenReturn(null);
        contactService.findById(anyInt());

        verify(contactRepository,times(2)).findById(anyInt());
    }

    @Test
    public void getAll() throws Exception {
        //prepare
        when(contactRepository.findAll()).thenReturn(contacts);
        //testing and validate
        assertEquals(contactService.getAll(),contacts);
        verify(contactRepository).findAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void add() throws Exception {
        //prepare
        when(contactRepository.save(contact1)).thenReturn(contact1);
        //testing
        Contact contactToTest = contactService.add(contact1);
        //validate
        verify(contactRepository).save(contact1);
        assertEquals(contactToTest,contact1);
        contactService.add(null);
    }

    @Test
    public void update() throws Exception {
        when(contactRepository.update(contact1)).thenReturn(contact1);

        contactService.update(contact1);
        verify(contactRepository).update(contact1);
    }

    @Test
    public void remove() throws Exception {
        doNothing().when(contactRepository).delete(contact1.getId());

        contactService.remove(contact1.getId());
        verify(contactRepository).delete(1);
    }

}