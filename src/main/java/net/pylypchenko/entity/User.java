package net.pylypchenko.entity;

import net.pylypchenko.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Вадим on 07.08.2017.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="username", nullable = false)
    private String username;

    @Column(name ="password",nullable = false)
    private String password;

    @Column(name ="full_name",nullable = false)
    private String fullName;

    @Column(name ="role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contact> contacts;



    /**
     * An overridden method from UserDetails interface. The method checks user's account expiring.
     *
     * @return true if account is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account locking.
     *
     * @return true if account is't locked, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * An overridden method from UserDetails interface. The method checks expiring of user's account credentials.
     *
     * @return true if account's credentials is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account is enabled, or not.
     *
     * @return true if account is enabled, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Method for getting collection of user's authorities. An overridden method from UserDetails interface.
     *
     * @return a collection of user's authorities. In our case the collection has just one entry,
     * and the entry contains information about user's role.
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + getRole().name());
        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }

    public User() {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.role = UserRole.USER;
        this.contacts = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
    }

    public User(int id, String username, String password, String fullName, UserRole role, List<Contact> contacts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact( Contact contact){
        contacts.add(contact);
    }

    public void removeContact( Contact contact){
        contacts.remove(contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", contacts=" + contacts +
                '}';
    }
}
