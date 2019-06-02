package model;

import org.junit.Test;

import static org.junit.Assert.*;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;

public class CashRegisterTest {

    public CashRegisterTest(){}

    /**
     * A test of the method addPaymentToRegister
     */
    @Test
    public void testAddPaymentToRegister() {
        double payment = 30.5;
        CashRegister testRegister = new CashRegister();
        testRegister.addPaymentToRegister(payment);
        assertEquals("method AddPaymentToRegister doesnt work",30.5,testRegister.getBalance());
    }

    /**
     * A test of the method addPaymentToRegister
     */
    @Test
    public void testAddPaymentToRegisterNoPayment() {
        double payment = 0;
        CashRegister testRegister = new CashRegister();
        testRegister.addPaymentToRegister(payment);
        assertEquals("method AddPaymentToRegister doesnt work",0.0,testRegister.getBalance());
    }

    /**
     * A test of the method calculateChange
     */
    @Test
    public void testCalculateChange() {
        double payment = 30.5;
        double cost = 25;
        double expectedResult = 5.5;
        CashRegister testRegister = new CashRegister();
        double actualResult = testRegister.calculateChange(payment,cost);
        assertEquals("the method calculateChange returns the incorrect change",expectedResult,actualResult);
    }

    /**
     * A test of calculateChange when no payment is in evidence
     */
    @Test
    public void testCalculateChangeWithNoPayment() {
        double payment = 0;
        double cost = 25;
        double expectedResult = -25;
        CashRegister testRegister = new CashRegister();
        double actualResult = testRegister.calculateChange(payment,cost);
        assertEquals("the method calculateChange returns the incorrect change",expectedResult,actualResult);
    }

}
