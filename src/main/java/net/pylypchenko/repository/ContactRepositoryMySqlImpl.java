package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

/**
 * Created by Вадим on 08.08.2017.
 */
@Repository
//@Profile("mysql")
public class ContactRepositoryMySqlImpl implements ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contact findById(int id) {
        return entityManager.find(Contact.class, id);
    }

    @Override
    public List<Contact> findAll() {
        return entityManager.createQuery("select c from Contact c").getResultList();
    }

    @Override
    public Contact save(Contact contact) {
        return entityManager.merge(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact contactToUpdate = findById(contact.getId());
        return entityManager.merge(contactToUpdate);
    }

    @Override
    public void delete(int id) {
        Contact contactToDelete = findById(id);
        entityManager.remove(contactToDelete);

    }
}
