package com.sjsu.project;

import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.VirtualMachineCapability;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class VM_Manager {
	
	 public static void main(String[] args) throws Exception
	  {
	    long start = System.currentTimeMillis();
	    ServiceInstance si = new ServiceInstance(new URL("https://192.168.200.128/sdk"), "root", "pgajbhiye2", true);
	    long end = System.currentTimeMillis();
	    System.out.println("time taken : " + (end-start));
	    Folder rootFolder = si.getRootFolder();
	    String name = rootFolder.getName();
	    System.out.println("root : " + name);
	
	  		ManagedEntity mes[];
	  		
	  		try {
	  			mes=new InventoryNavigator(rootFolder)
	  				.searchManagedEntities("VirtualMachine");
	  						
	  			if(mes == null || mes.length == 0){
	  				System.out.println("Vms are not added to your datacenter");
	  				return;
	  			}
	  			
	  			System.out.println("Virtual Machines Status... ");
	  			
	  			System.out.println("---------------------------------------------------------------------------------");
	  			for(int i=0;i<mes.length;i++){
	  				System.out.println("Hosts are : "+ mes[i].getName());
	  				
	  				VirtualMachine vm=(VirtualMachine)mes[i];
	  				
	  				System.out.println("Parent is : "+mes[i].getParent().getName());
	  			
	  				
	  				System.out.println("---------------------------------------------------------------------");
	  				
	  				System.out.println("Virtual Machine Status");
	  				System.out.println("Hello "+ vm.getName());
	  				//System.out.println("Resource Pool:"+mes[i].getDeclaredAlarmState());
	  				System.out.println("Datastore : "+ vm.getDatastores());
	  				System.out.println("VM Config : " + vm.getConfig().name);
	  				System.out.println("Guest of the vm : "+ vm.getGuest().guestFullName);
	  				System.out.println("Guest IP Address of the vm : "+ vm.getGuest().getIpAddress());
	  				System.out.println("Parent name for the vm : "+ vm.getParent().getName());
	  				System.out.println("Resource Pool : "+vm.getResourcePool());
	  				System.out.println("Resouce Pool Name : " + vm.getResourcePool().getName());
	  				System.out.println("VM Runtime : " + vm.getRuntime());
	  				System.out.println("VM Storage:: " + vm.getStorage().toString());
	  				
	  				//Virtual Machine Info
	  				VirtualMachineConfigInfo vminfo = vm.getConfig();
	  				VirtualMachineCapability vmc = vm.getCapability();
	  				
	  				System.out.println("GuestOS : " + vminfo.getGuestFullName());
	  				System.out.println("GuestName : " + vminfo.getName());
	  				System.out.println("GuestID : " + vminfo.getGuestId());
	  				
	  				System.out.println("Is multiple snapshots supported in this VM? : "+ vmc.isMultipleSnapshotsSupported());

	  				//CPU Stats
	  				System.out.println("---------------------------------------------------------------------");
	  				VirtualMachineRuntimeInfo vmri = vm.getRuntime();
	  				System.out.println("");
	  				System.out.println("");
	  				System.out.println("");
	  				System.out.println("VM Runtime Statistics");
	  				System.out.println(vmri.host);
	  				System.out.println("Connection State : "+ vmri.getConnectionState());
	  				System.out.println("Power State : " + vmri.getPowerState());
	  				System.out.println("Max CPU Usage : " + vmri.getMaxCpuUsage());
	  				System.out.println("Max Memory Usage : "+ vmri.getMaxMemoryUsage());

	  			}
	  			
	  			
	  		} 
	  		
	  		catch (RemoteException e) 
	  		{
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  	    }

	    si.getServerConnection().logout();
	  }

}
