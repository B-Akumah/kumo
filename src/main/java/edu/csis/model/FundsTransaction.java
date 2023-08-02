package edu.csis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * The FundsTransaction class represents a financial transaction between two bank accounts.
 * It is mapped to the "USER_TRANSACTIONS" table in the database.
 *
 * @author bakumah
 */
@Entity
@Table(name = "USER_TRANSACTIONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundsTransaction {
    /**
     * The unique ID of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer transactionId;

    /**
     * The source account from which the funds are transferred.
     * This is a Many-to-One relationship, as multiple transactions can be associated with the same source account.
     * The FetchType.EAGER indicates that the source account will be eagerly fetched when loading the transaction.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_ACCOUNT")
    Account fromAccount;

    /**
     * The destination account to which the funds are transferred.
     * This is a Many-to-One relationship, as multiple transactions can be associated with the same destination account.
     * The FetchType.EAGER indicates that the destination account will be eagerly fetched when loading the transaction.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_ACCOUNT")
    Account toAccount;

    /**
     * The amount of funds transferred in the transaction.
     */
    double amount;

    /**
     * The timestamp when the transaction was created.
     */
    @CreationTimestamp
    Timestamp transactionTimestamp;
}
