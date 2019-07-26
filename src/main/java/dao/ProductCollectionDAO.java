/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author wiljo912
 */
public class ProductCollectionDAO {
    private static Collection<Product> products = new ArrayList<Product>();
    
    public void saveProduct(Product product){
        this.products.add(product);
    }
     
     public void deleteProduct(Product product){
        this.products.remove(product);
    }

    public Collection<Product> getProducts() {
        return products; 
    }
}
