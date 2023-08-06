package chattingapplication;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.net.*; /* for server */

public class Server implements ActionListener {
           
            JTextField text; 
            JPanel p;
            static Box vertical = Box.createVerticalBox(); /* its use for create vertical box*/
            static JFrame sf= new JFrame(); /* its used for extends all jframe function */
            static DataOutputStream dout;
            
   Server(){
 
       sf.setLayout(null);
         
       
       JPanel f = new JPanel();
       f.setBackground(new Color(4,45,70));
       f.setBounds(0,0,450,70);
       f.setLayout(null);
       sf.add(f);
       
       /* This objject is used for getting images in a source program*/
       ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icons/arrow.png.png"));
       /* This function is used for set the image icon in a absolute condition*/
       Image i1 = i.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
       /* we cannot call modify image thats why we create new imageicon class for calling modiied image*/
       ImageIcon i2 = new ImageIcon(i1);
       JLabel back = new JLabel(i2);
       back.setBounds(5,10,50,50);
       f.add(back);
       
       /* the given function used to control the click */
       back.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       
        /* This objject is used for getting images in a source program*/
       ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/itachi.png.jpg"));
       /* This function is used for set the image icon in a absolute condition*/
       Image i4 = i3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
       /* we cannot call modify image thats why we create new imageicon class for calling modiied image*/
       ImageIcon i5 = new ImageIcon(i4);
       JLabel back1 = new JLabel(i5);
       back1.setBounds(60,10,50,50);
       f.add(back1);
       
       ImageIcon i6 = new ImageIcon(ClassLoader.getSystemResource("icons//3icon.png.png"));
       Image i7 = i6.getImage().getScaledInstance(10,20,Image.SCALE_DEFAULT);
       ImageIcon i8 = new ImageIcon(i7);
       JLabel back2 = new JLabel(i8);
       back2.setBounds(390,10,50,50);
       f.add(back2);
       
       ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("icons//phone.png.png"));
       Image i10 = i9.getImage().getScaledInstance(28,28,Image.SCALE_DEFAULT);
       ImageIcon i11 = new ImageIcon(i10);
       JLabel back3 = new JLabel(i11);
       back3.setBounds(360,12,50,50);
       f.add(back3);
       
       ImageIcon i12 = new ImageIcon(ClassLoader.getSystemResource("icons//video.png.png"));
       Image i13 = i12.getImage().getScaledInstance(28,28,Image.SCALE_DEFAULT);
       ImageIcon i14 = new ImageIcon(i13);
       JLabel back4 = new JLabel(i14);
       back4.setBounds(320,12,50,50);
       f.add(back4);
       
       JLabel name = new JLabel("Itachi Uchiha");
       name.setBounds(120, 15, 80, 18);
       name.setForeground(Color.white);
       name.setFont(new Font("SAN_SARIF",Font.BOLD,12));
       f.add(name);
       
       JLabel status = new JLabel("Activate");
       status.setBounds(120, 30, 80, 18);
       status.setForeground(Color.white);
       status.setFont(new Font("SAN_SARIF",Font.BOLD,12));
       f.add(status);
       
       p = new JPanel();
       p.setBounds(0,71,450,530);
       sf.add(p);
       
       
       text = new JTextField();
       text.setBounds(5,605, 300, 50);
       sf.add(text);
       
       JButton b = new JButton("SEND");
       b.setBounds(310,605,138,50);
       b.setBackground(new Color(4,45,70));
       b.setForeground(Color.white);
       b.addActionListener(this);
       sf.add(b);
       
       sf.setSize(450,700);
       sf.setLocation(300,20);
       sf.setUndecorated(true);
       sf.getContentPane().setBackground(Color.WHITE);
       
       sf.setVisible(true);
       
   }
   
   public void actionPerformed(ActionEvent ae){
       
       try {
       String s = text.getText();
       
       JLabel show = new JLabel(s);
       
       JPanel p2 = formatLabel(s);

       p.setLayout(new BorderLayout());
       
       /* this fuction used to create border layout */
       JPanel ibox = new JPanel(new BorderLayout());
       ibox.add(p2,BorderLayout.LINE_END);
       vertical.add(ibox);
       vertical.add(Box.createVerticalStrut(15));
       p.add(vertical,BorderLayout.PAGE_START);
       
       dout.writeUTF(s);
       text.setText("");
       
       sf.repaint();
       sf.invalidate();
       sf.validate();
       } catch (Exception e){
           e.printStackTrace();
       }
       
   }
   
      public static JPanel formatLabel(String s){
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
       
       JLabel show = new JLabel("<html><p style=\"widht:150px\">"+ s +"</p></html>");
       show.setFont(new Font("Tahoma",Font.PLAIN,14));
       show.setBackground(Color.LIGHT_GRAY);
       show.setOpaque(true);
       show.setBorder(new EmptyBorder(15,15,15,50));
       
       panel.add(show);
       
       Calendar cal = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
      
       JLabel time = new JLabel();
       time.setText(sdf.format(cal.getTime()));
       
       panel.add(time);
       
       return panel;
   }
   
    public static void main(String args[]) {
        new Server();
        
        try {
         
            ServerSocket stk = new ServerSocket(6001); /* for which port to run this code */
           
            while(true){
            Socket ss = stk.accept();
            DataInputStream din = new DataInputStream(ss.getInputStream());
            dout = new DataOutputStream(ss.getOutputStream());
            
            while(true){
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                vertical.add(left);
                sf.validate();
            }
           }
        } catch (Exception e){
            
        }
    }
}
