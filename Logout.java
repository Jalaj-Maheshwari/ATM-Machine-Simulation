import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Logout extends JFrame implements ActionListener
{

JFrame frame;
JLabel label1,label2,lbl_img;
Container co;
JButton btn_lgout;
JPanel panel;

public Logout()
{
panel=new JPanel();
frame=new JFrame();
frame.setSize(600,600);
frame.setTitle("LOGOUT");
co=getContentPane();
panel.setLayout(null);

ImageIcon ig=new ImageIcon("atmimage.png");
  lbl_img=new JLabel(ig);
lbl_img.setSize(500,100);
lbl_img.setLocation(50,25); 
panel.add(lbl_img);       


label1=new JLabel("THANK  YOU  FOR  USING  ICICI  BANK  ATM !!!!");
label1.setFont(new Font("Perpetua",Font.BOLD,22));
label1.setSize(500,35);
label1.setLocation(60,200);

label2=new JLabel("WE  HOPE  TO  SEE  YOU  AGAIN !!!");
label2.setFont(new Font("Perpetua",Font.BOLD,22));
label2.setSize(400,35);
label2.setLocation(135,250);

btn_lgout=new JButton(" Press to Logout");
btn_lgout.setSize(200,50);
btn_lgout.setLocation(190,325);
btn_lgout.addActionListener(this);
btn_lgout.setFont(new Font("Perpetua",Font.BOLD,18));
panel.add(label1);
panel.add(label2);
panel.add(btn_lgout);
//panel.setBackground(Color.orange);


co.add(panel);
frame.add(co);
frame.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==btn_lgout)
{
JOptionPane.showMessageDialog(frame,"Session Completed");
frame.setVisible(false);
AtmSimulatorFrame a=new AtmSimulatorFrame();
}
}


}
