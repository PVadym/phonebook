package net.pylypchenko.entity;

import javax.persistence.*;

/**
 * Created by Вадим on 07.08.2017.
 */
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="last_name",nullable = false)
    private String lastName;

    @Column(name ="first_name",nullable = false)
    private String firstName;

    @Column(name ="middle_name",nullable = false)
    private String middleName;

    @Column(name ="mobile_phone",nullable = false)
    private String mobilePhone;

    @Column(name ="home_phone")
    private String homePhone;

    @Column(name ="address")
    private String address;

    @Column(name ="e_mail")
    private String email;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    public Contact() {
    }

    public Contact(String lastName, String firstName, String middleName, String mobilePhone, String homePhone, String address, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (middleName != null ? !middleName.equals(contact.middleName) : contact.middleName != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(contact.mobilePhone) : contact.mobilePhone != null) return false;
        if (homePhone != null ? !homePhone.equals(contact.homePhone) : contact.homePhone != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return user != null ? user.equals(contact.user) : contact.user == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
