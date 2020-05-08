/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *Hello there
 * @author wiljo912
 */
public class CustomerCollectionDAO implements CustomerDAOInterface {

    private static final Map<String, Customer> customers = new HashMap<>();

    public CustomerCollectionDAO() {
        if (customers.isEmpty()) {
            // some dummy data for testing
            Customer boris = new Customer();
            boris.setUsername("boris");
            boris.setFirstname("Boris");
            boris.setSurname("McNorris");
            boris.setPassword("guest");
            boris.setShipping("123 Some Street,\nNorth East Valley,\nDunedin");
            boris.setEmail("boris@example.net");

            Customer doris = new Customer();
            doris.setUsername("doris");
            doris.setFirstname("Doris");
            doris.setSurname("Dolores");
            doris.setPassword("guest");
            doris.setShipping("321 Anywere Ave,\nSt Clair,\nDunedin");
            doris.setEmail("doris@example.net");

            this.saveCustomer(boris);
            this.saveCustomer(doris);
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        System.out.println("Saving customer: " + customer);
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public Customer getCustomer(String username) {
        return customers.get(username);
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        if (customers.containsKey(username)) {
            return customers.get(username).getPassword().equals(password);
        } else {
            return false;
        }
    }

}
