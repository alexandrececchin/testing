package com.example.demo.account;

import com.example.demo.address.Address;
import com.example.demo.creditcard.CreditCard;
import com.example.demo.data.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private AccountNumber accountNumber;

    private Boolean defaultAccount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CreditCard> creditCards;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addresses;

    public Account() {
        this.creditCards = new HashSet<>();
        this.addresses = new HashSet<>();
        this.defaultAccount = false;
    }

    public Account(String username, AccountNumber accountNumber) {
        this();
        this.username = username;
        this.accountNumber = accountNumber;
        this.defaultAccount = false;
    }

    public Account(String username, String accountNumber) {
        this();
        this.username = username;
        this.accountNumber = new AccountNumber(accountNumber);
        this.defaultAccount = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(Boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username='" + username + '\''
                + ", accountNumber=" + accountNumber + ", defaultAccount=" + defaultAccount
                + ", creditCards=" + creditCards + ", addresses=" + addresses + "} "
                + super.toString();
    }
}
