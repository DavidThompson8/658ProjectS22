public class Driver {
    //driver class that provides a runnable ATM for manual testing
    public static void main(String[] args) throws InterruptedException {

        ATM atm = new ATM();
        ATMGUI view = new ATMGUI(atm);
        ATMController controller = new ATMController(view, atm);

        controller.runATM();

    }
}
