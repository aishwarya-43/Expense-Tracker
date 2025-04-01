package project01;

abstract class BankAccount {
  protected String accountHolderName;
  protected int accountNumber;
  protected double balance;

  public BankAccount(String accountHolderName, int accountNumber, double balance){
    this.accountHolderName = accountHolderName;
    this.accountNumber = accountNumber;
    this.balance = balance;
  }
  public void deposit(double amount){
    if(amount > 0){
      balance = balance + amount;
      System.out.println(amount+" deposited successfully and current balance is : " + balance);

    }else{
      System.out.println("deposit amount should be greater than zero ");
    }
  }
  public abstract void withdraw(double amount);

  public void displayAccountInfo(){
    System.out.println("bank account details");
    System.out.println("accountHolderName:"+accountHolderName);
    System.out.println("accountNumber:"+accountNumber);
    System.out.println("Current Balance: " + balance);
  }
}
class SavingsAccount extends BankAccount{
private static final double MIN_BALANCE = 500;

public SavingsAccount(String accountHolderName, int accountNumber, double balance){
  super(accountHolderName, accountNumber,balance);
}

@override
public void withdraw(double amount){
  if(balance-amount>=MIN_BALANCE){
    balance = balance-amount;
    System.out.println("amount is withdrawn successfully.current balance is: "+balance);
  }else{
    System.out.println("you should have minimum of "+MIN_BALANCE+" your savings account");

  }
}
}
class CheckingsAccount extends BankAccount{
  private static final double OVER_DRAFT = 1000;
  public CheckingsAccount(String accountHolderName, int accountNumber, double balance){
    super(accountHolderName, accountNumber, balance);
  }
  @Override
public void withdraw(double amount){
if(balance-amount>=-OVER_DRAFT){
  balance -= amount;
  System.out.println("your "+amount+" is withdrawn successfully and current balance is:"+balance);
}else{
  System.out.println("you've exceeded overdraft limit and your overdraft limit is:"+OVER_DRAFT);
}
}
}
public class Main{
  public static void main(String[] args) {
    SavingsAccount savingobj = new SavingsAccount("vamshi", 505, 2000);
    savingobj.displayAccountInfo();
    System.out.println("--------------------------------");
    savingobj.withdraw(2000);
    System.out.println("--------------------------------");
    savingobj.withdraw(300);
    System.out.println("--------------------------------");

    
    CheckingsAccount checkobj = new CheckingsAccount("joy", 50532, 5000);
    checkobj.displayAccountInfo();
    System.out.println("--------------------------------");
    checkobj.withdraw(500);
    System.out.println("--------------------------------");
    checkobj.withdraw(5600);
    System.out.println("--------------------------------");
    checkobj.displayAccountInfo();

  }
}
