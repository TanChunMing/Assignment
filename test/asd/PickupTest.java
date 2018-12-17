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
 * @author Pz
 */
public class PickupTest {
    Pickup p1,p2;
    public PickupTest() {
    }
    
    @Before
    public void setUp() {
        p1 = new Pickup();
        p2 = new Pickup("A00001","17-12-2018","12:00","Low Chun Hou");
    }

    /**
     * Test of getPickupID method, of class Pickup.
     */
    @Test
    public void testGetPickupID() {
        System.out.println("getPickupID");
        String expResult = "A00001";
        String result = p2.getPickupID();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPickupID method, of class Pickup.
     */
    @Test
    public void testSetPickupID() {
        System.out.println("setPickupID");
        p1.setPickupID("A00001");
        assertEquals("A00001", p1.getPickupID());

    }

    /**
     * Test of getPickupDate method, of class Pickup.
     */
    @Test
    public void testGetPickupDate() {
        System.out.println("getPickupDate");
        assertEquals("17-12-2018",p2.getPickupDate());
    }

    /**
     * Test of setPickupDate method, of class Pickup.
     */
    @Test
    public void testSetPickupDate() {
        System.out.println("setPickupDate");
        p1.setPickupDate("17-12-2018");
        assertEquals("17-12-2018",p1.getPickupDate());
    }

    /**
     * Test of getTime method, of class Pickup.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        assertEquals("12:00",p2.getTime());
    }

    /**
     * Test of setTime method, of class Pickup.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        p1.setTime("12:00");
        assertEquals("12:00",p1.getTime());
    }

    /**
     * Test of getCustomerName method, of class Pickup.
     */
    @Test
    public void testGetCustomerName() {
        System.out.println("getCustomerName");
        assertEquals("Low Chun Hou",p2.getCustomerName());
    }

    /**
     * Test of setCustomerName method, of class Pickup.
     */
    @Test
    public void testSetCustomerName() {
        System.out.println("setCustomerName");
        p1.setCustomerName("Low Chun Hou");
        assertEquals("Low Chun Hou",p1.getCustomerName());
    }
    
}
