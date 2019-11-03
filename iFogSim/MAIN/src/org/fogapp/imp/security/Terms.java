package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Terms extends JFrame {

	private JPanel contentPane;
	public static Terms frames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					frames = new Terms();
					 frames.pack();
					frames.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Terms() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Terms.class.getResource("/images/iot1.jpg")));
		setBackground(Color.GREEN);
		setBounds(100, 100, 557, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	JLabel lblNewLabel = new JLabel("This applications are for resarch purposes.");
	lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblNewLabel.setToolTipText("Terms");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(12, 35, 512, 69);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("<<Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args) {
			
				Homepage ob=new Homepage();
				ob.setVisible(true);
				 frames.setVisible(false);
		           frames.dispose();
		           
				
				
			
			}
		});
		button.setToolTipText("Back");
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.setBounds(211, 235, 117, 25);
		contentPane.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("Copyrighted users have access to to its use.");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 91, 482, 69);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWithThierOwn = new JLabel("Use it in papers with your own Algorithms.");
		lblWithThierOwn.setForeground(Color.BLACK);
		lblWithThierOwn.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblWithThierOwn.setBounds(12, 154, 287, 51);
		contentPane.add(lblWithThierOwn);
	}
}
