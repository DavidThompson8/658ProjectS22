import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ATMControllerTest {

    ATMGUI gui;
    ATM atm;
    ATMController controller;

    @Before
    public void setUp() throws Exception {
        atm = new ATM();
        gui = new ATMGUI(atm);
        controller = new ATMController(gui, atm);
    }

    @After
    public void tearDown() throws Exception {
        gui.dispose();
    }

    @Test
    public void useCase1() throws InterruptedException {
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(2, atm.getCurrentScreen());
    }

    @Test
    public void useCase2(){
        controller.addListeners();
        gui.input.setText("7");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void useCase3(){
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(5, atm.getCurrentScreen());
    }

    @Test
    public void useCase4(){
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void useCase5(){
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.balanceButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(6, atm.getCurrentScreen());
        assertEquals("<html>Current Balance:<br>$0.0", gui.screen.getText());
    }

    @Test
    public void useCase6(){
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.depositButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("100");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        controller.checkScreen();
        gui.changeScreen();
        gui.noButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(15, atm.getCurrentScreen());
        assertEquals("<html>Please take your receipt.<br>Have a nice day.", gui.screen.getText());
        assertEquals(100, atm.getCurrentAccount().getBalance(), .5);
    }

    @Test
    public void useCase7(){
        atm.changeSlotStatus();
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.depositButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.noButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(15, atm.getCurrentScreen());
        assertEquals("<html>Please take your receipt.<br>Have a nice day.", gui.screen.getText());
    }

    @Test
    public void useCase8(){
        controller.addListeners();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.withdrawButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("100");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        controller.checkScreen();
        gui.changeScreen();
        gui.noButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(15, atm.getCurrentScreen());
        assertEquals("<html>Please take your receipt.<br>Have a nice day.", gui.screen.getText());
        assertEquals(900, atm.getCurrentAccount().getBalance(), .5);
    }


    @Test
    public void useCase9(){
        controller.addListeners();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.withdrawButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("101");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        controller.checkScreen();
        gui.changeScreen();
        gui.noButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(15, atm.getCurrentScreen());
        assertEquals("<html>Please take your receipt.<br>Have a nice day.", gui.screen.getText());
        assertEquals(1000, atm.getCurrentAccount().getBalance(), .5);
    }

    @Test
    public void useCase10(){
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.withdrawButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("100");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(8, atm.getCurrentScreen());
        assertEquals("Insufficient funds.", gui.screen.getText());
        assertEquals(0, atm.getCurrentAccount().getBalance(), .5);
    }

    @Test
    public void useCase11(){
        controller.addListeners();
        gui.input.setText("2");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("123");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.withdrawButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        gui.input.setText("700");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        controller.checkScreen();
        gui.changeScreen();
        gui.noButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(15, atm.getCurrentScreen());
        assertEquals("<html>Please take your receipt.<br>Have a nice day.", gui.screen.getText());
        assertEquals(1000, atm.getCurrentAccount().getBalance(), .5);
    }
}