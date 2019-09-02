/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
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
public class DbManageProducts implements ProductsCollectionDAOInterface {

    String dbURI = DbConnection.getDefaultConnectionUri();

    public DbManageProducts() {

    }

    public DbManageProducts(String URI) {
        this.dbURI = URI;
    }

    @Override
    public void saveProduct(Product aProduct) {
        String statement = "merge into PRODUCT(PRODUCT_ID, PRODUCT_NAME, "
                + "DESCRIPTION, CATEGORY, LIST_PRICE, QUANTITY_IN_STOCK) "
                + "values(?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, aProduct.getProductID());
            stmt.setString(2, aProduct.getName());
            stmt.setString(3, aProduct.getDescription());
            stmt.setString(4, aProduct.getCategory());
            stmt.setBigDecimal(5, aProduct.getListPrice());
            stmt.setBigDecimal(6, aProduct.getQuantityInStock());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        String statement = "delete from product where product_id = ?";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            stmt.setString(1, product.getProductID());
            System.out.println(statement + product.getProductID());
            // execute the query
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getThroughCategory(String prodCategory) {
        String statement = "select * from PRODUCT where CATEGORY = ?";
        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(statement);) {
            // set the parameter
            stmt.setString(1, prodCategory);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            List<Product> products = new ArrayList<>();
            // query only returns a single result, so use 'if' instead of 'while'
            while (rs.next()) {
                String id = rs.getString("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                String description = rs.getString("DESCRIPTION");
                String category = rs.getString("CATEGORY");
                BigDecimal listprice = rs.getBigDecimal("LIST_PRICE");
                BigDecimal quantityInStock = rs.getBigDecimal("QUANTITY_IN_STOCK");

                // use the data to create a student object
                Product product = new Product(id, name, description,
                        category, listprice, quantityInStock);
                // and put it in the collection
                products.add(product);

            }
            return products;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Product getThroughID(String prodID) {
        String statement = "select * from PRODUCT where PRODUCT_ID = ?";
        try (
                // get connection to database
                Connection connection = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = connection.prepareStatement(statement);) {
            // set the parameter
            stmt.setString(1, prodID);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String id = rs.getString("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                String description = rs.getString("DESCRIPTION");
                String category = rs.getString("CATEGORY");
                BigDecimal listprice = rs.getBigDecimal("LIST_PRICE");
                BigDecimal quantityInStock = rs.getBigDecimal("QUANTITY_IN_STOCK");

                return new Product(id, name, description,
                        category, listprice, quantityInStock);

            } else {
                // no product matches given category so return null
                return null;
            }

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getProducts() {
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
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {
                // get the data out of the query
                String id = rs.getString("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                String description = rs.getString("DESCRIPTION");
                String category = rs.getString("CATEGORY");
                BigDecimal listprice = rs.getBigDecimal("LIST_PRICE");
                BigDecimal quantityInStock = rs.getBigDecimal("QUANTITY_IN_STOCK");

                // use the data to create a student object
                Product product = new Product(id, name, description,
                        category, listprice, quantityInStock);
                // and put it in the collection
                products.add(product);
            }

            return products;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<String> getCategories() {
        String statement = "select distinct CATEGORY from PRODUCT order by CATEGORY";
        try (
                // get a connection to the database
                Connection dbCon = DbConnection.getConnection(
                        DbConnection.getDefaultConnectionUri());
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(statement);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<String> productCategories = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {
                // get the data out of the query          
                String category = rs.getString("CATEGORY");

                // and put it in the collection
                productCategories.add(category);
            }

            return productCategories;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
