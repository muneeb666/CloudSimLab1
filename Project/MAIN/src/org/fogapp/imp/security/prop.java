package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prop extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prop frame = new prop();
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
	public prop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add topology");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\topologies\\data-topology.PNG"));
		lblNewLabel.setBounds(10, 11, 1139, 644);
		contentPane.add(lblNewLabel);
		
		JButton btnStartService = new JButton("Start Service");
		btnStartService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 systems o=new systems();
				 o.main(null);
			}
		});
		btnStartService.setBackground(Color.WHITE);
		btnStartService.setForeground(Color.BLACK);
		btnStartService.setBounds(388, 666, 109, 23);
		contentPane.add(btnStartService);
		
		JButton btnNewButton = new JButton("<<Back>>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Homepage o=new Homepage();
				o.setVisible(true);
			prop obj=new prop();
			obj.setVisible(false);
			}
		});
		btnNewButton.setBounds(644, 666, 89, 23);
		contentPane.add(btnNewButton);
	}
}
