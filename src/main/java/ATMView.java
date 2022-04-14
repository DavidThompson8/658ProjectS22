import javax.swing.*;
import java.awt.*;

public class ATMView {

    public ATMView() {
    }

    public void initialScreen(){
        //will draw initial screen, including UI
        System.out.println("Initial UI would appear now");

    }

    public void updateScreen(int screen){
        //will change the subscreen to the given screen
        System.out.println("Changing to screen " + screen);
    }

}
