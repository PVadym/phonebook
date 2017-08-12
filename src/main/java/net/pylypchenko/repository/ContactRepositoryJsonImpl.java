package net.pylypchenko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pylypchenko.entity.Contact;
import net.pylypchenko.utils.IdGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class provides a set of methods with entity {@link Contact} for the operation in a database,
 * implements {@link ContactRepository} in case active profile "profileJson"
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Repository
@Profile("profileJson")
public class ContactRepositoryJsonImpl implements ContactRepository {

    /**
     * Directory of Json database
     */
    @Value("${json.db}")
    private String db;

    /**
     * Suffix of stored files
     */
    private final String JSON = ".json";

    /**
     * Folder of storage
     */
    private final String CONTACTS_FOLDER = File.separator + "contacts" + File.separator;

    /**
     * An instance of Logger for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(ContactRepositoryJsonImpl.class);

    /**
     * The method finds contact by ID in database
     *
     * @param id an unique identifier.
     * @return contact with entered id or NULL in case if contact with entered ID is not exist in database
     */
    @Override
    public Contact findById(int id) {
        ObjectMapper mapper = new ObjectMapper();
        Contact contact;
        File file = new File(db + CONTACTS_FOLDER + id + JSON);
        try {
            contact = mapper.readValue(file, Contact.class);
        } catch (IOException e) {
            LOGGER.error("Contact with id =  " + id + "does not exist in db");
            return null;
        }
        return contact;
    }

    /**
     * The Method gets all contacts from database
     *
     * @return a collection of all contacts from database
     * @throws RuntimeException in case error during getting collection
     */
    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File folderContacts = new File(db + CONTACTS_FOLDER);
        try {
            File[] files = folderContacts.listFiles();
            if (files != null) {
                for (File file : files) {
                    contacts.add(mapper.readValue(file, Contact.class));
                }
            }

        } catch (IOException e) {
            LOGGER.error("Error during findAll() method");
            throw new RuntimeException(e);
        }
        return contacts;
    }


    /**
     * The Method adds a new contact into database
     *
     * @param contact a contact to add
     * @return added contact
     * @throws RuntimeException in case error during saving user
     */
    @Override
    public Contact save(Contact contact) {
        contact.setId(IdGenerator.getId());
        ObjectMapper mapper = new ObjectMapper();
        File folder = new File(db + CONTACTS_FOLDER);
        File file = new File(db + CONTACTS_FOLDER + contact.getId() + JSON);
        try {
            if (!file.exists())
                if (!folder.exists()) {
                    if (folder.mkdirs() && file.createNewFile()) {
                        mapper.writeValue(file, contact);
                    }
                } else {
                    mapper.writeValue(file, contact);
                }
        } catch (IOException e) {
            LOGGER.error("Error during saving new Contact with id = " + contact.getId());
            throw new RuntimeException(e);
        }
        return contact;
    }

    /**
     * The Method updates a contact in database
     *
     * @param contact a contact to update
     * @return updated contact
     * @throws RuntimeException in case error during saving user
     */
    @Override
    public Contact update(Contact contact) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(db + CONTACTS_FOLDER + contact.getId() + JSON);
        try {
            mapper.writeValue(file, contact);
        } catch (IOException e) {
            LOGGER.error("Error during updating Contact with id = " + contact.getId());
            throw new RuntimeException(e);
        }
        return contact;
    }

    /**
     * The Method removes contact from database by id
     *
     * @param id a unique identifier of contact, that needs to be removed
     */
    @Override
    public void delete(int id) {
        File file = new File(db + CONTACTS_FOLDER + id + JSON);
        file.delete();
    }
}
