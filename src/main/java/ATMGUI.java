import javax.swing.*;

public class ATMGUI extends JFrame{
    private JPanel panel;
    protected JButton balanceButton;
    protected JButton withdrawButton;
    protected JButton depositButton;
    protected JTextField input;
    protected JButton enterButton;
    protected JButton cancelButton;
    private JLabel screen;
    protected JButton yesButton;
    protected JButton noButton;
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
