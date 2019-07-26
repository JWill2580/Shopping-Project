/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import domain.Product;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author wiljo912
 */
public class ProductCollection {
    private static Collection<Product> products = new ArrayList<Product>();
    
    public void addProduct(Product product){
        this.products.add(product);
    }
    
     public void removeProduct(Product product){
        this.products.remove(product);
    }

    public static Collection<Product> getProducts() {
        return products;
    }
}
