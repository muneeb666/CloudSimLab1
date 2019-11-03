package org.fogapp.imp.security;


import java.awt.EventQueue;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.power.PowerHost;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.cloudbus.cloudsim.sdn.overbooking.BwProvisionerOverbooking;
import org.cloudbus.cloudsim.sdn.overbooking.PeProvisionerOverbooking;
import org.fog.application.AppEdge;
import org.fog.application.AppLoop;
import org.fog.application.Application;
import org.fog.application.selectivity.FractionalSelectivity;
import org.fog.entities.Actuator;
import org.fog.entities.FogBroker;
import org.fog.entities.FogDevice;
import org.fog.entities.FogDeviceCharacteristics;
import org.fog.entities.Sensor;
import org.fog.entities.Tuple;
import org.fog.placement.Controller;
import org.fog.placement.ModuleMapping;
import org.fog.placement.ModulePlacementEdgewards;
import org.fog.placement.ModulePlacementMapping;
import org.fog.policy.AppModuleAllocationPolicy;
import org.fog.scheduler.StreamOperatorScheduler;
import org.fog.utils.FogLinearPowerModel;
import org.fog.utils.FogUtils;
import org.fog.utils.TimeKeeper;

import sun.net.www.content.text.PlainTextInputStream;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;


public class cloudIotModel {

	
	
	// each fog device creates specific instructions processing rate and computational resources
		static List<FogDevice> fogDevices = new ArrayList<FogDevice>(); // arrays for storing many devices
		static List<Sensor> sensors = new ArrayList<Sensor>(); ////                             many sensors
		static List<Actuator> actuators = new ArrayList<Actuator>();//                          many actuators
		
		static boolean CLOUD = true; 
		
		static int gateways = 1 ; 
		static int networkproviders= 1; 
		

		 
		
		
		public static void main(String[] args) 
		{

			try 
			{
				int num_user = 1;
				Calendar calendar = Calendar.getInstance();
				boolean trace_flag = false; 
			//	Log.disable();
				

				CloudSim.init(num_user, calendar, trace_flag);

				String appId = "TIERAPP"; 
				
				FogBroker broker = new FogBroker("broker"); // use for the communication between fog devices
				
				Application application = createApplication(appId, broker.getId());
				application.setUserId(broker.getId());
				
					// it identifies the available resources in fog devices	
				ModuleMapping moduleMapping = ModuleMapping.createModuleMapping(); //call this class to initialize a mapping
				
				if(CLOUD)
				{
					moduleMapping.addModuleToDevice("Data Processing", "cloud"); //  management component are module mapping and controller*\
					moduleMapping.addModuleToDevice("Data Making", "cloud"); 
					moduleMapping.addModuleToDevice("Actuating Process", "cloud");
					


					for(FogDevice device : fogDevices){
						if(device.getName().startsWith("ActautingProcess")){
							
					moduleMapping.addModuleToDevice("NetworkProviders", device.getName());  
						}
					}
					createDevices(broker.getId(), appId);
				}
				
				// controller calculates the cost , network usage , energy consumption			
				Controller controller = new Controller("MajorCloudController", fogDevices, sensors, 
						actuators); // it launches the AppModule on their assigned Fog devices provided by Module mapping and periodically manages the resources of fog devices
				controller.submitApplication(application, 1, 
						(CLOUD)?(new ModulePlacementMapping(fogDevices, application, moduleMapping))
								:(new ModulePlacementEdgewards(fogDevices, sensors, actuators, application, moduleMapping)));

				TimeKeeper.getInstance().setSimulationStartTime(Calendar.getInstance().getTimeInMillis());

				CloudSim.startSimulation(); 
				CloudSim.pause(1, 1); 

				CloudSim.stopSimulation();

			
			} catch (Exception e) {
				e.printStackTrace();
				Log.printLine("There are some errors");
			}
		}
	  // Cloud in Public Network
		private static void createDevices(int userId, String appId) { //method that was called up
			FogDevice cloud = createDevice("Cloud-Datacenter",16000, 100000, 100000, 100000, 0, 0.01, 16*103, 16*83.25); 
			cloud.setParentId(-1);
			FogDevice csp = createDevice("Cloud-Network", 3000, 5000, 1000, 1000, 1, 10, 107.339, 50.4333);
			csp.setParentId(cloud.getId()); 	
			csp.setUplinkLatency(50);  //latency between cloud and public network in ms
		
			fogDevices.add(cloud);
			fogDevices.add(csp);
		
		
			
			for(int i=0;i<gateways;i++){
				addGw(i+"", userId, appId, csp.getId()); 
			}
			
		}

		private static FogDevice addGw(String id, int userId, String appId, int parentId){
			FogDevice td = createDevice("Gateway-"+id, 2000, 3000, 500, 500, 2,50, 50.53, 20.44);
			fogDevices.add(td);
			td.setParentId(parentId);
			td.setUplinkLatency(30);
			for(int i=0;i<networkproviders;i++){
				String mobileId = id+"-"+i;
				FogDevice sp = addsp(mobileId, userId, appId, td.getId()); 
				sp.setUplinkLatency(30.00); // 4G LTE latency 
				fogDevices.add(sp);
			}
			return td;
		}
		
