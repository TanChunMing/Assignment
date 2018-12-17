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
public class ArrayListTest {
    
    ArrayList<Integer> a1;
    
    public ArrayListTest() {
    }
    
    @Before
    public void setUp() {
        a1 = new ArrayList();
        a1.add(1);
    }

    /**
     * Test of add method, of class ArrayList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        int expResult = 2;
        a1.add(2);
        assertEquals((Integer)expResult,(Integer)a1.get(1));
    }

    /**
     * Test of get method, of class ArrayList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        assertEquals((Integer)1, a1.get(0));
    }

    /**
     * Test of add method, of class ArrayList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int i = 1;
        int item = 6;
        ArrayList instance = new ArrayList();
        instance.add(i, item);
        assertEquals((Integer)item, instance.get(0));
    }

    /**
     * Test of remove method, of class ArrayList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        a1.add(1);
        a1.add(2);
//        int i = 0;
//        ArrayList instance = new ArrayList();
//        boolean expResult = false;
//        boolean result = instance.remove(i);
        assertEquals(true, a1.remove(1));
    }

    /**
     * Test of isEmpty method, of class ArrayList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayList instance = new ArrayList();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of clear method, of class ArrayList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        ArrayList instance = new ArrayList();
        instance.clear();
    }
    
}
