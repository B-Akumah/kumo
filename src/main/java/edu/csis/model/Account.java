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
@Table(name = "BANK_ACCOUNTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    int balance;

    @CreationTimestamp
    Timestamp dateCreated;

    double interestRate;

    @Enumerated(EnumType.STRING)
    AccountStatus status;
}
