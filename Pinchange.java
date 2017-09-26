 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

public class Pinchange 
{

Statement st=null;
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
JPasswordField pass=new JPasswordField();
String newpin;
public static JFrame frame;
public static int count;
public Pinchange(String val,JFrame frame1)
{



	frame=frame1;
//count=type;
int flag=0;   


do
{
 flag=0;
           do{
        newpin=JOptionPane.showInputDialog(frame1,"Enter the new pin");           
          if(newpin.length()==0)
          JOptionPane.showMessageDialog(frame1, "Please Enter A Pin");          
          }while(newpin.length()==0);      
   

try
{
Statement st=null;
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;

       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
       String  query = "SELECT pin from Customer";
      ps = con.prepareStatement(query); // create a statement
      rs=ps.executeQuery();
while(rs.next())
{
String temp=rs.getString("pin");
if(temp.equals(newpin))
{
JOptionPane.showMessageDialog(frame1,"PIN EXISTS. TRY AGAIN!!!");
//pass.setText("");
flag=1;
 break ;                
}}}
catch(Exception ex)
  {


  }

  

for(int i=0;i<newpin.length();i++)
{
char c = newpin.charAt(i);
if (Character.isLetter(c) && flag!=1)
{
JOptionPane.showMessageDialog(frame1,"INVALID PIN. TRY AGAIN!!!");
//pass.setText("");
flag=1;
break;
}
}

if(newpin.length()!=4 && flag!=1)
{
JOptionPane.showMessageDialog(frame1,"INVALID PIN. TRY AGAIN!!!");
flag=1;
//pass.setText("");
}

if (flag==0)
{
           try {     
ResultSet rs=null;
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
       String       query = "UPDATE Customer SET pin = ? WHERE pin = ?";
      ps = con.prepareStatement(query); // create a statement

      ps.setString(1,newpin);
      ps.setString(2,val);                             
      ps.executeUpdate();
JOptionPane.showMessageDialog(frame1,"Pin Updated");
validation();

                }
catch(Exception ex)
  {
  
 }
}//end of if
}while(flag!=0);

/*else
{
do
{
 flag=0;
           do{
           newpin=JOptionPane.showInputDialog(frame1,"Enter the new pin");
   
               
          if(newpin.length()==0)
          JOptionPane.showMessageDialog(frame1, "Please Enter A Pin");          
          }while(newpin.length()==0);      
   

try
{
Statement st=null;
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;

       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
       String  query = "SELECT pin from Customer";
      ps = con.prepareStatement(query); // create a statement
      rs=ps.executeQuery();
while(rs.next())
{
String temp=rs.getString("pin");
if(temp.equals(newpin))
{
JOptionPane.showMessageDialog(frame1,"PIN EXISTS. TRY AGAIN!!!");
flag=1;
 break ;                
}}}
catch(Exception ex)
  {


  }

  

for(int i=0;i<newpin.length();i++)
{
char c = newpin.charAt(i);
if (Character.isLetter(c) && flag!=1)
{
JOptionPane.showMessageDialog(frame1,"INVALID PIN. TRY AGAIN!!!");
//text1.setText("");
flag=1;
break;
}
}

if(newpin.length()!=4  && flag!=1)
{
JOptionPane.showMessageDialog(frame1,"INVALID PIN. TRY AGAIN!!!");
flag=1;
//text1.setText("");
}

if (flag==0)
{
           try {     
ResultSet rs=null;
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con = DriverManager.getConnection("Jdbc:Odbc:atmdb");
       String       query = "UPDATE savingsCustomers SET pin = ? WHERE pin = ?";
      ps = con.prepareStatement(query); // create a statement

      ps.setString(1,newpin);
      ps.setString(2,val);                             
      ps.executeUpdate();
JOptionPane.showMessageDialog(frame1,"Pin Updated");
validation();

                }
catch(Exception ex)
  {
  
 }
}//end of if
}while(flag!=0);

}*/
}

public void validation()
{
  int n=JOptionPane.showConfirmDialog(frame,"Do you Want to Avail Other Services?");                 
        if (n == JOptionPane.NO_OPTION) 
        {
frame.setVisible(false);
Logout l=new Logout();
        }
else if(n == JOptionPane.YES_OPTION)
{
frame.setVisible(false);
Transaction t=new Transaction(newpin);
}

}
}