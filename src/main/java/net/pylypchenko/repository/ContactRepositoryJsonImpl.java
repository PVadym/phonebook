package net.pylypchenko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pylypchenko.entity.Contact;
import net.pylypchenko.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 10.08.2017.
 */
@Repository
@Profile("profileJson")
public class ContactRepositoryJsonImpl implements ContactRepository {

    @Value("${json.db}")
    private String db;

    private final String JSON = ".json";

    private final String CONTACTS_FOLDER = File.separator + "contacts" + File.separator;


    @Override
    public Contact findById(int id) {
        ObjectMapper mapper = new ObjectMapper();
        Contact contact;
        File file = new File(db + CONTACTS_FOLDER + id + JSON);
        try {
            contact = mapper.readValue(file, Contact.class);
        } catch (IOException e) {
            return null;
        }
        return contact;
    }

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
            throw new RuntimeException(e);
        }
        return contacts;
    }

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
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public Contact update(Contact contact) {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(db + CONTACTS_FOLDER + contact.getId() + JSON);
        try {
            mapper.writeValue(file, contact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public void delete(int id) {
        File file = new File(db + CONTACTS_FOLDER + id + JSON);
        file.delete();
    }

}
