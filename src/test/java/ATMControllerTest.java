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
    public void setUp() throws Exception { //set up atm for testing
        atm = new ATM();
        gui = new ATMGUI(atm);
        controller = new ATMController(gui, atm);
    }

    @After
    public void tearDown() throws Exception { //make sure GUI closes when done testing
        gui.dispose();
    }

    @Test
    public void useCase1() throws InterruptedException { //User provides valid PAN
        controller.addListeners();
        gui.input.setText("1");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(2, atm.getCurrentScreen());
    }

    @Test
    public void useCase2(){ //User provides invalid PAN
        controller.addListeners();
        gui.input.setText("7");
        gui.enterButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(4, atm.getCurrentScreen());
    }

    @Test
    public void useCase3(){ //User provides correct PIN after multiple attempts
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
    public void useCase4(){ //User fails to provide correct PIN in allowed number of attempts
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
    public void useCase5(){ //User checks their account balance
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
    public void useCase6(){ //User makes a deposit and no other transactions
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
    public void useCase7(){ //User wants to make a deposit, but deposit slot is not working
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
    public void useCase8(){ //User makes a withdrawal and no other transactions
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
    public void useCase9(){ //User attempts a withdrawal that is not a multiple of $10
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
    public void useCase10(){ //User attempts a withdrawal, but does not have enough funds in account
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
    public void useCase11(){ //User wants to make a withdrawal, but has exceeded the daily limit
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

    @Test
    public void useCase12(){ //User makes a withdrawal and wishes to make another transaction
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
        gui.yesButton.doClick();
        controller.checkScreen();
        gui.changeScreen();
        assertEquals(5, atm.getCurrentScreen());
        assertEquals("Please select transaction type.", gui.screen.getText());
        assertEquals(900, atm.getCurrentAccount().getBalance(), .5);
    }
}