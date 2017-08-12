package net.pylypchenko.service;

import net.pylypchenko.entity.Contact;
import net.pylypchenko.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
/**
 * Created by Вадим on 08.08.2017.
 */
@Service
public class ContactServiceImpl implements ContactService {


    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Contact findById(int id) {
        Contact contact = repository.findById(id);
        if (contact == null) {
            throw new NullPointerException("Can't find contact with id = " + id);
        }
        return contact;
    }


    @Transactional
    public Contact add(Contact contact) throws IllegalArgumentException {
        if (contact == null) {
            throw new IllegalArgumentException("Trying to save 'null'");
        }
        return repository.save(contact);
    }


    @Transactional
    public Contact update(Contact contact) {
        return repository.update(contact);
    }


    @Transactional(readOnly = true)
    public List<Contact> getAll() {
        return repository.findAll();
    }


    @Transactional
    public void remove(int id) {
        repository.delete(id);
    }

}
