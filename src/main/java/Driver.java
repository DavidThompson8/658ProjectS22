public class Driver {
    public static void main(String[] args){

        ATM atm = new ATM();
        ATMView view = new ATMView();
        ATMController controller = new ATMController(view, atm);

        controller.setUp();
        controller.runATM();

    }
}
