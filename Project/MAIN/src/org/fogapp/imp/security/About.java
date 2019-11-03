package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
	//	setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/images/iot1.jpg")));
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1042, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cloud is the backbone of the internet. World is moving towards IT and netwroking. Cloud is providing its services worldwide with more than 81% of the world's populuation.");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 35, 1006, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This simulator is for analysis of using Fogging with Cloud.");
		lblNewLabel_1.setBounds(20, 225, 921, 46);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		contentPane.add(lblNewLabel_1);
		
		JButton button = new JButton("<<Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage obj=new Homepage();
				About ob=new About();
				ob.setVisible(false);
				obj.setVisible(true);
				
				ob.dispose();
				
			}
		});
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.GREEN);
		button.setBounds(369, 320, 125, 25);
		contentPane.add(button);
		
		JLabel lblNewLabel_2 = new JLabel("The reasons are mainly security amd trust.But there are few people who are still not engaged with Cloud. To cover up these isssues we proposed our application ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 135, 1006, 46);
		contentPane.add(lblNewLabel_2);
	}
}
