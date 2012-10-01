package pl.gda.pg.eti.kernelhive.common.clusterService;

import pl.gda.pg.eti.kernelhive.common.graph.node.EngineGraphNodeDecorator;
import pl.gda.pg.eti.kernelhive.common.graph.node.GraphNodeType;
import pl.gda.pg.eti.kernelhive.common.source.IKernelString;

public class Job extends HasID {
	
	public enum JobState {
		PENDING,
		READY,
		SCHEDULED,
		PROCESSING,
		FINISHED
	}
	
	/*public List<String> dataHosts = new ArrayList<String>();
	public List<Integer> dataPorts = new ArrayList<Integer>();
	public List<Integer> dataIds = new ArrayList<Integer>();
	*/
	private Workflow task;
	
	public EngineGraphNodeDecorator node;
	public IKernelString assignedKernel;
	
	public Device device;
	
	public JobState state = JobState.PENDING;
	public int progress = -1;
		
	private int unassignedInt = 0;

	public String inputDataUrl;

	public Job() {
		
	}
	
	public Job(EngineGraphNodeDecorator node, Workflow task) {
		this.node = node;
		this.task = task;	
	}				

	private String getOffsets() {
		int[] offsets = assignedKernel.getOffset();
		return concatKernelAttrs(offsets);
	}

	private String getGlobalSizes() {
		int[] globalSizes = assignedKernel.getGlobalSize();
		return concatKernelAttrs(globalSizes);
	}
	
	private String getLocalSizes() {
		int[] localSizes = assignedKernel.getLocalSize();
		return concatKernelAttrs(localSizes);
	}
	
	private String getOutputSize() {
		// FIXME:
		//return this.node.getGraphNode().getOutputSize();
		return "4096";
	}
	
	private String concatKernelAttrs(int[] attrs) {
		return attrs[0] + " " + attrs[1] + " " + attrs[2];
	}

	private String getClusterHost() {
		if(device == null) return "hive-cluster";
		return device.unit.cluster.hostname;		
	}

	private int getClusterTCPPort() {
		if(device == null) return unassignedInt;
		return device.unit.cluster.TCPPort;
	}
	
	private int getClusterUDPPort() {
		if(device == null) return unassignedInt;
		return device.unit.cluster.UDPPort;
	}
	
	private int getUnitId() {
		if(this.device == null) return unassignedInt;
		return this.device.unit.ID;
	}	

	private String getDeviceId() {
		if(device == null) return "UNASSIGNED";
		return device.name;
	}
	
	private GraphNodeType getJobType() {
		return node.getGraphNode().getType();
	}

	/*
	public void setDataAddress(String status) {
		System.out.println(status);
		String[] dataAddress = status.split(" ");
		this.dataHost = dataAddress[0];
		this.dataPort = dataAddress[1];
		this.dataID = dataAddress[2];		
	}*/	

	public void run() {
		this.device.unit.cluster.runJob(this);	
		this.state = JobState.PROCESSING;
	}

	public void schedule(Device device) {
		this.device = device;
		this.state = JobState.SCHEDULED;		
	}

	public JobInfo getJobInfo() {
		// TODO: assert state
		
		JobInfo ret = new JobInfo();
		
		ret.unitID = getUnitId();
		ret.kernelString = this.assignedKernel.getKernel();
		
		ret.ID = ID;
		ret.clusterHost = getClusterHost();
		ret.clusterTCPPort = getClusterTCPPort();
		ret.clusterUDPPort = getClusterUDPPort();
		ret.deviceID = getDeviceId();
		ret.offsets = getOffsets();
		ret.globalSizes = getGlobalSizes();
		ret.localSizes = getLocalSizes();
		ret.outputSize = getOutputSize();
		ret.jobType = getJobType();
		
		System.out.println("Setting inputDataUrl " + inputDataUrl);
		ret.inputDataUrl = inputDataUrl;
		
		return ret;
	}
}