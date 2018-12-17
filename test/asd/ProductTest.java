/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USER
 */
public class ProductTest {
    
    Product p1,p2;
    
    public ProductTest() {
    }
    
    @Before
    public void setUp() {
        p1 = new Product();
        p2 = new Product("F00002","white rose","10","100","flower","paid");
    }

    /**
     * Test of getProductID method, of class Product.
     */
    @Test
    public void testGetProductID() {
        System.out.println("getProductID");
        String expResult = "F00002";
        String result = p2.getProductID();
        assertEquals(expResult, result);
        }

    /**
     * Test of setProductID method, of class Product.
     */
    @Test
    public void testSetProductID() {
        System.out.println("setProductID");
        p1.setProductID("F00001");
        assertEquals("F00001", p1.getProductID());
    }

    /**
     * Test of getProductName method, of class Product.
     */
    @Test
    public void testGetProductName() {
        System.out.println("getProductName");
        assertEquals("white rose",p2.getProductName());
    }

    /**
     * Test of setProductName method, of class Product.
     */
    @Test
    public void testSetProductName() {
        System.out.println("setProductName");
        p1.setProductName("red rose");
        assertEquals("red rose",p1.getProductName());
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        assertEquals(10,p2.getPrice());
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        p1.setPrice("10");
        assertEquals(10,p1.getPrice());
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        assertEquals(100,p2.getQuantity());
    }

    /**
     * Test of setQuantity method, of class Product.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        p1.setQuantity("100");
        assertEquals(100,p1.getQuantity());
    }

    /**
     * Test of getType method, of class Product.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        assertEquals("flower",p2.getType());
    }

    /**
     * Test of setType method, of class Product.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        p1.setType("flower");
        assertEquals("flower",p1.getType());
    }

    /**
     * Test of getPayment method, of class Product.
     */
    @Test
    public void testGetPayment() {
        System.out.println("getPayment");
        assertEquals("paid",p2.getPayment());
    }

    /**
     * Test of setPayment method, of class Product.
     */
    @Test
    public void testSetPayment() {
        System.out.println("setPayment");
        p1.setPayment("paid");
        assertEquals("paid",p1.getPayment());
    }
    
}
