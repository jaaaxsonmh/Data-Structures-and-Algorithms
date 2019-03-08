package Exercises;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallGuiComplete extends JPanel implements ActionListener
{
   private JLabel label;
   private JButton createBall, clear, createBalls;
   private int counter;
   
   public BallGuiComplete()
   {  super();
      setPreferredSize(new Dimension(300, 200));
      createBall = new JButton("1 ball");
      clear = new JButton("clear");
      createBalls = new JButton("100 balls");
      
      add(createBall);
      add(createBalls);
      add(clear);
      
      createBall.addActionListener(this);
      createBalls.addActionListener(this);
      clear.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e)
   {  
       
   }
   
   public void createGui() {
             JButton createBall = new JButton("1 bad ball");
   }
   
   public static void main(String[] args)
   {  JFrame frame = new JFrame("Main and Event Queue Thread Example");
      // kill all threads when frame closes
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new BallGuiComplete());
      frame.pack();
      // position the frame in the middle of the screen
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
      JPanel panel = new JPanel();
      frame.add(panel);
   }

    private Thread run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
