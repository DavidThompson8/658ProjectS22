import java.util.concurrent.TimeUnit;

public class ATM {
    private int currentScreen; //screen the ATM is currently on
    private final Account[] accounts; //accounts serviced by this ATM
    private Account currentAccount; //account currently being serviced
    private int currency; //money currently in ATM that can be dispensed
    private boolean slotWorking; //status of deposit slot
    private boolean chuteWorking; //status of withdrawal chute
    private boolean running; //status of the ATM (on/off)
    private int pinAttempts; //how many times users has attempted to enter PIN

    //Outputs for each screen
    protected String[] screens= new String[]{"<html>Welcome!<br>Please enter your member ID.",
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

    //Constructor
    public ATM() {
        this.currentScreen = 1;
        this.accounts = Account.generateAccounts();
        this.currentAccount = null;
        this.currency = 10000;
        this.slotWorking = true;
        this.chuteWorking = true;
        this.running = true;
        this.pinAttempts = 0;
    }


    //getters / setters
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

    public Account getCurrentAccount() {
        return currentAccount;
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
        if (valid){ //valid PAN goes to screen 2
            this.setCurrentAccount(accounts[location]);
            this.setCurrentScreen(2);
        }
        else{ //invalid PAN goes to screen 4
            this.setCurrentScreen(4);
        }
    }

    public void screen2(int pin){ //Asks for PIN
        if(pin == this.currentAccount.getPin()){ //Valid PIN logs in, goes to screen 5
            this.pinAttempts = 0; //reset number of attempts for next user
            this.setCurrentScreen(5);
        }
        else{ //invalid PIN goes to screen 3
            this.setCurrentScreen(3);
            this.pinAttempts ++;
        }

    }

    public void screen3(int pin){ //Asks for PIN after incorrect PIN provided
        if (this.pinAttempts < 3) { //Valid PIN logs in, goes to screen 5
            if (pin == this.currentAccount.getPin()) {
                this.pinAttempts = 0; //reset number of attempts for next user
                this.setCurrentScreen(5);
            } else { //invalid PIN reruns this screen until valid PIN is provided to all attemepts are used
                this.pinAttempts++;
                if (pinAttempts >= 3){ //If all attempts are used without valid entry, go to screen 4
                    this.setCurrentScreen(4);
                }
            }
            return;
        }
        else{this.setCurrentScreen(4);}
    }


    public int screen5(int choice){ //Asks for transactions type
        if(choice == 1){ //choice 1 is balance request, go to screen 6
            this.setCurrentScreen(6);
            return 0;
        }
        else if(choice == 2){ //choice 2 is deposit
            if(this.slotWorking){ //go to screen 7 if deposit slot is working
                this.setCurrentScreen(7);
                return 1;
            }
            else{ //go to scrren 12 if deposit slot is not working
                this.setCurrentScreen(12);
                return 0;
            }
        }
        else if(choice == 3){ //choice 3 is withdrawal
            if(this.chuteWorking){ //go to screen 7 if withdrawal chute is working
                this.setCurrentScreen(7);
                return 0;
            }
            else{ //go to screen 10 if withdrawal chute is not working
                this.setCurrentScreen(10);
                return 0;
            }

        }
        return 0;
    }

    //screen 6 displays current user's account balance
    public void screen6(){ //Shows current balance
        this.screens[5] = "<html>Current Balance:<br>$" + this.currentAccount.getBalance();
    }

    public void screen7(boolean deposit, double amount){ //Takes amount that is deposited or withdrawn
        if(deposit){ //if deposit was chosen on screen 5, deposit the amount
            this.setCurrentScreen(13);
            this.currentAccount.deposit(amount);
        }
        else{ //if withdrawal was chosen on screen 5, attempt to withdraw the amount
            int valid = this.currentAccount.withdraw(amount);
            if(amount > this.currency){ //if ATM lacks requested funds, go to screen 9
                this.setCurrentScreen(9);
            }
            else{
                if (valid < 1){ //if user is not allowed to withdraw requested funds, go to screen 8
                    this.setCurrentScreen(8);
                }
                else { //if user can perform withdrawal, withdraw the funds
                    this.currency -= amount;
                    this.setCurrentScreen(11);
                }
            }
        }
    }

    public void screen10(boolean another){ //Withdrawal chute broken
        if(another){ //go back to screen 5 if user wants to continue with transactions
            this.setCurrentScreen(5);
        }
        else{ //go to screen 15 if user is done
            this.setCurrentScreen(15);
        }
    }

    public void screen11(){ //Cash is dispensed
        //TimeUnit.SECONDS.sleep(5);
        this.setCurrentScreen(14);
    }

    public void screen12(boolean another){ //deposit slot broken
        if(another){ //go back to screen 5 if user wants to continue with transactions
            this.setCurrentScreen(5);
        }
        else{ //go to screen 15 if user is done
            this.setCurrentScreen(15);
        }
    }

    public void screen13() { //Deposit accepted
        //TimeUnit.SECONDS.sleep(5);
        this.setCurrentScreen(14);
    }

    public void screen14(boolean another){ //Transaction complete
        if(another){ //go back to screen 5 if user wants to continue with transactions
            this.setCurrentScreen(5);
        }
        else{ //go to screen 15 if user is done
            this.setCurrentScreen(15);
        }
    }


    //break or repair parts of the ATM, to be used when testing
    public void changeSlotStatus(){ //break or repair the imaginary deposit slot
        this.slotWorking = !this.slotWorking;
    }

    public void changeChuteStatus(){ //break or repair the imaginary withdrawal slot
        this.chuteWorking = !this.chuteWorking;
    }

    //more getters / setters
    public void stopRunning(){
        this.running = false;
    }

    public boolean isSlotWorking() {
        return slotWorking;
    }

    public boolean isChuteWorking() {
        return chuteWorking;
    }
}
