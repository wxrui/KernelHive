package pl.gda.pg.eti.kernelhive.engine.interfaces;

import java.util.List;

import pl.gda.pg.eti.kernelhive.common.clusterService.Job;

public interface ITaskOptimizer {
	
	public List<Job> arrangeJobs(List<Job> jobs);

}
