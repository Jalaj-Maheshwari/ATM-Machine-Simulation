import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chequebook extends JFrame implements ActionListener
 {

JFrame frame;
JLabel label,lbl_img;
Container co;
JRadioButton but1,but2;
JButton submit,cancel;
JPanel panel;
//public static JFrame frame2; 
ButtonGroup bg;

public Chequebook()
{
//frame2=frame1;
frame=new JFrame();
co= getContentPane();
co.setLayout(null);
frame.setTitle(" CHEQUEBOOK REQUEST");
label=new JLabel("SELECT  NUMBER  OF  LEAVES  IN  CHEQUEBOOK");
label.setFont(new Font("Perpetua",Font.BOLD,22));
label.setSize(500,100);
label.setLocation(50,150);
frame.setSize(600,600);
co.add(label);
frame.add(co);
but1=new JRadioButton("20 Leaves");
but1.setFont(new Font("Perpetua",Font.BOLD,20));
but1.setBounds(125,250,200,100);
//but1.setBackground(Color.orange);

but2=new JRadioButton("40 Leaves");
but2.setFont(new Font("Perpetua",Font.BOLD,20));
but2.setBounds(350,250,200,100);
//but2.setBackground(Color.orange);

//submit=new JButton("SUBMIT");
submit=new JButton("Submit");
submit.setFont(new Font("Perpetua",Font.BOLD,18));
submit.setSize(100,50);
submit.setLocation(150,375);
submit.addActionListener(this);

 //submit.setBackground(Color.RED);


cancel=new JButton("Cancel");
cancel.setSize(100,50);
cancel.setLocation(325,375);
cancel.setFont(new Font("Perpetua",Font.BOLD,18));
cancel.addActionListener(this);

ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25); 
co.add(lbl_img);       

co.add(but1);
co.add(but2);

co.add(submit);
co.add(cancel);
//co.setBackground(Color.orange);
bg=new ButtonGroup();
bg.add(but1);
bg.add(but2);
frame.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
      
if(ae.getSource()==submit)
{
  if(but1.isSelected()==true || but2.isSelected()==true )
  {
  JOptionPane.showMessageDialog(frame,"CHEQUEBOOK ISSUED !!!!");
  validation();
  }
  else
  JOptionPane.showMessageDialog(frame,"Please Select An Option");
}


else
{
frame.setVisible(false);
//frame2.setVisible(true);
}
}

public void validation()
{
  int n=JOptionPane.showConfirmDialog(frame,"Do you Want to Avail Other Services? ");                 
        if (n == JOptionPane.NO_OPTION) 
        {
frame.setVisible(false);
Logout l=new Logout();
        }
else
frame.setVisible(false);


}

public static void main(String args[])
{
Chequebook c=new Chequebook();
}
}