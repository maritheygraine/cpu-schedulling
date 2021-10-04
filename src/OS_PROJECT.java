
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

class Intro{
    int i = 0; int count =0;
    JFrame frame;
    JLabel intro;
    ImageIcon img = new ImageIcon("INTRO.gif");
    public JProgressBar pb;
    

    public Intro(){
        frame = new JFrame();
        intro = new JLabel();
        pb = new JProgressBar();
        
        pb.setValue(0);
        pb.setStringPainted(true);
        pb.setString("");
        pb.setForeground(Color.CYAN);
        pb.setBackground(new java.awt.Color(0,0,151));
        pb.setBounds(140, 450, 200, 5);
        pb.setBorder(BorderFactory.createLineBorder(new java.awt.Color(77,0,163)));

        intro.add(pb);
        
        frame.setTitle("CPU Schedulling");
        
        frame.setSize(486, 570);
        frame.setVisible(true);
        frame.setResizable(true);
        
        
        intro.setIcon(img);        
        frame.setLocationRelativeTo(null);
        
        frame.getContentPane().add(intro);
        frame.pack();
    }
    
}

//------------------MENU--------------------------------------------------------//
class Menu extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5745853225050496573L;
	ImageIcon img = new ImageIcon("MENU.png");
    JButton FCFS, SJFP, SJFNP, RR, PR;
    Font FONT = new Font("Century Gothic", Font.PLAIN,16);
    Font FONTB = new Font("Century Gothic", Font.BOLD,16);
    
    public Menu(){
        super("Menu");
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        
        JLabel bg = new JLabel(img);
        bg.setLayout(null);
        
        FCFS = new JButton("First Come First Serve");
        SJFP = new JButton("Shortest-Job-First Preemptive");
        SJFNP = new JButton("Shortest-Job-First Non-preemptive");
        RR = new JButton("Round-Robin");
        PR = new JButton("Priority");
        
        FCFS.setFont(FONT);
        SJFP.setFont(FONT);
        SJFNP.setFont(FONT);
        RR.setFont(FONT);
        PR.setFont(FONT);
        
        //No Border
        FCFS.setBorderPainted(false);
        SJFP.setBorderPainted(false);
        SJFNP.setBorderPainted(false);
        RR.setBorderPainted(false);
        PR.setBorderPainted(false);
        
        //No Background Color
        FCFS.setContentAreaFilled(false);
        SJFP.setContentAreaFilled(false);
        SJFNP.setContentAreaFilled(false);
        RR.setContentAreaFilled(false);
        PR.setContentAreaFilled(false);

        
        //Text Color
        FCFS.setForeground(new java.awt.Color(69,78,140));
        SJFP.setForeground(new java.awt.Color(69,78,140));
        SJFNP.setForeground(new java.awt.Color(69,78,140));
        RR.setForeground(new java.awt.Color(69,78,140));
        PR.setForeground(new java.awt.Color(69,78,140));

        
        //Button Position
        FCFS.setBounds(59,250,376,65); //x y w h
        SJFP.setBounds(59,300,376,65);
        SJFNP.setBounds(59,350,376,65);
        RR.setBounds(59,400,376,65);
        PR.setBounds(59,450,376,65);

        
        //MouseListeners
        FCFS.addMouseListener (new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt) {
                FCFS.setFont(FONTB);
                FCFS.setContentAreaFilled(false);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                FCFS.setFont(FONT);
                FCFS.setContentAreaFilled(false);
            }
            @Override
            public void mousePressed(MouseEvent evt){
                FCFS.setFont(FONTB);
                FCFS.setContentAreaFilled(false);
            }
        });
        SJFP.addMouseListener (new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt) {
                SJFP.setFont(FONTB);
                SJFP.setContentAreaFilled(false);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                SJFP.setFont(FONT);
                SJFP.setContentAreaFilled(false);
            }
            @Override
            public void mousePressed(MouseEvent evt){
                SJFP.setFont(FONTB);
                SJFP.setContentAreaFilled(false);
            }
        });
        SJFNP.addMouseListener (new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt) {
                SJFNP.setFont(FONTB);
                SJFNP.setContentAreaFilled(false);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                SJFNP.setFont(FONT);
                SJFNP.setContentAreaFilled(false);
            }
            @Override
            public void mousePressed(MouseEvent evt){
                SJFNP.setFont(FONTB);
                SJFNP.setContentAreaFilled(false);
            }
        });
        RR.addMouseListener (new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt) {
                RR.setFont(FONTB);
                RR.setContentAreaFilled(false);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                RR.setFont(FONT);
                RR.setContentAreaFilled(false);
            }
            @Override
            public void mousePressed(MouseEvent evt){
                RR.setFont(FONTB);
                RR.setContentAreaFilled(false);
            }
        });
        
        PR.addMouseListener (new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent evt) {
            	PR.setFont(FONTB);
            	PR.setContentAreaFilled(false);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
            	PR.setFont(FONT);
            	PR.setContentAreaFilled(false);
            }
            @Override
            public void mousePressed(MouseEvent evt){
            	PR.setFont(FONTB);
            	PR.setContentAreaFilled(false);
            }
        });
        
        FCFS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SJFP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SJFNP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        RR.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        PR.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        
        bg.add(FCFS);
        bg.add(SJFP);
        bg.add(SJFNP);
        bg.add(RR);
        bg.add(PR);
        this.add(bg);
        
        FCFS.addActionListener(this);
        SJFP.addActionListener(this);
        SJFNP.addActionListener(this);
        RR.addActionListener(this);
        PR.addActionListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean Quantum=false, Priorits=false;
        String algoMark =null;
        if (e.getSource()==FCFS){
            this.dispose();
            Quantum=false;
            Priorits=false;
            algoMark="FCFS";
            new compute(Quantum, Priorits, algoMark);
    		dispose();
        }
        else if (e.getSource()==SJFP){
            this.dispose();
            Quantum=false;
            Priorits=false;
            algoMark="SJFP";
            new compute(Quantum, Priorits, algoMark);
    		dispose();
        }
        else if (e.getSource()==SJFNP){
            this.dispose();
            Quantum=false;
            Priorits=false;
            algoMark="SJFNP";
            new compute(Quantum, Priorits, algoMark);
    		dispose();
        }
        else if(e.getSource()==RR){
            this.dispose();
            Quantum=true;
            Priorits=false;
            algoMark="RR";
            new compute(Quantum, Priorits, algoMark);
    		dispose();
        }else if(e.getSource()==PR){
            this.dispose();
            Quantum=false;
            Priorits=true;
            algoMark="PR";
            new compute(Quantum, Priorits, algoMark);
    		dispose();
        }
        
    }

}


//------------------RUN--------------------------------------------------------//
 public class OS_PROJECT {   
    public static void main(String[] args) {
        Intro intro = new Intro();
        intro.frame.setVisible(true);
        intro.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try{
            for (int i = 0; i<=100; i++){
                Thread.sleep(20);
                intro.pb.setValue(i);
                if(i==100){
                    intro.pb.setVisible(false);
                    intro.frame.setVisible(false);
                    intro.frame.dispose();
                    Menu menu = new Menu();
                    menu.setVisible(true);
                }
            }
        } catch (InterruptedException ex) {}
    }
}
