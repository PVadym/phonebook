package net.pylypchenko.service;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class provides a set of methods for operations with {@link Contact},
 * implementation of {@link ContactService}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Service
public class ContactServiceImpl implements ContactService {

    /**
     * An instance of {@link ContactRepository}
     */
    private ContactRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements {@link ContactService},
     *                   interface for working with {@link Contact}
     */
    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    /**
     * The method finds contact by ID
     *
     * @param id an unique identifier.
     * @return contact with entered id
     * @throws NullPointerException in case if contact with entered ID does not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public Contact findById(int id) {
        Contact contact = repository.findById(id);
        if (contact == null) {
            throw new NullPointerException("Can't find contact with id = " + id);
        }
        return contact;
    }

    /**
     * The Method gets all contacts
     *
     * @return a collection of all contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contact> getAll() {
        return repository.findAll();
    }

    /**
     * The Method adds a new contact
     *
     * @param contact a contact to add
     * @return added contact
     * @throws IllegalArgumentException in case if user is NULL
     */
    @Override
    @Transactional
    public Contact add(Contact contact) throws IllegalArgumentException {
        if (contact == null) {
            throw new IllegalArgumentException("Trying to save 'null'");
        }
        return repository.save(contact);
    }

    /**
     * The Method updates a contact
     *
     * @param contact a contact to update
     * @return updated contact
     */
    @Override
    @Transactional
    public Contact update(Contact contact) {
        return repository.update(contact);
    }

    /**
     * The Method removes contactby id
     *
     * @param id a unique identifier of contact, that needs to be removed
     */
    @Override
    @Transactional
    public void remove(int id) {
        repository.delete(id);
    }

}
