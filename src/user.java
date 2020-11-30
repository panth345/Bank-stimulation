import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class user extends functions{
    String actype,ac;
    Float amount;
    Scanner scanner=new Scanner(System.in);




    /*************************************************/
    public void NewUser() {

        System.out.print("Enter the number of Customers you want to Enter: ");
//reading the number of elements from the that we want to enter
        int n = scanner.nextInt();
        String[] name = new String[n];
        String [] contact = new String[n];
        String[] amnt  = new String[n];
        for (int i = 0; i < n; i++) {
//reading array elements from the user



            int c ;
            String lread;
            char nm[] = new char[6];

            System.out.println("\t\tEnter Your Details\n");
            System.out.println("-------------------------------------------------------------");

            try {

                PrintWriter outs = new PrintWriter(new FileWriter("userdetails.txt", true));
                BufferedWriter bw = new BufferedWriter(new FileWriter("statement.txt", true));

                do {
                    c = 0;
                    System.out.println("Please Enter Your Full Name :");
                    name[i] = scanner.next().trim();



                    if (name[i].length() < 3) {
                        System.out.println("\n--Name Should be greater than 2-Characters...");
                        c++;
                    } else if (name[i].matches(".*?[\\p{Punct}&&[^_]].*") || name[i].matches("[a-zA-Z ]*\\d+.*")) {
                        System.out.println("\n--Enter a Valid Name...");
                        c++;
                    }
                } while (c == 1);


                System.out.println("-------------------------------------------------------------");

                do {
                    c = 0;
                    System.out.println("Enter Your contact number :");
                    contact[i] = scanner.next().trim();

                    BufferedReader br = new BufferedReader(new FileReader("userdetails.txt"));

                    if (contact[i].matches("(.*)[a-zA-Z]+(.*)") || contact[i].matches(".*?[\\p{Punct}&&[^_]].*")) {
                        c++;
                        System.out.println("\n--Enter a Valid Contact Number...");
                    } else if (contact[i].length() != 10) {
                        c++;
                        System.out.println("\n--Contact Number Should be of 10-Digits...");
                    } else {
                        while ((lread = br.readLine()) != null) {
                            c = 0;
                            if (lread.contains(contact[i] + " ")) {
                                c++;
                                System.out.println("\n--Contact Number Already Exists...");
                                break;
                            }
                        }
                    }

                } while (c == 1);

                System.out.println("-------------------------------------------------------------");
                do {

                    c = 0;
                    System.out.println("Enter Your initial amount :");
                    amnt[i] = scanner.next().trim();

                    if (amnt[i].matches("(.*)[a-zA-Z]+(.*)") || contact[i].matches(".*?[\\p{Punct}&&[^_]].*")) {

                        c++;
                        System.out.println("\n--Enter a Valid Amount...");
                    } else {
                        amount = Float.parseFloat(amnt[i]);
                        if (amount < 1000) {
                            System.out.println("\n--Initial Amount Should be greater than or equals to Rs.1000...");
                            c++;
                        }
                    }
                } while (c == 1);

                System.out.println("-------------------------------------------------------------");
                do {
                    System.out.println("Enter Your Account type (Saving or Current) :");
                    ac = scanner.next();
                    actype = ac.toLowerCase();
                    if (!(actype.equals("saving") || actype.equals("current"))) {
                        System.out.println("\n--Account Type Should be saving or current...");
                    }
                } while (!(actype.equals("saving") || actype.equals("current")));

                if (actype.equals("saving")) {
                    actype = "saving ";
                }

                System.out.println();
                System.out.println("Account Created Successfully...");
                System.out.println("-------------------------------------------------------------");

                Random rnd = new Random();

                String username;
                name[i].getChars(0, 3, nm, 0);
                username = String.valueOf(nm).trim();

                if (username.length() < 2) {
                    username = username + (10000 + rnd.nextInt(90000));
                } else if (username.length() < 3) {
                    username = username + (1000 + rnd.nextInt(9000));
                } else {
                    username = username + (100 + rnd.nextInt(900));
                }

                if (username.contains(" ")) {
                    username = username.replace(" ", "a");
                }

                int acnt;
                do {
                    acnt = 10000 + rnd.nextInt(90000);
                    c = 0;
                    BufferedReader br = new BufferedReader(new FileReader("userdetails.txt"));
                    while ((lread = br.readLine()) != null) {
                        if (lread.contains(acnt + " ")) {
                            c++;
                            break;
                        }
                    }
                    br.close();
                } while (c == 1);


                int pass = 1000 + rnd.nextInt(9000);


                System.out.println("\nUsername : " + username);
                System.out.println("Pin Number : " + pass);
                System.out.println("Account Number : " + acnt);
                System.out.println("Initial Balance : " + amount);
                System.out.println("-------------------------------------------------------------");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                String date = sdf.format(new Date());


                bw.write(username + " " + acnt + "   " + amount + "  initial" + "    " + amount + "   " + date); //statement.txt

                bw.newLine();
                bw.close();
                outs.println(username + " " + pass + "  " + acnt + "  " + actype + "  " + contact[i] + "  " + amount); //userdetails.txt
                outs.close();

                BufferedWriter wr = new BufferedWriter(new FileWriter("name.txt", true));
                wr.write(username + " " + pass + "   " + name[i]);
                wr.newLine();
                wr.close();

                System.out.println("\nThank You");
                System.out.println("          For Banking...");
                System.out.println("-------------------------------------------------------------");

            } catch (InputMismatchException e) {
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
            } catch (Exception a) {
            }

        }
    }





        /****************************************************************/
        char account[]=new char[6];

        public void ExistingUser()
        {

            Scanner scan=new Scanner(System.in);
            int count=0;
            System.out.println("Enter User Name :");
            String uname=scan.next();
            System.out.println("Enter Your Pin Number :");
            String upass=scan.next();

            String userpass=uname+" "+upass+" ";
            char cnt=' ';
            int choice;
            String line,line2,fullname;
            char full[]=new char[100];

            try
            {


                BufferedReader ins=new BufferedReader(new FileReader("userdetails.txt"));


                while((line=ins.readLine())!=null)
                {

                    if(line.contains(userpass))
                    {

                        System.out.println("\nLogged In Successfully !!!");
                        System.out.println("-------------------------------------------------------------");
                        BufferedReader nmf=new BufferedReader(new FileReader("name.txt"));

                        while((line2=nmf.readLine())!=null)
                        {

                            if(line2.contains(userpass))
                            {
                                line2.getChars(13,line2.length(),full,0);
                                fullname=String.valueOf(full).trim();
                                System.out.println("\n\tWelcome Dear, "+fullname);
                            }
                        }
                        nmf.close();



                        line.getChars(12,18,account,0);
                        String ac=String.valueOf(account).trim();
                        String userac=uname+" "+ac;

                        do
                        {


                            System.out.println("-------------------------------------------------------------");
                            System.out.println("\nPlease Enter Your Choice :");
                            System.out.println("1.Deposit");
                            System.out.println("2.Withdraw");
                            System.out.println("3.Transfer Money");
                            System.out.println("4.Pay Utilities");
                            System.out.println("5.Mini Statement");
                            System.out.println("6.Loan Details");
                            System.out.println("7.Balance Inquiry");
                            System.out.println("8.Logout");
                            choice=scan.nextInt();
                            System.out.println("-------------------------------------------------------------");

                            switch(choice) {
                                case 1:
                                    ins.close();
                                    deposit(userpass);
                                    break;
                                case 2:
                                    ins.close();
                                    withdraw(userpass);
                                    break;
                                case 3:
                                    ins.close();
                                    System.out.println("Enter a account number to transfer from");
                                    String acc1 = scanner.nextLine();
                                    System.out.println("Enter a amount number to transfer to");
                                    String acc2 = scanner.nextLine();
                                    System.out.println("Enter the amount you want to transfer");
                                    Float a = scanner.nextFloat();

                                    withdrawfrom(acc1, a);
                                    depositto(acc2, a);
                                    break;
                                case 4:
                                    int options;
                                    do {
                                        System.out.println();

                                        System.out.println("1) Electricity Bill");
                                        System.out.println("2)Water Bill ");
                                        System.out.println("3) Mobile Bill ");
                                        System.out.println("4) Internet Bill");
                                        System.out.println("5) Back to the main menu");
                                        System.out.print("Enter choice [1-5]: ");
                                        options = scanner.nextInt();
                                        switch (options) {
                                            case 1:
                                                System.out.print(" Please Enter the Units that you Consumed  : ");
                                                int Units = scanner.nextInt();
                                                ElectricityBill1(Units, uname);
                                                break;

                                            case 2:

                                                System.out.print(" Enter the galoon of water used   : ");
                                                int galoonused = scanner.nextInt();
                                                waterBill(galoonused, userpass);
                                                break;

                                            case 3:

                                                System.out.print(" Please Enter the texts sent   : ");
                                                int textUsed = scanner.nextInt();
                                                System.out.print(" Please Enter minutes used  : ");
                                                double callUsed = scanner.nextDouble();
                                                mobileBill(textUsed, callUsed, userpass);

                                                break;

                                            case 4:
                                                System.out.print(" Please Enter the data consumed   : ");
                                                double dataconsumed = scanner.nextDouble();
                                                internetBill(dataconsumed, userpass);
                                                break;

                                            case 5:
                                                bank.main(null);
                                                break;
                                        }
                                    } while (options != 6);
                                           break;
                                case 5:
                                    ins.close();
                                    statement(userac);
                                    break;
                                case 6:
                                    Scanner scanner = new Scanner(System.in);

                                    System.out.print("Enter Principal Amount : ");
                                    double principal = scanner.nextDouble();
                                    System.out.print("Enter Time period in years : ");
                                    int year = scanner.nextInt();
                                  loandetails(principal, year);
                                    break;
                                case 7:
                                    ins.close();
                                    inquery(userpass);
                                    break;
                                case 8:
                                    bank.main(null);
                                    break;
                                default :
                                    System.out.println("\nOops !!! You Have Entered Wrong Choice...");
                                    System.out.println("-------------------------------------------------------------");
                                    break;

                            }
                            System.out.println("Do you want to continue (Y/N) :");
                            cnt=scan.next().charAt(0);




                        if(cnt=='Y' || cnt=='y')
                        {
                            continue;
                        }
                      else  if(cnt=='N')
                        {
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Thank You");
                            System.out.println("          For Banking...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        count=1;
                        break;
                        }while (choice!=9);
                    }


                }
                if(count==0)
                {
                    System.out.println("\nOops !!! You Have Entered Wrong User/Password(Pin)...");
                    System.out.println("-------------------------------------------------------------");
                }


                ins.close();


            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
            }
            catch(Exception e)
            {}
        }


        /****************************************************************/

        public void DeleteAccount()
        {
            String oldFileName = "userdetails.txt";
            String tmpFileName = "out.txt";

            System.out.println("Enter User Name :");
            String uname=scanner.next();
            System.out.println("Enter Your Pin Number :");
            String upin=scanner.next();

            String combo=uname+" "+upin+" ";
            String data;
            int cnt=0;
            String ac=null;
            String userac=null;
            BufferedReader ins=null;
            BufferedWriter outs=null;

            try
            {
                ins=new BufferedReader(new FileReader(oldFileName));
                outs=new BufferedWriter(new FileWriter(tmpFileName));

                while((data=ins.readLine())!=null)
                {

                    if(data.contains(combo))
                    {
                        data.getChars(11,17,account,0);
                        ac=String.valueOf(account).trim();

                        cnt++;
                        continue;
                    }

                    outs.write(data);
                    outs.newLine();
                }
                userac=uname+" "+ac;

                if(cnt==0)
                {
                    System.out.println("\nOops !!! You Have Entered Wrong User/Password(Pin)...");
                    System.out.println("-------------------------------------------------------------");
                }
                else
                {
                    System.out.println("\nAccount Deleted Successfully...");
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Thank You");
                    System.out.println("          For Banking...");
                    System.out.println("-------------------------------------------------------------");
                }

            }
            catch(Exception e){}
            finally
            {
                try
                {
                    if(ins != null)
                        ins.close();
                }
                catch (IOException e)
                {
                    //
                }
                try
                {
                    if(outs != null)
                        outs.close();
                }
                catch (IOException e)
                {
                    //
                }
            }

            File oldFile = new File(oldFileName);
            oldFile.delete();


            File newFile = new File(tmpFileName);
            newFile.renameTo(oldFile);
/////////////////////////////////////////////////////////////////////
            String old="statement.txt";
            String temp="delete.txt";
            try
            {
                ins=new BufferedReader(new FileReader(old));
                outs=new BufferedWriter(new FileWriter(temp));

                while((data=ins.readLine())!=null)
                {
                    if(data.contains(userac))
                    {
                        continue;
                    }

                    outs.write(data);
                    outs.newLine();
                }

            }
            catch(Exception e){}
            finally
            {
                try
                {
                    if(ins != null)
                        ins.close();
                }
                catch (IOException e)
                {
                    //
                }
                try
                {
                    if(outs != null)
                        outs.close();
                }
                catch (IOException e)
                {
                    //
                }
            }

            File oldF = new File(old);
            oldF.delete();


            File newF = new File(temp);
            newF.renameTo(oldF);
////////////////////////////////////////////////////////////////////////////////////////////////////

            String oldFile2="name.txt";
            String tempFile2="delete.txt";
            try
            {
                ins=new BufferedReader(new FileReader(oldFile2));
                outs=new BufferedWriter(new FileWriter(tempFile2));

                while((data=ins.readLine())!=null)
                {
                    if(data.contains(combo))
                    {
                        continue;
                    }

                    outs.write(data);
                    outs.newLine();
                }

            }
            catch(Exception e){}
            finally
            {
                try
                {
                    if(ins != null)
                        ins.close();
                }
                catch (IOException e)
                {
                    //
                }
                try
                {
                    if(outs != null)
                        outs.close();
                }
                catch (IOException e)
                {
                    //
                }
            }

            File oldName = new File(oldFile2);
            oldName.delete();


            File newName = new File(tempFile2);
            newName.renameTo(oldName);


        }
    }


