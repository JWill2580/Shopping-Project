 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author wiljo912
 */
public class ProductCollectionDAO implements ProductsDAOInterface {
    private static Collection<Product> products = new HashSet<>();
    private static Map<String, Product> productsMap = new HashMap<>();
    private static Multimap<String, Product> productsMM = ArrayListMultimap.create();

    
    @Override
    public void saveProduct(Product product){
        products.add(product);
        productsMap.put(product.getProductID(), product);
        productsMM.put(product.getCategory(), product);
    }

     
    @Override
    public void deleteProduct(Product product){
        this.products.remove(product);
    }

    @Override
    public Collection<Product> getProducts() {
        return products; 
    }
    
    @Override
    public Collection<Product> getThroughCategory(String category){
        return productsMM.get(category);
    }
    
    @Override
    public Product getThroughID(String id){
        if(productsMap.containsKey(id)){
            return productsMap.get(id);
        } else {
            return null;
        }
    }
    
    @Override
    public Collection<String> getCategories(){
        Collection<String> categories = new HashSet<>();
        for(Product p : products){
            categories.add(p.getCategory());
        }
        return categories;
    }
}
