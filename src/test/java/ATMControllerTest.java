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
    public void runATM() throws InterruptedException {
        /*ByteArrayInputStream testInput = new ByteArrayInputStream(gui.input.setText("1"), gui.enterButton.doClick());
        System.setIn(testInput);
        controller.runATM();
        gui.input.setText("1");
        gui.enterButton.doClick();*/
    }
}