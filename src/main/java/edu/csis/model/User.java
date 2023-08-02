package edu.csis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Entity class representing a user in the system.
 * It is mapped to the "BANK_USERS" table in the database.
 *
 * @author bakumah
 */
@Entity
@Table(name = "BANK_USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userID;

    /**
     * The username of the user.
     */
    String username;

    /**
     * The first name of the user.
     */
    String firstName;

    /**
     * The last name of the user.
     */
    String lastName;

    /**
     * The email address of the user.
     */
    String email;

    /**
     * The phone number of the user.
     */
    String phoneNumber;

    /**
     * The hashed password of the user.
     */
    byte[] passwordHash;

    /**
     * The salt used for password hashing.
     */
    byte[] passwordSalt;

    /**
     * The list of accounts associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts;

    /**
     * The address of the user.
     */
    @Embedded
    private Address address;

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passwordHash=" + Arrays.toString(passwordHash) +
                ", passwordSalt=" + Arrays.toString(passwordSalt) +
                ", address=" + address.toString() +
                '}';
    }
}
