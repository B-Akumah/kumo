package edu.csis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * @author bakumah
 */
@Entity
@Table(name = "USER_TRANSACTIONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundsTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer transactionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_ACCOUNT")
    Account fromAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_ACCOUNT")
    Account toAccount;

    double amount;

    @CreationTimestamp
    Timestamp transactionTimestamp;
}
