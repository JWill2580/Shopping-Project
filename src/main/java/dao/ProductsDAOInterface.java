/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author admin
 */
public interface ProductsDAOInterface {

    void deleteProduct(Product product);

    Collection<String> getCategories();

    Collection<Product> getProducts();

    Collection<Product> getThroughCategory(String category);

    Product getThroughID(String id);

    void saveProduct(Product product);
    
}
