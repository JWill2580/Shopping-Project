/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import dao.ProductsCollectionDAOInterface;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 *
 * @author admin
 */
public class ViewProductsTest {
    private ProductsCollectionDAOInterface dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product prod1;
    private Product prod2;
    private Collection<Product> products;
    private Collection<Product> singleProdCollection;
    private Collection<String> categories;

    
    @Before
    public void setUp() {
       robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
	// Do NOT make it less than 10 or you will have thread-race problems.
	robot.settings().delayBetweenEvents(200);
        
        prod1 = new Product("1", "Prod1", "Product", "Knitting", new BigDecimal(14.99), new BigDecimal(15));
        prod2 = new Product("2", "Prod2", "Product", "Drawing", new BigDecimal(15.99), new BigDecimal(32));
   
        
        products = new HashSet<>();
        products.add(prod1);
        products.add(prod2);

        singleProdCollection = new HashSet<>();
        singleProdCollection.add(prod1);
        
        categories = new HashSet<>();
        categories.add("Knitting");
        
	// create a mock for the DAO
        dao = mock(ProductsCollectionDAOInterface.class);
        
        when(dao.getProducts()).thenReturn(products);
        when(dao.getCategories()).thenReturn(categories);
        when(dao.getThroughID("2")).thenReturn(prod2);
        when(dao.getThroughCategory("Knitting")).thenReturn(singleProdCollection);

        
        // stub the deleteProduct method
        Mockito.doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // remove the product from the collection that getProducts() uses
                products.remove(prod1);
                return null;
            }
        }).when(dao).deleteProduct(prod1);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }
    
    @Test
    public void testDeleteProduct() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
      
        
        // click the product then deletes it button
        fixture.list("productList").selectItem(1); //Potential issue here
        fixture.button("deleteButton").click();

        SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

        
        assertTrue("Ensure product deleted", model.contains(prod1));
        assertEquals("List has only one product", 1, model.getSize());
    }
    
    @Test
    public void testGetThroughCategory() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        fixture.comboBox("cmbFilterCategory").selectItem(0); //There is an issue here regarding selected item brackets
        
        SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

        assertTrue("Prod1 found", model.contains(prod1));
        assertEquals("List has only one product", 1, model.getSize());
    }  
    
    @Test
    public void testGetThroughID() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        fixture.textBox("txtSearchID").enterText("2");
        fixture.button("searchButton").click();
        
        
        
        SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

        assertTrue("Prod2 found", model.contains(prod2));
        assertEquals("List has only one product", 1, model.getSize());
    }
    
    @Test
    public void testView() {
        ViewProducts dialog = new ViewProducts(null, true, dao);
        
        // using AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        verify(dao).getProducts(); 
        
        SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();
        
        assertTrue("Prod1 found", model.contains(prod1));
        assertTrue("Prod2 found", model.contains(prod2));
        assertEquals("List has two products", 2, model.getSize());
    }
}
