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
public class ManageProducts implements ProductsCollectionDAOInterface{
    String sql = DbConnection.getDefaultConnectionUri();

    public ManageProducts() {
    
    }
    
    public ManageProducts(String sql){
        this.sql = sql;
    }
    
    public Collection<Product> getAll() {
    try (
        // get a connection to the database
        Connection dbCon = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = dbCon.prepareStatement(sql);
    ) {
        // execute the query
        ResultSet rs = stmt.executeQuery();

        // Using a List to preserve the order in which the data was returned from the query.
        List<Product> products = new ArrayList<Product>();

        // iterate through the query results
        while (rs.next()) {
            // get the data out of the query
            String id = rs.getString("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            BigDecimal listprice = rs.getBigDecimal("listprice");
            BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

            // use the data to create a student object
            Product product = new Product(id, name, description, category, listprice, quantityInStock);

            // and put it in the collection
            products.add(product);
        }

        return products;

    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}

    @Override
    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getThroughCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getThroughID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
