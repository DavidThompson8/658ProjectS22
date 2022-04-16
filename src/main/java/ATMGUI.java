import javax.swing.*;

public class ATMGUI extends JFrame{
    private JPanel panel;
    private JButton balanceButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JTextField input;
    private JButton enterButton;
    private JButton cancelButton;
    private JLabel screen;
    private JButton yesButton;
    private JButton noButton;
    private ATM atm;

    public ATMGUI(ATM atm){
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(panel);
        this.atm = atm;
        this.screen.setText(atm.getScreen(atm.getCurrentScreen()));
    }

    public void changeScreen(){
        this.screen.setText(atm.getScreen(atm.getCurrentScreen()));
    }
}
