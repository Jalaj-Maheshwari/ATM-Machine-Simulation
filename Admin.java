import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.io.*;
public class Admin extends JFrame 
{
public static void main(String args[])throws Exception
{
JFrame fr=new JFrame();
fr.setVisible(true);
fr.setSize(300,300);
fr.setTitle("Administrator");

JTabbedPane jtp=new JTabbedPane();
jtp.addTab("Execute",new editPanel());
fr.getContentPane();
fr.add(jtp);

}
}

class editPanel extends JPanel implements ActionListener
{
JButton c,s,u,d,da;
JTextField t,t1,t2,t3;
JLabel label,idd,iddd,ik,im;
public editPanel()
{
label=new JLabel("Please Enter the ID");
idd=new JLabel("ID");
iddd=new JLabel("Tabel Name");
ik=new JLabel("Column Name");
im=new JLabel("Value");
t=new JTextField(20);
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
c=new JButton("Create Table");
s=new JButton("Show");
u=new JButton("Update");
d=new JButton("Delete");
da=new JButton("Delete All");
add(label);                                          //please
label.setBounds(20,20,200,20);
add(idd);                                           //id
idd.setBounds(20,70,200,20);
add(t);                                               //id textfield
t.setBounds(110,70,200,20);
add(iddd);                                       //table name
iddd.setBounds(20,100,200,20);
add(t1);                                            //table name textfield    
t1.setBounds(110,100,200,20);
add(ik);
ik.setBounds(20,130,200,20);
add(t2);
t2.setBounds(110,130,200,20);
add(im);
im.setBounds(20,160,200,20);
add(t3);
t3.setBounds(110,160,200,20);

//add(c);
//c.setBounds(20,200,200,20);
add(s);
s.setBounds(20,250,200,20);
add(u);
u.setBounds(20,300,200,20);
add(d);
d.setBounds(20,350,200,20);
add(da);
da.setBounds(20,400,200,20);

setLayout(null);

//c.addActionListener(this);
s.addActionListener(this);
u.addActionListener(this);
d.addActionListener(this);
da.addActionListener(this);
}


// DELETE QUERY
public void actionPerformed(ActionEvent m)
{
if(m.getSource()==d)
{
String u=t.getText();
int p=Integer.parseInt(u);
String q=t1.getText();
try
{
System.out.println("MYSQL DRIVER LOADING");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("Driver loaded");
System.out.println("Connection time");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/passportdatabase","root","mysqlroot1234567");
System.out.println("Connection Established");
PreparedStatement ps=null;
String ssss="DELETE FROM "+q+" WHERE ID=?";
ps=con.prepareStatement(ssss);
ps.setInt(1,p);

ps.executeUpdate();
System.out.println("record(s) deleted");
con.close();

}
catch(Exception o)
{
}
}

//DELETE ALL QUERY
else if(m.getSource()==da)
{
String tab=t1.getText();
try
{
System.out.println("Driver loading");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("driver loaded");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/passportdatabase","root","mysqlroot1234567");
System.out.println("Connection Established:");
PreparedStatement p=null;
p=c.prepareStatement("DELETE FROM "+tab);
p.executeUpdate();
System.out.println("table deleted");
c.close();
}
catch(Exception w)
{
}
}

else if(m.getSource()==u)
{
String s=t.getText();
int u=Integer.parseInt(s);
String s1=t1.getText();
String s2=t2.getText();
String s3=t3.getText();
try
{
System.out.println("Driver loading");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("driver loaded");
Connection nect=DriverManager.getConnection("jdbc:mysql://localhost:3306/passportdatabase","root","mysqlroot1234567");
System.out.println("Connection Established:");
PreparedStatement ppe=null;
String y="UPDATE applicant SET First_Name = ? WHERE ID= ? ";
ppe=nect.prepareStatement(y);
//ppe.setString(1,s1);
//ppe.setString(2,s2);
ppe.setString(1,s3);
ppe.setInt(2,u);
ppe.executeUpdate();
System.out.println("Table Updated");
nect.close();
}
catch(Exception w)
{
}
}

else if(m.getSource()==s)
{
String na=t1.getText();
try
{
System.out.println("Driver Loading");
System.out.println("Driver Loaded");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("driver loaded");
Connection mine=DriverManager.getConnection("jdbc:mysql://localhost:3306/passportdatabase","root","mysqlroot1234567");
System.out.println("Connection Established:");
Statement you=null;
you=mine.createStatement();
String y="Select * from applicant";
//you.setString(1,na);
System.out.println("\n");
ResultSet rr=you.executeQuery(y);
while(rr.next())
{
int i=rr.getInt("ID");
String first_name=rr.getString("First_Name");
String middle_name=rr.getString("Middle_Name");
String last_name=rr.getString("Last_Name");

System.out.format("%s %s %s %s\n", i, first_name, middle_name, last_name);
}

you.close();
mine.close();
}
catch(Exception w)
{
}
}






























}

}









