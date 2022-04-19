public class Account {

    private int pan;
    private int pin;
    private double balance;
    private double dailyWithdrawn;
    private final double dailyLimit = 1000;

    public Account(int pan, int pin, double balance) {
        this.pan = pan;
        this.pin = pin;
        this.balance = balance;
        dailyWithdrawn = 0;
    }

    public int getPan() {
        return pan;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double deposit){
        this.balance += deposit;
    }

    public int withdraw(double take){
        if(take + this.dailyWithdrawn > dailyLimit) {
            return -1;
        }
        else if(take > this.balance || take % 10 != 0){
            return 0;
        }
        else{
            this.dailyWithdrawn += take;
            this.balance -= take;
            return 1;
        }
    }

    public static Account[] generateAccounts(){
        Account [] accounts = new Account[3];
        accounts[0] = new Account(1, 123, 0);
        accounts[1] = new Account(2, 123, 1000);
        accounts[2] = new Account(3, 123, 15.17);
        return accounts;
    }
}
