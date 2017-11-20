import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

public class AccountChoice extends JFrame implements ActionListener {    
   JFrame frame;
   JLabel lbl_option,lbl_img,lbl_name; 
   JPanel panel;
   JButton btn_savings,btn_current;
   Container co;    
   Connection con;
   Statement st;
   PreparedStatement ps;
   public static String val;     
   public static String acno1;
   public static String str_name; 
   public static int type;
   ResultSet rs;

public AccountChoice(String str){
          
   val=str;

   frame=new JFrame();
   frame.setSize(600,600);
   frame.setTitle(" ACCOUNT CHOICE ");

   co= getContentPane();
   JPanel panel=new JPanel();

   panel.setLayout(null);
   lbl_option=new JLabel("PLEASE  SELECT  AN  ACCOUNT  FOR  TRANSACTION");
   lbl_option.setFont(new Font("Perpetua",Font.BOLD,22));
   lbl_option.setSize(550,100);
   lbl_option.setLocation(40,200);
   panel.add(lbl_option);

   btn_current=new JButton("Current Account");
   btn_current.setFont(new Font("Perpetua",Font.BOLD,18));
   btn_current.setSize(200,50);
   btn_current.setLocation(70,300);
   btn_current.addActionListener(this);
   panel.add(btn_current);

   btn_savings=new JButton("Savings Account");
   btn_savings.setFont(new Font("Perpetua",Font.BOLD,18));
   btn_savings.setSize(200,50);
   btn_savings.setLocation(320,300);
   btn_savings.addActionListener(this);
   panel.add(btn_savings);
//panel.setBackground(Color.orange);

ImageIcon ig=new ImageIcon("atmimage.png");
lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25); 
panel.add(lbl_img);       

  try { 
          String name=null;
                         
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
          String query = "select name from currentCustomers  where pin=?";

          ps = con.prepareStatement(query); // create a statement
          ps.setString(1,val);   // set input parameter
          rs = ps.executeQuery();      
          
          while(rs.next()){                    
                str_name=rs.getString("name");
          }
                            
        lbl_name = new JLabel("HELLO  "+str_name);
        rs=null;
        String query1 = "select name from savingsCustomers  where pin=?";

        ps = con.prepareStatement(query1); // create a statement
        ps.setString(1,val);   // set input parameter
        rs = ps.executeQuery();      
        while(rs.next()){                    
                str_name=rs.getString("name");
        }
        lbl_name = new JLabel("HELLO  "+str_name);
        lbl_name.setSize(400,50);
        lbl_name.setLocation(140,150);
        lbl_name.setVisible(true);
        lbl_name.setFont(new Font("Perpetua",Font.BOLD,22));  
        panel.add(lbl_name);

     }
  catch(Exception e)
     {}

  co.add(panel);
  frame.add(co);
  frame.setVisible(true);

}
public void actionPerformed(ActionEvent e)
{  
        st=null;
        con = null; rs = null;  ps = null;                
        
      int empblnc;
      int mainblnc=0;
      int ccno=0;
      String str_logout = "";
      String str_dep="  ";     
      String str_with="  ";
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
      int count=0;
      
  if(e.getSource()==btn_savings ){
    try{ 
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
    ps = con.prepareStatement("select * from savingsCustomers where pin = ?");
    ps.setString(1,val); 
    rs = ps.executeQuery(); 

    if(!rs.next()){ 
        JOptionPane.showMessageDialog(frame, "You Do Not Possess a Savings Acoount");
    }   
    else{
    frame.setVisible(false);
    Transaction t=new Transaction(val,1);
    }
    }
    catch(Exception ex){}
  }//end of 1st if of action event decider
  
         if(e.getSource()==btn_current )
  {
   
 try{ 
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
 ps = con.prepareStatement("select * from currentCustomers where pin = ?");
 ps.setString(1,val); 
 rs = ps.executeQuery(); 

if(!rs.next())
{ 
  JOptionPane.showMessageDialog(frame, "You Do Not Possess a Current Acoount");
}
else
{
frame.setVisible(false);
Transaction t=new Transaction(val,0);
}

}
catch(Exception ex)
{
 }
}


}

public void validation()
{
  int n=JOptionPane.showConfirmDialog(frame,"Do you Want to Avail Other Services?");                 
        if (n == JOptionPane.NO_OPTION) 
        {
frame.setVisible(false);
Logout l=new Logout();
        }
}




public static void main(String args[])
{

AccountChoice a=new AccountChoice("123456");
}

}

