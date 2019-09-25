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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.h2.jdbc.JdbcConnection;

/**
 *
 * @author wiljo912
 */
public class JDBCManageCustomers implements CustomerDAOInterface {

    String dbURI = DbConnection.getDefaultConnectionUri();

    public JDBCManageCustomers() {

    }

    public JDBCManageCustomers(String URI) {
        this.dbURI = URI;
    }

    @Override
    public void saveCustomer(Customer aCustomer) {
        String statement = "insert into CUSTOMER(USERNAME, FIRST_NAME, SURNAME, PASSWORD, EMAIL, SHIPPING) values(?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);) {
            // copy the data from the student domain object into the SQL parameters
                  
            stmt.setString(1, aCustomer.getUsername());
            stmt.setString(2, aCustomer.getFirstname());
            stmt.setString(3, aCustomer.getSurname());
            stmt.setString(4, aCustomer.getPassword());
            stmt.setString(5, aCustomer.getEmail());
            stmt.setString(6, aCustomer.getShipping());
            
            stmt.executeUpdate();  // execute the statement

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getString(1));

            
            
        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Customer getCustomer(String usernameDefined) {

        String statement = "select * from CUSTOMER where USERNAME = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {

            stmt.setString(1, usernameDefined);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String customer_ID = rs.getString("CUSTOMER_ID");
                String username = rs.getString("USERNAME");
                String firstname = rs.getString("FIRST_NAME");
                String surname = rs.getString("SURNAME");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                String shipping = rs.getString("SHIPPING");

                return new Customer(customer_ID, username, firstname,
                        surname, password, email, shipping);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean validateCredentials(String usernameDefined, String passwordDefined) {
        String statement = "select from CUSTOMER where USERNAME = ? and PASSWORD = ?";

        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            stmt.setString(1, usernameDefined);
            stmt.setString(2, passwordDefined);
            // execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");

                if (username == usernameDefined && password == passwordDefined) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

}
