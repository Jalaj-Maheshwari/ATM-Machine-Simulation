
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

public class MiniStatement extends JFrame implements ActionListener
 {

JFrame frame;
JLabel lbl_c[]=new JLabel[5];
JLabel lbl_a,lbl_b,lbl_img; 
Container co;
JPanel panel;
JButton btn_menu,btn_lgout;
Statement st=null;
int y=225,j,prevwith=0,intbal=0,count1,count2,testcount,rows=0; 
public static int flag=0;
public static String val;        
      Connection con = null; ResultSet rs = null; PreparedStatement ps = null;                
      String blnc=null;
      String cusno=null;
      String ano=null;
      String acno=null;
      String withd=null;
      String date=null;
      String mon=null;
      String bal=null;
      String dep=null;



public MiniStatement(String str)
{
  val=str; 
 //  flag=type;
  
   frame=new JFrame();
   frame.setSize(600,600);     
   frame.setTitle("MINI STATEMENT");
   panel=new JPanel();
   panel.setLayout(null);          
   panel.setVisible(true);


   co=getContentPane();

  ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25); 
panel.add(lbl_img);       


        lbl_a=new JLabel("MINI  STATEMENT");
        lbl_a.setSize(250,50); 
        lbl_a.setLocation(200,140);
lbl_a.setFont(new Font("Perpetua",Font.BOLD,22));  
        lbl_a.setVisible(true);
        panel.add(lbl_a);
     
        lbl_b=new JLabel("AccNo      WithDrawal      Deposit      Balance           Date");
        lbl_b.setSize(500,50);
        lbl_b.setLocation(70,175);
lbl_b.setFont(new Font("Perpetua",Font.BOLD,18));       
   lbl_b.setVisible(true);
         panel.add(lbl_b);
         //panel.setBackground(Color.orange);



        btn_menu=new JButton("Main Menu");
        btn_menu.setSize(150,50);
        btn_menu.setLocation(95,500);
btn_menu.setFont(new Font("Perpetua",Font.BOLD,18));  
        btn_menu.setVisible(true);
        btn_menu.addActionListener(this);
        panel.add(btn_menu);

        btn_lgout=new JButton("Logout");
        btn_lgout.setSize(150,50);
btn_lgout.setFont(new Font("Perpetua",Font.BOLD,18));  
        btn_lgout.setLocation(320,500);
        btn_lgout.addActionListener(this);
        panel.add(btn_lgout);

for (int i=0;i<5;i++)
{     
   lbl_c[i]=new JLabel("");        
lbl_c[i].setSize(475,50);
lbl_c[i].setLocation(75,y);
lbl_c[i].setFont(new Font("Perpetua",Font.BOLD,18));  
y=y+50;
lbl_c[i].setVisible(true);
panel.add(lbl_c[i]);
}
co.add(panel);
frame.add(co);
frame.setVisible(true);
          
 
             try { 

    int          empblnc=0;  
    int       mainblnc=0;
          
                 
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
       
       
String query = "select t.transaction_no,t.acc_no, t.withdrawal_amt, t.deposit_amt, t.date,t.avail_bal  from Transaction t,Account a where t.acc_no=a.acc_no and t.acc_no in (select a.acc_no from Account a,Customer c where a.cust_id=c.cust_id and pin=?) order by t.transaction_no desc";
System.out.println("done 1");


ps = con.prepareStatement(query); // create a statement

      ps.setString(1,val); // set input parameter

      rs = ps.executeQuery();       

          //lbl_a.setVisible(true);
          //lbl_b.setVisible(true);
 

       int i=0;                
          while(rs.next())
       {                                   
                bal=rs.getString("avail_bal");
                withd=rs.getString("withdrawal_amt");
                dep=rs.getString("deposit_amt"); 
                ano=rs.getString("acc_no");
                date=rs.getString("date");
                //mon=rs.getString("mm");
  
if(i!=0)
{
j=i;
while(j!=0)
{
lbl_c[j].setText(lbl_c[j-1].getText());
j--;
}
}
lbl_c[0].setText(ano+"                "+withd+"                       "+dep+"               "+bal+"             "+date);
                i++;
             
       } 
 
           }
   catch(Exception ex)
      {
          
      
      }
  
/*else
{
             try { 
    int          empblnc=0;  
    int       mainblnc=0;
          
                 
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
       
       
String query = "select t.ID,t.accno, t.withdrawal, t.deposit, t.dd, t.mm,t.availablebal  from savingsTransactions t,savingsAccount a where t.accno=a.accno and t.accno in (select a.accno from savingsAccount a,savingsCustomers c where a.cno=c.cno and pin=?) order by t.ID desc";
      ps = con.prepareStatement(query); // create a statement
      ps.setString(1,val); // set input parameter
      rs = ps.executeQuery();       

          lbl_a.setVisible(true);
          lbl_b.setVisible(true);
 

       int i=0;                
          while(rs.next())
       {                                   
                bal=rs.getString("availablebal");
                withd=rs.getString("withdrawal");
                dep=rs.getString("deposit"); 
                ano=rs.getString("accno");
                date=rs.getString("dd");
                mon=rs.getString("mm");
             //JOptionPane.showMessageDialog(null," hello");  
if(i!=0)
{
j=i;
while(j!=0)
{
lbl_c[j].setText(lbl_c[j-1].getText());
j--;
}
}
lbl_c[0].setText(ano+"                "+withd+"                       "+dep+"               "+bal+"             "+date+"/"+mon);
                i++;
             
       } 
 
  }
   catch(Exception ex)
      {
          
      
      }
}//end of else*/
}//end of constructor

public void actionPerformed(ActionEvent ae)
{

if(ae.getSource()==btn_menu)
{
frame.setVisible(false);
Transaction t=new Transaction(val);
}
/*else if(ae.getSource()==btn_menu && flag==1)
{
frame.setVisible(false);
Transaction t=new Transaction(val);
}*/
else 
{
frame.setVisible(false);
Logout l=new Logout();
}}

/*public static void main(String args[])
{
MiniStatement m=new MiniStatement("123456");
}*/

}

 