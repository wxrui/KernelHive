/**
 * Copyright (c) 2014 Gdansk University of Technology
 * Copyright (c) 2014 Pawel Rosciszewski
 *
 * This file is part of KernelHive.
 * KernelHive is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * KernelHive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with KernelHive. If not, see <http://www.gnu.org/licenses/>.
 */
package pl.gda.pg.eti.kernelhive.cluster;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class HiveCluster implements Daemon {

	private String clusterHostname;
	private static String engineHostname;
	private static Integer enginePort;
	private static final Logger logger = Logger.getLogger(HiveCluster.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HiveCluster cluster = new HiveCluster();
			System.out.println("Args: " + args.toString());
			parseArguments(args, cluster);
			cluster.start();
		} catch (IllegalArgumentException e) {
			logger.severe("Cluster hostname, engine hostname and engine port should be provided as argument.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception during initialization", e);
		}
	}

	@Override
	public void destroy() {
		System.out.println("SVC DESTROY");
	}

	@Override
	public void init(DaemonContext dc) throws DaemonInitException, Exception {
		System.out.println("SVC INIT");
		parseArguments(dc.getArguments(), this);
	}

	@Override
	public void start() throws Exception {
		System.out.println("SVC START");
		ClusterManager cm = new ClusterManager(this.clusterHostname, this.engineHostname, this.enginePort);

		// FIXME:
		while (true);
	}

	@Override
	public void stop() throws Exception {
		System.out.println("SVC STOP");
	}

	private static void parseArguments(String[] args, HiveCluster instance) throws IllegalArgumentException {
		if (args.length < 3) {
			throw new IllegalArgumentException();
		}
		instance.clusterHostname = args[0];
		System.out.println("Cluster hostname: " + instance.clusterHostname);
		engineHostname = args[1];
		System.out.println("Engine hostname: " + engineHostname);
		enginePort = Integer.parseInt(args[2]);
		System.out.println("Engine port: " + enginePort);
	}

	public static String getEngineHostname() {
		return engineHostname;
	}
}
