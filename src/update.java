import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class update {
String data;


        Scanner scan=new Scanner(System.in);

        public void UpdateUser()
        {
            int choice;
            System.out.println("Enter your choice for Update :");
            System.out.println("1.Account Holder Name");
            System.out.println("2.Contact Number");
            System.out.println("3.Exit");

            choice=scan.nextInt();

            switch(choice)
            {
                case 1:
                    Name();
                    break;

                case 2:
                    Contact();
                    break;

                case 3:
                    bank.main(null);
                    break;


                default :
                    System.out.println("Oops!!! You have Entered wrong choice...");
                    break;
            }

        }

        void Name()
        {

            System.out.println("Enter your old name");
            String name = scan.next().trim();
            String oldFileName = "name.txt";
            String tmpFileName = "out.txt";
            BufferedReader ins=null;
            BufferedWriter outs=null;

            try
            {
                ins=new BufferedReader(new FileReader(oldFileName));
                outs=new BufferedWriter(new FileWriter(tmpFileName));

                while((data=ins.readLine())!=null)
                {
                    if(data.contains(name))
                    {

                        try
                        {
                            System.out.println("Enter Update name :");
                            String wt=scan.next().trim();


                        if(name.length()<3)
                        {
                            System.out.println("\n--Name Should be greater than 2-Characters...");

                        }
                        else if(name.matches(".*?[\\p{Punct}&&[^_]].*")||name.matches("[a-zA-Z ]*\\d+.*"))
                        {
                            System.out.println("\n--Enter a Valid Name...");

                        }
                        else
                        {
                            data=data.replace(name,wt);
                            System.out.println("\nupdated name is : "+wt);

                        }

                        }
                        catch(NumberFormatException eoi)
                        {
                            System.out.println("\nOops !!! You Have Entered Wrong Input...");
                            System.out.println("-------------------------------------------------------------");

                        }

                    }

                    outs.write(data);

                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
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

                }
                try
                {
                    if(outs != null)
                        outs.close();
                }
                catch (IOException e)
                {

                }
            }

            File oldFile = new File(oldFileName);
            oldFile.delete();


            File newFile = new File(tmpFileName);
            newFile.renameTo(oldFile);


        }

        void Contact()
        {
            System.out.println("Enter your old contact number");
            String contact = scan.next().trim();
            String oldFileName = "userdetails.txt";
            String tmpFileName = "out.txt";
            BufferedReader ins=null;
            BufferedWriter outs=null;
            try
            {
                ins=new BufferedReader(new FileReader(oldFileName));
                outs=new BufferedWriter(new FileWriter(tmpFileName));

                while((data=ins.readLine())!=null)
                {
                    if(data.contains(contact))
                    {

                        try
                        {
                            System.out.println("Enter Updated contact number :");
                            String wt=scan.next().trim();



                            if (contact.matches("(.*)[a-zA-Z]+(.*)") || contact.matches(".*?[\\p{Punct}&&[^_]].*")) {

                                System.out.println("\n--Enter a Valid Contact Number...");
                            } else if (contact.length() != 10) {

                                System.out.println("\n--Contact Number Should be of 10-Digits...");
                            }
                            else
                            {
                                data=data.replace(contact,wt);
                                System.out.println("\nupdated contact number is : "+wt);
                            }

                        }
                        catch(NumberFormatException eoi)
                        {
                            System.out.println("\nOops !!! You Have Entered Wrong Input...");
                            System.out.println("-------------------------------------------------------------");

                        }

                    }

                    outs.write(data);
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
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
        }


    }


