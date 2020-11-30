import java.util.InputMismatchException;
import java.util.Scanner;

public class bank {


        public static void main(String[] args) {

            try{
                user U=new user();
                update Up =new update();



                int choice;
                char a='n';
                Scanner scan=new Scanner(System.in);
                System.out.println("-------------------------------------------------------------");
                System.out.println("                      Welcome                         	 ");
                System.out.println("-------------------------------------------------------------");

                do
                {


                    System.out.println("\nPlease Enter your choice :");
                    System.out.println("1.Create New Account");
                    System.out.println("2.Existing Account");
                    System.out.println("3.Delete Account");
                    System.out.println("4.Update Account");
                    System.out.println("5.Exit");
                    choice=scan.nextInt();
                    System.out.println("-------------------------------------------------------------");

                    if(choice>=6 || choice<1)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Choice...");
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Do you want to continue (Y/N) :");
                        a=scan.next().charAt(0);
                        System.out.println("-------------------------------------------------------------");
                    }

                }while(a=='y' || a=='Y');



                switch(choice)
                {
                    case 1:
                        U.NewUser();
                        U.ExistingUser();
                        break;
                    case 2:
                        U.ExistingUser();
                        break;
                    case 3:
                        U.DeleteAccount();
                        break;
                    case 4:
                        Up.UpdateUser();
                        break;
                    case 5:
                        System.out.println("Thank You");
                        System.out.println("          For Visiting...");
                        System.out.println("-------------------------------------------------------------");
                        return;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }



                }
            catch(InputMismatchException a)
            {
                System.out.println("-------------------------------------------------------------");
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
            }
            catch(Exception e){}
        }

    }


