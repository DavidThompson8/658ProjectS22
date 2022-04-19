import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ATMGUITest {

    ATMGUI gui;
    ATM atm;

    @Before
    public void setUp() throws Exception {
        atm = new ATM();
        gui = new ATMGUI(atm);
    }

    @After
    public void tearDown() throws Exception {
        gui.dispose();
    }

    @Test
    public void panel(){
        assertNotNull(gui.panel);
    }

    @Test
    public void balanceButton(){
        assertNotNull(gui.balanceButton);
    }

    @Test
    public void withdrawButton(){
        assertNotNull(gui.withdrawButton);
    }

    @Test
    public void input(){
        assertNotNull(gui.input);
        assertEquals("", gui.input.getText());
    }

    @Test
    public void enterButton(){
        assertNotNull(gui.enterButton);
    }

    @Test
    public void cancelButton(){
        assertNotNull(gui.cancelButton);
    }

    @Test
    public void screen(){
        assertNotNull(gui.screen);
        assertEquals("<html>Welcome!<br>Please enter your member ID.", gui.screen.getText());
    }

    @Test
    public void yesButton(){
        assertNotNull(gui.yesButton);
    }

    @Test
    public void noButton(){
        assertNotNull(gui.noButton);
    }

    @Test
    public void depositButton(){
        assertNotNull(gui.depositButton);
    }


    @Test
    public void changeScreen() {
        atm.setCurrentScreen(2);
        gui.changeScreen();
        assertEquals("Please enter your PIN.", gui.screen.getText());
    }
}