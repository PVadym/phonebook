package net.pylypchenko.entity;


import lombok.*;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The entity class, describe Contact entity, implements a set of standard methods for working with this entity.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "contacts")
public class Contact {

    /**
     * Unique identifier.
     */
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Contact's last name
     */
    @Size(min = 4, message = "Last Name must contain minimum 4 characters")
    @Column(name ="last_name",nullable = false)
    private String lastName;

    /**
     * Contact's first name
     */
    @Size(min = 4, message = "First Name must contain minimum 4 characters")
    @Column(name ="first_name",nullable = false)
    private String firstName;

    /**
     * Contact's middle name
     */
    @Size(min = 4, message = "Middle Name must contain minimum 4 characters")
    @Column(name ="middle_name",nullable = false)
    private String middleName;

    /**
     * Contact's mobile phone
     */
    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}", message = "Enter valid number for example: +380(66)1234567")
    @Column(name ="mobile_phone",nullable = false)
    private String mobilePhone;

    /**
     * Contact's home phone
     */
    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}|\\s*", message = "Enter valid number for example: +380(44)1234567")
    @Column(name ="home_phone")
    private String homePhone;

    /**
     * Contact's address
     */
    @Column(name ="address")
    private String address;

    /**
     * Contact's email
     */
    @Email(message = "Enter correct e-mail")
    @Column(name ="e_mail")
    private String email;


    /**
     * Author of Contact
     */
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;


}
