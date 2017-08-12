package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;

/**
 * Interface provides a set of methods with entity {@link Contact}
 * for the operation in a database.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@NoRepositoryBean
public interface ContactRepository{

    /**
     * The method finds contact by ID in database
     *
     * @param id an unique identifier.
     * @return contact with entered id
     */
    Contact findById(int id);

    /**
     * The Method gets all contacts from database
     *
     * @return a collection of all contacts from database
     */
    List<Contact> findAll();

    /**
     * The Method adds a new contact into database
     *
     * @param contact a contact to add
     * @return added contact
     */
    Contact save(Contact contact);

    /**
     * The Method updates a contact in database
     *
     * @param contact a contact to update
     * @return updated contact
     */
    Contact update(Contact contact);

    /**
     * The Method removes contact from database by id
     *
     * @param id a unique identifier of contact, that needs to be removed
     */
    void delete(int id);
}
