package pl.gda.pg.eti.kernelhive.common.clusterService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import pl.gda.pg.eti.kernelhive.common.clusterService.Job.JobState;
import pl.gda.pg.eti.kernelhive.common.graph.node.EngineGraphNodeDecorator;

public class Task extends HasID {
	
	private Map<Integer, Job> jobs = new Hashtable<Integer, Job>();
	private List<Job> readyJobs = new ArrayList<Job>();
	
	public Task(List<EngineGraphNodeDecorator> graph) {
		super();
				
		for(EngineGraphNodeDecorator node : graph) {
			Job newJob = new Job(node, this);
			if(node.getGraphNode().getChildrenNodes().size() == 0) {
				newJob.state = JobState.READY;
				readyJobs.add(newJob);
			}
			jobs.put(newJob.ID, newJob);
			// TODO: wrzucić kernele joba na serwer i ustawić jobowi adres kernela
		}
	}
	
	public List<Job> getReadyJobs() {
		return readyJobs;
	}
	
	public void updateReadyJobs() {
		readyJobs.clear();
		for(Job job : jobs.values())
			if(job.state == JobState.READY)
				readyJobs.add(job);
	}

	public boolean checkFinished() {
		
		for(Job job : jobs.values())
			if(job.state != JobState.FINISHED)
				return false;
				
		return true;
	}

}
