package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class results extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					results frame = new results();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public results() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("<<<<Reports>>>>");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(307, 23, 217, 84);
		contentPane.add(label);
		
		JButton btnTraditionalCloud = new JButton("Cloud");
		btnTraditionalCloud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

            	File file=new File("\\MAIN\\results\\tcloud.png");
            	
            	try
            	{
            		Desktop d=Desktop.getDesktop();
            		d.open(file);
            	}
            	catch (IOException e1)
            	{
            	    String workingDir = System.getProperty("user.dir");
            	    System.out.println("Current working directory : " + workingDir);
            	    e1.printStackTrace();
            	}
			      
			}
		});
		btnTraditionalCloud.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTraditionalCloud.setBackground(Color.GRAY);
		btnTraditionalCloud.setBounds(307, 155, 143, 23);
		contentPane.add(btnTraditionalCloud);
		
		JButton btnNewButton = new JButton("Tier");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

            	File file=new File("C:\\Users\\hp\\Desktop\\reports\\pcloud.png");
            	
            	try
            	{
            		Desktop d=Desktop.getDesktop();
            		d.open(file);
            	}
            	catch (IOException e1)
            	{
            	    String workingDir = System.getProperty("user.dir");
            	    System.out.println("Current working directory : " + workingDir);
            	    e1.printStackTrace();
            	}
			      
				
			}
		});
		btnNewButton.setBounds(307, 248, 143, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.LIGHT_GRAY);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Homepage o=new Homepage();
				o.setVisible(true);
				results frame = new results();
				frame.setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(336, 347, 89, 23);
		contentPane.add(btnBack);
	}
}
