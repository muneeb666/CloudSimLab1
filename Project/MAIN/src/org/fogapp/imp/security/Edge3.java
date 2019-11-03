package org.fogapp.imp.security;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
import org.fog.entities.PhysicalTopology;
import org.fog.entities.Sensor;
import org.fog.entities.Tuple;
import org.fog.placement.Controller;
import org.fog.placement.ModuleMapping;
import org.fog.placement.ModulePlacementMapping;
import org.fog.policy.AppModuleAllocationPolicy;
import org.fog.scheduler.StreamOperatorScheduler;
import org.fogapp.imp.security.ModulePlacementEdgewards;
import org.fog.utils.FogLinearPowerModel;
import org.fog.utils.FogUtils;
import org.fog.utils.GeoCoverage;
import org.fog.utils.JsonToTopology;
import org.fog.utils.TimeKeeper;
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
import org.fog.placement.ModulePlacementMapping;
import org.fog.policy.AppModuleAllocationPolicy;
import org.fog.scheduler.StreamOperatorScheduler;
import org.fog.utils.FogLinearPowerModel;
import org.fog.utils.FogUtils;
import org.fog.utils.TimeKeeper;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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





public class Edge3 
{
	
	static List<FogDevice> fogDevices = new ArrayList<FogDevice>(); // arrays for storing many devics
	static List<Sensor> sensors = new ArrayList<Sensor>(); ////                             many sensors
	static List<Actuator> actuators = new ArrayList<Actuator>();//                          many actuators
	
	static boolean CLOUD = true; 
	
	static int edgegateways = 1; //
	static int localdevices=2 ; //
	
	
	 
	
	
	public static void main(String[] args) 
	{

		try 
		{
			int num_user = 1;
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false; 
		//	Log.disable();
			

			CloudSim.init(num_user, calendar, trace_flag);

			String appId = "TIERAPP3"; 
			
			FogBroker broker = new FogBroker("broker"); 
			
			Application application = createApplication(appId, broker.getId());
			application.setUserId(broker.getId());
			
			
			
			ModuleMapping moduleMapping = ModuleMapping.createModuleMapping(); //call this class to initialize a mapping
			
			if(CLOUD)
			{
				moduleMapping.addModuleToDevice("Data Processing", "cloud"); 
				moduleMapping.addModuleToDevice("Data Making", "cloud"); 
				moduleMapping.addModuleToDevice("Actuating Process", "cloud");
				


				for(FogDevice device : fogDevices){
					if(device.getName().startsWith("ActautingProcess")){
						
				moduleMapping.addModuleToDevice("NetworkProviders", device.getName());  
					}
				}
				createDevices(broker.getId(), appId);
			}
			
						
			Controller controller = new Controller("MajorCloudController", fogDevices, sensors, 
					actuators);
			controller.submitApplication(application, 1, 
					(CLOUD)?(new ModulePlacementMapping(fogDevices, application, moduleMapping))
							:(new ModulePlacementEdgewards(fogDevices, sensors, actuators, application, moduleMapping)));

			TimeKeeper.getInstance().setSimulationStartTime(Calendar.getInstance().getTimeInMillis());

			CloudSim.startSimulation(); 
			CloudSim.pause(1, 3); 

			CloudSim.stopSimulation();

		
		} catch (Exception e) {
			e.printStackTrace();
			Log.printLine("There are some errors");
		}
	}
  // Cloud in Public Network
	private static void createDevices(int userId, String appId) { //method that was called up
		FogDevice cloud = createDevice("Cloud-Datacenter",1000, 1000, 1000, 1000, 0, 20, 5, 8); 
		cloud.setParentId(-1);
		FogDevice csp = createDevice("Edge-Server", 2000, 3000, 100, 300, 1, 10, 20.21, 20.11); // applications in the server
		csp.setParentId(cloud.getId()); 	
		csp.setUplinkLatency(20); // to and from server
	
		fogDevices.add(cloud);
		fogDevices.add(csp);
	
	
		
		for(int i=0;i<edgegateways;i++){
			addGw(i+"", userId, appId, csp.getId()); 
		}
		
	}

	private static FogDevice addGw(String id, int userId, String appId, int parentId){
		FogDevice td = createDevice("Edgegateway-"+id, 15000, 500000, 1000, 500, 2,50, 80, 87.11); // connected to the edge server
		fogDevices.add(td);
		td.setParentId(parentId);
		td.setUplinkLatency(10.00); // routers latency here 
		for(int i=0;i<localdevices;i++){
			String lid = id+"-"+i;
			FogDevice sp = addsp(lid, userId, appId, td.getId()); 
			sp.setUplinkLatency(10.00);
			fogDevices.add(sp);
		}
		return td;
	}
	
	private static FogDevice addsp(String id, int userId, String appId, int parentId){
		FogDevice sp = createDevice("Localdevices-"+id, 500, 5000, 50, 100, 3, 10, 12, 6 ); // connected to the Rusberrypi
		sp.setParentId(parentId);
		FogDevice sh = createDevice("User"+id, 500, 1000, 500, 200, 4, 10.5, 100, 80.9);
		sh.setParentId(parentId);
		Sensor user = new Sensor("Sensors-"+id, "TEMP", userId, appId, new Deterministic(10.1));
		sensors.add(user);
		Actuator display = new Actuator("Actuators-"+id, userId, appId, "DISPLAY");
		actuators.add(display);
		user.setGatewayDeviceId(sp.getId());
		user.setLatency(5.0); // 5 ms  
		display.setGatewayDeviceId(sp.getId());
		display.setLatency(5.0);  // 5 ms
		return sp;
	}
	
	
	private static FogDevice createDevice(String nodeName, long mips,
			int ram, long upBw, long downBw, int level, double ratePerMips, double busyPower, double idlePower) {
		
		List<Pe> peList = new ArrayList<Pe>();

		
		peList.add(new Pe(1, new PeProvisionerOverbooking(mips)));

		int hostId = FogUtils.generateEntityId();
		long storage = 5000000; 
		int BW = 700000;

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
		
		application.addAppModule("Customer", 200); 
		application.addAppModule("Videostream", 2000); 
		application.addAppModule("Websurfing", 2000);
		application.addAppEdge("TEMP","customer", 2000, 100, "SENSED", Tuple.UP, AppEdge.SENSOR);
		application.addAppEdge("customer","Videostream", 2000, 500, "_SENSOR", Tuple.UP, AppEdge.MODULE); 
		application.addAppEdge("Videostream", "Websurfing", 2000, 100, 2000, "Classification", Tuple.UP, AppEdge.MODULE); 
		application.addAppEdge("Videostream", "Customer", 100, 500, "TRUSTED", Tuple.DOWN, AppEdge.MODULE); 
		application.addAppEdge("Websurfing", "Customer", 100, 50, 2000, "Search", Tuple.DOWN, AppEdge.MODULE); 
		application.addAppEdge("Customer", "DISPLAY", 2000, 500, "Notifier", Tuple.DOWN, AppEdge.ACTUATOR);  
		application.addTupleMapping("Customer", "Trusted", "Allow", new FractionalSelectivity(10.0)); 
		application.addTupleMapping("Videostream", "_SENSOR", "TRUSTED", new FractionalSelectivity(10.0));
		application.addTupleMapping("Websurfing", "SEARCH", "Search", new FractionalSelectivity(3.0));  

		return application;
	}
}