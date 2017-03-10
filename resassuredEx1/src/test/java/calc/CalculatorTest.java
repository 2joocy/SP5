
package calc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CalculatorTest
{
    
    public CalculatorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of add method, of class Calculator.
     */
    @Test
    public void testAdd()
    {
        System.out.println("add");
        int i1 = 2;
        int i2 = 2;
        Calculator instance = new Calculator();
        int expResult = 4;
        int result = instance.add(i1, i2);
        assertEquals(expResult, result);
    }

    /**
     * Test of sub method, of class Calculator.
     */
    @Test
    public void testSub()
    {
        System.out.println("sub");
        int i1 = 2;
        int i2 = 2;
        Calculator instance = new Calculator();
        int expResult = 0;
        int result = instance.sub(i1, i2);
        assertEquals(expResult, result);
    }

    /**
     * Test of mul method, of class Calculator.
     */
    @Test
    public void testMul()
    {
        System.out.println("mul");
        int i1 = 2;
        int i2 = 2;
        Calculator instance = new Calculator();
        int expResult = 4;
        int result = instance.mul(i1, i2);
        assertEquals(expResult, result);
    }

    /**
     * Test of div method, of class Calculator.
     */
    @Test
    public void testDiv()
    {
        System.out.println("div");
        int i1 = 2;
        int i2 = 2;
        Calculator instance = new Calculator();
        int expResult = 1;
        int result = instance.div(i1, i2);
        assertEquals(expResult, result);

    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivErrorZero()
    {
        System.out.println("div");
        int i1 = 3;
        int i2 = 0;
        Calculator instance = new Calculator();
        int result = instance.div(i1, i2);
    }

    
}
