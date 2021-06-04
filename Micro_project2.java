import java.util.*;
import java.io.*;
class Exception1 extends Exception {
    Exception1(String msg) {
        super(msg);
    }
}

public class Micro_project2 {
    public static void main(String args[]) throws Exception {
        Scanner s = new Scanner(System.in);
        String accountNumber = "";
        String pin = "";
        double balance = 0;
        try {
            // Enter crediantials
            System.out.print("\n\n\t\tEnter Account Number : \t\t");
            accountNumber = s.nextLine();
            System.out.print("\n\t\tEnter Pin : \t\t\t");
            pin = s.nextLine();
            FileReader f1 = new FileReader("C:\\Users\\sanke\\Desktop\\tp\\Java ATM MicroProject\\Data.txt");
            BufferedReader br = new BufferedReader(f1);
            String str;
            mainLoop: while ((str = br.readLine()) != null) {
                String username = str.split(" ")[0];
                if (accountNumber.equalsIgnoreCase(username)) {
                    String password = str.split(" ")[1];
                    if (pin.equals(password)) {
                        System.out.println("\n\t\tCorrect Pin");
                    } else {
                        System.out.println("\n\t\tWrong Pin Or Account Number");
                    }
                    String amount = str.split(" ")[2];
                    balance = Double.parseDouble(amount);
                    while (true) {
                        System.out.println("\t------------------------------------------------------------------------------");
                        System.out.println("\t\t|\t\t\t\t\t\t\t|");
                        System.out.println("\t\t| \t\t Automated Teller Machine\t\t|");
                        System.out.println("\t\t| \t\tChoose 1 for Withdraw\t\t\t|");
                        System.out.println("\t\t| \t\tChoose 2 for Deposit\t\t\t|");
                        System.out.println("\t\t| \t\tChoose 3 for Check Balance\t\t|");
                        System.out.println("\t\t| \t\tChoose 4 for EXIT\t\t\t|");
                        System.out.println("\t\t|\t\t\t\t\t\t\t|");
                        System.out.println(
                                "\t------------------------------------------------------------------------------");
                        System.out.print("\n\tChoose the operation you want to perform: ");
                        int n = s.nextInt();
                        switch (n) {
                            case 1:
                                try {
                                    System.out.print("\n\t\tEnter money to be withdrawn: \t");
                                    double withdraw = s.nextInt();
                                    if (balance >= withdraw) {
                                        balance = balance - withdraw;
                                        System.out.println("\n\t\tPlease collect your money .........\n");
                                    } else {
                                        throw new Exception1("\n\t\tInsufficant balance");
                                    }
                                } catch (Exception1 e) {
                                    System.out.println(e);
                                }
                                break;

                            case 2:
                                System.out.print("\n\t\t\tEnter money to be deposited: \t");
                                double deposit = s.nextInt();
                                balance = balance + deposit;
                                System.out.println("\n\t\tYour Money has been successfully depsited ......");
                                System.out.println("");
                                break;

                            case 3:

                                System.out.println("\n\t\t\t\tBalance : " + balance);
                                System.out.println("");
                                break;

                            case 4:
                                break mainLoop;
                        }

                    }

                }

            }
            f1.close();
            s.close();

            try {
                // Account Class
                class Account {
                    public String username;
                    public String password;
                    public String balance;

                    public Account(String username, String password, String balance) {
                        this.username = username;
                        this.password = password;
                        this.balance = balance;
                    }
                }

                // Accounts Array
                ArrayList<Account> accounts = new ArrayList<Account>();

                // Load Accounts from Data.txt file
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sanke\\Desktop\\tp\\Java ATM MicroProject\\Data.txt"));
                String temp;
                while ((temp = reader.readLine()) != null) {
                    String[] strParts = temp.split(" ");
                    accounts.add(new Account(strParts[0], strParts[1], strParts[2]));
                }
                reader.close();

                // Change Account Balance
                for (Account account : accounts) {
                    if (account.username.equalsIgnoreCase(accountNumber)) {
                        account.balance = String.valueOf(balance);
                    }
                }

                // Save accounts to Data.txt
                FileWriter fw = new FileWriter("Data.txt");
                for (Account account : accounts) {
                    fw.write(String.join(" ", Arrays.asList(account.username, account.password, account.balance)));
                    fw.write("\n");
                }
                fw.close();

                System.out.println("Hurray!!");
            } catch (IOException ioException) {
                System.out.println("Unexpected Error Occured");
                System.out.println(ioException.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}