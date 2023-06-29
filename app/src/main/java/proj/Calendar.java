package proj;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Calendar extends JFrame {


    Calendar(){
   
	
   
        setUi();  
    

         
    }  
    
    void setUi(){
        setTitle("Main Home");
        setSize(300, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
