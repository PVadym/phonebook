package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * A class provides a set of methods with entity {@link Contact} for the operation in a database,
 * implements {@link ContactRepository} in case active profile "profileMysql"
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Repository
@Profile("profileMysql")
public class ContactRepositoryMySqlImpl implements ContactRepository {

    /**
     * An instance of {@link EntityManager}
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * The method finds contact by ID in database
     *
     * @param id an unique identifier.
     * @return contact with entered id
     */
    @Override
    public Contact findById(int id) {
        return entityManager.find(Contact.class, id);
    }

    /**
     * The Method gets all contacts from database
     *
     * @return a collection of all contacts from database
     */
    @Override
    public List<Contact> findAll() {
        return entityManager.createQuery("select c from Contact c").getResultList();
    }

    /**
     * The Method adds a new contact into database
     *
     * @param contact a contact to add
     * @return added contact
     */
    @Override
    public Contact save(Contact contact) {
        return entityManager.merge(contact);
    }

    /**
     * The Method updates a contact in database
     *
     * @param contact a contact to update
     * @return updated contact
     */
    @Override
    public Contact update(Contact contact) {
        return entityManager.merge(contact);
    }

    /**
     * The Method removes contact from database by id
     *
     * @param id a unique identifier of contact, that needs to be removed
     */
    @Override
    public void delete(int id) {
        Contact contactToDelete = findById(id);
        entityManager.remove(contactToDelete);
    }
}
