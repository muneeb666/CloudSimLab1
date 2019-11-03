package org.fogapp.imp.security;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class Homepage extends JFrame{
	
    Timer tm;
    JLabel pic = new JLabel("");
    int x = 0;
	static Homepage frame;
   
    String[] list = {
    		
    		 "C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\images\\sec.JPG",//0
             "C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\\\images\\\\sec.JPG",//1
             "C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\\\images\\\\sec.JPG",
             "C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\\\images\\\\sec.JPG",
          
                    };

    private JPanel contentPane;


 public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			
			try {
				frame = new Homepage();
				frame.setVisible(true);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
 public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setBackground(Color.BLACK);
        pic.setIcon(newImc);
    }


public Homepage()
{
	setType(Type.UTILITY);
	setBackground(Color.black);
	setIconImage(Toolkit.getDefaultToolkit().getImage("/images/iot1.jpg"));
	setTitle("TIER APP");
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 10, 100, 530);
	setSize(1180,760);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	contentPane.setBackground(Color.black);
    getContentPane().setBackground(Color.BLACK);
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JLabel lblWelcomeToFog = new JLabel("<<Multi-access Edge Computing>> ");
	lblWelcomeToFog.setBackground(Color.BLACK);
	lblWelcomeToFog.setToolTipText("Fog Computing");
	lblWelcomeToFog.setBounds(341, 89, 556, 95);
	lblWelcomeToFog.setFont(new Font("Tahoma", Font.BOLD, 25));
	lblWelcomeToFog.setForeground(Color.RED);
	getContentPane().add(lblWelcomeToFog);
	
	JButton btnRunHospotalService =    new JButton("Start the Simulation");
	btnRunHospotalService.setBounds(421, 229, 269, 46);
	
	btnRunHospotalService.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
        
          
		   prop o=new prop();
		   o.setVisible(true);
		   frame.setVisible(false);
		  // 
	    
     
	

		}
	});
	

	
		   
	btnRunHospotalService.setFont(new Font("Tahoma", Font.BOLD, 12));
	btnRunHospotalService.setBackground(Color.BLACK);
	btnRunHospotalService.setForeground(Color.WHITE);
   getContentPane().add(btnRunHospotalService);
	
	
	JButton Cbutton = new JButton("Open Comparison Report");
	Cbutton.setBounds(421, 356, 269, 47);
	Cbutton.setForeground(Color.WHITE);
	Cbutton.setFont(new Font("Tahoma", Font.BOLD, 12));
	Cbutton.setBackground(Color.black);
	getContentPane().add(Cbutton);
	Cbutton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	
			File file = new File("C:\\Users\\User\\Desktop\\Project\\MAIN\\results\\security.xlsx");
	        try {
	        	
	            Desktop.getDesktop().open(file);
	            
	        } catch (Exception ew) {
	            ew.printStackTrace();
	        }
		 
		}
	});
	JButton btnNewButton = new JButton("Manage Modeling Panel");
	btnNewButton.setBounds(421, 491, 269, 47);

	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Topology sdn = new Topology();
            sdn.setVisible(true);
        
		}
	});
	btnNewButton.setBackground(new Color(0, 0, 0));
	btnNewButton.setForeground(Color.WHITE);
	btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	getContentPane().add(btnNewButton);
	
	JButton btnLogOut = new JButton("Exit This Window");
	btnLogOut.setBounds(421, 611, 269, 47);
	btnLogOut.setForeground(Color.WHITE);
	btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 12));
	btnLogOut.setBackground(new Color(0, 0, 0));
	getContentPane().add(btnLogOut);
	

	btnLogOut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Home o=new Home();
		System.exit(0);
		}
	});


	
	
	pic.setBounds(27, 165, 1076, 945);
	contentPane.add(pic);
	 SetImageSize(3);
     
        tm = new Timer(2000,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        getContentPane().add(pic);
        
        JButton btnHome = new JButton("Home");
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 try { 
        	         String url = "http://fogcomputing.tk/";
        	         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        	       }
        	       catch (java.io.IOException e) {
        	           System.out.println(e.getMessage());
        	       }
        	}
        });
        btnHome.setBackground(Color.BLACK);
        btnHome.setVerticalAlignment(SwingConstants.TOP);
        btnHome.setToolTipText("Home");
        btnHome.setForeground(Color.RED);
        btnHome.setBounds(0, 0, 89, 34);
        contentPane.add(btnHome);
        
        JButton btnAbout = new JButton("About");
        btnAbout.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAbout.setBackground(Color.BLACK);
        btnAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		About o=new About();
        		o.setVisible(true);
        		frame.setVisible(false);
        	}
        });
        btnAbout.setForeground(Color.RED);
        btnAbout.setBounds(171, -6, 89, 40);
        contentPane.add(btnAbout);
        
        JButton btnComtacUs = new JButton("Contact Us");
        btnComtacUs.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnComtacUs.setBackground(Color.BLACK);
        btnComtacUs.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JOptionPane.showMessageDialog(null, "Mail: Youremail@xyz.com");
        	}
        });
        btnComtacUs.setForeground(Color.RED);
        btnComtacUs.setBounds(347, -6, 111, 40);
        contentPane.add(btnComtacUs);
        
        JButton btnLicense = new JButton("Terms");
        btnLicense.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Terms o=new Terms();
        		o.setVisible(true);
        		frame.setVisible(false);
        		
        	}
        	
        });
        btnLicense.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLicense.setBackground(Color.BLACK);
        btnLicense.setForeground(Color.RED);
        btnLicense.setBounds(550, -6, 96, 40);
        contentPane.add(btnLicense);
        
        JButton btnHelp = new JButton("Help");
        btnHelp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 try { 
        	         String url = "www.google.com";
        	         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        	       }
        	       catch (java.io.IOException e) {
        	           System.out.println(e.getMessage());
        	       }
        	}
        });
        btnHelp.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHelp.setBackground(Color.BLACK);
        btnHelp.setForeground(Color.RED);
        btnHelp.setBounds(751, -1, 89, 35);
        contentPane.add(btnHelp);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Project\\MAIN\\src\\images\\icon.png"));
        lblNewLabel.setBounds(239, 229, 46, 14);
        contentPane.add(lblNewLabel);
        
        JLabel label = new JLabel(">>");
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBackground(Color.BLACK);
        label.setForeground(Color.RED);
        label.setBounds(249, 246, 46, 14);
        contentPane.add(label);
        
        JButton btnNewButton_1 = new JButton("Exit(!)");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_1.setForeground(Color.RED);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnNewButton_1.setBounds(949, 0, 89, 34);
        contentPane.add(btnNewButton_1);
        tm.start();
}
}


