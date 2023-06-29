package proj;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Homepage extends JFrame {
    
    JLabel jL1,jL2; 
    JOptionPane jp;
    Container con;
    JButton []btn = new JButton[5];
    JButton bN1 = new JButton("출근하기");
    JButton bN2 = new JButton("퇴근하기");
    JButton bS1 = new JButton("등록하기");
    JButton bS2 = new JButton("수정하기");
    JButton bS3 = new JButton("지우기"); 

    Homepage(){
        JPanel Np = new JPanel();
        setLayout(new BorderLayout());
      
        Np.setLayout(new GridLayout(1,2)); //상단 패널에 버튼
        Np.add(bN1);
        Np.add(bN2);

        JPanel sP = new JPanel();
		sP.setLayout(new GridLayout(1, 3)); //하단버튼
		sP.add(bS1);
		sP.add(bS2);
		sP.add(bS3);
		
        
		add(Np, BorderLayout.NORTH);
		add(sP, BorderLayout.SOUTH);

        bN1.addActionListener(new Hhandler(this));
        bN2.addActionListener(new Hhandler(this));
        bS1.addActionListener(new Hhandler(this));
        bS2.addActionListener(new Hhandler(this));
        bS3.addActionListener(new Hhandler(this));
        
		bN1.setBackground(new Color(Prop.COLOR_MAIN));
        bN2.setBackground(new Color(Prop.COLOR_MAIN));
        bS1.setBackground(new Color(Prop.COLOR_MAIN));
        bS2.setBackground(new Color(Prop.COLOR_MAIN));
        bS3.setBackground(new Color(Prop.COLOR_MAIN));
        
        setUi();  
    
    }
    /*void init(){

		//p1 = new JPanel();
     
    
        /*p1.setLayout(new GridLayout(2,1));
        p1.add(bN1);
        p1.add(bN2);

        p1.add(bS1);
        p1.add(bS2);
        p1.add(bS3);
        con.add(p1,BorderLayout.NORTH);
       // pushb();
       // createP();
         
    }*/       
    
    void setUi(){
        setTitle("Main Home");
        setSize(300, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

public static void main(String[] args){
    Homepage h = new Homepage();
      
    }
}
    class Hhandler implements ActionListener{
    Homepage h1;
    Hhandler(Homepage h1){
        this.h1 = h1;
        
    }
    @Override
	public void actionPerformed(ActionEvent e){
        UIManager.put("OptionPane.background", new Color(177, 224, 227)); //UIOPTION 색상
       // UIManager.put("Panel.background", new Color(53, 20, 219)); 
        UIManager.put("Button.background", new Color(194, 177, 227));
        
		JButton b = (JButton)e.getSource();
		JOptionPane jp = new JOptionPane();
        if(b == h1.bN1 ){
            System.out.println("출근버튼 클릭");
            JOptionPane.showConfirmDialog(null,"출근하시겠습니까?","출근",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
      
        }else if(b== h1.bN2){
            System.out.println("퇴근버튼 클릭");
            JOptionPane.showConfirmDialog(null,"퇴근하시겠습니까?","퇴근",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null);     
        }  	
    }
}