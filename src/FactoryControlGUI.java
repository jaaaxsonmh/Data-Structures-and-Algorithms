package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;

public class FactoryControlGUI extends JPanel implements ActionListener {

    private JButton startMachines, stopMachines;
    private DrawPanel drawPanel;


    private static final int MAX_MACHINES = 20;
    private static final int MAX_COOLERS = 4;
    private int                rect_x = 20;

    private List<Machine> machines = new ArrayList<>();
    private List<MonitoringCooler> coolers = new ArrayList<>();


    public FactoryControlGUI() {
        super(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startMachines = new JButton("Start Machines");
        startMachines.addActionListener(this);
        buttonPanel.add(startMachines);

        stopMachines = new JButton("Stop Machines");
        stopMachines.addActionListener(this);
        buttonPanel.add(stopMachines);

        add(buttonPanel, BorderLayout.SOUTH);

        // call actionPerformed method every 25 using Swing timer
        // you can use this to update your GUI
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startMachines) {
//            for(int i = 0; i <= MAX_MACHINES; i++) {
//                machines.add(new Machine(50, 200));
//            }
//
//            for(int i = 0; i <= MAX_COOLERS; i++) {
//                coolers.add(new MonitoringCooler(machines, 25));
//            }

            machines.add(new Machine(50, 250));
            //coolers.add(new MonitoringCooler(machines, 25));
        }

        if(source == stopMachines) {

        }
        drawPanel.repaint();  // this will invoke DrawPanel to redraw itself, (paintComponent will be called)
    }

    public void drawTempLines(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(0, 600, 650, 600);

        g.setColor(Color.blue);
        g.drawLine(0, 450, 650, 450);

        g.setColor(Color.orange);
        g.drawLine(0, 300, 650, 300);

        g.setColor(Color.red);
        g.drawLine(0, 150, 650, 150);

        g.setColor(Color.black);
        g.drawLine(0, 50, 650, 50);
    }


    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            setPreferredSize(new Dimension(650, 650));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawTempLines(g);
            for (Machine m : machines) {
                m.startMachine();
                int currentTemp = 650 - (m.getCurrentTemp() * 2);
                int rect_width = 20;

                if(currentTemp <= 550 && currentTemp > 450) {
                    g.setColor(Color.blue);
                } else if (currentTemp <= 450 && currentTemp > 300){
                    g.setColor(Color.orange);
                } else if (currentTemp <= 300 && currentTemp > 150) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.fillRect(rect_x, currentTemp, rect_width,  m.getCurrentTemp() * 2);
            }

            for (MonitoringCooler cooler : coolers) {
                cooler.startCooler();
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Factory Machine Control");

        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FactoryControlGUI());
        frame.pack();

        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
        // now display something while the main thread is still alive
    }
}
