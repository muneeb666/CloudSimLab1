package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class models extends JFrame {
	

	 JPanel contentPane;
		public static JFrame frame;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			
						
						 frame = new models();
						 frame.pack();
						frame.setVisible(true);
						
					} 

		/**
		 * Create the frame.
		 */
		public models() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setForeground(Color.WHITE);
			setBackground(Color.BLACK);
			setBounds(100, 100, 963, 501);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setResizable(true);
			
			JButton btnCloudSimulation = new JButton("Traditional Cloud-IOT");
			btnCloudSimulation.setForeground(Color.BLACK);
			btnCloudSimulation.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnCloudSimulation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CloudSecurity obj=new CloudSecurity();
					obj.main(null);
					frame.dispose();
				}
			});
			btnCloudSimulation.setBounds(487, 95, 170, 45);
			btnCloudSimulation.setBackground(Color.WHITE);
			contentPane.add(btnCloudSimulation);
			
		JButton btnFogSimulation = new JButton("2  Tier Edge-IOT");
			btnFogSimulation.setForeground(Color.BLACK);
			btnFogSimulation.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnFogSimulation.addActionListener(new ActionListener() {
				public  void actionPerformed(ActionEvent arg0) {
					Edge obj=new Edge();
					 obj.main(null);
					
					
				}
			});
			btnFogSimulation.setBounds(487, 179, 170, 45);
			btnFogSimulation.setBackground(Color.WHITE);
			contentPane.add(btnFogSimulation);
			
			JButton btnOpenJsonProperties = new JButton("3 Tier Edge-IOT");
			btnOpenJsonProperties.setForeground(Color.BLACK);
			btnOpenJsonProperties.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnOpenJsonProperties.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				 Edge3 obj=new Edge3();
				 obj.main(null);
				}
			});
			btnOpenJsonProperties.setBounds(487, 271, 175, 45);
			btnOpenJsonProperties.setBackground(Color.WHITE);
			contentPane.add(btnOpenJsonProperties);
			
			JButton button = new JButton("<<<Back>>>");
			button.setForeground(Color.BLACK);
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg) {
					Homepage o=new Homepage();
				     o.setVisible(true);
					JFrame g=new JFrame();
					g.disable();
					frame.disable();
					
					
				}
			});
			button.setBounds(487, 353, 170, 38);
			contentPane.add(button);
			
			JLabel lblNewLabel_1 = new JLabel("       SELECT A MODEL TYPE BELOW ");
			lblNewLabel_1.setForeground(Color.BLACK);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel_1.setBackground(Color.WHITE);
			lblNewLabel_1.setBounds(431, 0, 270, 58);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\images\\all.png"));
			lblNewLabel_3.setBounds(10, 60, 449, 360);
			contentPane.add(lblNewLabel_3);
		}
}
