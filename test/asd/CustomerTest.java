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
 * @author ASUS
 */
public class CustomerTest {
    
    Customer c1,c2;
    
    public CustomerTest() {
        
    }
    
    @Before
    public void setUp() {
        c1 = new Customer();
        c2 = new Customer("C001","Low Chun Hou","cc","2000","500","The Big One");
    }

    /**
     * Test of getCustID method, of class Customer.
     */
    @Test
    public void testGetCustID() {
        System.out.println("getCustID");
        String expResult = "C001";
        String result = c2.getCustID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setCustID method, of class Customer.
     */
    @Test
    public void testSetCustID() {
        System.out.println("setCustID");
        c1.setCustID("C001");
        assertEquals("C001",c1.getCustID());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCustName method, of class Customer.
     */
    @Test
    public void testGetCustName() {
        System.out.println("getCustName");
        assertEquals("Low Chun Hou", c2.getCustName());
       
       
    }

    /**
     * Test of setCustName method, of class Customer.
     */
    @Test
    public void testSetCustName() {
        System.out.println("setCustName");
        c1.setCustName("Low Chun Hou");
        assertEquals("Low Chun Hou", c1.getCustName());
    }

    /**
     * Test of getCustType method, of class Customer.
     */
    @Test
    public void testGetCustType() {
        System.out.println("getCustType");
        assertEquals("cc", c2.getCustType());
        
    }

    /**
     * Test of setCustType method, of class Customer.
     */
    @Test
    public void testSetCustType() {
        System.out.println("getCustType");
        c1.setCustType("cc");
        assertEquals("cc", c1.getCustType());
    }

    /**
     * Test of getCreditLimit method, of class Customer.
     */
    @Test
    public void testGetCreditLimit() {
        System.out.println("getCreditLimit");
       
        assertEquals(2000,c2.getCreditLimit());
        
    }

    /**
     * Test of setCreditLimit method, of class Customer.
     */
    @Test
    public void testSetCreditLimit() {
        System.out.println("setCreditLimit");
        c1.setCreditLimit("2000");
        assertEquals(2000,c1.getCreditLimit());
    }

    /**
     * Test of getCurrentCredit method, of class Customer.
     */
    @Test
    public void testGetCurrentCredit() {
        System.out.println("getCurrentCredit");
        
        assertEquals(500,c2.getCurrentCredit());
        
    }

    /**
     * Test of setCurrentCredit method, of class Customer.
     */
    @Test
    public void testSetCurrentCredit() {
        System.out.println("setCurrentCredit");
        c1.setCurrentCredit("500");
        assertEquals(500,c1.getCurrentCredit());
    }

    /**
     * Test of getCompanyName method, of class Customer.
     */
    @Test
    public void testGetCompanyName() {
        System.out.println("getCompanyName");
        
        assertEquals("The Big One", c2.getCompanyName());
        
    }

    /**
     * Test of setCompanyName method, of class Customer.
     */
    @Test
    public void testSetCompanyName() {
        System.out.println("setCompanyName");
        c1.setCompanyName("The Big One");
        assertEquals("The Big One", c1.getCompanyName());
    }
    
}
