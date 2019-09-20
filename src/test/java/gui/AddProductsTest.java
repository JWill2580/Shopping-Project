/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


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
import dao.ProductsDAOInterface;


/**
 *
 * @author admin
 */
public class AddProductsTest {
    private ProductsDAOInterface dao;
    private DialogFixture fixture;
    private Robot robot;
    
    @Before
    public void setUp() {
       robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
	// Do NOT make it less than 10 or you will have thread-race problems.
	robot.settings().delayBetweenEvents(75);
                
        Collection<String> categories = new ArrayList<>();
        categories.add("Knitting");
	categories.add("Ninjitsu"); 
        
	// create a mock for the DAO
        dao = mock(ProductsDAOInterface.class);
        
        when(dao.getCategories()).thenReturn(categories);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }
    
    @Test
    public void testSaveProduct() {
        AddProducts dialog = new AddProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        // enter some details into the UI components
	fixture.textBox("txtID").enterText("1");
	fixture.textBox("txtName").enterText("Prod1");
        fixture.textBox("txtDescription").enterText("Product");
	fixture.comboBox("txtCategory").selectItem("Knitting");
        fixture.textBox("txtPrice").enterText("14.99");
        fixture.textBox("txtQuantityInStock").enterText("15");
        
        // click the save button
        fixture.button("save").click();

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called
        verify(dao).saveProduct(argument.capture());
        
        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();

        assertEquals("Ensure the ID was saved", "1", savedProduct.getProductID());
	assertEquals("Ensure the Name was saved", "Prod1", savedProduct.getName());
        assertEquals("Ensure the Description was saved", "Product", savedProduct.getDescription());
	assertEquals("Ensure the Category was saved", "Knitting", savedProduct.getCategory());
	assertEquals("Ensure the Price was saved", new BigDecimal("14.99"), savedProduct.getListPrice());
	assertEquals("Ensure the Quantity was saved", new BigDecimal("15"), savedProduct.getQuantityInStock());

    }
    
    @Test
    public void getCategories() {
        AddProducts dialog = new AddProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        // enter some details into the UI components
	fixture.textBox("txtID").enterText("1");
	fixture.textBox("txtName").enterText("Prod1");
        fixture.textBox("txtDescription").enterText("Product");
	fixture.comboBox("txtCategory").selectItem("Knitting");
        fixture.textBox("txtPrice").enterText("14.99");
        fixture.textBox("txtQuantityInStock").enterText("15");
        
        // click the save button
        fixture.button("save").click();

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called
        verify(dao).saveProduct(argument.capture());
        
        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();
        

        AddProducts dialog2 = new AddProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog2);
	fixture.show().requireVisible();
    }
}
