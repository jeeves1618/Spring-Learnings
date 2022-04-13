package net.myphenotype.Teller.Entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Slf4j
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_key")
    private int accountkey;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "account_balance")
    private double accountBalance;

    public Account() {
    }

    public Account(String accountNumber, String firstName, String lastName, String accountType, double accountBalance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        log.info("Generating record for Account Number: " + accountNumber);
    }
}
