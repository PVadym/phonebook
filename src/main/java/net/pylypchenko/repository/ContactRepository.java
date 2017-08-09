package net.pylypchenko.repository;

import net.pylypchenko.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by Вадим on 07.08.2017.
 */
@NoRepositoryBean
public interface ContactRepository{

    Contact findById(int id);

    List<Contact> findAll();

    Contact save(Contact contact);

    Contact update(Contact contact);

    void delete(int id);
}
