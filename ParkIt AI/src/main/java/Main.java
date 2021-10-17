/*
 * Project Description: Detects all available parking spaces in a given area using rekognition software. 
 * 
 * Authors: Noel Wilson III, Kevin Garcia, Matthew Bernardini, Jerome Cabacungan
 * 
 * Estimated Completion Time: 18 hours
 * 
 * Date Completed: Pending
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.Label;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;

//Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//PDX-License-Identifier: MIT-0 (For details, see https://github.com/awsdocs/amazon-rekognition-developer-guide/blob/master/LICENSE-SAMPLECODE.)

public class Main implements ActionListener {
	
	public static int number;
	private static JMenuBar MenuBar;
	private static JMenu Menu;
	private static JTextField Timetext;
	private static JTextField WebsiteText;
	private static JPasswordField PW_Text;
	private static JLabel Response;
	private static boolean result;
	private static int timeChoice;
	private static int maxCap;
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static JButton Reserve = new JButton("Reserve a Spot");
	private static int Remainder;
	private static JLabel Ava_Spots = new JLabel();
	private static String[] Time = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private static String[] AMPM = {"AM","PM"};
	private static JComboBox DropTime = new JComboBox(Time);
	private static JComboBox DropAM = new JComboBox(AMPM);
	private static JButton TimeSubmit = new JButton("Submit");
	public static String sourceImage = "C:\\Users\\Kevin\\Desktop\\test\\test.jpg";
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Please enter your parking lots maximum capacity: ");
		Scanner in = new Scanner(System.in);
		maxCap = in.nextInt();
		
		Remainder = maxCap;
		Region region = Region.US_EAST_1;
		RekognitionClient rekClient = RekognitionClient.builder().region(region).build();

		detectImageLabels(rekClient, sourceImage);
		rekClient.close();
		
		Main main = new Main();
		main.run(); //Open JFrame
		in.close();
	}
	
	/*public static void ChooseImage() throws IOException {
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		switch(i){
			case(0): sourceImage = "C:\\Users\\Test\\OneDrive\\Pictures\\Camera Roll\\1002211907.jpg"; break;
			case(1): sourceImage = "C:\\Users\\Test\\OneDrive\\Pictures\\Camera Roll\\Park1.jpg"; break;
			case(2): sourceImage = "C:\\Users\\Test\\OneDrive\\Pictures\\Camera Roll\\jmrblkkewfjy.jpg"; break;
			case(3): sourceImage = "C:\\Users\\Test\\OneDrive\\Pictures\\Camera Roll\\ev_mural.png"; break;
			case(4): sourceImage = "C:\\Users\\Test\\OneDrive\\Pictures\\Camera Roll\\33511483.jpg"; break;
		}
		Remainder = maxCap;
		Region region = Region.US_EAST_1;
		RekognitionClient rekClient = RekognitionClient.builder().region(region).build();

		detectImageLabels(rekClient, sourceImage);
		rekClient.close();
		
		Main main = new Main();
		main.run(); //Open JFrame
	}
	*/
	public static void detectImageLabels(RekognitionClient rekClient, String sourceImage) {

		try {
			//InputStream sourceStream = new URL(
			//		"https://images.unsplash.com/photo-1557456170-0cf4f4d0d362?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8bGFrZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80")
			//				.openStream();
			InputStream sourceStream = new FileInputStream(sourceImage);
			SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

			// Create an Image object for the source image.
			Image souImage = Image.builder().bytes(sourceBytes).build();

			DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder().image(souImage).maxLabels(10)
					.build();

			DetectLabelsResponse labelsResponse = rekClient.detectLabels(detectLabelsRequest);
			List<Label> labels = labelsResponse.labels();

			System.out.println("Detected labels for the given photo");
			for (Label label : labels) {
				if(label.confidence() >= 90) {
					System.out.println(label.name() + ": " + label.confidence().toString());
				}
				
				if(label.name().equals("Car")) {
					System.out.println(label.instances().size());
					number = label.instances().size();
				}
			}
			
			

		} catch (RekognitionException | FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void run() throws IOException {
		frame.setSize(400,600);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.getRootPane().setBackground(Color.BLUE);
		
		BufferedImage image = ImageIO.read(new File(sourceImage));
		java.awt.Image newImage = image.getScaledInstance(400, 600, 1);
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
		
		Reserve.addActionListener(new Main());
		
		Response = new JLabel();
		Response.setBounds(40,522,200,30);
		panel.add(Response);
		
		Timetext =  new JTextField(15);
		Timetext.setBounds(200,522,30,30);
		
		DropTime.setBounds(150,522,40,30);
		DropAM.setBounds(200,522,50,30);
		TimeSubmit.setBounds(260,522,90,30 );
		TimeSubmit.addActionListener(new Main());
		Ava_Spots.setText("available spots: " + Remainder);
		frame.setVisible(true);
	}
	public static void CreateParkITAI(){
        if(result == true) {
            ParkITAI reserveMe = new ParkITAI(timeChoice, number, maxCap);
            Remainder = reserveMe.getRemain((maxCap - number));
            reserveMe.Reservation(reserveMe.getTime(timeChoice));
            
        }
    }
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		
		if(e.getActionCommand()=="Reserve a Spot") {
			result = true;
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
			timeChoice = Integer.parseInt((String) DropTime.getSelectedItem());
			if(DropAM.getSelectedItem() == "PM") {
				timeChoice = timeChoice + 12;
			}
			System.out.println(timeChoice);
			Response.setText("Thank you");
			panel.remove(DropAM);
			panel.remove(DropTime);
			panel.remove(TimeSubmit);
			Remainder--;
			Ava_Spots.setText("Available Spots: ~"+ Remainder);
			frame.repaint();
			CreateParkITAI();
			
		}
		
		
	}
	
	
}
