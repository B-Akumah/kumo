package edu.csis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * The Account class represents a bank account entity in the application. It is mapped to the "BANK_ACCOUNTS" table in the database.
 *
 * @author bakumah
 */
@Entity
@Table(name = "BANK_ACCOUNTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    /**
     * The account number uniquely identifies each account. It is annotated with @Id, indicating it is the primary key of the table.
     * The @GeneratedValue annotation specifies the strategy for generating the account number, using the database's identity (auto-increment) strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountNumber;

    /**
     * The user associated with the account. It represents a many-to-one relationship with the User entity.
     * The @ManyToOne annotation defines the association and sets the fetch type to eager loading, meaning that the user will be fetched along with the account.
     * The @JoinColumn annotation specifies the foreign key column name "USER_ID" that links the account to the corresponding user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    User user;

    /**
     * The account type, represented by the AccountType enum, indicates whether the account is a savings or checking account.
     * The @Enumerated annotation with EnumType.STRING specifies that the account type will be stored as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    /**
     * The balance of the account, indicating the amount of money in the account.
     */
    double balance;

    /**
     * The date and time when the account was created.
     * The @CreationTimestamp annotation automatically sets this field to the current timestamp when the account is created.
     */
    @CreationTimestamp
    Timestamp dateCreated;

    /**
     * The interest rate of the account, applicable to savings accounts.
     */
    double interestRate;

    /**
     * The status of the account, represented by the AccountStatus enum, indicating whether the account is open or closed.
     * The @Enumerated annotation with EnumType.STRING specifies that the account status will be stored as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    AccountStatus status;
}
