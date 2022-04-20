import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ATMController {
    private ATMGUI gui;
    private ATM atm;
    //int in;
    private boolean depositing;
    ActionListener cancel = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            atm.setCurrentScreen(1);
        }
    };

    ActionListener enter = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = gui.input.getText();
            gui.input.setText("");
            Double in = Double.parseDouble(text);
            switch (atm.getCurrentScreen()){
                case 1: atm.screen1(in.intValue());
                    break;
                case 2: atm.screen2(in.intValue());
                    break;
                case 3: atm.screen3(in.intValue());
                    break;
                case 7: atm.screen7(depositing, in);
                default: break;
            }
        }
    };

    ActionListener balance = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(atm.getCurrentScreen() == 5){
                atm.screen5(1);
                depositing = true;
            }
        }
    };

    ActionListener deposit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(atm.getCurrentScreen() == 5){
                atm.screen5(2);
            }

        }
    };

    ActionListener withdraw = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(atm.getCurrentScreen() == 5){
                atm.screen5(3);
            }
        }
    };

    ActionListener yes = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (atm.getCurrentScreen()){
                case 10: atm.screen10(true);
                    break;
                case 12: atm.screen12(true);
                    break;
                case 14: atm.screen14(true);
                    break;
                default: break;
            }
        }
    };

    ActionListener no = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (atm.getCurrentScreen()){
                case 10: atm.screen10(false);
                    break;
                case 12: atm.screen12(false);
                    break;
                case 14: atm.screen14(false);
                    break;
                default: break;
            }
        }
    };

    public ATMController(ATMGUI gui, ATM atm) {
        this.gui = gui;
        this.atm = atm;
        //this.in = -1;
        this.depositing = false;
    }

    public void addListeners(){
        gui.cancelButton.addActionListener(cancel);
        gui.enterButton.addActionListener(enter);
        gui.balanceButton.addActionListener(balance);
        gui.depositButton.addActionListener(deposit);
        gui.withdrawButton.addActionListener(withdraw);
        gui.yesButton.addActionListener(yes);
        gui.noButton.addActionListener(no);
    }

    public void checkScreen(){
        switch(atm.getCurrentScreen()){
            case 6: atm.screen6();
                break;
            case 11: atm.screen11();
                break;
            case 13: atm.screen13();
                break;
            default: break;
        }
    }


    public void runATM() throws InterruptedException {


        addListeners();

        while(this.atm.isRunning()){
            checkScreen();
            this.gui.changeScreen();
        }

        gui.dispose();
    }
}
