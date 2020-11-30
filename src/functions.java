import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class functions {


        float damt=0.0f,wamt=0.0f,bal=0.0f;
   double RATE = 0.035;
        String data;

        char fbal[]=new char[20];
        char user[]=new char[20];
        char account[]=new char[20];
        int l=0;
        Scanner scan=new Scanner(System.in);

        public void deposit(String nmpass)
        {
            String oldFileName = "userdetails.txt";
            String tmpFileName = "out.txt";
            BufferedReader ins=null;
            BufferedWriter outs=null;


            try
            {

                ins=new BufferedReader(new FileReader(oldFileName));
                outs=new BufferedWriter(new FileWriter(tmpFileName));

                BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
                while((data=ins.readLine())!=null)
                {

                    if(data.contains(nmpass))
                    {
                        l=data.length();
                        data.getChars(40,l-1,fbal,0);
                        String b=String.valueOf(fbal).trim();

                        bal=Float.parseFloat(b);

                        data.getChars(0,6,user,0);
                        String u=String.valueOf(user).trim();
                        data.getChars(12,18,account,0);
                        String ac=String.valueOf(account).trim();

                        try
                        {
                            System.out.println("Please Enter Your Amount :");
                            String dm=scan.next();
                            damt=Float.parseFloat(dm);

                            if(damt<100.0f)
                            {
                                System.out.println("\nAmount Should be greater than or equals to Rs. 100...");
                                System.out.println("-------------------------------------------------------------");
                            }
                            else
                            {
                                bal=bal+damt;
                                System.out.println("\nTransaction Completed Successfully...");
                                System.out.println("\nTotal Balance : "+bal);
                                System.out.println("-------------------------------------------------------------");

                                String c=String.valueOf(bal);
                                data=data.replace(b,c);

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                                String date = sdf.format(new Date());

                                bw.write(u+" "+ac+"   "+damt+"  deposit"+"    "+bal+"   "+date);
                                bw.newLine();
                                bw.close();
                            }
                        }
                        catch(NumberFormatException ioe)
                        {
                            System.out.println("\nOops !!! You Have Entered Wrong Input...");
                            System.out.println("-------------------------------------------------------------");
                        }

                    }

                    outs.write(data);
                    outs.newLine();

                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOops !!! You Have Entered Wrong Input...");
                System.out.println("-------------------------------------------------------------");
            }
            catch (Exception e)
            {
                return;
            }
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

        /***************************************************************/

        public void withdraw(String nmpass)
        {
            String oldFileName = "userdetails.txt";
            String tmpFileName = "out.txt";
            BufferedReader ins=null;
            BufferedWriter outs=null;
            try
            {
                ins=new BufferedReader(new FileReader(oldFileName));
                outs=new BufferedWriter(new FileWriter(tmpFileName));

                BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
                while((data=ins.readLine())!=null)
                {
                    if(data.contains(nmpass))
                    {
                        l=data.length();
                        data.getChars(40,l-1,fbal,0);
                        String b=String.valueOf(fbal).trim();

                        bal=Float.parseFloat(b);

                        data.getChars(0,6,user,0);
                        String u=String.valueOf(user).trim();
                        data.getChars(12,18,account,0);
                        String ac=String.valueOf(account).trim();

                        try
                        {
                            System.out.println("Enter your Withdrawal Amount :");
                            String wt=scan.next();
                            wamt=Float.parseFloat(wt);
                            float less=bal-wamt;

                            if(wamt<100.0f)
                            {
                                System.out.println("\nWithrawal Should be greater than or equals to Rs.100...");
                                System.out.println("-------------------------------------------------------------");
                            }
                            else if(less<1000.0f)
                            {
                                System.out.println("\nInsufficient Balance...");
                                System.out.println("-------------------------------------------------------------");
                            }
                            else
                            {
                                bal=bal-wamt;
                                System.out.println("\nTransaction Completed Successfully...");
                                System.out.println("\nTotal Balance : "+bal);
                                System.out.println("-------------------------------------------------------------");
                                String c=String.valueOf(bal);

                                data=data.replace(b,c);

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                                String date = sdf.format(new Date());

                                bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                                bw.newLine();
                                bw.close();
                            }

                        }
                        catch(NumberFormatException eoi)
                        {
                            System.out.println("\nOops !!! You Have Entered Wrong Input...");
                            System.out.println("-------------------------------------------------------------");

                        }

                    }

                    outs.write(data);
                    outs.newLine();
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

        /**********************************************************************/

        public void inquery(String nmpass)
        {
            try
            {
                BufferedReader ins=new BufferedReader(new FileReader("userdetails.txt"));
                while((data=ins.readLine())!=null)
                {
                    if(data.contains(nmpass))
                    {
                        l=data.length();
                        data.getChars(40,l-1,fbal,0);
                        String b=String.valueOf(fbal).trim();
                        bal=Float.parseFloat(b);
                        System.out.println("\nTotal Balance : "+bal);
                        System.out.println("-------------------------------------------------------------");
                        break;
                    }
                }
                ins.close();
            }
            catch(Exception e){}

        }

        /*********************************************************************/

        public void statement(String nmacnt)
        {

            try
            {
                BufferedReader ins=new BufferedReader(new FileReader("statement.txt"));
                System.out.println("User |"+" A/c No. |"+" Transactions |"+" Total Bal. "+"|  Date & Time ");
                System.out.println("-------------------------------------------------------------");
                System.out.println();
                while((data=ins.readLine())!=null)
                {

                    if(data.contains(nmacnt))
                    {
                        System.out.println(data);

                    }
                }
                System.out.println("-------------------------------------------------------------");
                ins.close();
            }
            catch(Exception e){}
        }

    /*********************************************************************/

    public void depositto(String nmpass, float damt)
    {
        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;


        try
        {

            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {

                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {


                            bal=bal+damt;
                            System.out.println("\nTransaction Completed Successfully...");

                            System.out.println("-------------------------------------------------------------");

                            String c=String.valueOf(bal);
                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+damt+"  deposit"+"    "+bal+"   "+date);
                            bw.newLine();
                            bw.close();

                    }
                    catch(NumberFormatException ioe)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");
                    }

                }

                outs.write(data);
                outs.newLine();

            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nOops !!! You Have Entered Wrong Input...");
            System.out.println("-------------------------------------------------------------");
        }
        catch (Exception e)
        {
            return;
        }
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

    /***************************************************************/

    public void withdrawfrom(String nmpass,float wamt)
    {
        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;
        try
        {
            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {
                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {

                        float less=bal-wamt;


                      if(less<1000.0f)
                        {
                            System.out.println("\nInsufficient Balance...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        else
                        {
                            bal=bal-wamt;
                            System.out.println("\nTransaction Completed Successfully...");
                            System.out.println("\nTotal Balance : "+bal);
                            System.out.println("-------------------------------------------------------------");
                            String c=String.valueOf(bal);

                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                            bw.newLine();
                            bw.close();
                        }

                    }
                    catch(NumberFormatException eoi)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");

                    }

                }

                outs.write(data);
                outs.newLine();
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

    /*********************************************************************/

    public void loandetails(double principal, int year) {
        RATE = (RATE / 100) / 12;
        year = year * 12;
        double p = (principal * RATE) / (1 - Math.pow(1 + RATE, -year));
        //
        System.out.println("EMI : " + p);
    }

    /*********************************************************************/

    public void ElectricityBill1(int Units,String nmpass) {

        float Amount, Sur_Charge, Total_Amount;

        if (Units < 50) {
            Amount = (float) (Units * 2.60);
            Sur_Charge = 25;
        } else if (Units <= 100) {
            // For the First Fifty Units Charge = 130 (50 * 2.60)
            // Next, we are removing those 50 units from total units
            Amount = (float) (130 + ((Units - 50) * 3.25));
            Sur_Charge = 35;
        } else if (Units <= 200) {
            // First Fifty Units charge = 130, and 50 - 100 is 162.50 (50 * 3.25)
            // Next, we are removing those 100 units from total units
            Amount = (float) (130 + 162.50 + ((Units - 100) * 5.26));
            Sur_Charge = 45;
        } else {
	  		/* First Fifty Units = 130, 50 - 100 is 162.50,
	  		 and 100 - 200 is 526 (100 * 5.65)
	  		Next, we are removing those 200 units from total units */
            Amount = (float) (130 + 162.50 + 526 + ((Units - 200) * 7.75));
            Sur_Charge = 55;
        }

        Total_Amount = Amount + Sur_Charge;
        System.out.println("\n Electricity Bill  =  " + Total_Amount);
        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;
        try
        {
            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {
                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {

                        float less=bal-Total_Amount;

                   if(bal<less)
                        {
                            System.out.println("\nInsufficient Balance...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        else
                        {
                            bal=bal-Total_Amount;
                            System.out.println("\nTransaction Completed Successfully...");
                            System.out.println("\nTotal Balance : "+bal);
                            System.out.println("-------------------------------------------------------------");
                            String c=String.valueOf(bal);

                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                            bw.newLine();
                            bw.close();
                        }

                    }
                    catch(NumberFormatException eoi)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");

                    }

                }

                outs.write(data);
                outs.newLine();
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


    /*********************************************************************/


    public void waterBill(int galloonused , String nmpass) {

        final double PriceofWater = 50;

        final double DISCOUNT = 0.50;


        double totalTogether, totalDiscount, finalTotalForDis;


        totalTogether = galloonused * PriceofWater;

        totalDiscount = totalTogether * DISCOUNT;
        finalTotalForDis = totalTogether - totalDiscount;

        if (totalTogether >= 15) {
            System.out.println("Your discount is " + (totalDiscount));

            System.out.println("You final total is " + (finalTotalForDis));
        }

        else {
            System.out.println("Your not entitled to discount this time");

            System.out.println("Your total bill is " + (totalTogether));
        }
        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;
        try
        {
            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {
                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {

                        float less= (float) (bal-totalTogether);

                        if(bal<less)
                        {
                            System.out.println("\nInsufficient Balance...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        else
                        {
                            bal= (float) (bal-totalTogether);
                            System.out.println("\nTransaction Completed Successfully...");
                            System.out.println("\nTotal Balance : "+bal);
                            System.out.println("-------------------------------------------------------------");
                            String c=String.valueOf(bal);

                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                            bw.newLine();
                            bw.close();
                        }

                    }
                    catch(NumberFormatException eoi)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");

                    }

                }

                outs.write(data);
                outs.newLine();
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

    /*********************************************************************/

    public void mobileBill(int textUsed, double callUsed,String nmpass) {


        final double TEXTCOST = 0.25;
        final double CALLCOST = 0.10;
        final double DISCOUNT = 0.20;


        double totalForCall, totalForText, totalTogether, totalDiscount, finalTotalForDis;


        totalForCall = callUsed * CALLCOST;
        totalForText = textUsed * TEXTCOST;

        totalTogether = totalForCall + totalForText;

        totalDiscount = totalTogether * DISCOUNT;
        finalTotalForDis = totalTogether - totalDiscount;

        if (totalTogether >= 15) {
            System.out.println("Your discount is " + (totalDiscount));

            System.out.println("You final total is " + (finalTotalForDis));
        }

        else {
            System.out.println("Your not entitled to discount this time");

            System.out.println("Your total bill is " + (totalTogether));
        }

        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;
        try
        {
            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {
                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {

                        float less= (float) (bal-totalTogether);

                        if(bal<less)
                        {
                            System.out.println("\nInsufficient Balance...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        else
                        {
                            bal= (float) (bal-totalTogether);
                            System.out.println("\nTransaction Completed Successfully...");
                            System.out.println("\nTotal Balance : "+bal);
                            System.out.println("-------------------------------------------------------------");
                            String c=String.valueOf(bal);

                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                            bw.newLine();
                            bw.close();
                        }

                    }
                    catch(NumberFormatException eoi)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");

                    }

                }

                outs.write(data);
                outs.newLine();
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

    /*********************************************************************/

    public void internetBill(double dataconsumed,String nmpass) {
        final double DATACOST = 10;
        final double DISCOUNT = 0.20;
        double totalcost, totalDiscount, finalTotalForDis;

        totalcost = dataconsumed * DATACOST;

        totalDiscount = totalcost * DISCOUNT;
        finalTotalForDis = totalcost - totalDiscount;

        if (totalcost >= 15) {
            System.out.println("Your discount is " + (totalDiscount));

            System.out.println("You final total is " + (finalTotalForDis));
        }

        else {
            System.out.println("Your not entitled to discount this time");

            System.out.println("Your total bill is " + (totalcost));
        }

        String oldFileName = "userdetails.txt";
        String tmpFileName = "out.txt";
        BufferedReader ins=null;
        BufferedWriter outs=null;
        try
        {
            ins=new BufferedReader(new FileReader(oldFileName));
            outs=new BufferedWriter(new FileWriter(tmpFileName));

            BufferedWriter bw=new BufferedWriter(new FileWriter("statement.txt",true));
            while((data=ins.readLine())!=null)
            {
                if(data.contains(nmpass))
                {
                    l=data.length();
                    data.getChars(40,l-1,fbal,0);
                    String b=String.valueOf(fbal).trim();

                    bal=Float.parseFloat(b);

                    data.getChars(0,6,user,0);
                    String u=String.valueOf(user).trim();
                    data.getChars(12,18,account,0);
                    String ac=String.valueOf(account).trim();

                    try
                    {
                        //System.out.println(totalcost);

                        float less= (float) (bal-totalcost);

                        if(bal<less)
                        {
                            System.out.println("\nInsufficient Balance...");
                            System.out.println("-------------------------------------------------------------");
                        }
                        else
                        {
                            bal= (float) (bal-totalcost);
                            System.out.println("\nTransaction Completed Successfully...");
                            System.out.println("\nTotal Balance : "+bal);
                            System.out.println("-------------------------------------------------------------");
                            String c=String.valueOf(bal);

                            data=data.replace(b,c);

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                            String date = sdf.format(new Date());

                            bw.write(u+" "+ac+"   "+wamt+"  withdraw"+"   "+bal+"   "+date);

                            bw.newLine();
                            bw.close();
                        }

                    }
                    catch(NumberFormatException eoi)
                    {
                        System.out.println("\nOops !!! You Have Entered Wrong Input...");
                        System.out.println("-------------------------------------------------------------");

                    }

                }

                outs.write(data);
                outs.newLine();
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


