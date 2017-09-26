
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

public class Transaction extends JFrame implements ActionListener
{
    
   JFrame frame1;
   JLabel lbl_title, lbl_a, lbl_b,lbl_name,lbl_img;
   JLabel lbl_c[]=new JLabel[5]; 
   JPanel panel1;
   JButton btn_chk,btn_wid,btn_dep,btn_log,btn_mini,btn_pchange,btn_req;
   Container co;    
   Connection con;
   Statement st;
   PreparedStatement ps;
   public static String val;     
   public static String acno1;
   public static int flag; 
   public static int counter=20;
   ResultSet rs;
public Transaction(String str)
{
   val=str; 
System.out.println(val);
   //flag=type;  
   frame1=new JFrame();
   frame1.setSize(600,600);     
   frame1.setTitle("MENU");
   panel1=new JPanel();
   panel1.setLayout(null);          
   panel1.setVisible(true);
   //panel1.setBackground(Color.orange);

   co=getContentPane();
    ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25); 
panel1.add(lbl_img);       


         
   con=null;ps=null;st=null;rs=null;


        lbl_title = new JLabel("PLEASE  SELECT  AN  OPTION");
        lbl_title.setSize(300,50);
        lbl_title.setLocation(160,150);
        lbl_title.setVisible(true);
        lbl_title.setFont(new Font("Perpetua",Font.BOLD,22));  
        panel1.add(lbl_title);

         


             
        btn_chk = new JButton("Check Balance");
        btn_chk.setFont(new Font("Perpetua",Font.BOLD,18));
        btn_chk.setBounds(75, 250, 180, 50);
        panel1.add(btn_chk);
        
        btn_wid = new JButton("Withdraw");
        btn_wid.setFont(new Font("Perpetua",Font.BOLD,18));
        btn_wid.setBounds(350, 250, 180, 50);
        panel1.add(btn_wid);
        
        btn_mini = new JButton("Mini Statement");
        btn_mini.setBounds(75, 340, 180, 50);
        btn_mini.setFont(new Font("Perpetua",Font.BOLD,18));
        panel1.add(btn_mini);

        btn_dep = new JButton("Deposit");
        btn_dep.setFont(new Font("Perpetua",Font.BOLD,18));
        btn_dep.setBounds(350, 340, 180, 50);
        panel1.add(btn_dep);

        btn_pchange = new JButton("Pin Change");
        btn_pchange.setBounds(75, 430, 180, 50);
        btn_pchange.setFont(new Font("Perpetua",Font.BOLD,18));
        panel1.add(btn_pchange);
        
        btn_req = new JButton("Issue Chequebook");
        btn_req.setBounds(350, 430, 180, 50);
        btn_req.setFont(new Font("Perpetua",Font.BOLD,18));
        panel1.add(btn_req);


        co.add(panel1);
        frame1.add(co);
        frame1.setVisible(true);           
        
