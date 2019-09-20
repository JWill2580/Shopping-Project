/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author wiljo912
 */
public class ProductCollectionDAOTest {
    //private ProductsCollectionDAOInterface dao = new ProductCollectionDAO();
    private ProductsDAOInterface dao = new JDBCManageProducts(
"jdbc:h2:mem:tests;INIT=runscript from 'src/main/resources/schema.sql'");
    
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;
    
    public ProductCollectionDAOTest() {
    }
    
    @Before
    public void setUp() {
        this.prodOne = new Product("1", "name1", "cat1", "desc1",
                new BigDecimal("11.00"), new BigDecimal("22.00"));
        this.prodTwo = new Product("2", "name2", "cat2", "desc2",
                new BigDecimal("33.00"), new BigDecimal("44.00"));
        this.prodThree = new Product("3", "name3", "cat3", "desc3",
                new BigDecimal("55.00"), new BigDecimal("66.00"));
        
        // add the products
        dao.saveProduct(prodOne);
        dao.saveProduct(prodTwo);
        //not adding prodThree
    }
    
    @After
    public void tearDown() {
        dao.deleteProduct(prodOne);
        dao.deleteProduct(prodTwo);
        dao.deleteProduct(prodThree);
    }

    @Test
    public void testSaveProduct() {
        // save the product using the DAO
        dao.saveProduct(prodThree);
        // ensure that the data store includes the product
        assertTrue("Ensure that the product was saved", 
                dao.getProducts().contains(prodThree));
    }

    @Test
    public void testDeleteProduct() {
        // sanity check to make sure prodOne does exist before we delete it
        assertTrue("Ensure that the product does exist",
                dao.getProducts().contains(prodOne));
        
        // delete the product via the DAO
        dao.deleteProduct(prodOne);
        
        // ensure that the product no longer exists
        assertFalse("Ensure that the product does not exist",
                dao.getProducts().contains(prodOne));
    }

    @Test
    public void testGetProducts() {
        Collection<Product> products = dao.getProducts();
        
        // ensure the result includes the two saved products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
        
        // ensure the result ONLY includes the two saved products
        assertEquals("Only 2 products in result", 2, products.size());
    }
    
}
