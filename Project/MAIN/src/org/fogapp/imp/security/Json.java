package org.fogapp.imp.security;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fog.gui.core.Bridge;
import org.fog.gui.core.Graph;
import org.fog.gui.core.GraphView;
import org.fog.gui.dialog.AddActuator;
import org.fog.gui.dialog.AddFogDevice;
import org.fog.gui.dialog.AddLink;
import org.fog.gui.dialog.AddPhysicalEdge;
import org.fog.gui.dialog.AddPhysicalNode;
import org.fog.gui.dialog.AddSensor;
import org.fog.gui.dialog.SDNRun;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
public class Json extends JFrame {

	 JPanel contentPane;
	 String physicalTopologyFile = "";  //physical
	 String deploymentFile = "";        //virtual
	 String workloads_background = "";  //workload
	 String workloads = "";             //workload
	 JButton btnRun;
 JPanel panel;
	 JPanel graph;
	 String mode; 
	
	 Graph physicalGraph;
	
	 GraphView physicalCanvas;
	static Json frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Json();
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
	public Json() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 100, 392, 300);
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.YELLOW);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("/media/rh/76046E35046DF90F/FYP THINGS/FogApp/fogsim-master/src/images/iot1.jpg"));
		setResizable(false);
		contentPane.setLayout(null);
		initUI();
		initGraph();
		
		pack();
		
		JButton btnOpenJsonFile = new JButton("View Properties");
		btnOpenJsonFile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOpenJsonFile.setForeground(Color.WHITE);
		btnOpenJsonFile.setBackground(Color.BLACK);

		btnOpenJsonFile.setBounds(129, 173, 167, 62);
		        contentPane.add(btnOpenJsonFile);
		        
		        JButton button = new JButton("<<Back");
		        button.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		models o=new models();
		        		o.setVisible(true);
		        		frame.setVisible(false);
		        	}
		        });
		        button.setBackground(Color.BLACK);
		        button.setForeground(Color.WHITE);
		        button.setBounds(157, 276, 117, 39);
		        contentPane.add(button);
		  btnOpenJsonFile.addActionListener(importPhyTopoListener);
		  ImageIcon run = new ImageIcon(
	                getClass().getResource("/images/play.png"));
		  btnRun = new JButton(run);
		  JMenu graph = new JMenu("Graph");
	        graph.setMnemonic(KeyEvent.VK_G);
	        
	        //Graph by importing jfiles
	        final JMenuItem MiPhy = new JMenuItem("Physical Topology");
	        final JMenu MuPhy = new JMenu("Physical");
	        JMenuItem MiFogDevice = new JMenuItem("Add Fog Device");
	        JMenuItem MiPhyEdge = new JMenuItem("Add Edge");
	        JMenuItem MiPhyOpen = new JMenuItem("Import Physical Topology");
	        JMenuItem MiPhySave = new JMenuItem("Save Physical Topology");
	        MuPhy.add(MiFogDevice);
	        MuPhy.add(MiPhyEdge);
	        MuPhy.add(MiPhyOpen);
	        MuPhy.add(MiPhySave);
	     btnRun.setToolTipText("Start simulation");
	     
	        btnRun.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	            	if("i"==mode){
	            		if(physicalTopologyFile==null || physicalTopologyFile.isEmpty()){
	            			JOptionPane.showMessageDialog(panel, "Please select physicalTopologyFile", "Error", JOptionPane.ERROR_MESSAGE);
	            			return;
	            		}
	            		if(deploymentFile==null || deploymentFile.isEmpty()){
	            			JOptionPane.showMessageDialog(panel, "Please select deploymentFile", "Error", JOptionPane.ERROR_MESSAGE);
	            			return;
	            		}
	            		if(workloads_background==null || workloads_background.isEmpty()){
	            			JOptionPane.showMessageDialog(panel, "Please select workloads_background", "Error", JOptionPane.ERROR_MESSAGE);
	            			return;
	            		}
	            		if(workloads==null || workloads.isEmpty()){
	            			JOptionPane.showMessageDialog(panel, "Please select workloads", "Error", JOptionPane.ERROR_MESSAGE);
	            			return;
	            		}
	            		// run simulation
	            		SDNRun run = new SDNRun(physicalTopologyFile, deploymentFile, 
	            								workloads_background, workloads, Json.this);

	            		
			        }else if("m"==mode){
			        	
			        }
	            	
	            }
	        });
	}
	ActionListener importPhyTopoListener = new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) {
	    	String fileName = importFile("json");
	    	Graph phyGraph= Bridge.jsonToGraph(fileName, 0);
	    	physicalGraph = phyGraph;
	    	physicalCanvas.setGraph(physicalGraph);
	    	physicalCanvas.repaint();
	    }
	};
	
	public final void initUI() {
		setFont (new javax.swing.plaf.FontUIResource("Serif",Font.BOLD,18));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        graph = new JPanel(new java.awt.GridLayout(1, 2));
        
		initBar();
		doPosition();
	}
	
	/** position window */
	private void doPosition() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;

		int x = (width / 2 - 1280 / 2);
		int y = (height / 2 - 800 / 2);
		// One could use the dimension of the frame. But when doing so, one have to call this method !BETWEEN! pack and
		// setVisible. Otherwise the calculation will go wrong.

		this.setLocation(x, y);
	}
	 private void initGraph(){
	    	physicalGraph = new Graph();
	    	//virtualGraph = new Graph();
	    	
	    	physicalCanvas = new GraphView(physicalGraph);
	    	//virtualCanvas = new GraphView(virtualGraph);
	    	
			graph.add(physicalCanvas);
			//graph.add(virtualCanvas);
			contentPane.add(graph, BorderLayout.CENTER);
	    }
	    
	
	/** Initialize project menu and tool bar */
    private final void initBar() {
    	//---------- Start ActionListener ----------
    	ActionListener readPhyTopoListener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	physicalTopologyFile = importFile("josn");
            	checkImportStatus();
		    }
		};
	ActionListener importPhyTopoListener = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	String fileName = importFile("josn");
	    	Graph phyGraph= Bridge.jsonToGraph(fileName, 0);
	    	physicalGraph = phyGraph;
	    	physicalCanvas.setGraph(physicalGraph);
	    	physicalCanvas.repaint();
	    }
	};
    }
	   private String importFile(String type){
	        JFileChooser fileopen = new JFileChooser();
	        FileFilter filter = new FileNameExtensionFilter(type.toUpperCase()+" Files", type);
	        fileopen.addChoosableFileFilter(filter);

	        int ret = fileopen.showDialog(panel, "Import file");

	        if (ret == JFileChooser.APPROVE_OPTION) {
	            File file = fileopen.getSelectedFile();
	            return file.getPath();
	        }
	        return "";
	    }
	   
	   private void checkImportStatus(){
	    	if((physicalTopologyFile!=null && !physicalTopologyFile.isEmpty()) &&
	    	   (deploymentFile!=null && !deploymentFile.isEmpty()) &&
	           (workloads_background!=null && !workloads_background.isEmpty()) &&
	    	   (workloads!=null && !workloads.isEmpty())){
	    		
	    	
	    	}else
	    	{
	    	
	    	}
	    }
}

