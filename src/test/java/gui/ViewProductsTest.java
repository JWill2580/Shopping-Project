/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import dao.ProductsCollectionDAOInterface;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
//Assertj
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
//Junit
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//Mockito
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 *
 * @author admin
 */
public class ViewProductsTest {
    private ProductsCollectionDAOInterface dao;
    private DialogFixture fixture;
    private Robot robot;
    
    @Before
    public void setUp() {
       robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
	// Do NOT make it less than 10 or you will have thread-race problems.
	robot.settings().delayBetweenEvents(75);
        
        Product prod1 = new Product("1", "Prod1", "Product", "Knitting", new BigDecimal(14.99), new BigDecimal(15));
        Product prod2= new Product("2", "Prod2", "Product", "Knitting", new BigDecimal(15.99), new BigDecimal(32));
   
        
        Collection<Product> products = new ArrayList<>();
        products.add(prod1);
        products.add(prod2);

        
	// create a mock for the DAO
        dao = mock(ProductsCollectionDAOInterface.class);
        
        when(dao.getProducts()).thenReturn(products);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }
    
    //@Test
    //public void testEdit() {
        
    //}
    
    @Test
    public void testDeleteProduct() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
      
        
        // click the product then deletes it button
        fixture.list().click(); //Potential issue here
        fixture.button("delete").click();
        fixture.button("YES_OPTION").click();

        //System.out.println(products);
        
        assertEquals("Ensure product deleted", 1, savedProduct.toString());//how to check size of list

    }
    
    @Test
    public void testGetThroughCategory() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called
        verify(dao).saveProduct(argument.capture());
        
        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();
        
	assertEquals("Ensure the Quantity was saved", new BigDecimal("15"), savedProduct.getQuantityInStock());
    }    
}
