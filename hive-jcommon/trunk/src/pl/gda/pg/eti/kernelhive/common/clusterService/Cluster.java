package pl.gda.pg.eti.kernelhive.common.clusterService;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import pl.gda.pg.eti.kernelhive.common.clientService.ClusterInfo;

public class Cluster extends HasID {
	
	private Object monitor = new Object(); 
	
	public String hostname = "hive-cluster";
	public int TCPPort;
	public int UDPPort;
	public List<Unit> unitList = new ArrayList<Unit>();
	@XmlTransient
	public List<Job> jobsToRun = new ArrayList<Job>();
	
	public Cluster() {
		
	}
	
	public Cluster(int clusterTCPPort, int clusterDataPort, int clusterUDPPort) {
		this.TCPPort = clusterTCPPort;
		this.UDPPort = clusterUDPPort;
	}

	public void runJob(Job jobToRun) {
		jobsToRun.add(jobToRun);
		synchronized(monitor) {
			System.out.println("Notify on cluster " + this);
			monitor.notifyAll();
		}
	}

	public ClusterInfo getClusterInfo() {
		StringBuilder sb = new StringBuilder();
		
		for(Unit unit : unitList)
			for(Device device : unit.devices)
				sb.append(device.toString());
		
		return new ClusterInfo(sb.toString());
	}

	public void updateReverseReferences() {
		for(Unit unit : unitList) {
			unit.updateReverseReferences(this);
		}
		
	}

	public Job getJob() {		
		try
		{
			// wait until there is a task for this Cluster, then return it
			synchronized (monitor) {
				while(true) {				
					System.out.println("Wait on cluster" + this);
					monitor.wait();
					System.out.println("Get job released");
					if(jobsToRun.size() > 0) {
						Job jobToRun = jobsToRun.get(0);
						jobsToRun.remove(0);
						return jobToRun;
					}
				}
			}
		}
		catch (InterruptedException e) {
			System.out.println("getJob interrupted");
			return null;
		}
	}
	
}