        btn_chk.addActionListener(this);
        btn_wid.addActionListener(this);
        btn_mini.addActionListener(this);
//        btn_log.addActionListener(this);
        btn_pchange.addActionListener(this);   
        btn_req.addActionListener(this);   
        btn_dep.addActionListener(this);
}
    
    public void actionPerformed(ActionEvent e)
{  

        st=null;
        con = null; rs = null;  ps = null;                
      int count=0;        
      int empblnc;
      int mainblnc=0;
      int ccno=0;
      String str_logout = "";
      String str_dep;      
      String str_with = "";
      int withdraw;
      String blnc=null;
      String cusno=null;
      String ano=null;
      String acno=null;
      String withd=null;
      String date=null;
      String mon=null;
      String bal=null;
      String dep=null;
      int num,num1;


if(e.getSource()==btn_wid )
{
   

 do{     
          count=0;
          str_with = JOptionPane.showInputDialog(frame1,"Please Enter Amount to Withdraw in Multiples of 100");     


          for(int i=0;i<str_with.length();i++)
          {
          char c = str_with.charAt(i);
          if (Character.isLetter(c)) 
          count=1;
          }
          if(count==1)
          JOptionPane.showMessageDialog(frame1,"Enter Amount to Withdraw in Multiples of 100");
          else if(str_with.length()==0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Withdraw in Multiples of 100 ");          
          count=1;
          }
          else if((Integer.parseInt(str_with)) % 100 !=0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Withdraw in Multiples of 100 ");
          count=1;
          }
          else if((Integer.parseInt(str_with)) > 25000)
          {
          JOptionPane.showMessageDialog(frame1, "Cannot Withdraw Greater than  Rs 25,000 ");
          count=1;
          }
          else if((Integer.parseInt(str_with))==0)
          {
           JOptionPane.showMessageDialog(frame1, "Please enter a minimum of Rs 100 ");
          count=1;
          }
     }while(count==1);      
      
     try { 
          empblnc=0;  
          mainblnc=0;
                         
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
          String query = "select a.avail_bal,a.cust_id,a.acc_no from Account a,Customer c where a.cust_id=c.cust_id and pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();       
     while(rs.next())
            {                    
                blnc=rs.getString("avail_bal");
System.out.println( blnc); 
                cusno=rs.getString("cust_id");
System.out.println( cusno);
                acno=rs.getString("acc_no");
System.out.println( acno);            
}                            
                mainblnc = Integer.parseInt(blnc);
                ccno=     Integer.parseInt(cusno);             
//              acno1=acno;
                
                withdraw = Integer.parseInt(str_with);
                
                if(mainblnc<withdraw)
                {
                    JOptionPane.showMessageDialog(frame1, "Your Account balance is Low. Cannot withdraw                     amount","Message",JOptionPane.ERROR_MESSAGE);
                }
                else
                  {
                    
                    empblnc = mainblnc-withdraw;
                    
                   // JOptionPane.showMessageDialog(frame1, "You have Balance: "+empblnc);
                 
                        
       query = "UPDATE Account SET avail_bal = ? WHERE cust_id = ?";
      ps = con.prepareStatement(query); // create a statement
System.out.println(empblnc);      
ps.setInt(1,empblnc);
      ps.setInt(2,ccno);                             
      ps.executeUpdate();
System.out.println(" done");

       query = "INSERT INTO Transaction(Acc_no,Transaction_no,Withdrawal_amt,Deposit_amt,Avail_bal,Date) VALUES(?,?,?,0,?,'2016-12-12')";
      ps = con.prepareStatement(query); // create a statement
      System.out.println("done 2");
int newac=Integer.parseInt(acno);
System.out.println(newac);
int newwith=Integer.parseInt(str_with);
System.out.println(newwith);
System.out.println(counter);
     ps.setInt(1,newac);
ps.setInt(2,counter);
ps.setInt(3,newwith);     
 ps.setInt(4,empblnc);    
System.out.println(empblnc);                         
System.out.println("done 3");
      ps.executeUpdate();
counter++;
 System.out.println("done 4");
System.out.println(empblnc);
System.out.println(str_with);
      Progress p=new Progress(empblnc,0,str_with);
//      validation();
     
                     } //end of else
            }//end of try
catch(Exception ex)
{ }               
          }//end of 1st if of action event decider

/*if(e.getSource()==btn_wid)
{

do{     
          count=0;
          str_with = JOptionPane.showInputDialog(frame1,"Please Enter Amount to Withdraw in Multiples of 100");     


          for(int i=0;i<str_with.length();i++)
          {
          char c = str_with.charAt(i);
          if (Character.isLetter(c)) 
          count=1;
          }
          if(count==1)
          JOptionPane.showMessageDialog(frame1,"Enter Amount to Withdraw in Multiples of 100");
          else if(str_with.length()==0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Withdraw in Multiples of 100 ");          
          count=1;
          }
          else if((Integer.parseInt(str_with)) % 100 !=0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Withdraw in Multiples of 100 ");
          count=1;
          }
          else if((Integer.parseInt(str_with)) > 25000)
          {
          JOptionPane.showMessageDialog(frame1, "Cannot Withdraw Greater than  Rs 25,000 ");
          count=1;
          }
          else if((Integer.parseInt(str_with))==0)
          {
           JOptionPane.showMessageDialog(frame1, "Please enter a minimum of Rs 100 ");
          count=1;
          }
           
     }while(count==1);      

       try { 
          empblnc=0;  
          mainblnc=0;
                         
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
          String query = "select availablebal,a.cno,a.accno from currentAccount a,currentCustomers c where a.cno=c.cno and pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();       
     while(rs.next())
           {                    
                blnc=rs.getString("availablebal");  
                cusno=rs.getString("cno");
                acno=rs.getString("accno");
           }                            
                mainblnc = Integer.parseInt(blnc);
                ccno=     Integer.parseInt(cusno);             
//              acno1=acno;
                
                withdraw = Integer.parseInt(str_with);
                
                if(mainblnc<withdraw)
                {
                    JOptionPane.showMessageDialog(frame1, "Your Account balance is Low. Cannot withdraw                     amount","Message",JOptionPane.ERROR_MESSAGE);
                }
                else
                  {
                    
                    empblnc = mainblnc-withdraw;
                    
                   // JOptionPane.showMessageDialog(frame1, "You have Balance: "+empblnc);
                 
                        
       query = "UPDATE currentAccount SET availablebal = ? WHERE cno = ?";
      ps = con.prepareStatement(query); // create a statement
      ps.setInt(1,empblnc);
      ps.setInt(2,ccno);                             
      ps.executeUpdate();

       query = "INSERT INTO currentTransactions(withdrawal,deposit,accno,dd,mm,availablebal) VALUES(?,0,?,20,11,?)";
      ps = con.prepareStatement(query); // create a statement
      ps.setString(1,str_with);
      ps.setString(2,acno);
      ps.setInt(3,empblnc);                             
      ps.executeUpdate();
 
      Progress p=new Progress(empblnc,0,str_with);
//      validation();
     
                     } //end of else
            }//end of try
catch(Exception ex)
{ }                    
}*/

if(e.getSource()==btn_dep)
{

do{     
          count=0;
          str_dep = JOptionPane.showInputDialog(frame1,"Please Enter Amount to Deposit in Multiples of 100");     


          for(int i=0;i<str_dep.length();i++)
          {
          char c = str_dep.charAt(i);
          if (Character.isLetter(c)) 
          count=1;
          }
          if(count==1)
          JOptionPane.showMessageDialog(frame1,"Enter Amount to Deposit in Multiples of 100");
          else if(str_dep.length()==0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Deposit in Multiples of 100 ");          
          count=1;
          }
          else if((Integer.parseInt(str_dep)) % 100 !=0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Deposit in Multiples of 100 ");
          count=1;
          }
          else if((Integer.parseInt(str_dep))==0)
          {
           JOptionPane.showMessageDialog(frame1, "Please enter a minimum of Rs 100 ");
          count=1;
          }
     }while(count==1);                
    try {
          empblnc=0;  
          mainblnc=0;
          
                  
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
          String query = "select a.avail_bal,a.cust_id,a.acc_no from Account a,Customer c where a.cust_id=c.cust_id and pin=?";
System.out.println("connection established");
          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();
       System.out.println("done 1");
     while(rs.next())
           {                    
                blnc=rs.getString("avail_bal");  
                cusno=rs.getString("cust_id");
                acno=rs.getString("acc_no");
           }    
           ccno=     Integer.parseInt(cusno);
           int depamt=Integer.parseInt(str_dep);
           mainblnc=Integer.parseInt(blnc);
           empblnc = mainblnc + depamt;
System.out.println("done 2");
   //JOptionPane.showMessageDialog(frame1, "You have Balance: "+empblnc);                                     
           
                  query = "UPDATE Account SET avail_bal = ? WHERE cust_id = ?";
      ps = con.prepareStatement(query); // create a statement
      ps.setInt(1,empblnc);
      ps.setInt(2,ccno); 
        //      JOptionPane.showMessageDialog(frame1, " Balance updated to : "+empblnc);       
System.out.println(empblnc);                   
      ps.executeUpdate();
System.out.println("done 3");
query = "INSERT INTO Transaction(Acc_no,Transaction_no,Withdrawal_amt,Deposit_amt,Avail_bal,Date) VALUES(?,?,0,?,?,'2016-12-12')";
      ps = con.prepareStatement(query); // create a statement
int newacno=Integer.parseInt(acno);
      ps.setInt(1,newacno );
ps.setInt(2,counter);
int newdep=Integer.parseInt(str_dep);
      ps.setInt(3,newdep);
      ps.setInt(4,empblnc);
System.out.println("done 4");                             
      ps.executeUpdate(); 
    counter++;
System.out.println(counter);
  System.out.println("done 5");
      Progress p=new Progress(empblnc,1,str_dep);
}
   catch(Exception ex)
      {
          
      }
}            
                   

/*if(e.getSource()==btn_dep && flag==1)
{
do{     
          count=0;
          str_dep = JOptionPane.showInputDialog(frame1,"Please Enter Amount to Deposit in Multiples of 100");     


          for(int i=0;i<str_dep.length();i++)
          {
          char c = str_dep.charAt(i);
          if (Character.isLetter(c)) 
          count=1;
          }
          if(count==1)
          JOptionPane.showMessageDialog(frame1,"Enter Amount to Deposit in Multiples of 100");
          else if(str_dep.length()==0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Deposit in Multiples of 100 ");          
          count=1;
          }
          else if((Integer.parseInt(str_dep)) % 100 !=0)
          {
          JOptionPane.showMessageDialog(frame1, "Please Enter Amount to Deposit in Multiples of 100 ");
          count=1;
          }
          else if((Integer.parseInt(str_dep))==0)
          {
           JOptionPane.showMessageDialog(frame1, "Please enter a minimum of Rs 100 ");
          count=1;
          }
     }while(count==1);      
              
    try {
          empblnc=0;  
          mainblnc=0;
          
                  
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
          String query = "select a.availablebal,a.cno,a.accno from savingsAccount a,savingsCustomers c where a.cno=c.cno and pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();       
     while(rs.next())
           {                    
                blnc=rs.getString("availablebal");  
                cusno=rs.getString("cno");
                acno=rs.getString("accno");
           }    
           ccno=     Integer.parseInt(cusno);
           int depamt=Integer.parseInt(str_dep);
           mainblnc=Integer.parseInt(blnc);
           empblnc = mainblnc + depamt;
//           JOptionPane.showMessageDialog(frame1, "You have Balance: "+empblnc);                                     
           
                  query = "UPDATE savingsAccount SET availablebal = ? WHERE cno = ?";
      ps = con.prepareStatement(query); // create a statement
      ps.setInt(1,empblnc);
      ps.setInt(2,ccno); 
              //JOptionPane.showMessageDialog(frame1, " Balance updated to : "+empblnc);                          
      ps.executeUpdate();

       query = "INSERT INTO savingsTransactions(withdrawal,deposit,accno,dd,mm,availablebal) VALUES(0,?,?,20,11,?)";
      ps = con.prepareStatement(query); // create a statement
      ps.setString(1,str_dep);
      ps.setString(2,acno);
      ps.setInt(3,empblnc);                             
      ps.executeUpdate(); 
  
      Progress p=new Progress(empblnc,1,str_dep);
}
   catch(Exception ex)
      {
          
      }
}*/

if(e.getSource()==btn_mini)
{

MiniStatement m=new MiniStatement(val);
}      
        

/*if(e.getSource()==btn_mini)
{
MiniStatement m=new MiniStatement(val,1);
}*/      


if(e.getSource()==btn_chk)
{
try { 
          empblnc=0;  
          mainblnc=0;
          
                  
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
          String query = "select avail_bal from Account a,Customer c where a.cust_id=c.cust_id and pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();       
     while(rs.next())
           {                    
                blnc=rs.getString("avail_bal");  
           }                            
                mainblnc = Integer.parseInt(blnc);
        JOptionPane.showMessageDialog(frame1,"The available balance is Rs "+mainblnc);    
 validation();    
 }
catch(Exception ex)
      {
          

      }
}

/*if(e.getSource()==btn_chk && flag==1)
{

    try { 
          empblnc=0;  
          mainblnc=0;
          
                  
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
          String query = "select availablebal from savingsAccount a,savingsCustomers c where a.cno=c.cno and pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val); // set input parameter
          rs = ps.executeQuery();       
     while(rs.next())
           {                    
                blnc=rs.getString("availablebal");  
           }                            
                mainblnc = Integer.parseInt(blnc);
        JOptionPane.showMessageDialog(frame1,"The available balance is Rs "+mainblnc);    
 validation();    
 }
catch(Exception ex)
      {
          

      }
}*/

if(e.getSource()==btn_req )
{
Chequebook c=new Chequebook();
}


if(e.getSource()==btn_pchange )
{
Pinchange p=new Pinchange(val,frame1);
}

/*if(e.getSource()==btn_pchange)
{
Pinchange p=new Pinchange(val,frame1);
}*/

}//end of actionPerformed


public void validation()
{
  int n=JOptionPane.showConfirmDialog(frame1,"Do you Want to Avail Other Services?");                 
        if (n == JOptionPane.NO_OPTION) 
        {
Logout l=new Logout();
        }
}
}// end of Transaction class

