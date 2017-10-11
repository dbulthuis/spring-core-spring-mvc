package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJapDaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {

        List<Customer> customers = (List<Customer>) customerService.listAll();

        assertEquals(19,customers.size());

    }

    @Test
    public void testGetById() throws Exception {
        Customer customer = customerService.getById(2);

        assertEquals("Last 2", customer.getLastName());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        String firstName = "Micheal";
        String lastName = "Weston";
        String addressLine1 = "1 Main St";
        String addressLine2 = "Apt 301";
        String city = "Miami";
        String state = "Florida";
        String zipCode = "33101";
        String email = "micheal@burnnotice.com";
        String phoneNumber = "305.333.0101";

        Customer returnCustomer = new Customer();
        
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setAddressLine1(addressLine1);
        returnCustomer.setAddressLine2(addressLine2);
        returnCustomer.setCity(city);
        returnCustomer.setState(state);
        returnCustomer.setZipCode(zipCode);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);

        Customer savedCustomer = customerService.saveOrUpdate(returnCustomer);

        assertEquals(firstName, savedCustomer.getFirstName());
        assertEquals(lastName, savedCustomer.getLastName());
        assertEquals(addressLine1, savedCustomer.getAddressLine1());
        assertEquals(addressLine2, savedCustomer.getAddressLine2());
        assertEquals(city, savedCustomer.getCity());
        assertEquals(state, savedCustomer.getState());
        assertEquals(zipCode, savedCustomer.getZipCode());
        assertEquals(email, savedCustomer.getEmail());
        assertEquals(phoneNumber, savedCustomer.getPhoneNumber());
    }

    @Test
    public void testDelete() throws Exception {
        customerService.delete(2);

        assertNull(customerService.getById(2));
    }
}
