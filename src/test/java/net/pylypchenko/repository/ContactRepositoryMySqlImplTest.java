package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link ContactRepositoryMySqlImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryMySqlImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private ContactRepositoryMySqlImpl contactRepository;

    private Contact contact1;
    private Contact contact2;

    @Before
    public void setUp() throws Exception {
        contact1 = new Contact();
        contact2 = new Contact();
    }

    @Test
    public void findById() throws Exception {

        //prepare
        when(entityManager.find(Contact.class, 1)).thenReturn(contact1);

        //testing
        Contact contactToTest = contactRepository.findById(1);

        //validate
        verify(entityManager).find(Contact.class,1);
        assertEquals(contactToTest,contact1);
    }

    @Test
    public void findAll() throws Exception {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        //prepare
        when(entityManager.createQuery("select c from Contact c")).thenReturn(query);
        when(query.getResultList()).thenReturn(contacts);

        //testing
        List<Contact> contactsToTest = contactRepository.findAll();

        //validate
        verify(entityManager).createQuery("select c from Contact c");
        assertTrue(contactsToTest.equals(contacts));
    }

    @Test
    public void saveAndUpdate() throws Exception {
        //prepare
        Contact savedContact = new Contact();
        savedContact.setId(1);
        when(entityManager.merge(contact1)).thenReturn(savedContact);

        //testing
        Contact contactToTest = contactRepository.save(contact1);
        Contact updatedContact = contactRepository.update(contact1);

        //validate
        verify(entityManager, times(2)).merge(contact1);
        assertEquals(contactToTest,savedContact);
        assertEquals(updatedContact,savedContact);
    }


    @Test
    public void delete() throws Exception {
        //prepare
        when(entityManager.find(Contact.class,1)).thenReturn(contact1);

        //testing
        contactRepository.delete(1);

        //validate
        verify(entityManager).find(Contact.class,1);
        verify(entityManager).remove(contact1);

    }

}