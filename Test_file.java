package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test_file implements ActionListener {
	

	private static JMenuBar MenuBar;
	private static JMenu Menu;
	private static JTextField Timetext;
	private static JTextField WebsiteText;
	private static JPasswordField PW_Text;
	private static JLabel Response;
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static JButton Reserve = new JButton("Reserve a Spot");
	private static int Remainder = 1;
	private static JLabel Ava_Spots = new JLabel("Available Spots: ~"+Remainder);
	private static String[] Time = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static String[] AMPM = {"AM","PM"};
	private static JComboBox DropTime = new JComboBox(Time);
	private static JComboBox DropAM = new JComboBox(AMPM);
	private static JButton TimeSubmit = new JButton("Submit");


	public static void main(String args[]) throws IOException {
		
		
		frame.setSize(400,600);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.getRootPane().setBackground(Color.BLUE);
		
		BufferedImage image = ImageIO.read(new File("C:\\Users\\Kevin\\Desktop\\New folder\\shutterstock_44905951.png"));
		Image newImage = image.getScaledInstance(400, 600, Image.SCALE_DEFAULT);
	    JLabel label = new JLabel(new ImageIcon(newImage));
	    label.setBounds(20, 75, 350, 400);
	    panel.add(label);
		
		panel.setLayout(null);
		
		MenuBar= new JMenuBar();
		MenuBar.setBounds(0, 0, 850, 20);
		panel.add(MenuBar);
		
		Menu = new JMenu("File");
		MenuBar.add(Menu);
		
		JMenuItem m1= new JMenuItem("RESET");
		JMenuItem m2= new JMenuItem("Change Image");
		
		Menu.add(m1);
		Menu.add(m2);
	
		JLabel Title = new JLabel("ParkIT AI");
		Title.setBounds(140, 11, 300, 90);
		Title.setFont(new Font("Courier New", Font.PLAIN, 24));
		panel.add(Title);
		
		Reserve.setBounds(120, 522, 150, 30);
		panel.add(Reserve);
		
	
		
		Ava_Spots.setBounds(140, 490, 150, 30);
		panel.add(Ava_Spots);
		
		Reserve.addActionListener(new Test_file());
		
		Response = new JLabel();
		Response.setBounds(40,522,200,30);
		panel.add(Response);
		
		Timetext =  new JTextField(15);
		Timetext.setBounds(200,522,30,30);
		
		DropTime.setBounds(150,522,40,30);
		DropAM.setBounds(200,522,50,30);
		TimeSubmit.setBounds(260,522,90,30 );
		TimeSubmit.addActionListener(new Test_file());
		
		frame.setVisible(true);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		
		if(e.getActionCommand()=="Reserve a Spot") {
			panel.remove(Reserve);
			if(Remainder == 0)
			{
				Response.setText("Sorry out of spots");
			}else {
				Response.setText("Please enter time");
				panel.add(DropAM);
				panel.add(DropTime);
				panel.add(TimeSubmit);
			}
			frame.repaint();
			
		}
		
		if(e.getActionCommand()=="Submit") {
			Response.setText("Thank you");
			panel.remove(DropAM);
			panel.remove(DropTime);
			panel.remove(TimeSubmit);
			Remainder--;
			Ava_Spots.setText("Available Spots: ~"+ Remainder);
			frame.repaint();
		}
		
		
	}

}