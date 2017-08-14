package net.pylypchenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.pylypchenko.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The entity class, describe User entity, provides a set of standard methods for working with this entity,
 * implements {@link UserDetails} interface
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     * Unique identifier.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * User's username
     */
    @Pattern(regexp = "[a-zA-Z]*", message = "Username must contain only latin characters")
    @Size(min = 3, message = "Minimum 3 characters")
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * User's password
     */
    @Size(min = 5, message = "Minimum 5 characters")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User's full name
     */
    @Size(min = 5, message = "Minimum 5 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * User's role
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * This field has information about locking user's account. If isLocked == false, then account isn't locked,
     * if isLocked == true, then account is locked
     */
    @Transient
    private boolean isLocked;

    /**
     * A list of contacts that the user create
     */
    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contact> contacts;

    /**
     * Default constructor
     */
    public User() {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.role = UserRole.USER;
        this.contacts = new ArrayList<>();
        this.isLocked = false;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account expiring.
     *
     * @return true if account is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account locking.
     *
     * @return true if account is't locked, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks expiring of user's account credentials.
     *
     * @return true if account's credentials is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account is enabled, or not.
     *
     * @return true if account is enabled, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !isLocked;
    }

    /**
     * Method for getting collection of user's authorities. An overridden method from UserDetails interface.
     *
     * @return a collection of user's authorities. In our case the collection has just one entry,
     * and the entry contains information about user's role.
     */
    @Override
    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + getRole().name());
        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }

}
