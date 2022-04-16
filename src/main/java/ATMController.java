import java.util.Scanner;

public class ATMController {
    private ATMGUI view;
    private ATM atm;

    public ATMController(ATMGUI view, ATM atm) {
        this.view = view;
        this.atm = atm;
    }

    /*public void setUp(){
        this.view.initialScreen();
    }*/

    public void runATM(){
        //FIX THIS TO WORK AS GRAPHICS INSTEAD OF TEXT
        Scanner scan = new Scanner(System.in);
        while(this.atm.isRunning()){
            boolean deposit = false;
            boolean another = false;

            switch(this.atm.getCurrentScreen()){
                case 1: this.atm.screen1(scan.nextInt());
                    break;
                case 2: this.atm.screen2(scan.nextInt());
                    break;
                case 3: this.atm.screen3(scan.nextInt());
                    break;
                case 4: this.atm.screen4();
                    this.atm.stopRunning();
                    break;
                case 5: {if(this.atm.screen5(scan.nextInt()) == 1){
                    deposit = true;
                    }
                    break;}
                case 6: this.atm.screen6();
                    this.atm.stopRunning();
                    break;
                case 7: this.atm.screen7(deposit, scan.nextInt());
                    break;
                case 8: this.atm.screen8();
                    this.atm.stopRunning();
                    break;
                case 9: this.atm.screen9();
                    this.atm.stopRunning();
                    break;
                case 10: {if(scan.next().equals("yes")){
                    another = true;
                    }
                    this.atm.screen10(another);
                    another = false;
                    break;}
                case 11: this.atm.screen11();
                    break;
                case 12: {if(scan.next().equals("yes")){
                    another = true;
                    }
                    this.atm.screen12(another);
                    another = false;
                    break;}
                case 13: this.atm.screen13();
                    break;
                case 14: {if(scan.next().equals("yes")){
                    another = true;
                    }
                    this.atm.screen14(another);
                    break;}
                case 15: this.atm.screen15();
                    this.atm.stopRunning();
                    break;
                default: break;
            }

            this.view.changeScreen(this.atm.getScreen2());
        }
    }
}
