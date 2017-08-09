package net.pylypchenko.service;

import net.pylypchenko.entity.Contact;

import java.util.List;

/**
 * Created by Вадим on 08.08.2017.
 */
public interface ContactService {

    Contact findById(int id);

    List<Contact> getAll();

    Contact add(Contact contact);

    Contact update(Contact contact);

    void remove(int id);

}
