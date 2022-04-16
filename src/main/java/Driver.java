public class Driver {
    public static void main(String[] args){

        ATM atm = new ATM();
        ATMGUI view = new ATMGUI(atm);
        ATMController controller = new ATMController(view, atm);

        controller.runATM();

    }
}
