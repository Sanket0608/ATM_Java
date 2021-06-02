import java.util.*;
import java.io.*;
class Exception1 extends Exception
{
    Exception1(String msg)
    {
        super(msg);
    }
}

class ATM_Transaction
{
    public static void main(String args[]) throws IOException
    {
        Scanner s=new Scanner(System.in);
        try
        {
            //Enter crediantials
            System.out.print("\n\n\t\tEnter Account Number : \t\t");
            String user=s.nextLine();
            System.out.print("\n\t\tEnter Pin : \t\t\t");
            String pass=s.nextLine(); 
            FileReader f1=new FileReader("Data.txt");
            BufferedReader br=new BufferedReader(f1);
            String str;
            while((str=br.readLine())!=null)
            {
                String username=str.split(" ")[0];
                if(user.equalsIgnoreCase(username))  
                {
                    String password=str.split(" ")[1];
                    if(pass.equals(password))
                    {
                        System.out.println("\n\t\tCorrect Pin");
                    }
                    else
                    {
                        System.out.println("\n\t\tWrong Pin Or Account Number");
                    }
                     String amount=str.split(" ")[2];
                     double balance=Double.parseDouble(amount); 
                    while(true)
                    {     
                        System.out.println("\t------------------------------------------------------------------------------");
                        System.out.println("\t\t|\t\t\t\t\t\t\t|");
                        System.out.println("\t\t| \t\t Any time Money\t\t\t\t|");
                        System.out.println("\t\t| \t\tChoose 1 for Withdraw\t\t\t|");
                        System.out.println("\t\t| \t\tChoose 2 for Deposit\t\t\t|");
                        System.out.println("\t\t| \t\tChoose 3 for Check Balance\t\t|");
                        System.out.println("\t\t| \t\tChoose 4 for EXIT\t\t\t|");
                        System.out.println("\t\t|\t\t\t\t\t\t\t|");
                        System.out.println("\t------------------------------------------------------------------------------");
                        System.out.print("\n\tChoose the operation you want to perform: ");
                        int n = s.nextInt();
                        switch(n)
                        {
                            case 1:
                            try
                            {
                                System.out.print("\n\t\tEnter money to be withdrawn: \t");
                                double withdraw = s.nextInt();
                                if(balance >= withdraw) 
                                {                    
                                    balance = balance - withdraw;
                                    System.out.println("\n\t\tPlease collect your money .........\n");
                                }
                                else
                                {
                                    throw new Exception1("\n\t\tInsufficant balance");
                                }
                            }
                            catch(Exception1 e)
                            {
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
                            
                            System.out.println("\n\t\t\t\tBalance : "+balance);
                            System.out.println("");
                            break;
                    
                            case 4:
                            System.exit(0);
                        }     
                        
                    }
                   
                }
                 
            }  
            f1.close();   
            s.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }

}