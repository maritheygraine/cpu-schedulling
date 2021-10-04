import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class compute extends JFrame implements ActionListener{
    private static final long serialVersionUID = -2125813716721505843L;

    JLabel proLabel, btLabel, p1Label, atLabel, bg;
    JButton btnAdd, btnDone, btnReset;
    JTextField[] proText, atText;
    JTextField quantum;
    JPanel jp;
    ImageIcon img = new ImageIcon("PROCESS.png");
    JScrollPane js;
    JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    int arrsize = 1,  yAxis = 250,  LyAxis = 100;
    int lowerBound=0, upperBound=1, sizeY = 500, quantumTime=0;
    int[] bt, at;
    String marker=null;

    Font FONTB = new Font("Century Gothic", Font.BOLD,16);
    Font FONT = new Font("Century Gothic", Font.PLAIN,16);
    
    ArrayList<Integer> burst = new ArrayList<Integer>();
	ArrayList<Integer> arrive = new ArrayList<Integer>();
	boolean priorits=false, quants=false;
	
	
    public compute(boolean quan, boolean prio, String mark){
        bg = new JLabel(img);
        jp = new JPanel();
        jp.setLayout(null);
        
        //Settings For Arrival, Priority, Or Quantum Time
        String name = null;
        priorits = prio;
        quants = quan;
        marker = mark;
        if(priorits == false && quants== false){
        	name = "ARRIVAL TIME (AT) ";
        }else if(priorits == true && quants== false){
        	name = "        PRIORITY";
        	
        }else if(priorits == false && quants== true){
        	name = "ARRIVAL TIME (AT)";
        	quantum = new JTextField("0");
        	quantum.setFont(FONT);
        	quantum.setHorizontalAlignment(JTextField.CENTER);
	        quantum.setBounds(320,10,50,25);
	        
	        JLabel quantumLabel = new JLabel("QUANTUM TIME: "); 
	        quantumLabel.setFont(FONTB);
	        quantumLabel.setBounds(170,10,200,25);

	         jp.add(quantum);
	         jp.add(quantumLabel);
        }
        
        //=================================
        proLabel = new JLabel("PROCESS");
        proLabel.setFont(FONTB);
        proLabel.setBounds(30,40,200,25);

        btLabel = new JLabel("BURST TIME (BT) ");
        btLabel.setFont(FONTB);
        btLabel.setBounds(170,40,200,25);

        atLabel = new JLabel(name);
        atLabel.setFont(FONTB);
        atLabel.setBounds(318,40,200,25);

        //Process Button
        btnAdd = new JButton("Add Process");
        btnAdd.setFont(FONTB);
        btnAdd.setSize(130,30);
        btnAdd.setLocation(160, 200);
        btnAdd.setForeground(Color.white);
        btnAdd.setBackground(new Color(53,56,193));
        btnAdd.setHorizontalAlignment(0);

        btnDone = new JButton("Calculate");
        btnDone.setFont(FONTB);
        btnDone.setSize(130,30);
        btnDone.setLocation(320, 200);
        btnDone.setForeground(Color.white);
        btnDone.setBackground(new Color(53,56,193));
        btnDone.setHorizontalAlignment(0);
        
        btnReset = new JButton("Reset");
        btnReset.setFont(FONTB);
        btnReset.setSize(130,30);
        btnReset.setLocation(320, 200);
        btnReset.setForeground(Color.white);
        btnReset.setBackground(new Color(53,56,193));
        btnReset.setHorizontalAlignment(0);
        
        btnPnl.add(btnDone);    
        btnPnl.add(btnAdd);
        btnPnl.add(btnReset);
        
        //Hand Cursor
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        fcfss(arrsize,  yAxis,  LyAxis); //adding of labels and shits
        
        btnAdd.addActionListener(this); 
        btnDone.addActionListener(this); 
        btnReset.addActionListener(this); 
       
        js = new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jp.setBackground(Color.WHITE);
        
        this.add(js);
        js.setPreferredSize(new Dimension(450,500));
        this.add(btnPnl, BorderLayout.SOUTH);
        this.getContentPane().add(bg, BorderLayout.NORTH);
        this.setSize(510, 550);    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        pack();
        this.setVisible(true);
    }


    public void fcfss(int arrsize, int yAxis, int LyAxis ){

        JLabel[] pLabel = new JLabel[arrsize];
        proText = new JTextField[arrsize];
        atText = new JTextField[arrsize];
        
        for(int i=lowerBound; i<upperBound; i++){
            pLabel[i] = new JLabel("P" + (i+1));
            pLabel[i].setFont(FONTB);
            pLabel[i].setBounds(50,LyAxis,200,25);

            proText[i] = new JTextField("");
            proText[i].setFont(FONT);
            proText[i].setHorizontalAlignment(JTextField.CENTER);
            proText[i].setBounds(200,LyAxis,50,25);
           
	            atText[i] = new JTextField("0");
	            atText[i].setFont(FONT);
	            atText[i].setHorizontalAlignment(JTextField.CENTER);
	            atText[i].setBounds(360,LyAxis,50,25);
	
	         if(quants == true || marker=="SJFNP"){
	        	 atText[i].setEditable(false);
	         }   
	            
	            
	        jp.add(atText[i]);
            jp.add(pLabel[i]);
            jp.add(proText[i]);
            repaint();
        }

        //Add Label
        jp.add(btLabel);
        jp.add(proLabel);
        jp.add(atLabel);
        jp.setPreferredSize(new Dimension(510,sizeY));
        sizeY+=40;
        
    }

    public void actionPerformed(ActionEvent ae){
    	boolean flag = true;
    	for(int i=lowerBound; i<upperBound; i++){
            try{
                int bt=0, at=0;
	        	at = Integer.parseInt(atText[i].getText());
                bt = Integer.parseInt(proText[i].getText());
                burst.add(bt);
                arrive.add(at);
                if(quants == true){
    	        	quantumTime = Integer.parseInt(quantum.getText()); 
                }
                if(i < upperBound){
                    proText[i].setEditable(false);
                    atText[i].setEditable(false);
                    if(quants == true){
                    	quantum.setEditable(false);
                    }
                    flag =false;
                }
                
               
            }catch(Exception nfe){
                    JOptionPane.showMessageDialog(null, "Please Enter an Input", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                    flag=true;
                    break;
            }	
    	}
 
        if(ae.getSource() == btnAdd && flag == false){
        	this.upperBound++;
        	this.lowerBound++;
            this.arrsize++;
            this.yAxis += 50;
            this.LyAxis += 50;
                
                fcfss(arrsize,yAxis,LyAxis);        
        }	
        else if(ae.getSource() == btnDone && flag==false){
        	if(marker == "FCFS"){//Done
	        	 fcfs show = new fcfs();
	    		 show.getInput(burst, arrive);
	 			 this.dispose();
 			}else if(marker == "SJFP"){
	        	 sjfp show = new sjfp();
	    		 show.getInput(burst, arrive);
	 			 this.dispose();
			}else if(marker == "SJFNP"){
				sjf show = new sjf();
	    		 show.getInput(burst, arrive);
	 			 this.dispose();
			}else if(marker == "RR"){
	        	 RoundRobin show = new RoundRobin();
	    		 show.getInput(burst, arrive, quantumTime);
	 			 this.dispose();
			}else if(marker == "PR"){
	        	 Priority show = new Priority();
	    		 show.getInput(burst, arrive);
	 			 this.dispose();
			}
        }
        else if(ae.getSource()==btnReset){
            this.dispose();
            compute BACK = new compute(quants,priorits, marker);
            BACK.setVisible(true);
        }
        
            
    }
	//public static void main(String[] args){new compute();}//checking
}
