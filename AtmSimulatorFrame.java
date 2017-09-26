import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class AtmSimulatorFrame extends JFrame implements ActionListener
{
JPanel panel1,panel2,panel3;
JButton but1,but2,but3,but4,but5,but6,but7;
JLabel label1,label2,label3,label4,label5,label6,label7,label8,lbl_img;
JTextField text2,text3,text4,text5;
Container co;
JFrame frame;
JPasswordField text1;
 Connection con;
    Statement st;
int count=0;   
/* Welcome Paage */
    
public AtmSimulatorFrame() 
{            

    
frame=new JFrame();

frame.setSize(600,600);
frame.setTitle("ATM SIMULATION MACHINE");
panel2=new JPanel();
panel2.setLayout(null);


co=getContentPane();

 ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25);        
panel2.add(lbl_img);

panel2.setVisible(true);
//panel2.setBackground(Color.orange);

label2 =new JLabel("WELCOME   TO   ICICI   BANK   ATM ");
label2.setSize(450,100);
label2.setLocation(90,150);
label2.setFont(new Font("Perpetua",Font.BOLD,25));
label2.setVisible(true);
label3 =new JLabel("Enter the 4 Digit Pin No.");
label3.setSize (220,50);
label3.setFont(new Font("Perpetua",Font.BOLD,20));
label3.setLocation(115,250);
text1=new JPasswordField(4);

//text1=new JTextField();
text1.setSize(100,35);
text1.setFont(new Font("Perpetua",Font.BOLD,20));
text1.setLocation(345,260);
but4=new JButton("Submit");
but4.setSize(100,50);
but4.setLocation(215,350);
but4.setFont(new Font("Perpetua",Font.BOLD,20));
but4.addActionListener(this);
label8=new JLabel();
label8.setSize(50,50);
label8.setLocation(300,45);
panel2.add(label2);
panel2.add(label3);
panel2.add(label8);
panel2.add(text1);
panel2.add(but4);
frame.add(co);
co.add(panel2);
frame.setVisible(true);
try{
                 Class.forName("com.mysql.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","mysqlroot1234567");
               System.out.println("connection established");
            }catch(Exception ex){
                
                JOptionPane.showMessageDialog(null, "Failed Connection","Error",JOptionPane.ERROR_MESSAGE);
            }
   
}

public void actionPerformed(ActionEvent ae)
{
      
if(ae.getActionCommand()=="Submit")
{
String str;
str=new String(text1.getPassword());
int flag=2,flag1=3; //****CHANGED REVERT BACK LATER

  if(str.length()==0)
{
JOptionPane.showMessageDialog(frame,"PLEASE ENTER A PIN");
flag=1;
}
  if(str.length()!=4 && str.length()!=0)
{
JOptionPane.showMessageDialog(frame,"INVALID PIN. TRY AGAIN!!!");
flag=1;
flag1=1;
text1.setText("");
}

if(flag1!=1)
{
for(int i=0;i<str.length();i++)
{
char c = str.charAt(i);
if (Character.isLetter(c))
{
JOptionPane.showMessageDialog(frame,"INVALID PIN. TRY AGAIN!!!");
text1.setText("");
flag=1;
break;
}
}
} 


try{
Connection con = null; ResultSet rs = null; PreparedStatement ps = null;
 
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","jalaj","jalaj");
 ps = con.prepareStatement("select * from Customer where pin = ? ");
  ps.setString(1,str);  
 rs = ps.executeQuery(); 
 if(rs.next())
count=1;  

ps = con.prepareStatement("select * from Customer where pin = ? ");
  ps.setString(1,str);  
 rs = ps.executeQuery(); 
 if(rs.next())
count =1;  

 }
catch(Exception ex)
{
 } 

 if(flag!=1 && count==0 )
{  JOptionPane.showMessageDialog(frame, "INVALID PIN. TRY AGAIN!!!");
   text1.setText(""); 
   flag=1; 
} 

if(flag==1) //***CHANGED REVERT BACK
{
frame.setVisible(false);
Transaction  t=new Transaction(str);
}
}    
}

}

 