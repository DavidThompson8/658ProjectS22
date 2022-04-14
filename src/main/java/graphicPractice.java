import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class graphicPractice extends Canvas{

        public static void main(String[] args) {
            JFrame frame = new JFrame("My Drawing");
            Canvas canvas = new graphicPractice();
            canvas.setSize(1000, 1000);
            frame.add(canvas);
            frame.pack();
            frame.setVisible(true);


        }

        public void paint(Graphics g) {
            try {
                //File pathToFile = new File("src/main/resources/testimage.png");
                Image image = ImageIO.read(new File("src/main/resources/testimage.png"));
                g.drawImage(image, 100, 200, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

}
