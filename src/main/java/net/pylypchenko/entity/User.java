package net.pylypchenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
 * Created by Вадим on 07.08.2017.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Pattern(regexp = "[a-zA-Z]*", message = "Username must contain only latin characters")
    @Size(min = 3, message = "Minimum 3 characters")
    @Column(name ="username", nullable = false)
    private String username;

    @Size(min = 5, message = "Minimum 5 characters")
    @Column(name ="password",nullable = false)
    private String password;

    @Size(min = 5, message = "Minimum 5 characters")
    @Column(name ="full_name",nullable = false)
    private String fullName;

    @Column(name ="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @Transient
    private boolean enabled;

    @JsonIgnore
    @Transient
    private boolean accountNonExpired;

    @JsonIgnore
    @Transient
    private boolean accountNonLocked;

    @JsonIgnore
    @Transient
    private boolean credentialsNonExpired;

    @JsonIgnore
    @Transient
    private List<GrantedAuthority> authorities;

    @JsonIgnore
    @Transient
    private boolean isLocked = false;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contact> contacts;

    public User() {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.role = UserRole.USER;
        this.contacts = new ArrayList<>();
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account expiring.
     *
     * @return true if account is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isAccountNonExpired() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account locking.
     *
     * @return true if account is't locked, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks expiring of user's account credentials.
     *
     * @return true if account's credentials is't expired, or false otherwise. In our case returns not isLocked value.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !isLocked;
    }

    /**
     * An overridden method from UserDetails interface. The method checks user's account is enabled, or not.
     *
     * @return true if account is enabled, or false otherwise. In our case returns not isLocked value.
     */
    @Override
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
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + getRole().name());
        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }

}
