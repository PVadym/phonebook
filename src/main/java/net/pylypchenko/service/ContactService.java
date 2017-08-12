package net.pylypchenko.service;

import net.pylypchenko.entity.Contact;

import java.util.List;

/**
 * Interface provides a set of methods for the operation with entity {@link Contact}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
public interface ContactService {

    /**
     * The method finds contact by ID
     *
     * @param id an unique identifier.
     * @return contact with entered id
     */
    Contact findById(int id);

    /**
     * The Method gets all contacts
     *
     * @return a collection of all contacts
     */
    List<Contact> getAll();

    /**
     * The Method adds a new contact
     *
     * @param contact a contact to add
     * @return added contact
     */
    Contact add(Contact contact);

    /**
     * The Method updates a contact
     *
     * @param contact a contact to update
     * @return updated contact
     */
    Contact update(Contact contact);

    /**
     * The Method removes contact by id
     *
     * @param id a unique identifier of contact, that needs to be removed
     */
    void remove(int id);

}
