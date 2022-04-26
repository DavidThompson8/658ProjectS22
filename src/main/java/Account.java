public class Account {

    private int pan; //account number
    private int pin; //account PIN
    private double balance; //money in account
    private double dailyWithdrawn; //money user has withdrawn today
    private final double dailyLimit = 500; //money that users may withdraw in a single day

    //constructor
    public Account(int pan, int pin, double balance) {
        this.pan = pan;
        this.pin = pin;
        this.balance = balance;
        dailyWithdrawn = 0;
    }

    //getters
    public int getPan() {
        return pan;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    //deposit funds into account
    public void deposit(double deposit){
        this.balance += deposit;
    }

    //withdraw funds from account
    public int withdraw(double take){
        if(take + this.dailyWithdrawn > dailyLimit) { //user may not exceed daily limit
            return -1;
        }
        //user may not withdraw more funds than are in account
        //user must withdraw in multiples of 10
        else if(take > this.balance || take % 10 != 0){
            return 0;
        }
        else{ //withdraw funds from account
            this.dailyWithdrawn += take;
            this.balance -= take;
            return 1;
        }
    }

    public static Account[] generateAccounts(){ //generate a default list of accounts for use in an ATM
        Account [] accounts = new Account[3];
        accounts[0] = new Account(1, 123, 0);
        accounts[1] = new Account(2, 123, 1000);
        accounts[2] = new Account(3, 123, 15.17);
        return accounts;
    }
}
