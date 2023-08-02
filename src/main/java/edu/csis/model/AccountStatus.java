package edu.csis.model;

/**
 * The AccountStatus enum represents the status of a bank account, indicating whether it is open or closed.
 *
 * @author bakumah
 */
public enum AccountStatus {
    /**
     * Indicates that the bank account is open and active, allowing transactions to be made.
     */
    OPEN,

    /**
     * Indicates that the bank account is closed and no longer accessible for transactions.
     * Once an account is closed, it cannot be reopened, and further operations are not allowed.
     */
    CLOSED
}

