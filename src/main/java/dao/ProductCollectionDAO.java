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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author wiljo912
 */
public class ProductCollectionDAO {
    private static Collection<Product> products = new HashSet<>();
    public static Map<String, Product> productsMap = new HashMap<>();
    
    public void saveProduct(Product product){
        this.products.add(product);
        this.productsMap.put(product.getProductID(), product);
    }

     
    public void deleteProduct(Product product){
        this.products.remove(product);
    }

    public Collection<Product> getProducts() {
        return products; 
    }
    
    public Collection<Product> getThroughCategory(String category){
        Collection<Product> categories = new HashSet<>();
        for(Product p : products){
            categories.add(p);
        }
        return categories;
    }
    
    public Product getThroughID(String id){
         Product prod = productsMap.get(id);
        return prod;
    }
    
    public Collection<String> getCategories(){
        Collection<String> categories = new HashSet<>();
        for(Product p : products){
            categories.add(p.getCategory());
        }
        return categories;
    }
}
