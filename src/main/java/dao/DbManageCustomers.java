/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.h2.jdbc.JdbcConnection;
/**
 *
 * @author wiljo912
 */
public class DbManageCustomers implements CustomerCollectionDAOInterface{
    
    String dbURI = DbConnection.getDefaultConnectionUri();

    public DbManageCustomers() {

    }

    public DbManageCustomers(String URI) {
        this.dbURI = URI;
    }

    @Override
    public void saveCustomer(Customer aCustomer) {
        String statement = "merge into CUSTOMER(CUSTOMER_ID, USERNAME, FIRST_NAME, SURNAME, PASSWORD, EMAIL, SHIPPING) values(?,?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setInt(1, aCustomer.getCustomerID());
            stmt.setString(2, aCustomer.getUsername());
            stmt.setString(3, aCustomer.getFirstname());
            stmt.setString(4, aCustomer.getSurname());
            stmt.setString(4, aCustomer.getEmail());            
            stmt.setString(4, aCustomer.getPassword());
            stmt.setString(4, aCustomer.getShipping());
            

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String username) {
          String statement = "select * from PRODUCT order by PRODUCT_ID";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Customer> customers = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {
                // get the data out of the query
                //String id = rs.getString("CUSTOMER_ID");
                String username = rs.getString("USERNAME");
                String firstname = rs.getString("FIRST_NAME");
                String surname = rs.getString("SURNAME");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                String shippping = rs.getString("SHIPPING");

               
                // use the data to create a student object
                Customer customer = new  Customer(username,  firstname,  surname,  password,  email,  shipping);
                // and put it in the collection
                customers.add(customer);
            }

            return customers;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
