public class Model {
    private int currentScreen;
    private final Account[] accounts;
    private Account currentAccount;
    private int currency;

    public Model() {
        this.currentScreen = 1;
        this.accounts = Account.generateAccounts();
        this.currentAccount = null;
        this.currency = 1000;
    }

    public int getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(int screen){
        this.currentScreen = screen;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void screen1(int pan){
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

    public void screen2(int pin){
        if(pin == this.currentAccount.getPin()){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(3);
        }
    }

    public void screen3(int pin){
        int attempts = 1;
        while (attempts <= 3) {
            if (pin == this.currentAccount.getPin()) {
                this.setCurrentScreen(5);
                break;
            } else {
                attempts++;
            }
        }
        this.setCurrentScreen(4);
    }

    public void screen4(){
        //screen is terminal until clear is added
    }

    public void screen5(int choice, boolean workingSlot, boolean workingChute){
        if(choice == 1){
            this.setCurrentScreen(6);
        }
        else if(choice == 2){
            if(workingSlot){
                this.setCurrentScreen(7);
            }
            else{
                this.setCurrentScreen(12);
            }
        }
        else if(choice == 3){
            if(workingChute){
                this.setCurrentScreen(7);
            }
            else{
                this.setCurrentScreen(10);
            }

        }

    }

    public double screen6(){
        return currentAccount.getBalance();
    }

    public void screen7(boolean deposit, double amount){
        if(deposit){
            this.setCurrentScreen(13);
            this.currentAccount.deposit(amount);
        }
        else{
            int valid = this.currentAccount.withdraw(amount);
            if(amount > this.currency || amount % 10 != 0 || valid < 1){
                this.setCurrentScreen(9);
            }
            else{
                this.currency -= amount;
                this.setCurrentScreen(11);
            }
        }
    }

    public void screen8(){
        //screen is terminal until clear is added
    }

    public void screen9(){
        //screen is terminal until clear is added
    }

    public void screen10(boolean another){
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen11(){
        this.setCurrentScreen(14);
    }

    public void screen12(boolean another){
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen13(){
        this.setCurrentScreen(14);
    }

    public void screen14(boolean another){
        if(another){
            this.setCurrentScreen(5);
        }
        else{
            this.setCurrentScreen(15);
        }
    }

    public void screen15(){
        //screen is terminal until clear is added
    }

}