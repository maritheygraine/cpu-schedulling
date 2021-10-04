//update-oresca
//updated by marithe
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RoundRobin implements ActionListener{
	JFrame frame;
        JScrollPane js;
        JLabel bg, p, bts, atL, cts, tat, wts;
        JLabel t1, t2, t3, t4,t5;
        JButton btnExit, btnHome;
        
        ImageIcon img = new ImageIcon("RESULTS_top.png");
        Font FONTB = new Font("Century Gothic", Font.BOLD,16);
        Font FONTG = new Font("Century Gothic", Font.BOLD,14);


		ArrayList<Integer> bt = new ArrayList<Integer>();
		ArrayList<Integer> at = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		
		int [] arrivalTime, burstTime, pid, ct, ta, wt, rem, gantt, pID2;	
		public int temp, quantumTime=0, m=0, nukacolaquantum;
		public float avgwt=0,avgta=0;
	    int yAxis = 75;

        JLabel atat, awt, atatAns, awtAns;
        public JLabel[] ctLabel, taLabel, wtLabel, btlabel, atlabel, plabel;
		JLabel quantumLabel1, quantumLabel2;
        public JLabel[] Ps;
        JPanel btnPnl, TopPnl, view, CenterPnl, btnTop;   
		JLabel qTime;
		boolean trigger=false;
		
        
	
        public RoundRobin(){
        	
            frame = new JFrame("Round Robin");
            frame.getContentPane().setBackground(Color.WHITE);
            btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
            TopPnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); //title pic
            view = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            CenterPnl = new JPanel();
            btnTop = new JPanel();

            bg = new JLabel(img);
            CenterPnl.setLayout(null);
            btnTop.setLayout(null);

            buttons();
            getInput(bt,at,quantumTime);
            InsideScrollBar();

            TopPnl.setBounds(0, 0, 710, 94);
            CenterPnl.setLocation(0, 94);
            view.setBounds(0, 94, 710, 300);
            btnTop.setBounds(0, 380, 710, 280);

            CenterPnl.setBackground(Color.WHITE); //panel for labels
            view.setBackground(Color.WHITE);    //pinasok yung centerpnl sa view for scroll
            btnTop.setBackground(Color.WHITE);  //panel for gantt chart

            TopPnl.add(bg);
            CenterPnl.add(p);
            CenterPnl.add(bts);
            CenterPnl.add(atL);
            CenterPnl.add(tat);
            CenterPnl.add(cts);
            CenterPnl.add(wts);
            CenterPnl.add(t1);
            CenterPnl.add(t2);
            CenterPnl.add(t3);
            CenterPnl.add(t4);
            CenterPnl.add(t5);

            js = new JScrollPane(CenterPnl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            js.setPreferredSize(new Dimension(710, 300));
            js.setBorder(createEmptyBorder());

            view.add(js);       

            btnPnl.add(btnExit);    
            btnPnl.add(btnHome);

            frame.add(TopPnl, BorderLayout.NORTH);
            frame.add(view, BorderLayout.CENTER);
            frame.add(btnTop, BorderLayout.CENTER);
            frame.add(btnPnl, BorderLayout.SOUTH);       
            frame.pack();
            frame.setSize(710, 650); //w h
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setResizable(false); 
            frame.setVisible(true);

            btnExit.addActionListener(this);
            btnHome.addActionListener(this);
        }
    
    
        //GETTER
  	public void getInput(ArrayList<Integer> burst, ArrayList<Integer> arrive,int quant){
  		
	  		for(int i=0; i<burst.size(); i++){
	  			bt.add(burst.get(i));
	  			at.add(arrive.get(i));
	  		}
	  		
	    	burstTime = new int[burst.size()];
	    	arrivalTime = new int[arrive.size()];

	  		for(int i=0; i<burst.size(); i++){
	  			arrivalTime[i] = at.get(i);
	  			burstTime[i] = bt.get(i);
	  			quantumTime = quant;
	  		}
	  		nukacolaquantum = quant;
		  	
	  		computeNonPre();
  	}
    
  	//ALGORITHM 
  	public void computeNonPre(){
            
  		
  		int size = bt.size();
    	ct = new int[size];
    	ta = new int[size];
    	wt = new int[size];
    	rem = new int[size];
        int flag=0;      
        
        for(int i=0; i<size; i ++){ rem[i] = burstTime[i];}
        
        do{
            flag=0;
	            for(int i=0;i<size;i++){
	                        if(rem[i]>= quantumTime){
	                        	 rem[i]=rem[i]- quantumTime;
	                        	 m++;
	                                 
	                        }
	                        else if(rem[i]>0){
	                        			m++;
	                        			rem[i]=0;
	                                
	                        }
	            }
	            for(int i=0;i<size;i++)
	                        if(rem[i]>0)
	                        	flag=1;
			}while(flag==1);
   
			int h=0, k=0;
			gantt= new int[m];
			pID2 = new int[m];
            while(h<m){
            	for(int i=0; i<size; i++){
		            if(burstTime[i] == 0){
		            		continue;
		            }else {
		            	if(burstTime[i] <= quantumTime){
	            			k = k + burstTime[i];
	            			ta[i] = k;
	            			gantt[h] = k;
	            			pID2[h] = i+1;
	            			h++;
	            			burstTime[i] -= burstTime[i] ;
	            		}else if(h == m-1){
	            			gantt[h] = burstTime[i];
	            			pID2[h] = i+1;
	            			h++;
	            			burstTime[i] -= burstTime[i] ;
	            		}else{
	            			k = k + quantumTime;
	            			ta[i] = k;
	            			gantt[h] = k;
	            			pID2[h] = i+1;
	            			h++;
	            			burstTime[i] = burstTime[i] - quantumTime;
	            		}
		            }	
            	}
            	
            	if(h == m-1){ 
            		float ta_temp = 0, wt_temp = 0;
                	for(int i=0; i<size; i++){
                		wt[i] = ta[i] - bt.get(i);
                		wt_temp+=wt[i];
                        ta_temp+=ta[i];
                	}
            		avgta = (ta_temp/size);
            		avgwt = (wt_temp/size);
            		trigger = true;
            	}
            }
          
     	   	 

            generate();
	    	   
        }	
        
        
        public void generate() {
    	
            int totalSize = burstTime.length;
            int yAxis=75;
    	
	    plabel = new JLabel[totalSize];
	    atlabel = new JLabel[totalSize];
	    btlabel = new JLabel[totalSize];
	    wtLabel = new JLabel[totalSize];
	    taLabel = new JLabel[totalSize];
	    ctLabel = new JLabel[totalSize];
    	
	    for(int i=0; i < totalSize; i++) {
	    	
		    //P to N Label
	    	plabel[i] = new JLabel("P" + (i+1));
	    	plabel[i].setFont(FONTB);
	    	plabel[i].setBounds(50,yAxis,200,25);
	    	
	    	//Burst Time Label
	    	btlabel[i] = new JLabel(Integer.toString(bt.get(i)));
	    	btlabel[i].setFont(FONTB);
	    	btlabel[i].setBounds(165,yAxis,200,25);
	    	
	    	//Arrival Time Label
	    	atlabel[i] = new JLabel(Integer.toString(arrivalTime[i]));
	    	atlabel[i].setFont(FONTB);
	    	atlabel[i].setBounds(255,yAxis,200,25);
	    	
	    	//Completion Time
	    	ctLabel[i] = new JLabel(Integer.toString(ta[i]));
	    	ctLabel[i].setFont(FONTB);
	    	ctLabel[i].setBounds(365,yAxis,200,25);
	    	
	    	//Turn Around Time
	    	taLabel[i] = new JLabel(Integer.toString(ta[i]));
	    	taLabel[i].setFont(FONTB);
	    	taLabel[i].setBounds(505,yAxis,200,25);
	    	
	    	//Turn Around Time
	    	wtLabel[i] = new JLabel(Integer.toString(wt[i]));
	    	wtLabel[i].setFont(FONTB);
	    	wtLabel[i].setBounds(625,yAxis,200,25);
	    	
                CenterPnl.setPreferredSize(new Dimension(710,yAxis + 30));
	    	yAxis += 30; 
	    	CenterPnl.add(plabel[i]);
	    	CenterPnl.add(btlabel[i]);
	    	CenterPnl.add(atlabel[i]);
	    	CenterPnl.add(ctLabel[i]);
	    	CenterPnl.add(taLabel[i]);
	    	CenterPnl.add(wtLabel[i]);
	    }    	
	   
	    
	    Ps = new JLabel[m];
		   
	    int tempo=0;
	    int r = 0,g = 0,b = 0;

	    for(int i=0; i<m; i++){
	    	//Change Color
	    	while(!(r > 100 && g > 100 && b > 100)){
	    		r = randomGenerator.nextInt(256);
	    		g = randomGenerator.nextInt(256);
	    		b = randomGenerator.nextInt(256);
	    	}
	        Ps[i] = new JLabel("P" + pID2[i] + " (" + gantt[i] +")");
	        Ps[i].setHorizontalAlignment(JLabel.RIGHT);
	        Ps[i].setFont(FONTG);
	        Ps[i].setForeground(Color.BLACK);       
	        Ps[i].setOpaque(true);       
	        Ps[i].setBackground(new Color(r, g, b));        
	        Ps[i].setHorizontalTextPosition(JLabel.CENTER);        
	        Ps[i].setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	        tempo = i;
	        if(i==0) tempo = 1;
	        Ps[i].setBounds(50, 380, tempo*50, 30);
	        btnTop.add(Ps[i]); 
	    }
       
         
	    if(trigger == true){
        atat = new JLabel ("Average Turn Around Time: ");
        atat.setFont(FONTB);
        atat.setBounds(50, 300, 350, 30);
      
        atatAns = new JLabel(Float.toString(avgta));
        atatAns.setFont(FONTB);
        atatAns.setBounds(270,300,400,30);
        
        //Average Waiting Time
        awt = new JLabel ("Average Waiting Time: ");
        awt.setFont(FONTB);
        awt.setBounds(380,300,350,30);
        
        awtAns = new JLabel (Float.toString(avgwt));
        awtAns.setFont(FONTB);
        awtAns.setBounds(560,300,200,30);
        //display quantum time
        quantumLabel1 = new JLabel ("Quantum Time: ");
        quantumLabel1.setFont(FONTB);
        quantumLabel1.setBounds(50,330,350,30);
        
        quantumLabel2 = new JLabel (Integer.toString(nukacolaquantum));
        quantumLabel2.setFont(FONTB);
        quantumLabel2.setBounds(180,330,200,30);
        
        btnTop.add(atat);
        btnTop.add(atatAns);
        btnTop.add(awt);
        btnTop.add(awtAns);
        btnTop.add(quantumLabel1);
        btnTop.add(quantumLabel2);
	    }
     
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnExit){
        	frame.dispose();
        }
        else if (e.getSource()==btnHome){
        	frame.dispose();
            Menu menu = new Menu();
            menu.setVisible(true);
        }

    }
    
    public void buttons(){
        btnExit = new JButton("EXIT");
        btnExit.setSize(130,30);
        btnExit.setLocation(200, 200);
        btnExit.setBackground(new Color(53,56,193));
        btnExit.setForeground(Color.white);
        btnExit.setFont(FONTB);
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnHome = new JButton("HOME");
        btnHome.setSize(130,30);
        btnHome.setLocation(200, 200);
        btnHome.setBackground(new Color(53,56,193));
        btnHome.setForeground(Color.white);
        btnHome.setFont(FONTB);
        btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public void InsideScrollBar(){
        //TITLE LABELS
        p = new JLabel("PROCESS");
        p.setFont(FONTB);
        p.setBounds(28,24,100,25);
        
        bts = new JLabel("BURST");
        bts.setFont(FONTB);
        bts.setBounds(147,15,50,25);
        t1 = new JLabel("TIME");
        t1.setFont(FONTB);
        t1.setBounds(152,35,50,25);
        
        atL = new JLabel("ARRIVAL");
        atL.setFont(FONTB);
        atL.setBounds(219,15,80,25);
        t2 = new JLabel("TIME");
        t2.setFont(FONTB);
        t2.setBounds(233,35,50,25);
        
        cts = new JLabel("COMPLETION");
        cts.setFont(FONTB);
        cts.setBounds(313,15,100,25);
        t3 = new JLabel("TIME");
        t3.setFont(FONTB);
        t3.setBounds(345,35,100,25);
        
        tat = new JLabel("TURNAROUND");
        tat.setFont(FONTB);
        tat.setBounds(447,15,150,25);
        t4 = new JLabel("TIME");
        t4.setFont(FONTB);
        t4.setBounds(480,35,100,25);
        
        wts = new JLabel("WAITING");
        wts.setFont(FONTB);
        wts.setBounds(593, 15,100,25);
        t5 = new JLabel("TIME");
        t5.setFont(FONTB);
        t5.setBounds(610, 35,100,25);
        
    }
    
    
    
}
