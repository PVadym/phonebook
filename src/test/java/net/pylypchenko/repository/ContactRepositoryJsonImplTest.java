package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class for testing {@link ContactRepositoryJsonImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryJsonImplTest {


    private ContactRepositoryJsonImpl contactRepository;

    private Contact contact1;
    private Contact contact2;
    private Field db;

    public ContactRepositoryJsonImplTest() {
        this.contactRepository = new ContactRepositoryJsonImpl();
        this.contact1 = new Contact();
        this.contact1.setId(1);
        this.contact2 = new Contact();
        this.contact2.setId(2);
    }

    @Before
    public void setUp() throws Exception {
        Class v = contactRepository.getClass();
        db = v.getDeclaredField("db");
        db.setAccessible(true);
        db.set(contactRepository,"D:\\jsondbTest\\");
    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(new File("D:\\jsondbTest\\" + File.separator + "contacts"));
    }

    @AfterClass
    public static void afterTest() throws Exception {
        FileUtils.deleteDirectory(new File("D:\\jsondbTest\\"));

    }

    @Test
    public void findById() throws Exception {
        contactRepository.save(contact1);
        assertNotNull(contactRepository.findById(contact1.getId()));
        assertEquals(contact1, contactRepository.findById(contact1.getId()));

        db.set(contactRepository,null);
        assertNull(contactRepository.findById(contact1.getId()));
    }

    @Test
    public void findAll() throws Exception {
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        List<Contact> contacts = contactRepository.findAll();
        assertNotNull(contacts);
        assertTrue(contacts.contains(contact1)&&contacts.contains(contact2));

        db.set(contactRepository, null);
        assertTrue(contactRepository.findAll().isEmpty());

    }

    @Test(expected = RuntimeException.class)
    public void update() throws Exception {
        contactRepository.save(contact1);
        Contact contactToUpdate = contactRepository.findById(contact1.getId());
        contactToUpdate.setLastName("lastname");
        Contact contactAfterUpdate = contactRepository.update(contactToUpdate);
        assertEquals("lastname", contactAfterUpdate.getLastName());

        db.set(contactRepository,null);
        contactRepository.update(contactToUpdate);
    }

    @Test
    public void delete() throws Exception {
        Contact contactAfterSave = contactRepository.save(contact1);
        assertNotNull(contactRepository.findById(contactAfterSave.getId()));
        contactRepository.delete(contactAfterSave.getId());
        assertNull(contactRepository.findById(contactAfterSave.getId()));

    }

}