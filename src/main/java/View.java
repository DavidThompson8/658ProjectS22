import javax.swing.*;
import java.awt.*;

public class View{

    public View() {
    }

    public void initialScreen(Graphics graphics, JFrame frame, Canvas canvas, Image UI){
        //will draw initial screen, including UI
        canvas.setSize(6000, 1000);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

    }

    public void updateScreen(int screen){
        //will change the subscreen to the given screen
        System.out.println("Changing to screen " + screen);
    }

}
