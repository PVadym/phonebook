package net.pylypchenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Вадим on 07.08.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, message = "Last Name must contain minimum 4 characters")
    @Column(name ="last_name",nullable = false)
    private String lastName;

    @Size(min = 4, message = "First Name must contain minimum 4 characters")
    @Column(name ="first_name",nullable = false)
    private String firstName;

    @Size(min = 4, message = "Middle Name must contain minimum 4 characters")
    @Column(name ="middle_name",nullable = false)
    private String middleName;

    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}", message = "Enter valid number for example: +380(66)1234567")
    @Column(name ="mobile_phone",nullable = false)
    private String mobilePhone;

    @Pattern(regexp = "\\+380\\([1-9]{2}\\)[0-9]{7}|\\s*", message = "Enter valid number for example: +380(44)1234567")
    @Column(name ="home_phone")
    private String homePhone;


    @Column(name ="address")
    private String address;

    @Email(message = "Enter correct e-mail")
    @Column(name ="e_mail")
    private String email;


    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;


}
