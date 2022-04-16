public class ATM {
    private int currentScreen;
    private final Account[] accounts;
    private Account currentAccount;
    private int currency;
    private boolean slotWorking;
    private boolean chuteWorking;
    private boolean running;
    private int pinAttempts;
    private String[] screens= new String[]{"<html>Welcome!<br>Please enter your member ID.",
            "Please enter your PIN.",
            "<html>Incorrect PIN.<br>Please try again.",
            "Invalid account details. You lose.",
            "Please select transaction type.",
            "Current Balance: ",
            "<html>How much are you depositing / withdrawing?<br> Withdrawals must be in multiples of $10.",
            "Insufficient funds.",
            "<html>Machine can only dispense $10 notes.<br>Machine may have insufficient funds.",
            "<html>Temporarily unable to process withdrawals.<br>Another transaction?",
            "<html>Your balance is being updated.<br>Please take cash from dispenser.",
            "<html>Temporarily unable to process deposits.<br>Another transaction?",
            "Please insert funds into deposit slot.",
            "<html>Receipt printing. <br>Another transaction?",
            "<html>Please take your receipt.<br>Have a nice day.",};

    public ATM() {
        this.currentScreen = 1;
        this.accounts = Account.generateAccounts();
        this.currentAccount = null;
        this.currency = 1000;
        this.slotWorking = true;
        this.chuteWorking = true;
        this.running = true;
        this.pinAttempts = 0;
    }


    public String getScreen(int screen) {
        return screens[screen-1];
    }

    public int getCurrentScreen() {
        return currentScreen;
    }

    public boolean isRunning() {
        return running;
    }

    public void setCurrentScreen(int screen){
        this.currentScreen = screen;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void screen1(int pan){ //Starting screen, asks for PAN
        boolean valid = false;
        int location = -1;
        for(int i = 0; i < accounts.length; i++){
            if (accounts[i].getPan() == pan){
                valid = true;
                location = i;
                break;
            }
        }
        if (valid){
            this.setCurrentScreen(2);
            this.setCurrentAccount(accounts[location]);
        }
        else{
            this.setCurrentScreen(4);
        }
    }

    public void screen2(int pin){ //Asks for PIN
        if(pin == this.currentAccount.getPin()){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(3);
        }
        this.pinAttempts ++;
    }

    public void screen3(int pin){ //Asks for PIN after incorrect PIN provided
        while (this.pinAttempts <= 3) {
            if (pin == this.currentAccount.getPin()) {
                this.setCurrentScreen(5);
                break;
            } else {
                this.pinAttempts++;
            }
        }
        this.setCurrentScreen(4);
    }

    public void screen4(){ //Invalid account information
        //screen is terminal until clear is added
    }

    public int screen5(int choice){ //Asks for transactions type
        if(choice == 1){
            this.setCurrentScreen(6);
            return 0;
        }
        else if(choice == 2){
            if(this.slotWorking){
                this.setCurrentScreen(7);
                return 1;
            }
            else{
                this.setCurrentScreen(12);
                return 0;
            }
        }
        else if(choice == 3){
            if(this.chuteWorking){
                this.setCurrentScreen(7);
                return 0;
            }
            else{
                this.setCurrentScreen(10);
                return 0;
            }

        }
        return 0;
    }

    public void screen6(){ //Shows current balance
        this.screens[5] = "<html>Current Balance:<br>$" + this.currentAccount.getBalance();
    }

    public void screen7(boolean deposit, double amount){ //Takes amount that is deposited or withdrawn
        if(deposit){
            this.setCurrentScreen(13);
            this.currentAccount.deposit(amount);
        }
        else{
            int valid = this.currentAccount.withdraw(amount);
            if(amount > this.currency || amount % 10 != 0){
                this.setCurrentScreen(9);
            }
            else{
                if (valid < 1){
                    this.setCurrentScreen(8);
                }
                else {
                    this.currency -= amount;
                    this.setCurrentScreen(11);
                }
            }
        }
    }

    public void screen8(){ //Account balance too low to withdraw
        //screen is terminal until clear is added
    }

    public void screen9(){ //Machine cannot provide withdrawal
        //screen is terminal until clear is added
    }

    public void screen10(boolean another){ //Withdrawal chute broken
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen11(){ //Cash is dispensed
        this.setCurrentScreen(14);
    }

    public void screen12(boolean another){ //deposit slot broken
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen13(){ //Deposit accepted
        this.setCurrentScreen(14);
    }

    public void screen14(boolean another){ //Transaction complete
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen15(){ //Usage complete
        //screen is terminal until clear is added
    }

    public void changeSlotStatus(){ //break or repair the imaginary deposit slot
        this.slotWorking = !this.slotWorking;
    }

    public void changeChuteStatus(){ //break or repair the imaginary withdrawal slot
        this.chuteWorking = !this.chuteWorking;
    }

    public void stopRunning(){
        this.running = false;
    }

}
