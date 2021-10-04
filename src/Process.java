
/*
Update:
    UI Design
 */
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Process extends JFrame implements ActionListener {

    private static final long serialVersionUID = -2125813716721505843L;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JLabel proLabel, btLabel, p1Label, atLabel;
    JLabel[] pLabel;
    JButton btnAdd, btnDone, btnReset;
    int arrsize = 1, yAxis = 250, LyAxis = 205, lowerBound = 0, upperBound = 1;
    JScrollPane scroll;
    JTextField[] proText, atText;

    ArrayList<Integer> burst = new ArrayList<Integer>();
    ArrayList<Integer> arrive = new ArrayList<Integer>();

    JLabel bg;
    ImageIcon img = new ImageIcon("PROCESS.png");
    Font FONTB = new Font("Century Gothic", Font.BOLD, 16);
    Font FONT = new Font("Century Gothic", Font.PLAIN, 16);

    public Process() {
        bg = new JLabel(img);
        bg.setLayout(null);
        fcfss(arrsize, yAxis, LyAxis);
    }

    public void fcfss(int arrsize, int yAxis, int LyAxis) {
        proLabel = new JLabel("PROCESS");
        proLabel.setFont(FONTB);
        proLabel.setBounds(30, 155, 200, 25);

        btLabel = new JLabel("BURST TIME (BT) ");
        btLabel.setFont(FONTB);
        btLabel.setBounds(170, 155, 200, 25);

        atLabel = new JLabel("ARRIVAL TIME (AT) ");
        atLabel.setFont(FONTB);
        atLabel.setBounds(318, 155, 200, 25);

        //Process Button
        btnAdd = new JButton("Add Process");
        btnAdd.setFont(FONTB);
        btnAdd.setSize(130, 30);
        btnAdd.setLocation(160, 200);
        btnAdd.setForeground(Color.white);
        btnAdd.setBackground(new Color(53, 56, 193));
        btnAdd.setHorizontalAlignment(0);

        //Calcaulate Button
        btnDone = new JButton("Calculate");
        btnDone.setFont(FONTB);
        btnDone.setSize(130, 30);
        btnDone.setLocation(320, 200);
        btnDone.setForeground(Color.white);
        btnDone.setBackground(new Color(53, 56, 193));
        btnDone.setHorizontalAlignment(0);

        //Calcaulate Button
        btnDone = new JButton("Calculate");
        btnDone.setFont(FONTB);
        btnDone.setSize(130, 30);
        btnDone.setLocation(320, 200);
        btnDone.setForeground(Color.white);
        btnDone.setBackground(new Color(53, 56, 193));
        btnDone.setHorizontalAlignment(0);

        //Reset Button
        btnReset = new JButton("Reset");
        btnReset.setFont(FONTB);
        btnReset.setSize(130, 30);
        btnReset.setLocation(320, 200);
        btnReset.setForeground(Color.white);
        btnReset.setBackground(new Color(53, 56, 193));
        btnReset.setHorizontalAlignment(0);

        //DYNAMIC LABEL AND TEXTBOX
        pLabel = new JLabel[arrsize];
        proText = new JTextField[arrsize];
        atText = new JTextField[arrsize];

        for (int i = lowerBound; i < upperBound; i++) {
            pLabel[i] = new JLabel("P" + (i + 1));
            pLabel[i].setFont(FONTB);
            pLabel[i].setBounds(50, LyAxis, 200, 25);

            proText[i] = new JTextField("");
            proText[i].setFont(FONT);
            proText[i].setHorizontalAlignment(JTextField.CENTER);
            proText[i].setBounds(200, LyAxis, 50, 25);

            atText[i] = new JTextField("0");
            atText[i].setFont(FONT);
            atText[i].setHorizontalAlignment(JTextField.CENTER);
            atText[i].setBounds(360, LyAxis, 50, 25);

            btnAdd.setLocation(170, yAxis);
            btnDone.setLocation(320, yAxis);
            btnReset.setLocation(20, yAxis);

            frame.add(atText[i]);
            frame.add(pLabel[i]);
            frame.add(proText[i]);
        }

        //ScrollBar
        panel.setBackground(Color.DARK_GRAY);
        scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll);
        frame.add(panel);

        //Add Button
        frame.add(btnDone);
        frame.add(btnAdd);
        frame.add(btnReset);

        //Add Label
        frame.add(btLabel);
        frame.add(proLabel);
        frame.add(atLabel);

        //Hand Cursor
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnAdd.addActionListener(this);
        btnDone.addActionListener(this);
        btnReset.addActionListener(this);

        windowSettings();

    }

    public void windowSettings() {
        //Window Settings
        frame.getContentPane().add(bg);
        frame.setSize(510, 610);
        frame.setPreferredSize(new Dimension(500, 600));
        frame.setMaximumSize(new Dimension(500, 600));
        frame.setMinimumSize(new Dimension(500, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(scroll, BorderLayout.EAST);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        boolean flag = true;

        for (int i = lowerBound; i < upperBound; i++) {
            try {

                int bt = 0, at = 0;
                at = Integer.parseInt(atText[i].getText());
                bt = Integer.parseInt(proText[i].getText());
                burst.add(bt);
                arrive.add(at);
                if (i < upperBound) {
                    proText[i].setEditable(false);
                    atText[i].setEditable(false);
                    flag = false;
                }
            } catch (Exception nfe) {
                JOptionPane.showMessageDialog(null, "Please Enter an Input", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                flag = true;
                break;
            }
        }

        if (ae.getSource() == btnAdd && flag == false) {
            upperBound++;
            lowerBound++;
            arrsize++;
            yAxis += 50;
            LyAxis += 50;
            frame.remove(btnAdd);
            frame.remove(btnDone);
            frame.remove(btnReset);
            fcfss(arrsize, yAxis, LyAxis);
        } else if (ae.getSource() == btnDone && flag == false) {
            Priority show = new Priority();
            show.getInput(burst, arrive);
            frame.dispose();

            //sjf show = new sjf();
            //show.getInput(burst, arrive);
            //dispose();
        }

    }

    //public static void main(String[] args){new Process();} //checking
}
