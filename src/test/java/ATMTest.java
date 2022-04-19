import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ATMTest {

    ATM atm;
    Account a1;
    Account a2;

    @Before
    public void setUp() throws Exception {
        atm = new ATM();
        a1 = new Account(4, 1, 1);
        a2 = new Account(5, 2, 2000.01);
        atm.setCurrentAccount(a2);
    }


    @Test
    public void getScreen() {
        assertEquals("<html>Welcome!<br>Please enter your member ID.", atm.getScreen(1));
    }

    @Test
    public void getCurrentScreen() {
        assertEquals(1, atm.getCurrentScreen());
    }

    @Test
    public void isRunning() {
        assertTrue(atm.isRunning());
    }

    @Test
    public void setCurrentScreen() {
        atm.setCurrentScreen(2);
        assertEquals(2, atm.getCurrentScreen());
    }

    @Test
    public void setCurrentAccount() {
        atm.setCurrentAccount(a1);
        assertEquals(a1, atm.getCurrentAccount());
    }

    @Test
    public void getCurrentAccount(){
        assertEquals(a2, atm.getCurrentAccount());
    }

    @Test
    public void screen1() {
        atm.screen1(1);
        assertEquals(2, atm.getCurrentScreen());
    }

    @Test
    public void screen1t2() {
        atm.screen1(10);
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void screen2() {
        atm.screen2(2);
        assertEquals(5, atm.getCurrentScreen());
    }

    @Test
    public void screen2t2() {
        atm.screen2(20);
        assertEquals(3, atm.getCurrentScreen());
    }

    @Test
    public void screen3() {
        atm.screen2(20);
        atm.screen3(2);
        assertEquals(5, atm.getCurrentScreen());
    }

    @Test
    public void screen3t2() {
        atm.screen2(20);
        atm.screen3(20);
        assertEquals(3, atm.getCurrentScreen());
    }

    @Test
    public void screen3t3() {
        atm.screen2(20);
        atm.screen3(20);
        atm.screen3(20);
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void screen3t4() {
        atm.screen2(20);
        atm.screen3(20);
        atm.screen3(2);
        assertEquals(5, atm.getCurrentScreen());
    }

    @Test
    public void screen3t5() {
        atm.screen2(20);
        atm.screen3(20);
        atm.screen3(20);
        atm.screen3(20);
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void screen5() {
        atm.screen5(1);
        assertEquals(6, atm.getCurrentScreen());
    }

    @Test
    public void screen5t2() {
        atm.screen5(2);
        assertEquals(7, atm.getCurrentScreen());
    }

    @Test
    public void screen5t3() {
        atm.screen5(3);
        assertEquals(7, atm.getCurrentScreen());
    }

    @Test
    public void screen5t4() {
        atm.changeSlotStatus();
        atm.screen5(2);
        assertEquals(12, atm.getCurrentScreen());
    }

    @Test
    public void screen5t5() {
        atm.changeChuteStatus();
        atm.screen5(3);
        assertEquals(10, atm.getCurrentScreen());
    }

    @Test
    public void screen6() {
        atm.screen6();
        assertEquals("<html>Current Balance:<br>$2000.01", atm.screens[5]);
    }

    @Test
    public void screen7() {
        atm.screen7(true, 1);
        assertEquals(13, atm.getCurrentScreen());
        assertEquals(2001.01, a2.getBalance(), .5);
    }

    @Test
    public void screen7t2() {
        atm.screen7(false, 1);
        assertEquals(8, atm.getCurrentScreen());
        assertEquals(2000.01, a2.getBalance(), .5);
    }

    @Test
    public void screen7t3() {
        atm.screen7(false, 10);
        assertEquals(11, atm.getCurrentScreen());
        assertEquals(1990.01, a2.getBalance(), .5);
    }

    @Test
    public void screen7t4() {
        atm.screen7(false, 10000);
        assertEquals(9, atm.getCurrentScreen());
        assertEquals(2000.01, a2.getBalance(), .5);
    }

    @Test
    public void screen10() {
        atm.screen10(true);
        assertEquals(5, this.atm.getCurrentScreen());
    }

    @Test
    public void screen10t2() {
        atm.screen10(false);
        assertEquals(15, this.atm.getCurrentScreen());
    }

    @Test
    public void screen11() {
        atm.screen11();
        assertEquals(14, atm.getCurrentScreen());
    }

    @Test
    public void screen12() {
        atm.screen12(true);
        assertEquals(5, this.atm.getCurrentScreen());
    }

    @Test
    public void screen12t2() {
        atm.screen12(false);
        assertEquals(15, this.atm.getCurrentScreen());
    }

    @Test
    public void screen13() {
        atm.screen13();
        assertEquals(14, atm.getCurrentScreen());
    }

    @Test
    public void screen14() {
        atm.screen14(true);
        assertEquals(5, this.atm.getCurrentScreen());
    }

    @Test
    public void screen14t2() {
        atm.screen14(false);
        assertEquals(15, this.atm.getCurrentScreen());
    }

    @Test
    public void changeSlotStatus() {
        atm.changeSlotStatus();
        assertFalse(atm.isSlotWorking());
    }

    @Test
    public void changeChuteStatus() {
        atm.changeChuteStatus();
        assertFalse(atm.isChuteWorking());
    }

    @Test
    public void stopRunning() {
        atm.stopRunning();
        assertFalse(atm.isRunning());
    }

    @Test
    public void getSlotStatus(){
        assertTrue(atm.isSlotWorking());
    }

    @Test
    public void getChuteStatus(){
        assertTrue(atm.isChuteWorking());
    }
}