		private static FogDevice addsp(String id, int userId, String appId, int parentId){
			FogDevice sp = createDevice("User-"+id, 1000, 50, 10, 270, 3, 100, 12.53, 5.44); // 100Mb link up and 50 MB link down 
			sp.setParentId(parentId);
			FogDevice sh = createDevice("Local-Network"+id, 2000, 50, 100, 50, 4, 100, 19.53, 50.44);
			sh.setParentId(parentId);
			Sensor user = new Sensor("Sensors-"+id, "TEMP", userId, appId, new Deterministic(10.1));
			sensors.add(user);
			Actuator display = new Actuator("Actuators-"+id, userId, appId, "DISPLAY");
			actuators.add(display);
			user.setGatewayDeviceId(sp.getId());
			user.setLatency(5.0);  
			display.setGatewayDeviceId(sp.getId());
			display.setLatency(5.0);  
			return sp;
		}
		
		
		private static FogDevice createDevice(String nodeName, long mips,
				int ram, long upBw, long downBw, int level, double ratePerMips, double busyPower, double idlePower) {
			
			List<Pe> peList = new ArrayList<Pe>();

			
			peList.add(new Pe(1, new PeProvisionerOverbooking(mips)));

			int hostId = FogUtils.generateEntityId();
			long storage = 5000000; 
			int BW = 500000;

			PowerHost host = new PowerHost(
					hostId,
					new RamProvisionerSimple(ram),
					new BwProvisionerOverbooking(BW),
					storage,
					peList,
					new StreamOperatorScheduler(peList),
					new FogLinearPowerModel(busyPower, idlePower)
				);

			List<Host> hostList = new ArrayList<Host>();
			hostList.add(host);

			String arch = "x86"; // system architecture
			String os = "Linux"; // operating system
			String vmm = "Xen";
			double time_zone = 1.0; // time zone this resource located
			double cost = 5.0; // the cost of using processing in this resource
			double costPerMem = 0.5; // the cost of using memory in this resource
			double costPerStorage = 0.6; // the cost of using storage in this
											// resource
			double costPerBw = 0.5; // the cost of using bw in this resource
			LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
														// devices by now

			FogDeviceCharacteristics characteristics = new FogDeviceCharacteristics(
					arch, os, vmm, host, time_zone, cost, costPerMem,
					costPerStorage, costPerBw);

			FogDevice fogdevice = null;
			try {
				fogdevice = new FogDevice(nodeName, characteristics, 
						new AppModuleAllocationPolicy(hostList), storageList, 10, upBw, downBw, 0, ratePerMips);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			fogdevice.setLevel(level);
			return fogdevice;
		}

		@SuppressWarnings({"serial" })
		private static Application createApplication(String appId, int userId){
			
			Application application = Application.createApplication(appId, userId); 
			
			application.addAppModule("Customer", 100); 
			application.addAppModule("Videostream", 1000); 
			application.addAppModule("Websurfing", 1000);
			//
			
			application.addAppEdge("TEMP","customer", 1000, 100, "SENSED", Tuple.UP, AppEdge.SENSOR);// includes tuple type , CPu , network length , direction
			application.addAppEdge("customer","Videostream", 1000, 500, "_SENSOR", Tuple.UP, AppEdge.MODULE); 
			application.addAppEdge("Videostream", "Websurfing", 1000, 100, 1000, "Classification", Tuple.UP, AppEdge.MODULE); 
			application.addAppEdge("Videostream", "Customer", 100, 500, "TRUSTED", Tuple.DOWN, AppEdge.MODULE); 
			application.addAppEdge("Websurfing", "Customer", 100, 50, 1000, "Search", Tuple.DOWN, AppEdge.MODULE); 
			application.addAppEdge("Customer", "DISPLAY", 1000, 500, "Notifier", Tuple.DOWN, AppEdge.ACTUATOR);  
			//
			application.addTupleMapping("Customer", "Trusted", "Allow", new FractionalSelectivity(10.0)); 
			application.addTupleMapping("Videostream", "_SENSOR", "TRUSTED", new FractionalSelectivity(10.0));
			application.addTupleMapping("Websurfing", "SEARCH", "Search", new FractionalSelectivity(3.0));  
	//
			final AppLoop loop1 = new AppLoop(new ArrayList<String>(){{add("DISPLAY");add("Videostream");add("Websurfing");}});
			final AppLoop loop2 = new AppLoop(new ArrayList<String>(){{add("customer");add("TEMP");}});
			List<AppLoop> loops = new ArrayList<AppLoop>(){{add(loop1);add(loop2);}};
			
			application.setLoops(loops);
			return application;
			
		}
	}
