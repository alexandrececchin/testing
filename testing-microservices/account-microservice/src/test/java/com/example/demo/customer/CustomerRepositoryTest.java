package com.example.demo.customer;

import com.example.demo.account.Account;
import com.example.demo.address.Address;
import com.example.demo.creditcard.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.address.AddressType.SHIPPING;
import static com.example.demo.creditcard.CreditCardType.VISA;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void customerTest() {
        // Create a new account
        Account account = new Account("5", "123456789");

        // Create a new customer for the
        // account
        Customer customer = new Customer("Jane", "Doe", "jane.doe@gmail.com", account);

        // Create a new credit card for the
        // account
        CreditCard creditCard = new CreditCard("1234567801234567", VISA);

        // Add the credit card to the
        // customer's
        // account
        customer.getAccount().getCreditCards().add(creditCard);

        // Create a new shipping address for
        // the
        // customer
        Address address = new Address("1600 Pennsylvania Ave NW", null, "DC", "Washington", "United States", SHIPPING, 20500);

        // Add address to the customer's
        // account
        customer.getAccount().getAddresses().add(address);

        this.entityManager.persist(customer);

        Customer actual = customerRepository.findById(customer.getId()).orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo("Jane");
        assertThat(actual.getLastName()).isEqualTo("Doe");
        assertThat(actual.getEmail()).isEqualTo("jane.doe@gmail.com");
    }
}
