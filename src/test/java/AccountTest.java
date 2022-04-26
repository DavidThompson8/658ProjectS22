import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest { //all tests are unit tests of methods they are named after

    Account a1;
    Account a2;

    @Before
    public void setUp() throws Exception {
        a1 = new Account(4, 4, 40);
        a2 = new Account(5, 5, 2000000);
    }


    @Test
    public void getPan() {
        assertEquals(4,a1.getPan());
    }

    @Test
    public void getPin() {
        assertEquals(4, a1.getPin());
    }

    @Test
    public void getBalance() {
        assertEquals(40.00, a1.getBalance(), .5);
    }

    @Test
    public void deposit() {
        a1.deposit(12);
        assertEquals(52.00, a1.getBalance(), .5);
    }

    @Test
    public void deposit2() {
        a1.deposit(1.20);
        assertEquals(41.20, a1.getBalance(), .5);
    }

    @Test
    public void withdraw() {
        a1.withdraw(10);
        assertEquals(30, a1.getBalance(), .5);
    }

    @Test
    public void withdraw2() {
        a1.withdraw(0.10);
        assertEquals(39.90, a1.getBalance(), .5);
    }

    @Test
    public void withdraw3() {
        a2.withdraw(1000000);
        assertEquals(2000000, a2.getBalance(), .5);
    }

    @Test
    public void withdraw4() {
        a1.withdraw(50);
        assertEquals(40, a1.getBalance(), .5);
    }

    @Test
    public void generateAccounts() {
        Account[] a = Account.generateAccounts();
        assertEquals(1, a[0].getPan());
        assertEquals(2, a[1].getPan());
        assertEquals(3, a[2].getPan());
        assertEquals(123, a[0].getPin());
        assertEquals(123, a[1].getPin());
        assertEquals(123, a[2].getPin());
        assertEquals(0, a[0].getBalance(), .5);
        assertEquals(1000, a[1].getBalance(), .5);
        assertEquals(15.17, a[2].getBalance(), .5);
    }
}