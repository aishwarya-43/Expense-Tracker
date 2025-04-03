import java.io.*;
import java.util.*;

public class ExpenseTracker{

//method to save an expense to the file
public static void saveExpenseToFile(String name,int amount){
  try(FileWriter writer = new FileWriter("expenses.txt", true)){
    writer.write(name+","+amount+"\n");
    System.out.println("expenses are saved successfully:"+name+"-rs"+amount);
  }
    catch(IOException e){
  System.out.println("couldnt save the expenses due to: "+e.getMessage());
  }
}
// Method to load and display expenses from file
public static void loadExpensesFromFile(){
  File file1 = new File("expenses.txt");
  //checkin if the file exists or not
  if(!file1.exists()){
    System.out.println("no files found");
    return;
  }
  try(BufferedReader reader = new BufferedReader(new FileReader(file1))){
    String line;
    System.out.println("\n saved Expenses:");
    boolean hasExpenses = false;
    //read file line by line
    while((line = reader.readLine()) !=null){
      System.out.println(line);
      hasExpenses = true;
    }
    if(!hasExpenses){
      System.out.println("no expenses recorded");
    }
  }catch(IOException e){
    System.out.println("found error"+e.getMessage());
  }

}

 //Method to delete an expense from the file
 public static void deleteExpenseFromFile(String expenseToDelete){

File inputFile = new File("expenses.txt");
File tempFile = new File("temp.txt");
if(!inputFile.exists()){
  System.out.println("file not found");
  return;
}
boolean found = false;
try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
  String line;
    // Read each line and copy all except the one to be deleted
    while((line =reader.readLine()) !=null){
      if(!line.startsWith(expenseToDelete +",")){
        writer.write(line+"\n");
      }else{
        found = true;
      }
    }
}catch(IOException e){
  System.out.println("found error"+e.getMessage());
}
if(found){
  inputFile.delete();
  tempFile.renameTo(inputFile);
  System.out.println("Expense deleted successfully");
}else{
  tempFile.delete();
  System.out.println("Expense not found"+expenseToDelete);
}
 }




  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
Map<String, Integer> expenses = new HashMap<>();

    boolean running = true;
    while(running){
      System.out.println("Smart Expense Tracker");
      System.out.println("1. Add expenses");
      System.out.println("2. View expenses");
      System.out.println("3. Delete expense");
      System.out.println("4. EXIT..");
      System.out.println("choose an option: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume newline character
if(choice == 1){
  System.out.println("Adding expenses...");
  System.out.println("enter expense name:");
  String name = sc.nextLine();
  System.out.println("enter amount spent:");
int amount = sc.nextInt();
sc.nextLine();
expenses.put(name, amount);
System.out.println("expense added successfully");
saveExpenseToFile(name, amount);


}else if(choice == 2){
  System.out.println("Viewing expenses...");
  System.out.println("Your expenses:");
  loadExpensesFromFile();

}
else if(choice == 3){
  System.out.println("Deleting expenses...");
  System.out.println("enter the expense to be deleted:");
  String expenseToDelete = sc.nextLine();
  deleteExpenseFromFile(expenseToDelete);
}


else if(choice == 4){
  System.out.println("Exiting!!! Have a good day");
  running = false;
}

else{
  System.out.println("Invalid!!please try again");

}
}
sc.close();
  }
}

  


