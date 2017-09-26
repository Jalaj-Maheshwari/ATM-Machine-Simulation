import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Progress extends JFrame implements Runnable
 {

JFrame frame;
JLabel lbl_a,lbl_b,lbl_c,lbl_d,lbl_img;
JPanel panel;
Container co;
Thread t;
public static int flag1;

public Progress(int empblnc,int flag,String amt)
{
flag1=flag;
String newamt=amt;

frame=new JFrame();
   frame.setSize(600,600);     
   frame.setTitle("TRANSACTION IN PROCESS");
   
   panel=new JPanel();
   panel.setLayout(null);          
   panel.setVisible(true);
   //panel.setBackground(Color.gray);
   panel.setForeground(Color.white);

   co=getContentPane();   
   ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25);        

panel.add(lbl_img);        

   Graphics g=getGraphics();
     // g.setBackground(Color.RED);
   lbl_a=new JLabel("Please Wait While Your Transaction is Being Processed....");
        lbl_a.setSize(550,50);
        lbl_a.setLocation(25,150);
        lbl_a.setVisible(true);
lbl_a.setFont(new Font("Perpetua",Font.BOLD,22));
 //lbl_a.Foreground(Color.white);

        panel.add(lbl_a);
// g.setBackground(Color.RED);
     
   lbl_b=new JLabel("Amount Withdrawn is Rs "+newamt);
        lbl_b.setSize(450,50);
lbl_b.setFont(new Font("Perpetua",Font.BOLD,22));
        //lbl_b.setLocation(100,200);
        lbl_b.setVisible(false);
        panel.add(lbl_b);

   lbl_c=new JLabel("Amount Deposited is Rs "+newamt);
lbl_c.setFont(new Font("Perpetua",Font.BOLD,22));
        lbl_c.setSize(450,50);
//        lbl_c.setLocation(50,150);
        lbl_c.setVisible(false);
        panel.add(lbl_c);

        lbl_d=new JLabel("Your Current Balance is Rs " + empblnc );
        lbl_d.setSize(400,50);
        lbl_d.setFont(new Font("Perpetua",Font.BOLD,22));
        lbl_d.setLocation(125,350);
        lbl_d.setVisible(false);
         panel.add(lbl_d);
co.add(panel);
frame.add(co);
frame.setVisible(true);

t=new Thread(this);

t.start();

}

public void run()
{

try
{
t.sleep(3000);
lbl_a.setLocation(125,150);
lbl_a.setText(" Transaction Processed Successfully");
if(flag1==0)
{
lbl_b.setLocation(150,250);
lbl_b.setVisible(true);
}
else
{
lbl_c.setLocation(150,250);
lbl_c.setVisible(true);
}
t.sleep(3000);
lbl_d.setVisible(true);
t.sleep(5000);
int n=JOptionPane.showConfirmDialog(frame,"Do you Want to Avail Other Services? ");                 
        if (n == JOptionPane.NO_OPTION) 
        {
frame.setVisible(false);
Logout l=new Logout();
        }
else if(n== JOptionPane.CANCEL_OPTION)
{}
else
{
frame.setVisible(false);

}
}
catch(Exception e){}
}

public static void main(String args[])
{
Progress p=new Progress(1000,1,"500");
}
}

